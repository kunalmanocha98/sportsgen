package com.sportsgen.CommonClasses;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.sportsgen.R;

public class BaseActivity extends AppCompatActivity {
Toolbar toolbar;
FrameLayout content_frame;
DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        toolbar=findViewById(R.id.toolbar);
        content_frame=findViewById(R.id.content_frame);
        drawerLayout=findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setIcon(R.drawable.app_logo_main);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white);
    }
    public DrawerLayout getDrawerLayout(){return drawerLayout;}
    public FrameLayout getContentframe(){return content_frame;}
    public Toolbar getToolbar(){return toolbar;}


}

