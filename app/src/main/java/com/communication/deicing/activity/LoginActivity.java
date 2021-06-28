package com.communication.deicing.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.communication.deicing.R;
import com.communication.deicing.base.BaseActivity;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.callback.JsonCallback;
import com.communication.deicing.callback.LoginCallBack;
import com.communication.deicing.entity.LoginEntity;
import com.communication.deicing.entity.VerificationCodeEntity;
import com.communication.deicing.http.DeicingService;
import com.communication.deicing.util.ConstantsKey;
import com.communication.deicing.util.ConstantsUtil;
import com.communication.deicing.util.DeicingUtil;
import com.communication.deicing.util.MethodsUtil;
import com.communication.deicing.util.PermissUtil;
import com.communication.deicing.util.RSAUtils;
import com.communication.deicing.util.SystemUtil;
import com.communication.deicing.util.UToastUtil;
import com.gyf.immersionbar.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LG
 * on 2021/6/15  10:40
 * Description：
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_verification_code)
    AppCompatImageView ivVerificationCode;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_register_portrol)
    TextView tvRegisterProtrol;

    private VerificationCodeEntity verificationCodeEntity;
    private LoginEntity loginEntity;

    private String sign;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        ImmersionBar.with(this).transparentStatusBar().init();

        if(getIntent() != null &&getIntent().getStringExtra(ConstantsUtil.ACCOUNT) != null){
            etUsername.setText(getIntent().getStringExtra(ConstantsUtil.ACCOUNT));
        }

        String str="登录即代表您已同意<font color='#1B55E2'>《用户注册协议》</font>和<font color='#1B55E2'>《隐私政策》</font>";
        tvRegisterProtrol.setTextSize(14);
        tvRegisterProtrol.setText(Html.fromHtml(str));

        initImage();

        PermissUtil.checkPermissions(this, PermissUtil.appNeedPermissions);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private void initImage(){

        sign = (new Date()).getTime() + "";

        String img_url = DeicingService.PREFIX + "/code?sign=" + sign;

        Picasso.get().load(img_url).placeholder(R.color.white).into(ivVerificationCode);

    }


    @Override
    public void onSuccess(Response response) {
        super.onSuccess(response);

        if (response != null){
            if (response.body() instanceof VerificationCodeEntity){
                verificationCodeEntity = (VerificationCodeEntity) response.body();

                DeicingUtil.setImage(verificationCodeEntity.getResponse(),ivVerificationCode);

            }else if (response.body() instanceof LoginEntity){
                loginEntity = (LoginEntity)response.body();

                if (loginEntity.getCode() == 1111){
                    MethodsUtil.saveKeyValue(ConstantsUtil.AUTHORIZATION,loginEntity.getData().getJwt(), this);
                    MethodsUtil.saveKeyValue(ConstantsUtil.ACCOUNT,etUsername.getText().toString(), this);
                    OkGo.getInstance().getCommonHeaders().put(ConstantsUtil.AUTHORIZATION, loginEntity.getData().getJwt());
                    Intent intent = new Intent();
                    intent.setClass(this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        }

    }

    @Override
    public void onFail(String base) {
        if (!TextUtils.isEmpty(base)){
            super.onFail(base);
            if (base.contains("验证码")){
                initImage();
            }
            UToastUtil.show(this,base);
        }
    }

    @OnClick({R.id.iv_verification_code,R.id.btn_login})
    public void onClick(View view){
        if (view.getId() == R.id.iv_verification_code){
//            OkGo.get(CuringService.PREFIX + "/captchaImage").execute(new JsonCallback(VerificationCodeEntity.class, this));
            initImage();
        }else if (view.getId() == R.id.btn_login){
            if (!DeicingUtil.etIsnull(etUsername)){
                UToastUtil.show(this,"请输入用户名");
            }else if (!DeicingUtil.etIsnull(etPassword)){
                UToastUtil.show(this,"请输入密码");
            }else if (!DeicingUtil.etIsnull(etVerificationCode)){
                UToastUtil.show(this,"请输入验证码");
            }else {

                String password = etPassword.getText().toString();

                try {
//                    String psd = Base64.encodeToString(RSAUtils.encryptByPublicKey(password, ConstantsKey.publicKey),Base64.NO_WRAP);
                    /*byte[] sy2=RSAUtils.decryptByPrivateKey(Base64.decode(psd,Base64.NO_WRAP), ConstantsKey.privateKey);
                    String outputpwd = new String(sy2);*/

                    String psd = Base64.encodeToString(password.getBytes("utf-8"),Base64.NO_WRAP);
                    String modelId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

                    HashMap<String, String> params = new HashMap<>();
                    params.put("code", etVerificationCode.getText().toString());
                    params.put("pwd", psd);
                    params.put("loginName", etUsername.getText().toString());
                    params.put("sign", sign);
                    params.put("serialNumber", modelId);
                    params.put("phoneBrand", SystemUtil.getDeviceBrand());
                    params.put("phoneModel", SystemUtil.getSystemModel());
                    params.put("androidVersion", SystemUtil.getSystemVersion());
                    params.put("versionCode", SystemUtil.getVersionCode(this));
                    params.put("versionName", SystemUtil.getVersionName(this));
                    params.put("packageName", getPackageName());
                    JSONObject jsonObject = new JSONObject(params);


                    OkGo.post(DeicingService.PREFIX + "/login")
                            .upJson(jsonObject)
                            .execute(new LoginCallBack(LoginEntity.class, this));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissUtil.PERMISSON_REQUESTCODE){
            if (grantResults[0] == PackageManager.PERMISSION_DENIED){
                finish();
            }
        }
    }

}
