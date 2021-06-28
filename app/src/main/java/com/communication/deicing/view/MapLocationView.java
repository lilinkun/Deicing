package com.communication.deicing.view;

import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.entity.DeicingDeviceListEntity;

import java.util.List;

/**
 * Created by LG
 * on 2021/6/24  10:58
 * Description：
 */
public interface MapLocationView {

    public void getDeicingDeviceSuccess(DeicingDeviceListEntity<List<DeicingDeviceEntity>> arrayListDeicingDeviceListEntity);

    public void getDeicingDeviceFail(String msg);
}
