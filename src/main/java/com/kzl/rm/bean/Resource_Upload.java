package com.kzl.rm.bean;

import java.util.Date;

public class Resource_Upload {
    private Long resourceId;

    private String resourceName;

    private String resourceUrl;

    private String uploaderAccount;

    private Date uploadTime;

    private String resourceType;

    private String resourceDescribe;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getUploaderAccount() {
        return uploaderAccount;
    }

    public void setUploaderAccount(String uploaderAccount) {
        this.uploaderAccount = uploaderAccount;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceDescribe() {
        return resourceDescribe;
    }

    public void setResourceDescribe(String resourceDescribe) {
        this.resourceDescribe = resourceDescribe;
    }
}