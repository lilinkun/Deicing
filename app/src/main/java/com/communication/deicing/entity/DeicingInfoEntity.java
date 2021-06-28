package com.communication.deicing.entity;

import java.io.Serializable;

/**
 * Created by LG
 * on 2021/6/16  17:21
 * Description：
 */
public class DeicingInfoEntity implements Serializable {

    /**
     * 系统组成
     */
    private String system_composition;
    /**
     * 建设完工时间
     */
    private String completion_time;
    private long create_time;
    private int device_id;
    /**
     * 管理养护单位负责人
     */
    private String management_unit_admin;
    /**
     * 售后联系人
     */
    private String after_sales_name;
    /**
     * 建设单位
     */
    private String construction_unit;
    /**
     * 系统类型 1:喷淋 2:发热电缆 3:超薄导电磨耗层'
     */
    private int type;
    /**
     * 售后电话
     */
    private String after_sales_phone;
    private long update_time;
    /**
     * 管理养护单位
     */
    private String management_unit;
    /**
     * 建设单位负责人
     */
    private String construction_unit_admin;
    private int id;

    public String getSystem_composition() {
        return system_composition;
    }

    public void setSystem_composition(String system_composition) {
        this.system_composition = system_composition;
    }

    public String getCompletion_time() {
        return completion_time;
    }

    public void setCompletion_time(String completion_time) {
        this.completion_time = completion_time;
    }

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

    public String getManagement_unit_admin() {
        return management_unit_admin;
    }

    public void setManagement_unit_admin(String management_unit_admin) {
        this.management_unit_admin = management_unit_admin;
    }

    public String getAfter_sales_name() {
        return after_sales_name;
    }

    public void setAfter_sales_name(String after_sales_name) {
        this.after_sales_name = after_sales_name;
    }

    public String getConstruction_unit() {
        return construction_unit;
    }

    public void setConstruction_unit(String construction_unit) {
        this.construction_unit = construction_unit;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAfter_sales_phone() {
        return after_sales_phone;
    }

    public void setAfter_sales_phone(String after_sales_phone) {
        this.after_sales_phone = after_sales_phone;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public String getManagement_unit() {
        return management_unit;
    }

    public void setManagement_unit(String management_unit) {
        this.management_unit = management_unit;
    }

    public String getConstruction_unit_admin() {
        return construction_unit_admin;
    }

    public void setConstruction_unit_admin(String construction_unit_admin) {
        this.construction_unit_admin = construction_unit_admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
