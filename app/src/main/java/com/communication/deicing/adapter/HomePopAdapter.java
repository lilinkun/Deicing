package com.communication.deicing.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.communication.deicing.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG
 * on 2021/6/22  17:21
 * Description：
 */
public class HomePopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private String nameStr;

    public HomePopAdapter(int layoutResId, List<String> strings, String nameStr) {
        super(layoutResId,strings);
        this.nameStr = nameStr;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv_pipeline,s);

        if (nameStr != null && nameStr.equals(s)){
            baseViewHolder.setTextColorRes(R.id.tv_pipeline,R.color.main_color);
        }

    }
}