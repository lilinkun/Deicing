package com.communication.deicing.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.communication.deicing.R;
import com.communication.deicing.base.BaseActivity;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.presenter.BindEmailPresenter;
import com.communication.deicing.util.ConstantsUtil;
import com.communication.deicing.util.DeicingUtil;
import com.communication.deicing.util.UToastUtil;
import com.communication.deicing.view.BindEmailView;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LG
 * on 2021/6/18  14:15
 * Description：
 */
public class BindEmailActivity extends BaseActivity<BindEmailView, BindEmailPresenter> implements BindEmailView {

    @BindView(R.id.tv_email_value)
    EditText etEmailValue;
    @BindView(R.id.tv_get_vcode)
    TextView tvGetCcode;
    @BindView(R.id.tv_vcode_value)
    TextView tvVCodeValue;

    private String sign;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_email;
    }

    @Override
    public void initView() {

        if (getIntent() != null && getIntent().getStringExtra(ConstantsUtil.EMAIL) != null){
            etEmailValue.setText(getIntent().getStringExtra(ConstantsUtil.EMAIL));
        }

    }

    @OnClick({R.id.tv_get_vcode,R.id.tv_modify_setting,R.id.ll_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_get_vcode:

                if (TextUtils.isEmpty(etEmailValue.getText().toString())){
                    UToastUtil.show(this,"请输入需要绑定的邮箱");
                }else if (!DeicingUtil.isEmail(etEmailValue.getText().toString())){
                    UToastUtil.show(this,"请输入正确的邮箱");
                }else {
                    sign = (new Date()).getTime() + "";
                    mPresenter.sendVcode(sign,etEmailValue.getText().toString());
                }

                break;

            case R.id.tv_modify_setting:

                mPresenter.bindEmail(sign,etEmailValue.getText().toString(),tvVCodeValue.getText().toString());

                break;

            case R.id.ll_back:

                finish();

                break;

            default:

                break;
        }
    }

    @Override
    protected BindEmailPresenter createPresenter() {
        return new BindEmailPresenter();
    }

    @Override
    public void sendVcodeSuccess() {
        UToastUtil.show(this,"发送成功");
    }

    @Override
    public void sendVcodeFail(String msg) {
        UToastUtil.show(this,msg);
    }

    @Override
    public void bindEmailSuccess() {
        UToastUtil.show(this,"绑定邮箱成功");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void bindEmailFail(String msg) {
        UToastUtil.show(this,msg);
    }
}
