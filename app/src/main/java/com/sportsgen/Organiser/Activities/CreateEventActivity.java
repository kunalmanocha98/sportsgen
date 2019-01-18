package com.sportsgen.Organiser.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.shuhart.stepview.StepView;
import com.sportsgen.CommonClasses.HelperClasses.Constants;
import com.sportsgen.CommonClasses.HelperClasses.Utils;
import com.sportsgen.Organiser.Fragments.DateandTimeFragment;
import com.sportsgen.Organiser.Fragments.EntryFeesFragment;
import com.sportsgen.Organiser.Fragments.ExtraDetailsFragment;
import com.sportsgen.Organiser.Fragments.ImageBanerFragment;
import com.sportsgen.Organiser.Fragments.NamesportDesFragment;
import com.sportsgen.Organiser.Fragments.VenueFragment;
import com.sportsgen.Organiser.Models.CreateEventData;
import com.sportsgen.Organiser.Models.ModelEntryFees;
import com.sportsgen.R;

import java.util.ArrayList;
import java.util.List;

public class CreateEventActivity extends AppCompatActivity implements View.OnClickListener, CreateEventData.OnDataEntryListener {
    FrameLayout frameLayout;
    Button btn_next, btn_pre;
    StepView stepView;
    CreateEventData modelalldata;

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
        modelalldata = new CreateEventData();
        loadFragment(NamesportDesFragment.newInstance(modelalldata));
        setupstepview();

    }

    private void setupstepview() {
        stepView.getState()
                .steps(new ArrayList<String>() {{
                    add("Name");
                    add("Venue");
                    add("Date Time");
                    add("Categories");
                    add("Extras");
                    add("Image");
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next: {
                if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(NamesportDesFragment.class.getName())) {
                    if (Constants.StringConstants.is_NameData_Submited) {
                        stepView.go(1, true);
                        loadFragment(VenueFragment.newInstance(modelalldata));
                    } else {
                        Utils.toast(CreateEventActivity.this, "Please Submit the data first");
                    }
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(VenueFragment.class.getName())) {
                    if (Constants.StringConstants.is_VenueData_submitted) {
                        stepView.go(2, true);
                        loadFragment(DateandTimeFragment.newInstance(modelalldata));
                    } else {
                        Utils.toast(CreateEventActivity.this, "Please Submit the data first");
                    }
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(DateandTimeFragment.class.getName())) {
                    if (Constants.StringConstants.is_DateTimeData_Submited) {
                        stepView.go(3, true);
                        loadFragment(EntryFeesFragment.newInstance(modelalldata));
                    } else {
                        Utils.toast(CreateEventActivity.this, "Please Submit the data first");
                    }
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(EntryFeesFragment.class.getName())) {
                    if (Constants.StringConstants.is_CategoriesData_submitted) {
                        stepView.go(4, true);
                        loadFragment(ExtraDetailsFragment.newInstance(modelalldata));
                    } else {
                        Utils.toast(CreateEventActivity.this, "Please Submit the data first");
                    }
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(ExtraDetailsFragment.class.getName())) {
                    if (Constants.StringConstants.is_Extra_Details_Data_submitted) {
                        stepView.go(5, true);
                        loadFragment(ImageBanerFragment.newInstance(modelalldata));
                    } else {
                        Utils.toast(CreateEventActivity.this, "Please Submit the data first");
                    }
                }
                break;
            }
            case R.id.btn_previous: {
                if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(VenueFragment.class.getName())) {
                    loadFragment(NamesportDesFragment.newInstance(modelalldata));
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(DateandTimeFragment.class.getName())) {
                    loadFragment(VenueFragment.newInstance(modelalldata));
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(EntryFeesFragment.class.getName())) {
                    loadFragment(DateandTimeFragment.newInstance(modelalldata));
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(ExtraDetailsFragment.class.getName())) {
                    loadFragment(EntryFeesFragment.newInstance(modelalldata));
                } else if (Constants.StringConstants.CREATE_EVENT_FRAGMENT.equals(ImageBanerFragment.class.getName())) {
                    loadFragment(ExtraDetailsFragment.newInstance(modelalldata));
                }
                break;
            }
        }
    }

    @Override
    public void setEventType(String Eventtype) {
        modelalldata.setEventtype(Eventtype);
    }

    @Override
    public void setEventName(String EventName) {
        modelalldata.setEventname(EventName);
    }

    @Override
    public void setEventSport(String EventSport) {
        modelalldata.setEventSport(EventSport);
    }

    @Override
    public void setEventDescription(String EventDescription) {
        modelalldata.setEventDescription(EventDescription);
    }

    @Override
    public void setVenuename(String Venuename) {
        modelalldata.setVenuename(Venuename);
    }

    @Override
    public void setVenueplace(String VenuePlace) {
        modelalldata.setVenueplace(VenuePlace);
    }

    @Override
    public void setVenueaddress(String Venueaddress) {
        modelalldata.setVenueaddress(Venueaddress);
    }

    @Override
    public void setVenueLat(String VenueLat) {
        modelalldata.setVenue_lat(VenueLat);
    }

    @Override
    public void setVenueLng(String VenueLng) {
        modelalldata.setVenue_lng(VenueLng);
    }

    @Override
    public void set_is_multiple_days(Boolean is_multiple_days) {
        modelalldata.setIs_multiple_days(is_multiple_days);
    }

    @Override
    public void set_single_date(String single_date) {
        modelalldata.setSingle_date(single_date);
    }

    @Override
    public void setFrom_date(String from_date) {
        modelalldata.setFrom_date(from_date);
    }

    @Override
    public void set_to_date(String to_date) {
        modelalldata.setTo_date(to_date);
    }

    @Override
    public void set_from_time(String from_time) {
        modelalldata.setFrom_date(from_time);
    }

    @Override
    public void set_to_time(String to_time) {
        modelalldata.setTo_time(to_time);
    }

    @Override
    public void set_is_paid(Boolean is_paid) {
        modelalldata.setIs_paid(is_paid);
    }

    @Override
    public void set_list_of_categories(List<ModelEntryFees> mlist) {
        modelalldata.setList_entry_fees(mlist);
    }


}
