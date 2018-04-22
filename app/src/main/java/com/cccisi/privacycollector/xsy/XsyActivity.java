package com.cccisi.privacycollector.xsy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.cccisi.privacycollector.R;

import butterknife.BindView;
import butterknife.ButterKnife;



public class XsyActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private UserInfo userInfo = new UserInfo();
    private SysInfo sysInfo = new SysInfo();
    private AppInfo appInfo = new AppInfo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xsy);
        ButterKnife.bind(this);
        viewPager.addOnPageChangeListener(this);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return userInfo;
                    case 1:
                        return sysInfo;
                    case 2:
                        return appInfo;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            viewPager.setCurrentItem(item.getOrder());
            return false;
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mNavigation.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
