package com.android.agendacontactos;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.agendacontactos.adapter.ContactListAdapter;
import com.android.agendacontactos.adapter.ViewPagerAdapter;
import com.android.agendacontactos.fragment.DummyFragment;
import com.android.agendacontactos.fragment.FormFragment;

import java.util.Observable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.tab_layout) TabLayout mTabLayout;
    @Bind(R.id.viewpager) ViewPager mViewPager;

    DummyFragment dummyFragment;
    FormFragment formFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dummyFragment = new DummyFragment();
        formFragment = new FormFragment();

        setSupportActionBar(mToolbar);
        mTabLayout.addTab(mTabLayout.newTab().setText("Contactos"),0,true);
        mTabLayout.addTab(mTabLayout.newTab().setText("Form"), 1, false);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(dummyFragment);
        viewPagerAdapter.addFrag(formFragment);

        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()){
                    case 0:
                        Log.d("AGENDACONTACTOS", "TAB#1");
                        break;
                    case 1:
                        formFragment.setForm();
                       // Log.d("AGENDACONTACTOS", ContactListAdapter.contactStatic.getName());
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

    public void newData(long id, boolean update) { //patron observer
        dummyFragment.refresh(id,update);
    }

    public void setTab(int tab){
        //mViewPager.setCurrentItem(1);
        mViewPager.setCurrentItem(tab);
    }

}
