package com.communication.deicing.util;

/**
 * Created by LG
 * on 2021/10/18  15:49
 * Description：
 */
public enum RoadStatusEnum {

    dried(1,"干"),damp(2,"潮"),wet(3,"湿"),
    snow(6,"雪"),ice(7,"冰"),ice_water_mixture(9,"冰水混合物");

    private int road_status_id;
    private String road_status_str;

    RoadStatusEnum(int road_status_id,String road_status_str){
        this.road_status_id = road_status_id;
        this.road_status_str = road_status_str;
    }

    public int getRoad_status_id() {
        return road_status_id;
    }

    public void setRoad_status_id(int road_status_id) {
        this.road_status_id = road_status_id;
    }

    public String getRoad_status_str() {
        return road_status_str;
    }

    public void setRoad_status_str(String road_status_str) {
        this.road_status_str = road_status_str;
    }

    public static String getVal(int value) {
        RoadStatusEnum[] enums = values();
        for (RoadStatusEnum roadStatusEnum : enums) {
            if (roadStatusEnum.getRoad_status_id() == value) {
                return roadStatusEnum.getRoad_status_str();
            }
        }
        return null;
    }
}
