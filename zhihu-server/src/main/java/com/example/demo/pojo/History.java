package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
@Component
@Entity
@Table(name = "history")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer questionId;
    private Integer userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date historyAt;
    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", userId=" + userId +
                ", historyAt=" + historyAt +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getHistoryAt() {
        return historyAt;
    }

    public void setHistoryAt(Date historyAt) {
        this.historyAt = historyAt;
    }

    public History() {
    }
}
