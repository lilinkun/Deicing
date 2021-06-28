package com.communication.deicing.view;

import com.communication.deicing.entity.AccountEntity;

/**
 * Created by LG
 * on 2021/6/21  15:32
 * Description：
 */
public interface MeView {

    public void getPersonalInfoSuccess(AccountEntity accountEntity);

    public void getPersonalInfoFail(String msg);
}
