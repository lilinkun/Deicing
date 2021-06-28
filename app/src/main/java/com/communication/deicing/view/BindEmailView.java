package com.communication.deicing.view;

/**
 * Created by LG
 * on 2021/6/18  16:34
 * Description：
 */
public interface BindEmailView {

    public void sendVcodeSuccess();
    public void sendVcodeFail(String msg);

    public void bindEmailSuccess();
    public void bindEmailFail(String msg);
}
