package com.communication.deicing.entity;

import java.io.Serializable;

/**
 * Created by LG
 * on 2021/6/16  17:23
 * Description：
 */
public class PeripheralInfoEntity implements Serializable {

    private long create_time;

    private int device_id;
    /**
     * 数据采集时间
     */
    private String acquisition_time;

    private int id;
    /**
     * 外设状态 0：正常 、其它
     */
    private String state;
    /**
     * 外设类型 路面监测12
     */
    private String type;
    /**
     * 外设编号
     */
    private int slave_sn;
    /**
     * 外设数据-路面监测-最新的
     */
    private MonitorDataEntity monitor_data;

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getAcquisition_time() {
        return acquisition_time;
    }

    public void setAcquisition_time(String acquisition_time) {
        this.acquisition_time = acquisition_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSlave_sn() {
        return slave_sn;
    }

    public void setSlave_sn(int slave_sn) {
        this.slave_sn = slave_sn;
    }

    public MonitorDataEntity getMonitor_data() {
        return monitor_data;
    }

    public void setMonitor_data(MonitorDataEntity monitor_data) {
        this.monitor_data = monitor_data;
    }
}
