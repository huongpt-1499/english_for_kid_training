package com.huucong.englishforkid.screen.home;

import com.huucong.englishforkid.screen.listvideo.ListVideoFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    private List<ListVideoFragment> mListVideoFragments;
    private List<String> mTitles;

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.mTitles = new ArrayList<>();
        this.mListVideoFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mListVideoFragments.get(position);
    }

    @Override
    public int getCount() {
        return mListVideoFragments == null ? 0 : mListVideoFragments.size();
    }

    public void addFragment(ListVideoFragment fragment, String title) {
        mListVideoFragments.add(fragment);
        mTitles.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
