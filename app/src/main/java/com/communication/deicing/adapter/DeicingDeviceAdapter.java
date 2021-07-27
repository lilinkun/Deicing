package com.communication.deicing.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.communication.deicing.R;
import com.communication.deicing.activity.MapActivity;
import com.communication.deicing.activity.MonitorActivity;
import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.util.ConstantsUtil;
import com.communication.deicing.util.DeicingUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG
 * on 2021/6/16  15:33
 * Description：
 */
public class DeicingDeviceAdapter extends BaseQuickAdapter<DeicingDeviceEntity, BaseViewHolder>  implements LoadMoreModule {

    Fragment fragment;

    public DeicingDeviceAdapter(int layoutResId, List<DeicingDeviceEntity> deicingDeviceEntities, Fragment fragment) {
        super(layoutResId,deicingDeviceEntities);
        this.fragment = fragment;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, DeicingDeviceEntity deicingDeviceEntity) {

        baseViewHolder.setText(R.id.tv_highways, deicingDeviceEntity.getHighways());

        baseViewHolder.setText(R.id.tv_type_value, DeicingUtil.getSystemName(deicingDeviceEntity.getDeicing_info().getType()));
        if(deicingDeviceEntity.getDeicing_info().getType() == 1) {
            if (deicingDeviceEntity.getDeicing_control().getLast_spray_time() !=null) {
                baseViewHolder.setText(R.id.tv_last_operate_time, "上次喷淋时间" );
                baseViewHolder.setText(R.id.tv_last_operate_time_value, deicingDeviceEntity.getDeicing_control().getLast_spray_time());
            }
        }else if(deicingDeviceEntity.getDeicing_info().getType() == 2) {
            if (deicingDeviceEntity.getDeicing_control().getLast_warm_time() !=null) {
                baseViewHolder.setText(R.id.tv_last_operate_time, "上次加热时间");
                baseViewHolder.setText(R.id.tv_last_operate_time_value, deicingDeviceEntity.getDeicing_control().getLast_warm_time());
            }
        }else if (deicingDeviceEntity.getDeicing_info().getType() == 3) {
            if (deicingDeviceEntity.getDeicing_control().getLast_warm_time() !=null) {
                baseViewHolder.setText(R.id.tv_last_operate_time, "上次加热时间");
                baseViewHolder.setText(R.id.tv_last_operate_time_value, deicingDeviceEntity.getDeicing_control().getLast_warm_time());
            }
        }

        baseViewHolder.setText(R.id.tv_device_id_value, deicingDeviceEntity.getId());

        if (deicingDeviceEntity.getState() == 0){
            baseViewHolder.setImageResource(R.id.iv_status,R.drawable.ic_online);
        }else if (deicingDeviceEntity.getState() == 1){
            baseViewHolder.setImageResource(R.id.iv_status,R.drawable.ic_offline);
        }

        if (deicingDeviceEntity.getDeicing_control().getWork_state() == 0){
            baseViewHolder.setText(R.id.tv_work_status_value,"待机");
        }else if (deicingDeviceEntity.getDeicing_control().getWork_state() == 1){
            baseViewHolder.setText(R.id.tv_work_status_value,"工作中");
        }

        if (deicingDeviceEntity.getDeicing_control().getControl_mode() == 0){
            baseViewHolder.setText(R.id.tv_work_mode_value,"全自动模式");
        }else if (deicingDeviceEntity.getDeicing_control().getControl_mode() == 1){
            baseViewHolder.setText(R.id.tv_work_mode_value,"半自动模式");
        }

        ImageView ivLocation = baseViewHolder.getView(R.id.iv_location);

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), MapActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsUtil.DEVICE,deicingDeviceEntity);
                intent.putExtra(ConstantsUtil.DEICING,bundle);

                fragment.startActivityForResult(intent,ConstantsUtil.UPDATEDATE);
            }
        });

        TextView tvMonitor = baseViewHolder.getView(R.id.tv_device_monitor);
        tvMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                Bundle bundle = new Bundle();

                bundle.putSerializable(ConstantsUtil.DEVICE,deicingDeviceEntity);

                intent.putExtra(ConstantsUtil.MONITOR,bundle);

                intent.setClass(getContext(), MonitorActivity.class);

                fragment.startActivityForResult(intent,ConstantsUtil.UPDATEDATE);
            }
        });

    }
}
