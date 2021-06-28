package com.communication.deicing.presenter;

import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.DeicingControlEntity;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.http.rxjavahelper.RxObserver;
import com.communication.deicing.http.rxjavahelper.RxResultHelper;
import com.communication.deicing.http.rxjavahelper.RxSchedulersHelper;
import com.communication.deicing.view.BindEmailView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by LG
 * on 2021/6/18  16:34
 * Description：
 */
public class BindEmailPresenter extends BasePresenter<BindEmailView> {

    /**
     * 获取邮箱验证码
     * @param sign  邮箱验证码签名
     * @param email 邮箱
     */
    public void sendVcode(String sign,String email){
        DeicingService.sendVCodeEmail(sign,email)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<Object>() {
                    @Override
                    public void _onNext(Object o) {
                        getView().sendVcodeSuccess();
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().sendVcodeFail(errorMessage);
                    }


                });
    }

    /**
     * 修改个人信息
     * @param sign  邮箱验证码签名
     * @param email 邮箱
     */
    public void bindEmail(String sign,String email,String code){


        HashMap<String, String> params = new HashMap<>();

        params.put("sign", sign);
        params.put("email",email);
        params.put("code",code);

        JSONObject jsonObject = new JSONObject(params);


        DeicingService.bindEmail(jsonObject)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<Object>() {
                    @Override
                    public void _onNext(Object o) {
                        getView().bindEmailSuccess();
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().bindEmailFail(errorMessage);
                    }

                });
    }

}
