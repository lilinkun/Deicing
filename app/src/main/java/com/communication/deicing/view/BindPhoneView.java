package com.communication.deicing.view;

/**
 * Created by LG
 * on 2021/6/23  14:31
 * Description：
 */
public interface BindPhoneView {


    public void sendVcodeSuccess();
    public void sendVcodeFail(String msg);

    public void bindPhoneSuccess();
    public void bindPhoneFail(String msg);
}
