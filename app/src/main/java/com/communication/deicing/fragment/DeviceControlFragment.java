package com.communication.deicing.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.communication.deicing.R;
import com.communication.deicing.base.BaseFragment;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.DeicingControlEntity;
import com.communication.deicing.presenter.DeviceControlPresenter;
import com.communication.deicing.util.UToastUtil;
import com.communication.deicing.view.DeviceControlView;
import com.communication.deicing.widget.CommonPopup;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LG
 * on 2021/6/17  11:25
 * Description：
 */
public class DeviceControlFragment extends BaseFragment<DeviceControlView, DeviceControlPresenter> implements DeviceControlView, CommonPopup.PopupClickListener {

    @BindView(R.id.tv_sray_capacity_num)
    TextView tvSrayCapacityNum;
    @BindView(R.id.cb_booster_pump)
    CheckBox cbBoosterPump;
    @BindView(R.id.cb_motorized)
    CheckBox cbMotorized;
    @BindView(R.id.cb_spray_switch)
    CheckBox cbSpraySwitch;
    @BindView(R.id.cl_control)
    ConstraintLayout clControl;
    @BindView(R.id.tv_spray_interva_value)
    TextView tvSprayIntervaValue;
    @BindView(R.id.tv_mode_value)
    TextView tvModeValue;
    @BindView(R.id.cl_spray_interva)
    ConstraintLayout clSprayInterva;
    @BindView(R.id.cl_heat_control)
    ConstraintLayout clHeatControl;
    @BindView(R.id.tv_gear)
    TextView tvGear;
    @BindView(R.id.tv_gear_value)
    TextView tvGearValue;
    @BindView(R.id.tv_spray_capacity_value)
    TextView tvSprayCapacityValue;
    @BindView(R.id.cb_switch)
    CheckBox cbSwitch;

    // 设备序列号
    private String sn;
    // 喷淋间隔时间 秒
    private String spray_interval_time;
    // 控制模式 0:全自动 1:半自动
    private String control_mode;
    // 喷淋量 ml
    private String spray_capacity;
    // 增压泵状态 0:关闭 1:开启
    private String inflator_state;
    // 电动阀状态 0:关闭 1:开启
    private String radiotube_state;
    // 工作状态 0:待机 1:工作中
    private String work_state;
    // 加热档位 1：低档 2：中挡 3.高档
    private String warm_gears;

    private List<String> pipelineStrs;
    private List<String> workModeStr;
    private List<String> gearStr;
    private List<String> sprayCapacityStr;

    //是否开始操作按钮
    private boolean isUpdate = false;
    private String deviceName;
    private DeviceInfoLisener deviceInfoLisener;


    public DeviceControlFragment(String deviceName,DeviceInfoLisener deviceInfoLisener){
        this.deviceName = deviceName;
        this.deviceInfoLisener = deviceInfoLisener;
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_device_control;
    }

    @Override
    public void initEventAndData() {
        pipelineStrs = Arrays.asList(getResources().getStringArray(R.array.pipeline_time));
        workModeStr = Arrays.asList(getResources().getStringArray(R.array.work_mode));
        gearStr = Arrays.asList(getResources().getStringArray(R.array.gear));
        sprayCapacityStr = Arrays.asList(getResources().getStringArray(R.array.spray_capacity));

        cbBoosterPump.setOnCheckedChangeListener(new CheckListener());
        cbMotorized.setOnCheckedChangeListener(new CheckListener());
        cbSpraySwitch.setOnCheckedChangeListener(new CheckListener());
        cbSwitch.setOnCheckedChangeListener(new CheckListener());

        if (deviceName.equals("喷淋抗冰系统")){
            clHeatControl.setVisibility(View.GONE);
        }else if (deviceName.equals("发热电缆抗冰系统")){
            clSprayInterva.setVisibility(View.GONE);
            clControl.setVisibility(View.GONE);
        }else if (deviceName.equals("超薄导电磨耗层抗冰系统")){
            clSprayInterva.setVisibility(View.GONE);
            clControl.setVisibility(View.GONE);
            tvGear.setVisibility(View.GONE);
            tvGearValue.setVisibility(View.GONE);
        }

    }



    @Override
    protected DeviceControlPresenter createPresenter() {
        return new DeviceControlPresenter();
    }


    public void getDeviceControlInfoSuccess(DeicingControlEntity deicingControlEntity,String sn) {

        this.sn = sn;

        inflator_state = deicingControlEntity.getInflator_state() + "";
        radiotube_state = deicingControlEntity.getRadiotube_state() + "";
        work_state = deicingControlEntity.getWork_state() + "";
        control_mode = deicingControlEntity.getControl_mode() +"";

        tvModeValue.setText(workModeStr.get(deicingControlEntity.getControl_mode()));

        if (deicingControlEntity.getControl_mode() == 0){
            clControl.setVisibility(View.GONE);
            clHeatControl.setVisibility(View.GONE);
        }

        isUpdate = false;
        cbSpraySwitch.setChecked(deicingControlEntity.getWork_state() == 0 ? false : true);
        cbMotorized.setChecked(deicingControlEntity.getRadiotube_state() == 0 ? false : true);
        cbBoosterPump.setChecked(deicingControlEntity.getInflator_state() == 0 ? false : true);
        cbSwitch.setChecked(deicingControlEntity.getWork_state() == 0 ? false:true);

        if (deicingControlEntity.getSpray_interval_time() != 0){
            spray_interval_time = deicingControlEntity.getSpray_interval_time()/60 + "";
            if (spray_interval_time.equals("30")){
                tvSprayIntervaValue.setText(pipelineStrs.get(0));
            }else if (spray_interval_time.equals("40")){
                tvSprayIntervaValue.setText(pipelineStrs.get(1));
            }else if (spray_interval_time.equals("50")){
                tvSprayIntervaValue.setText(pipelineStrs.get(2));
            }else if (spray_interval_time.equals("60")){
                tvSprayIntervaValue.setText(pipelineStrs.get(3));
            }
        }

        isUpdate = true;

        tvSrayCapacityNum.setText("共" + deicingControlEntity.getNozzle_number() + "个喷嘴");

        warm_gears = deicingControlEntity.getWarm_gears()+"";
        if (deicingControlEntity.getWarm_gears() == 1){
            tvGearValue.setText(gearStr.get(0));
        }else if (deicingControlEntity.getWarm_gears() == 2){
            tvGearValue.setText(gearStr.get(1));
        }else if (deicingControlEntity.getWarm_gears() == 3){
            tvGearValue.setText(gearStr.get(2));
        }


    }

    private void changeControlInfo(){
        mPresenter.changeDeviceControl(sn,spray_interval_time,control_mode,spray_capacity,inflator_state,radiotube_state,work_state,warm_gears);
    }

    @Override
    public void getDeicingControlSuccess(String msg) {

        UToastUtil.show(getActivity(),msg);
        deviceInfoLisener.deviceInfoSwitch();

    }

    @Override
    public void getDeviceControlFail(String msg) {
        UToastUtil.show(getActivity(),msg);
        deviceInfoLisener.deviceInfoSwitch();
    }

    @OnClick({R.id.tv_spray_interva_value,R.id.tv_mode_value,R.id.tv_gear_value,R.id.tv_spray_capacity_value})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_spray_interva_value:


                showPopup(4,pipelineStrs,tvSprayIntervaValue.getText().toString(),tvSprayIntervaValue);

                break;

            case R.id.tv_mode_value:

                showPopup(5,workModeStr,tvModeValue.getText().toString(),tvModeValue);

                break;

            case R.id.tv_gear_value:

                showPopup(6,gearStr,tvGearValue.getText().toString(),tvGearValue);

                break;

            case R.id.tv_spray_capacity_value:

                showPopup(7,sprayCapacityStr,tvSprayCapacityValue.getText().toString(),tvSprayCapacityValue);

                break;
        }
    }

    private void showPopup(int type,List<String> strings,String str,View view){

        CommonPopup commonPopup = new CommonPopup(getActivity(),type, strings,str,this);

        commonPopup.showAsDropDown(view);
    }

    @Override
    public void itemClick(int type, int position) {
        if (type == 4){
            tvSprayIntervaValue.setText(pipelineStrs.get(position));
            spray_interval_time = Integer.valueOf(pipelineStrs.get(position).substring(0,2)) * 60 + "";
        }else if (type == 5){
            control_mode = position+"";
            tvModeValue.setText(workModeStr.get(position));
            if (position == 0){

                if (deviceName.equals("喷淋抗冰系统")){
                    clControl.setVisibility(View.GONE);
                }else {
                    clHeatControl.setVisibility(View.GONE);
                }
            }else {
                if (deviceName.equals("喷淋抗冰系统")){
                    clControl.setVisibility(View.VISIBLE);
                }else {
                    clHeatControl.setVisibility(View.VISIBLE);
                }
            }
        }else if (type == 6){
            warm_gears = position + 1 +"";
            tvGearValue.setText(gearStr.get(position));
        }else if (type == 7){
            spray_capacity = sprayCapacityStr.get(position).substring(0,sprayCapacityStr.get(position).indexOf("m³"));
            tvSprayCapacityValue.setText(sprayCapacityStr.get(position));
        }
        changeControlInfo();
    }


    private class CheckListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView == cbBoosterPump){
                inflator_state = isChecked == true ? "1":"0";
            }else if (buttonView == cbMotorized){
                radiotube_state = isChecked == true ? "1":"0";
            }else if (buttonView == cbSpraySwitch){
                work_state = isChecked == true ? "1":"0";
            }else if (buttonView == cbSwitch){
                work_state = isChecked == true ? "1":"0";
            }

            if (isUpdate) {
                changeControlInfo();
            }
        }
    }


    public interface DeviceInfoLisener{
        public void deviceInfoSwitch();
    }
}
