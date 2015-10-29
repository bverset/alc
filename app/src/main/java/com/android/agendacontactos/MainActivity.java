package com.android.agendacontactos;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.agendacontactos.adapter.ViewPagerAdapter;
import com.android.agendacontactos.fragment.DummyFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tab_layout) TabLayout mTabLayout;
    @Bind(R.id.viewpager) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mTabLayout.addTab(mTabLayout.newTab().setText("TAB1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("TAB2"));

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new DummyFragment());
        viewPagerAdapter.addFrag(new DummyFragment());

        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));


        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()){
                    case 0:
                        Log.d("AGENDACONTACTOS", "TAB#1");
                        break;
                    case 1:
                        Log.d("AGENDACONTACTOS", "TAB#2");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
