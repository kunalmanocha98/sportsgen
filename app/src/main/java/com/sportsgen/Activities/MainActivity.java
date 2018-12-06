package com.sportsgen.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.sportsgen.Fragments.AcademiesFragment;
import com.sportsgen.Fragments.NewsFragment;
import com.sportsgen.Fragments.ScreeningsFragment;
import com.sportsgen.Fragments.TournamentFragment;
import com.sportsgen.HelperClasses.Constants;
import com.sportsgen.HelperClasses.Utils;
import com.sportsgen.R;

public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main,getContentframe());
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setTitle("SportsGen");
        getToolbar().setTitleTextColor(ContextCompat.getColor(this,R.color.colorwhite));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.main_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottom_menu_tournaments: {
                    TournamentFragment.newInstance();
                    Utils.toast(MainActivity.this,Constants.StringConstants.TOURNAMENTS);
                    return true;
                }
                case R.id.bottom_menu_screenings: {
                    ScreeningsFragment.newInstance();
                    Utils.toast(MainActivity.this,Constants.StringConstants.SCREENINGS);
                    return true;
                }
                case R.id.bottom_menu_academies: {
                    AcademiesFragment.newInstance();
                    Utils.toast(MainActivity.this,Constants.StringConstants.ACADEMIES);
                    return true;
                }
                case R.id.bottom_menu_news: {
                    NewsFragment.newInstance();
                    Utils.toast(MainActivity.this,Constants.StringConstants.NEWS);
                    return true;
                }
            }
            return false;
        }
    };


}
