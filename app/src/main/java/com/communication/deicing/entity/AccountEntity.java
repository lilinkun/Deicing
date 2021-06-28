package com.communication.deicing.entity;

import java.io.Serializable;

/**
 * Created by LG
 * on 2021/6/17  18:03
 * Description：
 */
public class AccountEntity implements Serializable {

    private int unReadMessage;
    private AccountInfoEntity accountInfo;
    private OrganizationInfoEntity organizationInfo;

    public int getUnReadMessage() {
        return unReadMessage;
    }

    public void setUnReadMessage(int unReadMessage) {
        this.unReadMessage = unReadMessage;
    }

    public AccountInfoEntity getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfoEntity accountInfo) {
        this.accountInfo = accountInfo;
    }

    public OrganizationInfoEntity getOrganizationInfo() {
        return organizationInfo;
    }

    public void setOrganizationInfo(OrganizationInfoEntity organizationInfo) {
        this.organizationInfo = organizationInfo;
    }
}
