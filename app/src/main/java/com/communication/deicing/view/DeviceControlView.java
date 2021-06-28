package com.communication.deicing.view;

import com.communication.deicing.entity.DeicingControlEntity;

/**
 * Created by LG
 * on 2021/6/17  16:54
 * Description：
 */
public interface DeviceControlView {

    public void getDeicingControlSuccess(String msg);
    public void getDeviceControlFail(String msg);

}
