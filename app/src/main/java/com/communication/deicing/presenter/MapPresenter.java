package com.communication.deicing.presenter;

import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.entity.DeicingDeviceListEntity;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.http.rxjavahelper.RxObserver;
import com.communication.deicing.http.rxjavahelper.RxResultHelper;
import com.communication.deicing.http.rxjavahelper.RxSchedulersHelper;
import com.communication.deicing.view.MapLocationView;

import java.util.List;

/**
 * Created by LG
 * on 2021/6/24  10:58
 * Description：
 */
public class MapPresenter extends BasePresenter<MapLocationView> {

    /**
     * 获取设备列表
     * @param pageIndex
     * @param pageSize
     */
    public void getDeviceList(int pageIndex,int pageSize,String highwaysSn,String highWays,String highwaysLength){

        DeicingService.deicingDeviceList(pageIndex,pageSize,highwaysSn,highWays,highwaysLength)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<DeicingDeviceListEntity<List<DeicingDeviceEntity>>>() {
                    @Override
                    public void _onNext(DeicingDeviceListEntity<List<DeicingDeviceEntity>> arrayListDeicingDeviceListEntity) {
                        getView().getDeicingDeviceSuccess(arrayListDeicingDeviceListEntity);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getDeicingDeviceFail(errorMessage);
                    }

                });

    }
}
