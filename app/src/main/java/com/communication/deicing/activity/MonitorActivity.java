package com.communication.deicing.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.communication.deicing.R;
import com.communication.deicing.adapter.TabPageAdapter;
import com.communication.deicing.base.BaseActivity;
import com.communication.deicing.base.BaseFragment;
import com.communication.deicing.entity.DeicingControlEntity;
import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.fragment.DeviceControlFragment;
import com.communication.deicing.fragment.StatusMonitorFragment;
import com.communication.deicing.presenter.MonitorPresenter;
import com.communication.deicing.util.ConstantsUtil;
import com.communication.deicing.util.DeicingUtil;
import com.communication.deicing.util.UToastUtil;
import com.communication.deicing.view.MonitorView;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LG
 * on 2021/6/17  11:17
 * Description：
 */
public class MonitorActivity extends BaseActivity<MonitorView, MonitorPresenter> implements MonitorView, DeviceControlFragment.DeviceInfoLisener {

    @BindView(R.id.vp_monitor)
    ViewPager vpMonitor;
    @BindView(R.id.stl_monitor)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.tv_highways_value)
    TextView tvHighways;
    @BindView(R.id.tv_device_id_value)
    TextView tvDeviceId;
    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;

    private List<BaseFragment> fragmentList;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private String sn;

    private StatusMonitorFragment statusMonitorFragment ;
    private DeviceControlFragment deviceControlFragment ;


    @Override
    public int getLayoutId() {
        return R.layout.activity_monitor;
    }

    @Override
    public void initView() {

        if(getIntent() != null && getIntent().getBundleExtra(ConstantsUtil.MONITOR) != null){
            Bundle bundle = getIntent().getBundleExtra(ConstantsUtil.MONITOR);
            DeicingDeviceEntity deicingDeviceEntity = (DeicingDeviceEntity)bundle.getSerializable(ConstantsUtil.DEVICE);
            tvHighways.setText(deicingDeviceEntity.getHighways());
            tvDeviceId.setText(deicingDeviceEntity.getId());
            tvDeviceName.setText(DeicingUtil.getSystemName(deicingDeviceEntity.getDeicing_info().getType()));
            sn = deicingDeviceEntity.getSn();
        }

        mPresenter.getDeviceControlInfo(sn);

        statusMonitorFragment = new StatusMonitorFragment(sn,tvDeviceName.getText().toString());
        deviceControlFragment = new DeviceControlFragment(tvDeviceName.getText().toString(),this);

        fragmentList = new ArrayList<>();
        fragmentList.add(statusMonitorFragment);
        fragmentList.add(deviceControlFragment);

        List<String> list = Arrays.asList(getResources().getStringArray(R.array.monitor));

        TabPageAdapter pageAdapter = new TabPageAdapter(getSupportFragmentManager(), fragmentList,list);
        vpMonitor.setAdapter(pageAdapter);

        bindTab();
    }

    public void putRequest(){

    }


    private void bindTab() {
        slidingTabLayout.setViewPager(vpMonitor);
        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpMonitor.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                }
            }
        });

        vpMonitor.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                slidingTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vpMonitor.setCurrentItem(0);
    }

    @Override
    protected MonitorPresenter createPresenter() {
        return new MonitorPresenter();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_back:
                finish();
                break;

            default:

                break;
        }
    }

    @Override
    public void getDeviceControlInfoSuccess(DeicingControlEntity deicingControlEntity) {
        deviceControlFragment.getDeviceControlInfoSuccess(deicingControlEntity,sn);
        statusMonitorFragment.setDeviceData(deicingControlEntity);
    }

    @Override
    public void getDeviceControlInfoFail(String msg) {
        UToastUtil.show(this,msg);
    }

    @Override
    public void deviceInfoSwitch() {
        mPresenter.getDeviceControlInfo(sn);
    }
}
