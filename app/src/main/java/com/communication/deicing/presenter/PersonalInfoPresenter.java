package com.communication.deicing.presenter;

import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.AccountEntity;
import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.entity.DeicingDeviceListEntity;
import com.communication.deicing.entity.ResponseData;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.http.rxjavahelper.RxObserver;
import com.communication.deicing.http.rxjavahelper.RxResultHelper;
import com.communication.deicing.http.rxjavahelper.RxSchedulersHelper;
import com.communication.deicing.view.PersonalInfoView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import io.reactivex.functions.Function;

/**
 * Created by LG
 * on 2021/6/17  17:55
 * Description：
 */
public class PersonalInfoPresenter extends BasePresenter<PersonalInfoView> {

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

    /**
     *
     * @param company 公司名
     * @param id      个人信息编号
     * @param job     职位
     * @param name    姓名
     * @param sex     性别0：女 1：男
     */
    public void modifyPersonInfo(String company,String id,String job,String name,String sex){

        HashMap<String, String> params = new HashMap<>();

        params.put("company", company);
        params.put("id", id);
        params.put("job",job);
        params.put("name",name);
        params.put("sex",sex);

        JSONObject jsonObject = new JSONObject(params);


        DeicingService.modifyPersonInfo(jsonObject)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<Object>() {
                    @Override
                    public void _onNext(Object s) {
                        getView().modifySuccess();
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().modifyFail(errorMessage);
                    }

                });


    }


}
