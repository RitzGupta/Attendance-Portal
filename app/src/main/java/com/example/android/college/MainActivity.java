package com.example.android.college;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";

    //Floating Button
    FloatingActionMenu fabm;
    FloatingActionButton studentLogin;

    //Cardview items
    CardView about, admission, placement, cources, training, gallery, social, contactUs;

    //ViewPager
    ViewPager viewPager;
    CustomSliderAdapter adapter;
    private Timer timer;
    private int current_position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        about = (CardView) findViewById(R.id.cvAbout);
        admission = (CardView) findViewById(R.id.cvAdmission);
        placement = (CardView) findViewById(R.id.cvPlacement);
        cources = (CardView) findViewById(R.id.cvCourses);
        training = (CardView) findViewById(R.id.cvTraining);
        gallery = (CardView) findViewById(R.id.cvGallery);
        social = (CardView) findViewById(R.id.cvSocial);
        contactUs = (CardView) findViewById(R.id.cvContactUs);


        fabm = (FloatingActionMenu) findViewById(R.id.menu);
        fabm.setClosedOnTouchOutside(true);
        studentLogin = (FloatingActionButton) findViewById(R.id.fbtnStudent_login);

        about.setOnClickListener(this);
        admission.setOnClickListener(this);
        placement.setOnClickListener(this);
        cources.setOnClickListener(this);
        training.setOnClickListener(this);
        gallery.setOnClickListener(this);
        social.setOnClickListener(this);
        contactUs.setOnClickListener(this);


        studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignIn.class);
                startActivity(intent);
                finish();

            }
        });


        //SliderView
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new CustomSliderAdapter(this);
        viewPager.setAdapter(adapter);
        createSlideShow();
    }

    //OnClick Activity
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.cvAbout:
                Intent intentAbout = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intentAbout);
                break;
            case R.id.cvAdmission:
                Intent intent = new Intent(MainActivity.this, Admission.class);
                startActivity(intent);
                break;
            case R.id.cvPlacement:
                Intent intentPlacement = new Intent(MainActivity.this, Placement.class);
                startActivity(intentPlacement);
                break;
            case R.id.cvCourses:
                Intent intentCourses = new Intent(MainActivity.this, Courses.class);
                startActivity(intentCourses);
                break;
            case R.id.cvTraining:
                Intent intentTraining = new Intent(MainActivity.this, Trainings.class);
                startActivity(intentTraining);
                break;
            case R.id.cvGallery:
                Intent intentGallery = new Intent(MainActivity.this, Gallery.class);
                startActivity(intentGallery);
                break;
            case R.id.cvSocial:
                Intent intentSocial = new Intent(MainActivity.this, Social.class);
                startActivity(intentSocial);
                break;
            case R.id.cvContactUs:
                Intent intentContactUs = new Intent(MainActivity.this, ContactUs.class);
                startActivity(intentContactUs);
                break;


        }
    }

    //SlideShow method
    private void createSlideShow() {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (current_position == Integer.MAX_VALUE)
                    current_position = 0;
                viewPager.setCurrentItem(current_position++, true);

            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);
            }
        }, 250, 2500);

    }

    private static long sayBackPress;

    @Override
    public void onBackPressed() {
        if (sayBackPress + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
        }
        else{
            Toast.makeText(MainActivity.this, "Press once again to exit!", Toast.LENGTH_SHORT).show();
            sayBackPress = System.currentTimeMillis();
        }
    }
}
