package com.communication.deicing.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.communication.deicing.R;
import com.communication.deicing.base.BaseActivity;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.presenter.ChangePasswordPresenter;
import com.communication.deicing.util.ActivityUtil;
import com.communication.deicing.util.ConstantsUtil;
import com.communication.deicing.util.MethodsUtil;
import com.communication.deicing.util.UToastUtil;
import com.communication.deicing.view.ChangePasswordView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LG
 * on 2021/6/23  15:09
 * Description：
 */
public class ChangePasswordActivity extends BaseActivity<ChangePasswordView, ChangePasswordPresenter> implements ChangePasswordView{

    @BindView(R.id.ev_psd_old_value)
    EditText evPsdOldValue;
    @BindView(R.id.ev_psd_new_value)
    EditText evPsdNewValue;
    @BindView(R.id.ev_psd_sure_new_value)
    EditText evPsdSureNewValue;

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_psd;
    }

    @Override
    public void initView() {

        ActivityUtil.addActivity(this);

    }

    @Override
    protected ChangePasswordPresenter createPresenter() {
        return new ChangePasswordPresenter();
    }

    @Override
    public void changePasswordSuccess() {

        MethodsUtil.saveKeyValue(ConstantsUtil.AUTHORIZATION, "", this);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(ConstantsUtil.ACCOUNT,MethodsUtil.getValueByKey(ConstantsUtil.ACCOUNT,this));
        startActivity(intent);
        ActivityUtil.finishAll();
    }

    @Override
    public void changePasswordFail(String msg) {
        UToastUtil.show(this,msg);
    }

    @OnClick({R.id.tv_modify_save,R.id.ll_back})
    public void onClick(View view){
        if (view.getId() == R.id.tv_modify_save){

            if (TextUtils.isEmpty(evPsdOldValue.getText().toString())){
                UToastUtil.show(this,"请输入旧密码");
            }else if (TextUtils.isEmpty(evPsdNewValue.getText().toString())){
                UToastUtil.show(this,"请输入新密码");
            }else if (TextUtils.isEmpty(evPsdSureNewValue.getText().toString())){
                UToastUtil.show(this,"请再次输入新密码");
            }else if (!evPsdNewValue.getText().toString().equals(evPsdSureNewValue.getText().toString())){
                UToastUtil.show(this,"请确认两次新密码输入一致");
            }else {
                mPresenter.changePassword(evPsdOldValue.getText().toString(),evPsdNewValue.getText().toString());
            }

        }else if (view.getId() == R.id.ll_back){
            finish();
        }
    }

}
