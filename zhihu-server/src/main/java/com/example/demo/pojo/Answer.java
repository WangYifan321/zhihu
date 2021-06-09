package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
@Entity
@Component
@Table(name = "answer")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Answer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "question_id")
    private Integer questionId;
    @Column(name = "hdu_user_id")
    private Integer hduUserId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//到数据库中得格式
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//返回给前端得格式
    @Column(name = "answer_at")
    private Date answerAt;
    @Column(name = "article", columnDefinition = "longtext")
    private String article;

    public Answer() {
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", hduUserId=" + hduUserId +
                ", answerAt=" + answerAt +
                ", article='" + article + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getHduUserId() {
        return hduUserId;
    }

    public void setHduUserId(Integer hduUserId) {
        this.hduUserId = hduUserId;
    }

    public Date getAnswerAt() {
        return answerAt;
    }

    public void setAnswerAt(Date answerAt) {
        this.answerAt = answerAt;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
