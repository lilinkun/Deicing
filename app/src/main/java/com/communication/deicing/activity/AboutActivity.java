package com.communication.deicing.activity;

import android.view.View;

import com.communication.deicing.R;
import com.communication.deicing.base.BaseActivity;
import com.communication.deicing.base.BasePresenter;

import butterknife.OnClick;

/**
 * Created by LG
 * on 2021/6/24  11:21
 * Description：关于app
 */
public class AboutActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {



    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view){
        if (view.getId() == R.id.ll_back){
            finish();
        }
    }

}
