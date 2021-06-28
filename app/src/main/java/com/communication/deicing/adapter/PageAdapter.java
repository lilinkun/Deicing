package com.communication.deicing.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.communication.deicing.base.BaseFragment;

import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragmentList;

    public PageAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentList = fragmentList;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
}
