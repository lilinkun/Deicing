package com.communication.deicing.presenter;

import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.entity.DeicingDeviceListEntity;
import com.communication.deicing.entity.UpdateVersionBean;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.http.rxjavahelper.RxObserver;
import com.communication.deicing.http.rxjavahelper.RxResultHelper;
import com.communication.deicing.http.rxjavahelper.RxSchedulersHelper;
import com.communication.deicing.view.HomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG
 * on 2021/6/16  15:50
 * Description：
 */
public class HomePresenter extends BasePresenter<HomeView> {


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


    /**
     *获取路段编号
     */
    public void gethighwaysSn(){

        DeicingService.highwaysSn()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArrayList<String>>() {
                    @Override
                    public void _onNext(ArrayList<String> strings) {
                        getView().getHighwaysSnSuccess(strings);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getHighwaysSnFail(errorMessage);
                    }

                });

    }


    /**
     *获取路段名
     */
    public void getHighways(String highWays_sn){

        DeicingService.highways(highWays_sn)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArrayList<String>>() {
                    @Override
                    public void _onNext(ArrayList<String> strings) {
                        getView().getHighwaysSuccess(strings);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getHighwaysFail(errorMessage);
                    }

                });

    }


    /**
     *获取桩号
     */
    public void getHighWaysLength(String highWays_sn,String highWays){

        DeicingService.highWaysLength(highWays_sn,highWays)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<ArrayList<String>>() {
                    @Override
                    public void _onNext(ArrayList<String> strings) {
                        getView().getHighwaysLengthSuccess(strings);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getHighwaysLengthFail(errorMessage);
                    }

                });

    }


}
