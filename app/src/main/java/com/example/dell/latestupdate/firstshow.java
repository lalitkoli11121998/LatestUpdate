package com.example.dell.latestupdate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class firstshow extends AppCompatActivity {

    ViewPager viewPager;

    private Button skip , next;
    LinearLayout dotlayout;
    private int[] layouts = {R.layout.first_layout , R.layout.second_layout , R.layout.third_layout};
    private int dotscount;
    private ImageView[] dots;
    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstshow);
        skip = findViewById(R.id.button);
        next = findViewById(R.id.start);
        dotlayout = findViewById(R.id.dotlayout);
        if(Build.VERSION.SDK_INT >=19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new PagerAdapter(layouts ,this);
        viewPager.setAdapter(pagerAdapter);
        // dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_launcher_background));
        addDotindicator();
        next .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int next_slide = viewPager.getCurrentItem()+1;
                if(next_slide < layouts.length)
                {
                    viewPager.setCurrentItem(next_slide);
                }
                else{
                    Intent i = new Intent(firstshow.this , Main2Activity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstshow.this ,Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i=0;i<dotscount;i++)
                {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_launcher_background));
                }
                // dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext() , R.drawable.ic_launcher_background));
                if(position == layouts.length-1)
                {
                    next.setText("start");
                    skip.setVisibility(View.INVISIBLE);
                }
                else{
                    next.setText("Next");
                    skip.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public  void addDotindicator(){

        dots = new ImageView[dotscount];

        for(int i=0;i< dotscount;i++)
        {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_launcher_background));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8 ,0 ,8 ,0);
            dotlayout.addView(dots[i],params);
        }
    }
}



