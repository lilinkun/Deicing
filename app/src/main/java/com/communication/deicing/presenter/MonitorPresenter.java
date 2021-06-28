package com.communication.deicing.presenter;

import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.DeicingControlEntity;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.http.rxjavahelper.RxObserver;
import com.communication.deicing.http.rxjavahelper.RxResultHelper;
import com.communication.deicing.http.rxjavahelper.RxSchedulersHelper;
import com.communication.deicing.view.MonitorView;

import java.util.List;

/**
 * Created by LG
 * on 2021/6/17  11:17
 * Description：
 */
public class MonitorPresenter extends BasePresenter<MonitorView> {


    public void getDeviceControlInfo(String sn){

        DeicingService.deviceControlInfo(sn)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<List<DeicingControlEntity>>() {
                    @Override
                    public void _onNext(List<DeicingControlEntity> deicingControlEntitys) {
                        getView().getDeviceControlInfoSuccess(deicingControlEntitys.get(0));
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDeviceControlInfoFail(errorMessage);
                    }

                });

    }

}
