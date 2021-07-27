package com.communication.deicing.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.communication.deicing.R;
import com.communication.deicing.adapter.HomePopAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by LG
 * on 2021/6/23  11:00
 * Description：
 */
public class CommonPopup extends PopupWindow {

    private View conentView;
    private Context context;
    private int type;
    private List<String> strings;
    private String clickStr;
    private PopupClickListener popupClickListener;

    /**
     *
     * @param context
     * @param type    popup是哪个控件生成的 4代表喷淋间隔 5为工作模式 6为档位
     * @param strings   要显示的list
     * @param str
     * @param popupClickListener
     */
    public CommonPopup(Context context, int type, List<String> strings, String str,PopupClickListener popupClickListener){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.listview_home, null);
        this.popupClickListener = popupClickListener;
        this.context = context;
        this.type = type;
        this.strings = strings;
        this.clickStr = str;
        this.setContentView(conentView);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        initData();
    }

    private void initData(){

        RecyclerView recyclerView = conentView.findViewById(R.id.rv_pop_home);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        HomePopAdapter homePopAdapter = new HomePopAdapter(R.layout.item_pipeline,strings,clickStr);

        recyclerView.setAdapter(homePopAdapter);

        homePopAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                popupClickListener.itemClick(type,position);
                dismiss();
            }
        });
    }



    public interface PopupClickListener{
        public void itemClick(int type,int position);
    }


}
