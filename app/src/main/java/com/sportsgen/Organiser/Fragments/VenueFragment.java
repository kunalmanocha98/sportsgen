package com.sportsgen.Organiser.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sportsgen.CommonClasses.HelperClasses.Constants;
import com.sportsgen.CommonClasses.HelperClasses.Utils;
import com.sportsgen.Organiser.Models.CreateEventData;
import com.sportsgen.R;

public class VenueFragment extends Fragment implements View.OnClickListener {

    TextInputEditText edt_venuename,edt_venueplace,edt_venueaddress;
    Button btn_submit_venue;
    CreateEventData modelalldata;
    CreateEventData.OnDataEntryListener onDataEntryListener;
    public static String OBJECT_KEY="56";


    public static Fragment newInstance(CreateEventData modelalldata){
        Fragment f=new VenueFragment();
        Bundle b=new Bundle();
        b.putParcelable(OBJECT_KEY,modelalldata);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onDataEntryListener=(CreateEventData.OnDataEntryListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_create_event_venue,container,false);
        modelalldata =(CreateEventData) getArguments().getParcelable(OBJECT_KEY);
        edt_venuename=v.findViewById(R.id.edt_venuename);
        edt_venueplace=v.findViewById(R.id.edt_venueplace);
        edt_venueaddress=v.findViewById(R.id.edt_venueaddress);
        btn_submit_venue=v.findViewById(R.id.btn_submit_venue);
        btn_submit_venue.setOnClickListener(this);
        checkmodeldata();
        return v;
    }

    private void checkmodeldata() {
        if (modelalldata !=null){
            if (modelalldata.getVenuename().equals("")){
               edt_venuename.setText(modelalldata.getVenuename());
            }
            if(modelalldata.getVenueplace().equals("")){
                edt_venueplace.setText(modelalldata.getVenueplace());
            }
            if (modelalldata.getVenueaddress().equals("")){
                edt_venueaddress.setText(modelalldata.getVenueaddress());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit_venue:{
                checkdata();
                break;
            }
        }
    }

    private void checkdata() {
        if (!edt_venuename.getText().toString().equals("")) {
            if (!edt_venueplace.getText().toString().equals("")) {
                if (!edt_venueaddress.getText().toString().equals("")) {
                    proceedsubmit();
                } else {
                        edt_venueaddress.setError("This Field is Required");
                }
            } else {
                edt_venueplace.setError("This Field is Required");
            }
        } else {
            edt_venuename.setError("This Field is Required");
        }
    }

    private void proceedsubmit() {
        onDataEntryListener.setVenuename(edt_venuename.getText().toString());
        onDataEntryListener.setVenueplace(edt_venueplace.getText().toString());
        onDataEntryListener.setVenueaddress(edt_venueaddress.getText().toString());
        onDataEntryListener.setVenueLat("8923578935");
        onDataEntryListener.setVenueLng("8397305345");
        Constants.StringConstants.is_VenueData_submitted = true;
        Utils.toast(getActivity(), "Submitted Successfully");
    }
}
