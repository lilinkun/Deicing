package com.communication.deicing.fragment;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.communication.deicing.R;
import com.communication.deicing.adapter.PipelineAdapter;
import com.communication.deicing.base.BaseFragment;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.DeicingControlEntity;
import com.communication.deicing.entity.MonitorDataEntity;
import com.communication.deicing.entity.MonitorEntity;
import com.communication.deicing.presenter.StatusMonitorPresenter;
import com.communication.deicing.util.DeicingUtil;
import com.communication.deicing.util.RoadStatusEnum;
import com.communication.deicing.util.UToastUtil;
import com.communication.deicing.view.StatusMonitorView;
import com.communication.deicing.widget.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by LG
 * on 2021/6/17  11:21
 * Description：
 */
public class StatusMonitorFragment extends BaseFragment<StatusMonitorView, StatusMonitorPresenter> implements StatusMonitorView{

    @BindView(R.id.tv_road_temp)
    TextView tvRoadTemp;
    @BindView(R.id.tv_humidity)
    TextView tvHumidity;
    @BindView(R.id.tv_wind_speed)
    TextView tvWindSpeed;
    @BindView(R.id.tv_wind_direction)
    TextView tvWindDirection;
    @BindView(R.id.tv_ice_thickness)
    TextView tvIceThickness;
    @BindView(R.id.tv_snow_thickness)
    TextView tvSnowThickness;
    @BindView(R.id.tv_ponding)
    TextView tvPonding;
    @BindView(R.id.tv_kpa)
    TextView tvKpa;
    @BindView(R.id.tv_temp)
    TextView tvTemp;
    @BindView(R.id.tv_road_status)
    TextView tvRoadStatus;
    @BindView(R.id.tv_rainfall)
    TextView tvRainfall;
    @BindView(R.id.tv_wet_coefficient)
    TextView tvWetCoefficient;
    @BindView(R.id.tv_liquid_tank_level_value)
    TextView tvLiquidTankLevel;
    @BindView(R.id.tv_inflator_state_value)
    TextView tvInflatorState;
    @BindView(R.id.tv_motorized_state_value)
    TextView tvMotorizedState;
    @BindView(R.id.tv_radiotube_state_value)
    TextView tvRadiotubeState;
    @BindView(R.id.tv_heating_power)
    TextView tvHeatingPower;
    @BindView(R.id.tv_total_power)
    TextView tvTotalPower;
    @BindView(R.id.ll_ice_thickness)
    LinearLayout llIceThickness;
    @BindView(R.id.ll_unspray)
    LinearLayout llUnspray;
    @BindView(R.id.cl_spray)
    ConstraintLayout clSpray;
    @BindView(R.id.tv_main_pipeline_kpa_value)
    TextView tvMainPipelineKpa;
    @BindView(R.id.rv_kap)
    RecyclerView rvKap;

    private String sn;
    private String deviceName;
    private PipelineAdapter pipelineAdapter;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_status_monitor;
    }

    public StatusMonitorFragment(String sn,String deviceName){
        this.sn = sn;
        this.deviceName = deviceName;
    }

    @Override
    public void initEventAndData() {
        mPresenter.getMonitorWeatherInfo(sn);

        if (!deviceName.equals("喷淋抗冰系统")){
            clSpray.setVisibility(View.GONE);
        }else {
            llUnspray.setVisibility(View.GONE);
        }

        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        rvKap.setLayoutManager(linearLayoutManager);

        pipelineAdapter = new PipelineAdapter(R.layout.adapter_pipeline,null);

        rvKap.setAdapter(pipelineAdapter);

    }

    @Override
    protected StatusMonitorPresenter createPresenter() {
        return new StatusMonitorPresenter();
    }

    @Override
    public void getDataSuccess(MonitorEntity monitorEntity) {
        tvRoadTemp.setText(monitorEntity.getPavement_data().getRoadTemperature() +"℃");
        tvHumidity.setText(String.valueOf(monitorEntity.getWeather_data().getHumidity()) +"%");
        tvWindSpeed.setText(String.valueOf(monitorEntity.getWeather_data().getWindForce()) +"m/s");
        tvWindDirection.setText(String.valueOf(monitorEntity.getWeather_data().getWindDirection())+"°");
        tvIceThickness.setText(String.valueOf(monitorEntity.getPavement_data().getIceThickness()));
        tvWetCoefficient.setText(monitorEntity.getPavement_data().getSlipperyDegree()+"");
        tvSnowThickness.setText(String.valueOf(monitorEntity.getPavement_data().getSnowThickness()));
        tvPonding.setText(String.valueOf(monitorEntity.getPavement_data().getPonding()));
        tvKpa.setText(String.valueOf(monitorEntity.getWeather_data().getKpa()));
        tvRainfall.setText(String.valueOf(monitorEntity.getWeather_data().getRainfall()));
        tvTemp.setText(monitorEntity.getWeather_data().getTemperature() +"℃");

        String str = RoadStatusEnum.getVal(monitorEntity.getPavement_data().getRoadInfo());

        tvRoadStatus.setText(TextUtils.isEmpty(str) ? "qita" : str);
    }

    public void setDeviceData(DeicingControlEntity deicingControlEntity){

        tvLiquidTankLevel.setText(deicingControlEntity.getLiquid_tank_level()+"mm");
        if (deicingControlEntity.getInflator_state() == 0) {
            tvInflatorState.setText("关闭");
        }else {
            tvInflatorState.setText("开启");
        }
        if (deicingControlEntity.getRadiotube_state() == 0) {
            tvMotorizedState.setText("关闭");
        }else {
            tvMotorizedState.setText("开启");
        }
        if (deicingControlEntity.getMotorized_state() == 0) {
            tvRadiotubeState.setText("关闭");
        }else {
            tvRadiotubeState.setText("开启");
        }

        if(deviceName.equals("超薄导电磨耗层抗冰系统")){
            tvHeatingPower.setText(deicingControlEntity.getUnit_heating_zone_power() +"w/㎡");
        }else if (deviceName.equals("发热电缆抗冰系统")){
            tvHeatingPower.setText(deicingControlEntity.getUnit_heating_zone_power() +"w/m");
        }

        tvTotalPower.setText(deicingControlEntity.getTotal_power() + "kw");
        tvMainPipelineKpa.setText(deicingControlEntity.getMain_pipeline_kpa()+"mPa");

        List arrayList = new ArrayList();

        if (deicingControlEntity.getBranch_pipeline_kpa() != null && !TextUtils.isEmpty(deicingControlEntity.getBranch_pipeline_kpa())){
            if (deicingControlEntity.getBranch_pipeline_kpa().contains(",")){
                arrayList = Arrays.asList(deicingControlEntity.getBranch_pipeline_kpa().split(","));
            }else {
                arrayList.add(deicingControlEntity.getBranch_pipeline_kpa());
            }

            pipelineAdapter.setNewInstance(arrayList);
        }

    }

    @Override
    public void getDataFail(String msg) {
        UToastUtil.show(getActivity(),msg);
        DeicingUtil.otherLogin(getActivity(),msg);
    }
}
