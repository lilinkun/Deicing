package com.communication.deicing.presenter;

import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.DeicingControlEntity;
import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.entity.DeicingDeviceListEntity;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.http.rxjavahelper.RxObserver;
import com.communication.deicing.http.rxjavahelper.RxResultHelper;
import com.communication.deicing.http.rxjavahelper.RxSchedulersHelper;
import com.communication.deicing.view.DeviceControlView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by LG
 * on 2021/6/17  16:54
 * Description：设备控制的presenter
 */
public class DeviceControlPresenter extends BasePresenter<DeviceControlView> {

    /**
     *
     * @param sn                  // 设备序列号
     * @param spray_interval_time // 喷淋间隔时间 秒
     * @param control_mode        // 控制模式 0:全自动 1:半自动
     * @param spray_capacity      // 喷淋量 ml
     * @param inflator_state      // 增压泵状态 0:关闭 1:开启
     * @param radiotube_state     // 电动阀状态 0:关闭 1:开启
     * @param work_state          // 工作状态 0:待机 1:工作中
     * @param warm_gears          // 加热档位 1：低档 2：中挡 3.高档
     */
    public void changeDeviceControl(String sn,String spray_interval_time,String control_mode,String spray_capacity,String inflator_state,String radiotube_state,String work_state,String warm_gears){

        HashMap<String,String> hashMap = new HashMap<>();

        hashMap.put("sn",sn);
        hashMap.put("spray_interval_time",spray_interval_time);
        hashMap.put("control_mode",control_mode);
        hashMap.put("spray_capacity",spray_capacity);
        hashMap.put("inflator_state",inflator_state);
        hashMap.put("radiotube_state",radiotube_state);
        hashMap.put("work_state",work_state);
        hashMap.put("warm_gears",warm_gears);


        JSONObject jsonObject = new JSONObject(hashMap);



        DeicingService.deicingcontrol(jsonObject)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<String>() {
                    @Override
                    public void _onNext(String str) {
                        getView().getDeicingControlSuccess(str);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDeviceControlFail(errorMessage);
                    }

                });

    }

}
