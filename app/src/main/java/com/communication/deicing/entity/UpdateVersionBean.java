package com.communication.deicing.entity;

/**
 * Created by LG
 * on 2021/6/11  14:47
 * Description：
 */
public class UpdateVersionBean {
    private String appName;
    private String version;
    private int updateType;
    private String updateFeature;
    private String downloadPath;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getUpdateType() {
        return updateType;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    public String getUpdateFeature() {
        return updateFeature;
    }

    public void setUpdateFeature(String updateFeature) {
        this.updateFeature = updateFeature;
    }


    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }



}
