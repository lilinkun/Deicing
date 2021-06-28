package com.communication.deicing.presenter;

import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.entity.DeicingDeviceListEntity;
import com.communication.deicing.entity.MonitorEntity;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.http.rxjavahelper.RxObserver;
import com.communication.deicing.http.rxjavahelper.RxResultHelper;
import com.communication.deicing.http.rxjavahelper.RxSchedulersHelper;
import com.communication.deicing.view.StatusMonitorView;

import java.util.List;

/**
 * Created by LG
 * on 2021/6/17  16:19
 * Description：
 */
public class StatusMonitorPresenter extends BasePresenter<StatusMonitorView> {

    public void getMonitorWeatherInfo(String sn){


        DeicingService.monitorWeatherInfo(sn)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<MonitorEntity>() {
                    @Override
                    public void _onNext(MonitorEntity monitorEntity) {
                        getView().getDataSuccess(monitorEntity);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDataFail(errorMessage);
                    }

                });



    }



}
