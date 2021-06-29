package com.communication.deicing.adapter;

import android.widget.BaseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.communication.deicing.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by LG
 * on 2021/6/22  14:16
 * Description：
 */
public class PipelineAdapter extends BaseQuickAdapter<String, BaseViewHolder>{

    public PipelineAdapter(int layoutResId, ArrayList<String> strings) {
        super(layoutResId,strings);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv_pipeline_name,"支管网" + (baseViewHolder.getAdapterPosition()+1) + "压力值");

        baseViewHolder.setText(R.id.tv_pipeline_value,s+"mPa");
    }
}
