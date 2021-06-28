package com.communication.deicing.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.communication.deicing.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG
 * on 2021/6/22  11:13
 * Description：
 */
public class TabPageAdapter extends FragmentPagerAdapter {
    List<BaseFragment> fragments = new ArrayList<>();
    private List<String> titles;
    FragmentManager fm;

    public TabPageAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 将实例化的fragment进行显示即可。
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fm.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//            Fragment fragment = fragments.get(position);// 获取要销毁的fragment
//            getSupportFragmentManager().beginTransaction().hide(fragment).commit();// 将其隐藏即可，并不需要真正销毁，这样fragment状态就得到了保存
    }
}