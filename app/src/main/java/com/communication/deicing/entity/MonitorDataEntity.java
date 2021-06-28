package com.communication.deicing.entity;

import java.io.Serializable;

/**
 * Created by LG
 * on 2021/6/16  17:25
 * Description：
 */
public class MonitorDataEntity implements Serializable {

    /**
     * 冰厚度
     */
    private double iceThickness;
    /**
     * 雪厚度
     */
    private double snowThickness;
    /**
     * 路面温度
     */
    private double roadTemperature;
    /**
     * 路面状态共3位数 百位(0无警告、1警告、2报警、3霜警告) 十位(始终为0预留) 个位(0出错、1干、2潮、3湿、4/5/8预留、6雪、7冰、9冰水混合物)
     */
    private String roadState;
    /**
     * 供电电压
     */
    private double serviceVoltage;
    /**
     * 硬件状态共2位 十位(0接收器窗清洁、1警告-接收器窗污染、2报警-接收器窗污染严重、3额外环境光引起数据暂时丢失、4-9预留) 个位(0硬件正常、1cpu硬件警告)
     */
    private String hardwareStatusType2;
    /**
     * 机壳温度
     */
    private double casingTemperature;

    private String icePoint;
    /**
     * 湿滑程度0.82表示正常
     */
    private double slipperyDegree;
    /**
     * 水累积量
     */
    private double ponding;
    /**
     * (1干、2潮、3湿、6雪、7冰、9冰水混合物、其它：未知)
     */
    private int roadInfo;

    public double getIceThickness() {
        return iceThickness;
    }

    public void setIceThickness(double iceThickness) {
        this.iceThickness = iceThickness;
    }

    public double getSnowThickness() {
        return snowThickness;
    }

    public void setSnowThickness(double snowThickness) {
        this.snowThickness = snowThickness;
    }

    public double getRoadTemperature() {
        return roadTemperature;
    }

    public void setRoadTemperature(double roadTemperature) {
        this.roadTemperature = roadTemperature;
    }

    public String getRoadState() {
        return roadState;
    }

    public void setRoadState(String roadState) {
        this.roadState = roadState;
    }

    public double getServiceVoltage() {
        return serviceVoltage;
    }

    public void setServiceVoltage(double serviceVoltage) {
        this.serviceVoltage = serviceVoltage;
    }

    public String getHardwareStatusType2() {
        return hardwareStatusType2;
    }

    public void setHardwareStatusType2(String hardwareStatusType2) {
        this.hardwareStatusType2 = hardwareStatusType2;
    }

    public double getCasingTemperature() {
        return casingTemperature;
    }

    public void setCasingTemperature(double casingTemperature) {
        this.casingTemperature = casingTemperature;
    }

    public String getIcePoint() {
        return icePoint;
    }

    public void setIcePoint(String icePoint) {
        this.icePoint = icePoint;
    }

    public double getSlipperyDegree() {
        return slipperyDegree;
    }

    public void setSlipperyDegree(double slipperyDegree) {
        this.slipperyDegree = slipperyDegree;
    }

    public double getPonding() {
        return ponding;
    }

    public void setPonding(double ponding) {
        this.ponding = ponding;
    }

    public int getRoadInfo() {
        return roadInfo;
    }

    public void setRoadInfo(int roadInfo) {
        this.roadInfo = roadInfo;
    }
}
