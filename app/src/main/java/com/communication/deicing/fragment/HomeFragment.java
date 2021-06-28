package com.communication.deicing.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.communication.deicing.R;
import com.communication.deicing.activity.LoginActivity;
import com.communication.deicing.activity.MapActivity;
import com.communication.deicing.adapter.DeicingDeviceAdapter;
import com.communication.deicing.adapter.HomePopAdapter;
import com.communication.deicing.base.BaseFragment;
import com.communication.deicing.base.BasePresenter;
import com.communication.deicing.entity.DeicingDeviceEntity;
import com.communication.deicing.entity.DeicingDeviceListEntity;
import com.communication.deicing.presenter.HomePresenter;
import com.communication.deicing.util.UToastUtil;
import com.communication.deicing.view.HomeView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.internal.util.ArrayListSupplier;


/**
 * Created by LG
 * on 2021/6/10
 * Description：
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView{

    @BindView(R.id.rv_deicing)
    RecyclerView rvDeicing;
    @BindView(R.id.tv_road_id)
    TextView tvRoadId;
    @BindView(R.id.tv_road_section)
    TextView tvRoadSection;
    @BindView(R.id.tv_road_mileage)
    TextView tvRoadMileage;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private int pageIndex = 1;
    private final int pageSize = 10;
    private DeicingDeviceAdapter deicingDeviceAdapter;
    private String highWaysSn, highWays,highWaysLength;
    private List<DeicingDeviceEntity> deicingDeviceEntities;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initEventAndData() {

        mPresenter.getDeviceList(pageIndex,pageSize,highWaysSn,highWays,highWaysLength);

        deicingDeviceAdapter = new DeicingDeviceAdapter(R.layout.adapter_deicing_device,null);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        rvDeicing.setLayoutManager(linearLayoutManager);

        rvDeicing.setAdapter(deicingDeviceAdapter);

        deicingDeviceAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                pageIndex++;
                mPresenter.getDeviceList(pageIndex,pageSize,highWaysSn,highWays,highWaysLength);
            }
        });

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                highWaysSn = "";
                highWays = "";
                highWaysLength = "";

                tvRoadId.setText(R.string.road_id);
                tvRoadMileage.setText(R.string.road_mileage);
                tvRoadSection.setText(R.string.road_section);

                mPresenter.getDeviceList(pageIndex,pageSize,highWaysSn,highWays,highWaysLength);
            }
        });

    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void getDeicingDeviceSuccess(DeicingDeviceListEntity<List<DeicingDeviceEntity>> listDeicingDeviceListEntity) {

        if (this.deicingDeviceEntities == null){
            this.deicingDeviceEntities = listDeicingDeviceListEntity.getData();
        }else {
            if (pageIndex > 1){
                this.deicingDeviceEntities.addAll(listDeicingDeviceListEntity.getData());
            }else {
                this.deicingDeviceEntities = listDeicingDeviceListEntity.getData();
            }
        }

        deicingDeviceAdapter.setNewInstance(deicingDeviceEntities);

        if (listDeicingDeviceListEntity.getCount() == deicingDeviceEntities.size()) {
            deicingDeviceAdapter.getLoadMoreModule().loadMoreEnd();
        }else {
            deicingDeviceAdapter.getLoadMoreModule().loadMoreComplete();
        }

    }

    @Override
    public void getDeicingDeviceFail(String msg) {
        UToastUtil.show(getActivity(),msg);
        if (msg.contains("登录")){
            Intent intent = new Intent();
            intent.setClass(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void getHighwaysSnSuccess(ArrayList<String> strings) {

        tvRoadId.setTextColor(getResources().getColor(R.color.main_color));
        popupCommon(strings,0,highWaysSn);

    }

    @Override
    public void getHighwaysSnFail(String msg) {
    }

    @Override
    public void getHighwaysSuccess(ArrayList<String> strings) {

        tvRoadSection.setTextColor(getResources().getColor(R.color.main_color));
        popupCommon(strings,1,highWays);

    }

    @Override
    public void getHighwaysFail(String msg) {

    }

    @Override
    public void getHighwaysLengthSuccess(ArrayList<String> strings) {

        tvRoadMileage.setTextColor(getResources().getColor(R.color.main_color));
        popupCommon(strings,2,highWaysLength);

    }

    @Override
    public void getHighwaysLengthFail(String msg) {

    }

    @OnClick({R.id.tv_road_id,R.id.tv_road_mileage,R.id.tv_road_section,R.id.iv_home_map})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_road_id:

                mPresenter.gethighwaysSn();

                break;

            case R.id.tv_road_section:

                if (!TextUtils.isEmpty(highWaysSn)) {
                    mPresenter.getHighways(highWaysSn);
                }else {
                    UToastUtil.show(getActivity(),"请先选择道路编号");
                }

                break;

            case R.id.tv_road_mileage:

                if (TextUtils.isEmpty(highWaysSn)) {
                    UToastUtil.show(getActivity(),"请先选择道路编号和路段");
                }else if(TextUtils.isEmpty(highWays)){
                    UToastUtil.show(getActivity(),R.string.choose_road);
                } else {
                    mPresenter.getHighWaysLength(highWaysSn,highWays);
                }

                break;

            case R.id.iv_home_map:

                Intent intent = new Intent();
                intent.setClass(getActivity(), MapActivity.class);
                startActivity(intent);


                break;


            default:
                break;

        }
    }

    private void popupCommon(ArrayList<String> strings,int type,String nameStr){

        ArrayList<String> allStr = new ArrayList<>();
        allStr.add(getResources().getString(R.string.all));
        allStr.addAll(strings);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.listview_home,null);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);

        RecyclerView recyclerView = view.findViewById(R.id.rv_pop_home);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        HomePopAdapter homePopAdapter = new HomePopAdapter(R.layout.item_pipeline,allStr,nameStr);

        recyclerView.setAdapter(homePopAdapter);


        homePopAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                if (type == 0){

                    tvRoadMileage.setText(R.string.road_mileage);
                    tvRoadSection.setText(R.string.road_section);
                    if (position == 0){
                        highWaysSn = "";
                        tvRoadId.setText(R.string.road_id);
                        mPresenter.getDeviceList(pageIndex,pageSize,"","","");
                    }else {
                        highWaysSn = allStr.get(position);
                        tvRoadId.setText(allStr.get(position));
                        mPresenter.getDeviceList(pageIndex,pageSize,highWaysSn,"","");
                    }

                }else if (type == 1){

                    tvRoadMileage.setText(R.string.road_mileage);
                    if (position == 0){
                        highWays = "";
                        tvRoadSection.setText(R.string.road_section);
                        mPresenter.getDeviceList(pageIndex,pageSize,highWaysSn,"","");
                    }else {
                        highWays = allStr.get(position);
                        tvRoadSection.setText(allStr.get(position));
                        mPresenter.getDeviceList(pageIndex,pageSize,highWaysSn,highWays,"");
                    }

                }else if (type == 2){

                    if (position == 0){
                        highWaysLength = "";
                        tvRoadMileage.setText(R.string.road_mileage);
                        mPresenter.getDeviceList(pageIndex,pageSize,highWaysSn,highWays,"");
                    }else {
                        highWaysLength = allStr.get(position);
                        tvRoadMileage.setText(allStr.get(position));
                        mPresenter.getDeviceList(pageIndex,pageSize,highWaysSn,highWays,highWaysLength);
                    }

                }

                popupWindow.dismiss();
            }
        });


        popupWindow.setOutsideTouchable(true);

        popupWindow.showAsDropDown(tvRoadId);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                    tvRoadId.setTextColor(getResources().getColor(R.color.bg_home_text));
                    tvRoadMileage.setTextColor(getResources().getColor(R.color.bg_home_text));
                    tvRoadSection.setTextColor(getResources().getColor(R.color.bg_home_text));
            }
        });


    }
}
