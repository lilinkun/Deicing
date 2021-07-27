package com.communication.deicing.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.communication.deicing.R;
import com.communication.deicing.base.BaseActivity;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.entity.DeicingDeviceListEntity;
import com.communication.deicing.presenter.MapPresenter;
import com.communication.deicing.util.ConstantsUtil;
import com.communication.deicing.util.DeicingUtil;
import com.communication.deicing.util.PermissUtil;
import com.communication.deicing.util.UToastUtil;
import com.communication.deicing.util.XLogUtils;
import com.communication.deicing.view.MapLocationView;
import com.gyf.immersionbar.ImmersionBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by LG
 * on 2021/6/15  16:23
 * Description：
 */
public class MapActivity extends BaseActivity<MapLocationView, MapPresenter> implements MapLocationView{


    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.cl_map_bottom)
    ConstraintLayout clMapBottom;
    @BindView(R.id.tv_road)
    TextView tvRoadName;
    @BindView(R.id.tv_system_name)
    TextView tvSystemName;

    private BaiduMap mBaiduMap;
    private LocationClient mLocClient;
    // 是否首次定位
    boolean isFirstLoc = true;
    private BitmapDescriptor bitmapA = BitmapDescriptorFactory.fromResource(R.drawable.ic_freeze_online);
    private BitmapDescriptor bitmapB = BitmapDescriptorFactory.fromResource(R.drawable.ic_freeze_offline);
    private BitmapDescriptor bitmapC = BitmapDescriptorFactory.fromResource(R.drawable.ic_freeze_blue);
    private BitmapDescriptor bitmapD = BitmapDescriptorFactory.fromResource(R.drawable.ic_freeze_yellow);
    private BitmapDescriptor bitmapE = BitmapDescriptorFactory.fromResource(R.drawable.ic_freeze_orange);
    private BitmapDescriptor bitmapF = BitmapDescriptorFactory.fromResource(R.drawable.ic_freeze_red);
    private BitmapDescriptor bitmapG = BitmapDescriptorFactory.fromResource(R.drawable.ic_freeze_black);
    private Marker mMarker;
    private MarkerOptions markerOption;
    private DeicingDeviceEntity deicingDeviceEntity;
    private List<DeicingDeviceEntity> deicingDeviceEntities;

    double latitude = 0;
    double longitude = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    public void initView() {

        ImmersionBar.with(this).transparentBar().init();

        if (getIntent() != null && getIntent().getBundleExtra(ConstantsUtil.DEICING) != null){
            Bundle bundle = getIntent().getBundleExtra(ConstantsUtil.DEICING);
            deicingDeviceEntity = (DeicingDeviceEntity)bundle.getSerializable(ConstantsUtil.DEVICE);
            latitude = deicingDeviceEntity.getLat();
            longitude = deicingDeviceEntity.getLng();
            clMapBottom.setVisibility(View.VISIBLE);
            tvRoadName.setText(deicingDeviceEntity.getHighways());
            tvSystemName.setText(DeicingUtil.getSystemName(deicingDeviceEntity.getDeicing_info().getType()));
        }else {
            mPresenter.getDeviceList(1,100,"","","");
            loading(true);
        }


        mBaiduMap = mMapView.getMap();
        final MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, true, null));
        mMapView.showZoomControls(false);

        if(PermissUtil.checkPermissions(this, PermissUtil.needPermissions)){
            // 定位初始化
            initLocation();
        }

        initListener();
    }

    /**
     * 定位初始化
     */
    public void initLocation(){
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        MyLocationListenner myListener = new MyLocationListenner();
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        // 打开gps
        option.setOpenGps(true);
        // 设置坐标类型
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        if (deicingDeviceEntity != null) {
            showMark(deicingDeviceEntity);
        }
    }

    @OnClick({R.id.tv_device_monitor})
    public void onClick(View view){
        if (view.getId() == R.id.tv_device_monitor){
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable(ConstantsUtil.DEVICE,deicingDeviceEntity);
            intent.putExtra(ConstantsUtil.MONITOR,bundle);
            intent.setClass(this, MonitorActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void getDeicingDeviceSuccess(DeicingDeviceListEntity<List<DeicingDeviceEntity>> arrayListDeicingDeviceListEntity) {
        deicingDeviceEntities = arrayListDeicingDeviceListEntity.getData();
        loading(false);
        for (int i = 0; i < deicingDeviceEntities.size(); i++) {
            showMark(deicingDeviceEntities.get(i));
        }
    }

    @Override
    public void getDeicingDeviceFail(String msg) {
        loading(false);
        UToastUtil.show(this,msg);
        DeicingUtil.otherLogin(this,msg);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

    /**
     * 显示mark点
     * @param deicingDeviceEntitie
     */
    private void showMark(DeicingDeviceEntity deicingDeviceEntitie){
        if (deicingDeviceEntitie.getState() == 0){
            double freezing_probability = deicingDeviceEntitie.getAdditionalData().getFreezing_probability();
            if (freezing_probability == 0) {
                initMarker(deicingDeviceEntitie.getLat(), deicingDeviceEntitie.getLng(), 1);
            }else if ( 0 < freezing_probability && freezing_probability < 0.1){
                initMarker(deicingDeviceEntitie.getLat(), deicingDeviceEntitie.getLng(), 3);
            }else if ( 0.1 <= freezing_probability && freezing_probability < 0.5){
                initMarker(deicingDeviceEntitie.getLat(), deicingDeviceEntitie.getLng(), 4);
            }else if ( 0.5 <= freezing_probability && freezing_probability < 0.9){
                initMarker(deicingDeviceEntitie.getLat(), deicingDeviceEntitie.getLng(), 5);
            }else if ( 0.9 <= freezing_probability && freezing_probability < 1.0){
                initMarker(deicingDeviceEntitie.getLat(), deicingDeviceEntitie.getLng(), 6);
            }else if (1.0 <= freezing_probability){
                initMarker(deicingDeviceEntitie.getLat(), deicingDeviceEntitie.getLng(), 7);
            }
        }else if (deicingDeviceEntitie.getState() == 1){
            initMarker(deicingDeviceEntitie.getLat(),deicingDeviceEntitie.getLng(),2);
        }
    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // MapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }

            if (latitude == 0){
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())// 设置定位数据的精度信息，单位：米
                    .direction(location.getDirection()) // 此处设置开发者获取到的方向信息，顺时针0-360
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            // 设置定位数据, 只有先允许定位图层后设置数据才会生效
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng latLng = new LatLng(latitude, longitude);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(latLng).zoom(10.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            }
            if (mMarker != null){
//                mMarker.setPosition(new LatLng(latitude, longitude));
            }
        }
    }

    private void initMarker(double latitude,double longitude,int mark){
        LatLng latLng = new LatLng(latitude, longitude);
        addMarker(latLng,mark);

    }

    private void initListener(){

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {

                clMapBottom.setVisibility(View.VISIBLE);

                if (deicingDeviceEntities != null) {
                    for (int i = 0; i < deicingDeviceEntities.size(); i++) {

                        if (marker.getPosition().latitude == deicingDeviceEntities.get(i).getLat() && marker.getPosition().longitude == deicingDeviceEntities.get(i).getLng()){
                            deicingDeviceEntity = deicingDeviceEntities.get(i);
                            clMapBottom.setVisibility(View.VISIBLE);
                            tvRoadName.setText(deicingDeviceEntity.getHighways());
                            tvSystemName.setText(DeicingUtil.getSystemName(deicingDeviceEntity.getDeicing_info().getType()));
                        }

                    }
                }


                return false;
            }
        });

    }

    /**
     * 添加marker
     *
     * @param latLng 经纬度
     */
    public void addMarker(LatLng latLng,int mark){
        if (latLng.latitude == 0.0 || latLng.longitude == 0.0){
            return;
        }
        if (mark == 1) {
            markerOption = new MarkerOptions().position(latLng).yOffset(30).icon(bitmapA).draggable(true);
        }else if (mark == 2){
            markerOption = new MarkerOptions().position(latLng).yOffset(30).icon(bitmapB).draggable(true);
        }else if (mark == 3){
            markerOption = new MarkerOptions().position(latLng).yOffset(30).icon(bitmapC).draggable(true);
        }else if (mark == 4){
            markerOption = new MarkerOptions().position(latLng).yOffset(30).icon(bitmapD).draggable(true);
        }else if (mark == 5){
            markerOption = new MarkerOptions().position(latLng).yOffset(30).icon(bitmapE).draggable(true);
        }else if (mark == 6){
            markerOption = new MarkerOptions().position(latLng).yOffset(30).icon(bitmapF).draggable(true);
        }else if (mark == 7){
            markerOption = new MarkerOptions().position(latLng).yOffset(30).icon(bitmapG).draggable(true);
        }
        mMarker = (Marker) mBaiduMap.addOverlay(markerOption);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        bitmapA.recycle();
        if (mLocClient != null) {
            // 退出时销毁定位
            mLocClient.stop();
        }
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        // 在activity执行onDestroy时必须调用mMapView.onDestroy()
        mMapView.onDestroy();
    }

    @Override
    protected MapPresenter createPresenter() {
        return new MapPresenter();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissUtil.PERMISSON_REQUESTCODE){
            if (grantResults[0] == PackageManager.PERMISSION_DENIED){
                finish();
            }else {
                // 定位初始化
                initLocation();
            }
        }
    }
}
