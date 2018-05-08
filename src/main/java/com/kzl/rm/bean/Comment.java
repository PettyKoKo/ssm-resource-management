package com.kzl.rm.bean;

import java.util.Date;

public class Comment {
    private Long commentId;

    private String observerAccount;

    private Long articleId;

    private Date commentTime;

    private String commentContent;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getObserverAccount() {
        return observerAccount;
    }

    public void setObserverAccount(String observerAccount) {
        this.observerAccount = observerAccount;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}