package com.communication.deicing.view;

import com.communication.deicing.entity.DeicingControlEntity;

/**
 * Created by LG
 * on 2021/6/17  11:17
 * Description：
 */
public interface MonitorView {

    public void getDeviceControlInfoSuccess(DeicingControlEntity deicingControlEntity);
    public void getDeviceControlInfoFail(String msg);

}
