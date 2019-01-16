package com.sportsgen.Organiser.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sportsgen.CommonClasses.HelperClasses.Constants;
import com.sportsgen.CommonClasses.HelperClasses.Utils;
import com.sportsgen.Organiser.Models.CreateEventData;
import com.sportsgen.R;

import java.util.Calendar;

public class DateandTimeFragment extends Fragment implements View.OnClickListener {

    SwitchCompat mSwitch;
    LinearLayout single_date_layout, multiple_date_layout;
    RelativeLayout wrapper_single_date, wrapper_start_date, wrapper_end_date;
    TextView edt_single_date, edt_start_date, edt_end_date;
    TextView edt_start_time, edt_end_time;
    DatePickerDialog singledatepicker, startdatepicker, enddatepicker;
    TimePickerDialog starttimepicker, endtimepicker;
    Button btn_submit;
    CreateEventData modelalldata;
    CreateEventData.OnDataEntryListener onDataEntryListener;
    public static String OBJECT_KEY="57";

    public static Fragment newInstance(CreateEventData modelalldata) {
        Fragment f = new DateandTimeFragment();
        Bundle b= new Bundle();
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
        View v = inflater.inflate(R.layout.fragment_create_event_date_time, container, false);
        modelalldata=getArguments().getParcelable(OBJECT_KEY);
        mSwitch = v.findViewById(R.id.switch_toggle);
        single_date_layout = v.findViewById(R.id.date_s_layout);
        multiple_date_layout = v.findViewById(R.id.date_layout);
        edt_single_date = v.findViewById(R.id.edt_single_date);
        edt_start_date = v.findViewById(R.id.edt_start_date);
        edt_end_date = v.findViewById(R.id.edt_end_date);

        edt_start_time = v.findViewById(R.id.edt_start_time);
        edt_end_time = v.findViewById(R.id.edt_end_time);

        btn_submit=v.findViewById(R.id.btn_submit_datetime);
        btn_submit.setOnClickListener(this);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    single_date_layout.setVisibility(View.VISIBLE);
                    multiple_date_layout.setVisibility(View.INVISIBLE);
                } else {
                    single_date_layout.setVisibility(View.INVISIBLE);
                    multiple_date_layout.setVisibility(View.VISIBLE);
                }
            }
        });
        singledatepicker = new DatePickerDialog(getActivity(), R.style.MyDatepicker, singledateSetListener, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        startdatepicker = new DatePickerDialog(getActivity(), R.style.MyDatepicker, startdateSetListener, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        enddatepicker = new DatePickerDialog(getActivity(), R.style.MyDatepicker, enddateSetListener, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        starttimepicker = new TimePickerDialog(getActivity(), R.style.MyDatepicker, startTimeSetListener, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), false);
        endtimepicker = new TimePickerDialog(getActivity(), R.style.MyDatepicker, endTimeSetListener, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), false);


        edt_single_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singledatepicker.show();
            }
        });
        edt_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startdatepicker.show();
            }
        });
        edt_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enddatepicker.show();
            }
        });
        edt_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starttimepicker.show();
            }
        });
        edt_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endtimepicker.show();
            }
        });

        checkmodeldata();
        return v;
    }

    private void checkmodeldata() {
        if (modelalldata !=null){
            if (modelalldata.getIs_multiple_days()){
                mSwitch.setChecked(false);
                edt_start_date.setText(modelalldata.getFrom_date());
                edt_end_date.setText(modelalldata.getTo_date());
            }else{
                mSwitch.setChecked(true);
                edt_single_date.setText(modelalldata.getSingle_date());
            }
            edt_start_time.setText(modelalldata.getFrom_time());
            edt_end_time.setText(modelalldata.getTo_time());
        }
    }


    DatePickerDialog.OnDateSetListener singledateSetListener = (datePicker, year, month, day) -> {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        cal.set(Calendar.MONTH, datePicker.getMonth());
        cal.set(Calendar.YEAR, datePicker.getYear());
        String startdate = Utils.DateTimeFormatter.converttodateformat("EEE, dd MMM yy", cal.getTime());
        edt_single_date.setText(startdate);
    };
    DatePickerDialog.OnDateSetListener startdateSetListener = (datePicker, year, month, day) -> {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        cal.set(Calendar.MONTH, datePicker.getMonth());
        cal.set(Calendar.YEAR, datePicker.getYear());
        String startdate = Utils.DateTimeFormatter.converttodateformat("EEE, dd MMM yy", cal.getTime());
        edt_start_date.setText(startdate);
    };
    DatePickerDialog.OnDateSetListener enddateSetListener = (datePicker, year, month, day) -> {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        cal.set(Calendar.MONTH, datePicker.getMonth());
        cal.set(Calendar.YEAR, datePicker.getYear());
        String startdate = Utils.DateTimeFormatter.converttodateformat("EEE, dd MMM yy", cal.getTime());
        edt_end_date.setText(startdate);
    };
    TimePickerDialog.OnTimeSetListener startTimeSetListener = (timePicker, hourofday, minute) -> {
        Calendar datetime = Calendar.getInstance();
        datetime.set(Calendar.HOUR_OF_DAY, hourofday);
        datetime.set(Calendar.MINUTE, minute);
        String starttime = Utils.DateTimeFormatter.converttotimeformat("hh:mm a", datetime.getTime());
        edt_start_time.setText(starttime);
    };
    TimePickerDialog.OnTimeSetListener endTimeSetListener = (timePicker, hourofday, minute) -> {
        Calendar datetime = Calendar.getInstance();
        datetime.set(Calendar.HOUR_OF_DAY, hourofday);
        datetime.set(Calendar.MINUTE, minute);
        String starttime = Utils.DateTimeFormatter.converttotimeformat("hh:mm a", datetime.getTime());
        edt_end_time.setText(starttime);
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit_datetime:{
                checkdate();
                break;
            }
        }
    }

    private void checkdate() {
        if (mSwitch.isChecked()){
            if (!edt_single_date.getText().toString().equals("")){
                checktime();
            }else
            {
                Utils.toast(getActivity(),"Some data missing");
            }
        }else {
            if (!edt_single_date.getText().toString().equals("") && !edt_end_date.getText().toString().equals("")){
                checktime();
            }else {
                Utils.toast(getActivity(),"Some data missing");
            }
        }
    }

    private void checktime() {
        if (!edt_single_date.getText().toString().equals("") && !edt_end_date.getText().toString().equals("")){
            proceedsubmit();
        }else {
            Utils.toast(getActivity(),"Some data missing");
        }
    }

    private void proceedsubmit() {
        if (mSwitch.isChecked()){
            onDataEntryListener.set_is_multiple_days(false);
            onDataEntryListener.set_single_date(edt_single_date.getText().toString());
        }else {
            onDataEntryListener.set_is_multiple_days(true);
            onDataEntryListener.setFrom_date(edt_start_date.getText().toString());
            onDataEntryListener.set_to_date(edt_end_date.getText().toString());
        }
        onDataEntryListener.set_from_time(edt_start_time.getText().toString());
        onDataEntryListener.set_to_time(edt_end_time.getText().toString());
        Constants.StringConstants.is_NameData_Submited = true;
        Utils.toast(getActivity(), "Submitted Successfully");
    }

}
