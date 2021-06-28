package com.communication.deicing.fragment;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.communication.deicing.R;
import com.communication.deicing.activity.AboutActivity;
import com.communication.deicing.activity.ChangePasswordActivity;
import com.communication.deicing.activity.LoginActivity;
import com.communication.deicing.activity.MapActivity;
import com.communication.deicing.activity.PersonalInfoActivity;
import com.communication.deicing.base.BaseFragment;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.AccountEntity;
import com.communication.deicing.presenter.MePresenter;
import com.communication.deicing.util.ConstantsUtil;
import com.communication.deicing.util.MethodsUtil;
import com.communication.deicing.util.UToastUtil;
import com.communication.deicing.view.MeView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LG
 * on 2021/6/10
 * Description：
 */
public class MeFragment extends BaseFragment<MeView, MePresenter> implements MeView{

    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_personal)
    TextView tvPersonal;

    private AccountEntity accountEntity;
    private final int PERSONFORRESULT = 0x212;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initEventAndData() {

        mPresenter.getPersonalInfo();

    }

    @OnClick({R.id.tv_my_info,R.id.tv_exit_login,R.id.tv_change_psd,R.id.tv_about})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_my_info:

                Intent intent = new Intent();
                intent.putExtra(ConstantsUtil.PERSONAL,accountEntity);
                intent.setClass(getActivity(), PersonalInfoActivity.class);
                startActivityForResult(intent,PERSONFORRESULT);

                break;

            case R.id.tv_exit_login:

                MethodsUtil.saveKeyValue(ConstantsUtil.AUTHORIZATION, "", getActivity());
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), LoginActivity.class);
                startActivity(intent1);
                getActivity().finish();

                break;

            case R.id.tv_change_psd:

                Intent intent2 = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent2);

                break;

            case R.id.tv_about:

                Intent intent3 = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent3);

                break;

            default:

                break;
        }
    }


    @Override
    protected MePresenter createPresenter() {
        return new MePresenter();
    }


    @Override
    public void getPersonalInfoSuccess(AccountEntity accountEntity) {

        this.accountEntity = accountEntity;

        if (!TextUtils.isEmpty(accountEntity.getAccountInfo().getName())) {
            tvAccount.setText(accountEntity.getAccountInfo().getName());
            tvPersonal.setText(accountEntity.getAccountInfo().getName().substring(0,1));
        }
        if (!TextUtils.isEmpty(accountEntity.getAccountInfo().getCompany()) && !TextUtils.isEmpty(accountEntity.getAccountInfo().getJob())) {
            tvCompany.setText(accountEntity.getAccountInfo().getCompany() + "(" + accountEntity.getAccountInfo().getJob() + ")");
        }else if (!TextUtils.isEmpty(accountEntity.getAccountInfo().getCompany())){
            tvCompany.setText(accountEntity.getAccountInfo().getCompany());
        }else if (!TextUtils.isEmpty(accountEntity.getAccountInfo().getJob())){
            tvCompany.setText(accountEntity.getAccountInfo().getJob());
        }else {
            tvCompany.setVisibility(View.GONE);
        }
    }

    @Override
    public void getPersonalInfoFail(String msg) {
        UToastUtil.show(getActivity(),msg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERSONFORRESULT &&resultCode == Activity.RESULT_OK){
            mPresenter.getPersonalInfo();
        }
    }
}
