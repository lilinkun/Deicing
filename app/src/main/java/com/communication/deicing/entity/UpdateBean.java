package com.communication.deicing.entity;

import java.io.Serializable;

/**
 * Created by LG
 * on 2021/6/11  11:04
 * Description：蒲公英更新
 *  */
public class UpdateBean implements Serializable {

    public double buildVersion;
    public String downloadURL;
    public String buildName;
    public int buildFileSize;

}