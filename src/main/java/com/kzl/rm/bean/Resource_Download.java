package com.kzl.rm.bean;

import java.util.Date;

public class Resource_Download {
    private Long resourceId;

    private String resourceName;

    private String downloaderAccount;

    private Date downloadTime;

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

    public String getDownloaderAccount() {
        return downloaderAccount;
    }

    public void setDownloaderAccount(String downloaderAccount) {
        this.downloaderAccount = downloaderAccount;
    }

    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }
}