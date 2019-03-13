package com.example.android.college;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TimeTable extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewpagerAdapterTT adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);

        adapter = new ViewpagerAdapterTT(getSupportFragmentManager());

        //add Fragment here
        adapter.addFragment(new FragmentDayTT(),"Monday");

        adapter.addFragment(new FragmentDayTT(),"Tuesday");
        adapter.addFragment(new FragmentDayTT(),"Wednesday");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
