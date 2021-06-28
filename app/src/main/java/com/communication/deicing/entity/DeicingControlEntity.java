package com.communication.deicing.entity;

import java.io.Serializable;

/**
 * Created by LG
 * on 2021/6/17  10:07
 * Description：
 */
public class DeicingControlEntity implements Serializable {

    /**
     * 上次加热时间
     */
    private String last_warm_time;
    /**
     *最后喷淋时间
     */
    private String last_spray_time;
    private long update_time;
    /**
     *喷淋间隔时间(s)
     */
    private int spray_interval_time;
    /**
     * 总功率kw
     */
    private String total_power;
    private String create_time;
    /**
     * 工作状态 0：待机 1：工作中
     */
    private int work_state;
    private int device_id;
    /**
     * 控制模式 0:全自动 1:半自动',
     */
    private int control_mode;
    /**
     *  喷淋系统 '报警状态0:无报警 1:电源缺相 2:增压泵过载 3:电动阀过流 4:电磁阀过流 5:主管网泄露 6:支路管网泄露 7:除冰液低液位'
     *  发热电缆抗冰系统  '报警状态0:无报警 1:电源缺相 2:电源过流'
     *  导电磨耗层系统  报警状态0:无报警 1:电源缺相 2:电源过流
     *
     */
    private String alarm_state;
    private String unit_heating_zone_power;
    private int id;

    /**
     * 主管网压力值 (mPa)
     */
    private String main_pipeline_kpa;

    /**
     * 支路官网压力值(mPa) 多个支管网用 "," 分隔 例：10,23,46,5 表示支管网1-4的压力值
     */
    private String branch_pipeline_kpa;

    /**
     * 储液罐液位 (mm)
     */
    private String liquid_tank_level;

    /**
     * '增压泵状态 0:关闭 1:开启'
     */
    private int inflator_state;

    /**
     * 电磁阀状态 0:关闭 1:开启',
     */
    private int motorized_state;

    /**
     * 电动阀状态 0:关闭 1:开启'
     */
    private int radiotube_state;

    /**
     * 喷嘴数量
     */
    private int nozzle_number;

    /**
     * 当前加热档位
     */
    private int warm_gears;


    public String getLast_warm_time() {
        return last_warm_time;
    }

    public void setLast_warm_time(String last_warm_time) {
        this.last_warm_time = last_warm_time;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public String getTotal_power() {
        return total_power;
    }

    public void setTotal_power(String total_power) {
        this.total_power = total_power;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getWork_state() {
        return work_state;
    }

    public void setWork_state(int work_state) {
        this.work_state = work_state;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int getControl_mode() {
        return control_mode;
    }

    public void setControl_mode(int control_mode) {
        this.control_mode = control_mode;
    }

    public String getAlarm_state() {
        return alarm_state;
    }

    public void setAlarm_state(String alarm_state) {
        this.alarm_state = alarm_state;
    }

    public String getUnit_heating_zone_power() {
        return unit_heating_zone_power;
    }

    public void setUnit_heating_zone_power(String unit_heating_zone_power) {
        this.unit_heating_zone_power = unit_heating_zone_power;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_spray_time() {
        return last_spray_time;
    }

    public void setLast_spray_time(String last_spray_time) {
        this.last_spray_time = last_spray_time;
    }

    public String getLiquid_tank_level() {
        return liquid_tank_level;
    }

    public void setLiquid_tank_level(String liquid_tank_level) {
        this.liquid_tank_level = liquid_tank_level;
    }

    public int getInflator_state() {
        return inflator_state;
    }

    public void setInflator_state(int inflator_state) {
        this.inflator_state = inflator_state;
    }

    public int getMotorized_state() {
        return motorized_state;
    }

    public void setMotorized_state(int motorized_state) {
        this.motorized_state = motorized_state;
    }

    public int getRadiotube_state() {
        return radiotube_state;
    }

    public void setRadiotube_state(int radiotube_state) {
        this.radiotube_state = radiotube_state;
    }

    public String getMain_pipeline_kpa() {
        return main_pipeline_kpa;
    }

    public void setMain_pipeline_kpa(String main_pipeline_kpa) {
        this.main_pipeline_kpa = main_pipeline_kpa;
    }

    public String getBranch_pipeline_kpa() {
        return branch_pipeline_kpa;
    }

    public void setBranch_pipeline_kpa(String branch_pipeline_kpa) {
        this.branch_pipeline_kpa = branch_pipeline_kpa;
    }

    public int getNozzle_number() {
        return nozzle_number;
    }

    public void setNozzle_number(int nozzle_number) {
        this.nozzle_number = nozzle_number;
    }

    public int getWarm_gears() {
        return warm_gears;
    }

    public void setWarm_gears(int warm_gears) {
        this.warm_gears = warm_gears;
    }

    public int getSpray_interval_time() {
        return spray_interval_time;
    }

    public void setSpray_interval_time(int spray_interval_time) {
        this.spray_interval_time = spray_interval_time;
    }
}
