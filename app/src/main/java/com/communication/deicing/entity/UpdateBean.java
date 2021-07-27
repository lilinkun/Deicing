package com.communication.deicing.entity;

import java.io.Serializable;

/**
 * Created by LG
 * on 2021/6/11  11:04
 * Description：蒲公英更新
 *  */
public class UpdateBean implements Serializable {

    public int code;
    public String message;
    public Data data;

    public class Data implements Serializable{
        public double buildVersion;
        public int buildVersionNo;
        public String downloadURL;
        public String buildName;
        public int buildFileSize;

    }

}