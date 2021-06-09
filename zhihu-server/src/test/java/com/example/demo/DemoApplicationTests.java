package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.User;
import com.example.demo.utils.RedisUtil;
import net.minidev.json.JSONArray;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.naming.directory.SearchResult;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    //@Qualifier("restHighLevelClient")如果下面的名字不一样，就需要配置
    private RestHighLevelClient restHighLevelClient;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        conn.close();

    }
    @Test
    void testCreateIndex() throws IOException {
        //创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("kuang_index");
        //客户端执行请求 ，请求后获得响应
        CreateIndexResponse createIndexResponse =
                restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("kuang_index");
        Boolean exists =
                restHighLevelClient.indices().exists(request,RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("kuang_index");
        AcknowledgedResponse delete =
                restHighLevelClient.indices().delete(request,RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }
    @Test
    void testAddDocument() throws IOException {
        //创建对象
        User user = new User(2,"王一凡",1);
        //创建请求
        IndexRequest request = new IndexRequest("kuang_index");
        //规则 put /kuang_index/_doc/1
        request.id("1").timeout(TimeValue.timeValueSeconds(1)).timeout("1s");//超时设置
        //将请求放入json
        request.source(JSON.toJSONString(user), XContentType.JSON);
        //客户端发送请求
        IndexResponse indexResponse=restHighLevelClient.index(request,RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());
    }
    @Test
    //获取文档，判断是否存在
    void testIsExists() throws IOException {
        GetRequest getRequest = new GetRequest("kuang_index","1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));//过滤 ，不获取_source的上下文
        getRequest.storedFields("_none_");//返回值none
        boolean exists = restHighLevelClient.exists(getRequest,RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
        //获取文档信息
    void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("kuang_index","1");
        GetResponse getResponse = restHighLevelClient.get(getRequest,RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());//打印文档内容
        System.out.println(getResponse);//获得文档信息
    }
    @Test
       //更新文档信息
    void testUpdateRequest() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("kuang_index","1");
        updateRequest.timeout("1s");
        User user = new User(10,"王一凡晒",3);
        updateRequest.doc(JSON.toJSONString(user),XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest,RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
    }
    @Test
       //删除文档信息
    void testDeleteRequest () throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("kuang_index","1");
        deleteRequest.timeout("1s");
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest,RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
    }
    @Test
       //批量操作
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        ArrayList<User> userList=new ArrayList<>();
        userList.add(new User(11,"asd1",2));
        userList.add(new User(12,"asd2",2));
        userList.add(new User(13,"asd34",2));
        userList.add(new User(14,"asd4",2));
        userList.add(new User(15,"asd5",2));
        for(int i=0;i<userList.size();i++){
            bulkRequest.add(
                    new IndexRequest("kuang_index").source(JSON.toJSONString(userList.get(i)),XContentType.JSON));

        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures());//是否失败

    }
    //复杂查询
    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("kuang_index");
        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //查询条件，使用QueryBuilders实现
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("content","asd1");
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse  searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("================");
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }
    }
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Test
    void testRedis(){
        /*redisTemplate.opsForValue().set("1","wangyifan");
        System.out.println(redisTemplate.opsForValue().get("mykey"));*/
        User user = new User();
        user.setContent("aaa");
        user.setId(1);
        user.setState(0);
        redisUtil.set("name",user);
        System.out.println(redisUtil.get("name"));
    }



}
