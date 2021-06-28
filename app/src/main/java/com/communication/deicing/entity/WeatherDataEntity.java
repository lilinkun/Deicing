package com.communication.deicing.entity;

import java.io.Serializable;

/**
 * Created by LG
 * on 2021/6/18  10:26
 * Description：
 */
public class WeatherDataEntity implements Serializable {

    /**
     * 降雨量
     */
    private double rainfall;
    /**
     * 硬件状态
     */
    private String weatherHardwareStatus;
    /**
     *  温度
     */
    private double temperature;
    /**
     * 风速
     */
    private double windForce;
    /**
     * 湿度
     */
    private double humidity;
    /**
     * ,风向 范围(0°-359°) 0°-北 90°-东 以此类推
     */
    private double windDirection;
    /**
     * 气压
     */
    private double kpa;
    private String roadInfo;

    public double getRainfall() {
        return rainfall;
    }

    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }

    public String getWeatherHardwareStatus() {
        return weatherHardwareStatus;
    }

    public void setWeatherHardwareStatus(String weatherHardwareStatus) {
        this.weatherHardwareStatus = weatherHardwareStatus;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindForce() {
        return windForce;
    }

    public void setWindForce(double windForce) {
        this.windForce = windForce;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public double getKpa() {
        return kpa;
    }

    public void setKpa(double kpa) {
        this.kpa = kpa;
    }

    public String getRoadInfo() {
        return roadInfo;
    }

    public void setRoadInfo(String roadInfo) {
        this.roadInfo = roadInfo;
    }
}
