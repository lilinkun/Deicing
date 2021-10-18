package com.communication.deicing.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.communication.deicing.R;
import com.communication.deicing.base.BaseActivity;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.presenter.BindPhonePresenter;
import com.communication.deicing.util.ConstantsUtil;
import com.communication.deicing.util.DeicingUtil;
import com.communication.deicing.util.UToastUtil;
import com.communication.deicing.view.BindPhoneView;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LG
 * on 2021/6/18  9:42
 * Description：绑定手机
 */
public class BindPhoneActivity extends BaseActivity<BindPhoneView, BindPhonePresenter> implements BindPhoneView{

    @BindView(R.id.ev_mobile_phone_value)
    EditText evMobilePhoneValue;
    @BindView(R.id.tv_vcode_value)
    TextView tvVCodeValue;

    private String sign;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    public void initView() {

        if (getIntent() != null && getIntent().getStringExtra(ConstantsUtil.PHONE) != null){
            evMobilePhoneValue.setText(getIntent().getStringExtra(ConstantsUtil.PHONE));
        }

    }

    @Override
    protected BindPhonePresenter createPresenter() {
        return new BindPhonePresenter();
    }

    @OnClick({R.id.tv_get_vcode,R.id.tv_modify_save,R.id.ll_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_back:

                finish();

                break;

            case R.id.tv_get_vcode:

                if (TextUtils.isEmpty(evMobilePhoneValue.getText().toString())){
                    UToastUtil.show(this,R.string.input_bind_phone);
                }else if (!DeicingUtil.isMobileNO(evMobilePhoneValue.getText().toString())){
                    UToastUtil.show(this,R.string.input_phone);
                }else {
                    sign = (new Date()).getTime() + "";
                    mPresenter.sendVcode(sign,evMobilePhoneValue.getText().toString());
                }

                break;

            case R.id.tv_modify_save:

                mPresenter.bindphone(sign,evMobilePhoneValue.getText().toString(),tvVCodeValue.getText().toString());

                break;

            default:

                break;

        }
    }

    @Override
    public void sendVcodeSuccess() {
        UToastUtil.show(this,R.string.send_success);
    }

    @Override
    public void sendVcodeFail(String msg) {
        UToastUtil.show(this,msg);
        DeicingUtil.otherLogin(this,msg);
    }

    @Override
    public void bindPhoneSuccess() {
        UToastUtil.show(this,R.string.input_bind_phone_success);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void bindPhoneFail(String msg) {
        UToastUtil.show(this,msg);
        DeicingUtil.otherLogin(this,msg);
    }
}
