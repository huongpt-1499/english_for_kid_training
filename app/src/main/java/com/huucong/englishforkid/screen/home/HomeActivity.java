package com.huucong.englishforkid.screen.home;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.huucong.englishforkid.R;
import com.huucong.englishforkid.screen.BaseActivity;
import com.huucong.englishforkid.screen.listvideo.ListVideoFragment;
import com.huucong.englishforkid.utils.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends BaseActivity implements HomeContract.View,
    NavigationView.OnNavigationItemSelectedListener {
    private HomePresenter mPresenter;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private NavigationView mNavigation;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        mPresenter = new HomePresenter();
        mPresenter.setView(this);

        mDrawer = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.toolbar);
        mNavigation = findViewById(R.id.navigation_view);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, mDrawer, mToolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigation.setNavigationItemSelectedListener(this);

        initTabLayoutAndViewPager();

    }

    private void initTabLayoutAndViewPager() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListVideoFragment(Constants.CATEGORY_SONG),
            getString(R.string.title_song));
        adapter.addFragment(new ListVideoFragment(Constants.CATEGORY_SHORT_STORY),
            getString(R.string.title_short_stories));
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabTextColors(getResources().getColor(R.color.colorPrimaryLight),
            getResources().getColor(R.color.colorWhite));

    }


    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
