package com.communication.deicing.view;

import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.entity.DeicingDeviceListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG
 * on 2021/6/16  15:56
 * Description：
 */
public interface HomeView {

    public void getDeicingDeviceSuccess(DeicingDeviceListEntity<List<DeicingDeviceEntity>> arrayListDeicingDeviceListEntity);

    public void getDeicingDeviceFail(String msg);

    public void getHighwaysSnSuccess(ArrayList<String> strings);
    public void getHighwaysSnFail(String msg);

    public void getHighwaysSuccess(ArrayList<String> strings);
    public void getHighwaysFail(String msg);

    public void getHighwaysLengthSuccess(ArrayList<String> strings);
    public void getHighwaysLengthFail(String msg);


}
