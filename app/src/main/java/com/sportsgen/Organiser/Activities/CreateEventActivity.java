package com.sportsgen.Organiser.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.shuhart.stepview.StepView;
import com.sportsgen.CommonClasses.HelperClasses.Constants;
import com.sportsgen.Organiser.Fragments.DateandTimeFragment;
import com.sportsgen.Organiser.Fragments.EntryFeesFragment;
import com.sportsgen.Organiser.Fragments.ImageBanerFragment;
import com.sportsgen.Organiser.Fragments.NamesportDesFragment;
import com.sportsgen.Organiser.Fragments.VenueFragment;
import com.sportsgen.R;

import java.util.ArrayList;

public class CreateEventActivity extends AppCompatActivity implements View.OnClickListener {
    FrameLayout frameLayout;
    Button btn_next, btn_pre;
    StepView stepView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        frameLayout = findViewById(R.id.frame_container_create_event);
        btn_next = findViewById(R.id.btn_next);
        btn_pre = findViewById(R.id.btn_previous);
        stepView = findViewById(R.id.step_view);
        btn_next.setOnClickListener(this);
        btn_pre.setOnClickListener(this);
        loadFragment(NamesportDesFragment.newInstance());
        setupstepview();

    }

    private void setupstepview() {
        stepView.getState()
                .steps(new ArrayList<String>() {{
                    add("Name");
                    add("Venue");
                    add("Date and Time");
                    add("Categories");
                    add("Image Banner");
                }})
                .commit();
    }

    private void loadFragment(Fragment fragment) {
        Constants.StringConstants.CREATE_EVENT_FRAGMENT = fragment.getClass().getName();
        if (Constants.StringConstants.CREATE_EVENT_FRAGMENT == NamesportDesFragment.class.getName()) {
            btn_pre.setVisibility(View.INVISIBLE);
            btn_next.setVisibility(View.VISIBLE);
        } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT == ImageBanerFragment.class.getName()) {
            btn_next.setVisibility(View.INVISIBLE);
            btn_pre.setVisibility(View.VISIBLE);
        } else {
            btn_next.setVisibility(View.VISIBLE);
            btn_pre.setVisibility(View.VISIBLE);
        }
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_create_event, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next: {
                if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(NamesportDesFragment.class.getName())) {
                    loadFragment(VenueFragment.newInstance());
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(VenueFragment.class.getName())) {
                    loadFragment(DateandTimeFragment.newInstance());
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(DateandTimeFragment.class.getName())) {
                    loadFragment(EntryFeesFragment.newInstance());
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(EntryFeesFragment.class.getName())) {
                    loadFragment(ImageBanerFragment.newInstance());
                }
                break;
            }
            case R.id.btn_previous: {
                if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(VenueFragment.class.getName())) {
                    loadFragment(NamesportDesFragment.newInstance());
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(DateandTimeFragment.class.getName())) {
                    loadFragment(VenueFragment.newInstance());
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(EntryFeesFragment.class.getName())) {
                    loadFragment(DateandTimeFragment.newInstance());
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(ImageBanerFragment.class.getName())) {
                    loadFragment(EntryFeesFragment.newInstance());
                }
                break;
            }
        }
    }
}
