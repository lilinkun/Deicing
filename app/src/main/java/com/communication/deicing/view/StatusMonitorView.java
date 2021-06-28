package com.communication.deicing.view;

import com.communication.deicing.entity.MonitorDataEntity;
import com.communication.deicing.entity.MonitorEntity;

/**
 * Created by LG
 * on 2021/6/17  16:19
 * Description：
 */
public interface StatusMonitorView {

    public void getDataSuccess(MonitorEntity monitorEntity);
    public void getDataFail(String msg);


}
