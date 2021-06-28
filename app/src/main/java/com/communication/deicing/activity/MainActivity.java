package com.communication.deicing.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;

import com.communication.deicing.R;
import com.communication.deicing.adapter.PageAdapter;
import com.communication.deicing.base.BaseActivity;
import com.communication.deicing.base.BaseFragment;
import com.communication.deicing.entity.TabEntity;
import com.communication.deicing.entity.UpdateBean;
import com.communication.deicing.entity.UpdateVersionBean;
import com.communication.deicing.fragment.HomeFragment;
import com.communication.deicing.fragment.MeFragment;
import com.communication.deicing.presenter.MainPresenter;
import com.communication.deicing.service.UpdateService;
import com.communication.deicing.util.ActivityUtil;
import com.communication.deicing.util.DeicingUtil;
import com.communication.deicing.util.SystemUtil;
import com.communication.deicing.util.UToastUtil;
import com.communication.deicing.view.MainView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.ctl_main)
    CommonTabLayout commonTablayout;

    private ArrayList<BaseFragment> fragmentList;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int[] mIconUnselectIds = {
            R.drawable.ic_home_control_nor, R.drawable.ic_home_me_nor};
    private int[] mIconSelectIds = {
            R.drawable.ic_home_control_sel, R.drawable.ic_home_me_sel};

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        ActivityUtil.addActivity(this);

        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new MeFragment());

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), fragmentList);
        vpMain.setAdapter(pageAdapter);

        for (int i = 0; i < fragmentList.size(); i++) {
            mTabEntities.add(new TabEntity(getResources().getStringArray(R.array.main_activity)[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        bindTab();

//        mPresenter.checkVersionUpdate();

    }

    private void bindTab() {
        commonTablayout.setTabData(mTabEntities);
        commonTablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpMain.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                }
            }
        });

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTablayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vpMain.setCurrentItem(0);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void getUpdateVersionDataSuccess(UpdateVersionBean updateBean) {

        int rt = updateBean.getVersion().compareTo(SystemUtil.getVersionName(this));

        if (rt > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this).setMessage("请升级更新app").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    /*mApkUrl = bean1.getInstall_url();
                    deleteApkFile();
                    downloadApkFile(dialog);*/

                    UpdateService.startAction(MainActivity.this, updateBean.getDownloadPath(), updateBean.getAppName());

                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
//            builder.create().setCanceledOnTouchOutside(false);
            //  builder.setCancelable(false);
            /*builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    finish();
                }
            });*/
            builder.show();
        }
    }

    @Override
    public void getUpdateDataSuccess(UpdateBean updateBean) {
        if (updateBean.buildVersion > Double.valueOf(SystemUtil.getVersionName(this))) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this).setMessage("请升级更新app").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    /*mApkUrl = bean1.getInstall_url();
                    deleteApkFile();
                    downloadApkFile(dialog);*/

                    UpdateService.startAction(MainActivity.this, updateBean.downloadURL, updateBean.buildName);

                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
//            builder.create().setCanceledOnTouchOutside(false);
            //  builder.setCancelable(false);
            /*builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    finish();
                }
            });*/
            builder.show();
        }
    }

    @Override
    public void getDataFail(String msg) {
        UToastUtil.show(this, msg);
    }

}