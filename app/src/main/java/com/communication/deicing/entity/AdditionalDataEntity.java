package com.communication.deicing.entity;

import java.io.Serializable;

/**
 * Created by LG
 * on 2021/6/16  17:20
 * Description：
 */
public class AdditionalDataEntity implements Serializable {
    /**
     * 创建时间
     */
    private String create_time;

    private String id;

    private String sn;

    private String state;
    /**
     * 结冰概率
     */
    private double freezing_probability;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getFreezing_probability() {
        return freezing_probability;
    }

    public void setFreezing_probability(double freezing_probability) {
        this.freezing_probability = freezing_probability;
    }
}
