package com.communication.deicing.entity;

import java.io.Serializable;

/**
 * Created by LG
 * on 2021/6/17  16:34
 * Description：
 */
public class MonitorEntity implements Serializable {

    private MonitorDataEntity pavement_data;
    private WeatherDataEntity weather_data;

    public MonitorDataEntity getPavement_data() {
        return pavement_data;
    }

    public void setPavement_data(MonitorDataEntity pavement_data) {
        this.pavement_data = pavement_data;
    }

    public WeatherDataEntity getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(WeatherDataEntity weather_data) {
        this.weather_data = weather_data;
    }
}
