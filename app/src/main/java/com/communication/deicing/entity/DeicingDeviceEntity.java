package com.communication.deicing.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LG
 * on 2021/6/16  15:35
 * Description：
 */
public class DeicingDeviceEntity implements Serializable {

    /**
     * 高速编号
     */
    private String highways_sn;
    /**
     *
     */
    private String node_location;
    /**
     *
     */
    private String description;
    /**
     * 设备类型编号
     */
    private int device_type;
    /**
     * 电量
     */
    private String electric_quantity;
    /**
     * 结束桩号
     */
    private String end_pile;

    /**
     * 更新时间
     */
    private long update_time;
    /**
     * 物联网卡号
     */
    private String iccid;
    /**
     *
     */
    private String power_fault;
    /**
     * 设备网络号
     */
    private String nsid;
    /**
     *
     */
    private String lora_commcardfault;
    /**
     * 开始桩号
     */
    private String start_pile;
    /**
     * 高速段
     */
    private String highways;
    /**
     * 结束公里数
     */
    private String end;
    /**
     * 设备id
     */
    private String id;
    /**
     * 设备序列号
     */
    private String sn;
    /**
     * 设备状态 0为在线 1为离线
     */
    private int state;
    /**
     * 东经
     */
    private double lat;
    /**
     * 北纬
     */
    private double lng;
    /**
     * 公里数
     */
    private int mileage;
    /**
     * 创建时间
     */
    private long create_time;
    /**
     * 设备序列号
     */
    private int sw;
    /**
     *
     */
    private int devices;
    /**
     * 开始公里数
     */
    private String start;
    /**
     * 名称
     */
    private String name;
    /**
     * 最通讯时间
     */
    private long last_operation_time;

    private List<PeripheralInfoEntity> peripheral_info;

    private AdditionalDataEntity additionalData;

    private DeicingInfoEntity deicing_info;

    private DeicingControlEntity deicing_control;

    public String getHighways_sn() {
        return highways_sn;
    }

    public void setHighways_sn(String highways_sn) {
        this.highways_sn = highways_sn;
    }

    public String getNode_location() {
        return node_location;
    }

    public void setNode_location(String node_location) {
        this.node_location = node_location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

    public String getElectric_quantity() {
        return electric_quantity;
    }

    public void setElectric_quantity(String electric_quantity) {
        this.electric_quantity = electric_quantity;
    }

    public String getEnd_pile() {
        return end_pile;
    }

    public void setEnd_pile(String end_pile) {
        this.end_pile = end_pile;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getPower_fault() {
        return power_fault;
    }

    public void setPower_fault(String power_fault) {
        this.power_fault = power_fault;
    }

    public String getNsid() {
        return nsid;
    }

    public void setNsid(String nsid) {
        this.nsid = nsid;
    }

    public String getLora_commcardfault() {
        return lora_commcardfault;
    }

    public void setLora_commcardfault(String lora_commcardfault) {
        this.lora_commcardfault = lora_commcardfault;
    }

    public String getStart_pile() {
        return start_pile;
    }

    public void setStart_pile(String start_pile) {
        this.start_pile = start_pile;
    }

    public String getHighways() {
        return highways;
    }

    public void setHighways(String highways) {
        this.highways = highways;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public int getDevices() {
        return devices;
    }

    public void setDevices(int devices) {
        this.devices = devices;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public long getLast_operation_time() {
        return last_operation_time;
    }

    public void setLast_operation_time(long last_operation_time) {
        this.last_operation_time = last_operation_time;
    }

    public List<PeripheralInfoEntity> getPeripheral_info() {
        return peripheral_info;
    }

    public void setPeripheral_info(List<PeripheralInfoEntity> peripheral_info) {
        this.peripheral_info = peripheral_info;
    }

    public AdditionalDataEntity getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalDataEntity additionalData) {
        this.additionalData = additionalData;
    }

    public DeicingInfoEntity getDeicing_info() {
        return deicing_info;
    }

    public void setDeicing_info(DeicingInfoEntity deicing_info) {
        this.deicing_info = deicing_info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeicingControlEntity getDeicing_control() {
        return deicing_control;
    }

    public void setDeicing_control(DeicingControlEntity deicing_control) {
        this.deicing_control = deicing_control;
    }
}
