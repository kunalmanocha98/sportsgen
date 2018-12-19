package com.sportsgen.Organiser.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.sportsgen.CommonClasses.BaseActivity;
import com.sportsgen.CommonClasses.HelperClasses.Constants;
import com.sportsgen.CommonClasses.HelperClasses.Utils;
import com.sportsgen.Organiser.Fragments.DashboardFragment;
import com.sportsgen.Organiser.Fragments.EventsFragment;
import com.sportsgen.Organiser.Fragments.RatingsFragment;
import com.sportsgen.Organiser.Fragments.SalesFragment;
import com.sportsgen.R;
import com.sportsgen.User.Fragments.AcademiesFragment;
import com.sportsgen.User.Fragments.NewsFragment;
import com.sportsgen.User.Fragments.ScreeningsFragment;
import com.sportsgen.User.Fragments.TournamentFragment;

public class OrganiserMainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_organiser_main,getContentframe());
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation_organiser);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottom_menu_dashboard: {
                    loadFragment(DashboardFragment.newInstance());
                    Utils.toast(OrganiserMainActivity.this,Constants.StringConstants.DASHBOARD);
                    return true;
                }
                case R.id.bottom_menu_events: {
                    loadFragment(EventsFragment.newInstance());
                    Utils.toast(OrganiserMainActivity.this,Constants.StringConstants.EVENTS);
                    return true;
                }
                case R.id.bottom_menu_sales: {
                    loadFragment(SalesFragment.newInstance());
                    Utils.toast(OrganiserMainActivity.this,Constants.StringConstants.SALES);
                    return true;
                }
                case R.id.bottom_menu_ratings: {
                    loadFragment(RatingsFragment.newInstance());
                    Utils.toast(OrganiserMainActivity.this,Constants.StringConstants.RATINGS);
                    return true;
                }
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_organiser, fragment);
        fragmentTransaction.commit();
    }

}
