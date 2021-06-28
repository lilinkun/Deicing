package com.communication.deicing.presenter;

import android.util.Base64;

import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.DeicingControlEntity;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.http.rxjavahelper.RxObserver;
import com.communication.deicing.http.rxjavahelper.RxResultHelper;
import com.communication.deicing.http.rxjavahelper.RxSchedulersHelper;
import com.communication.deicing.util.DeicingUtil;
import com.communication.deicing.view.ChangePasswordView;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LG
 * on 2021/6/23  15:31
 * Description：
 */
public class ChangePasswordPresenter extends BasePresenter<ChangePasswordView> {

    public void changePassword(String oldPsd,String newPsd){

        try {
//            hashMap.put("pwd", Base64.encodeToString(oldPsd.getBytes("utf-8"),Base64.NO_WRAP));
//            hashMap.put("newPwd", Base64.encodeToString(newPsd.getBytes("utf-8"),Base64.NO_WRAP));

            String pwd = Base64.encodeToString(oldPsd.getBytes("utf-8"),Base64.NO_WRAP);
            String newPwd = Base64.encodeToString(newPsd.getBytes("utf-8"),Base64.NO_WRAP);

            DeicingService.changePassword(pwd,newPwd)
                    .compose(RxSchedulersHelper.io_main())
                    .compose(RxResultHelper.handleResult())
                    .subscribe(new RxObserver<Object>() {
                        @Override
                        public void _onNext(Object o) {
                            getView().changePasswordSuccess();
                        }

                        @Override
                        public void _onError(String errorMessage) {
                            getView().changePasswordFail(errorMessage);
                        }

                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



    }
}
