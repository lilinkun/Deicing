package com.communication.deicing.presenter;

import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.http.rxjavahelper.RxObserver;
import com.communication.deicing.http.rxjavahelper.RxResultHelper;
import com.communication.deicing.http.rxjavahelper.RxSchedulersHelper;
import com.communication.deicing.view.BindPhoneView;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by LG
 * on 2021/6/23  14:30
 * Description：
 */
public class BindPhonePresenter extends BasePresenter<BindPhoneView> {

    /**
     * 获取手机验证码
     * @param sign  手机验证码签名
     * @param phone 手机号
     */
    public void sendVcode(String sign,String phone){
        DeicingService.sendVCodePhone(sign,phone)
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
     * 修改绑定手机
     * (默认删除手机号其它账户绑定信息)
     * @param phone 手机,
     * @param sign  手机验证码签名,
     * @param code  验证码
     */
    public void bindphone(String sign,String phone,String code){


        HashMap<String, String> params = new HashMap<>();

        params.put("sign", sign);
        params.put("phone",phone);
        params.put("code",code);

        JSONObject jsonObject = new JSONObject(params);


        DeicingService.bindPhone(jsonObject)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxObserver<Object>() {
                    @Override
                    public void _onNext(Object o) {
                        getView().bindPhoneSuccess();
                    }

                    @Override
                    public void _onError(String errorMessage) {
                        getView().bindPhoneFail(errorMessage);
                    }

                });
    }


}
