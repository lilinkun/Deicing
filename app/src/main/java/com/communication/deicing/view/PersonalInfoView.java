package com.communication.deicing.view;

import com.communication.deicing.entity.AccountEntity;

/**
 * Created by LG
 * on 2021/6/17  17:46
 * Description：
 */
public interface PersonalInfoView {

    public void getPersonalInfoSuccess(AccountEntity accountEntity);

    public void getPersonalInfoFail(String msg);

    public void modifySuccess();

    public void modifyFail(String msg);

}
