package com.sportsgen.Organiser.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.sportsgen.R;

public class CreateEventActivity extends AppCompatActivity {
    FrameLayout frameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        frameLayout=findViewById(R.id.frame_container_create_event);
    }
    private void loadFragment(Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_organiser, fragment);
        fragmentTransaction.commit();
    }
}
