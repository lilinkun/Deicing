package com.communication.deicing.presenter;

import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.AccountEntity;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.http.rxjavahelper.RxObserver;
import com.communication.deicing.http.rxjavahelper.RxResultHelper;
import com.communication.deicing.http.rxjavahelper.RxSchedulersHelper;
import com.communication.deicing.view.MeView;

/**
 * Created by LG
 * on 2021/6/21  15:32
 * Description：
 */
public class MePresenter extends BasePresenter<MeView> {

    /**
     *获取个人信息
     */
    public void getPersonalInfo(){

        DeicingService.getAccount()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<AccountEntity>() {
                    @Override
                    public void _onNext(AccountEntity accountEntity) {
                        getView().getPersonalInfoSuccess(accountEntity);
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().getPersonalInfoFail(errorMessage);
                    }

                });
    }

}
