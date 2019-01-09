package com.sportsgen.Organiser.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sportsgen.CommonClasses.HelperClasses.Utils;
import com.sportsgen.R;

import java.util.Calendar;

public class DateandTimeFragment extends Fragment {

    SwitchCompat mSwitch;
    LinearLayout single_date_layout, multiple_date_layout;
    RelativeLayout wrapper_single_date, wrapper_start_date, wrapper_end_date;
    TextView edt_single_date, edt_start_date, edt_end_date;
    TextView edt_start_time, edt_end_time;
    DatePickerDialog singledatepicker, startdatepicker, enddatepicker;
    TimePickerDialog starttimepicker, endtimepicker;

    public static Fragment newInstance() {
        Fragment f = new DateandTimeFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_event_date_time, container, false);
        mSwitch = v.findViewById(R.id.switch_toggle);
        single_date_layout = v.findViewById(R.id.date_s_layout);
        multiple_date_layout = v.findViewById(R.id.date_layout);
        edt_single_date = v.findViewById(R.id.edt_single_date);
        edt_start_date = v.findViewById(R.id.edt_start_date);
        edt_end_date = v.findViewById(R.id.edt_end_date);

        edt_start_time = v.findViewById(R.id.edt_start_time);
        edt_end_time = v.findViewById(R.id.edt_end_time);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    single_date_layout.setVisibility(View.INVISIBLE);
                    multiple_date_layout.setVisibility(View.VISIBLE);
                } else {
                    single_date_layout.setVisibility(View.VISIBLE);
                    multiple_date_layout.setVisibility(View.INVISIBLE);
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
        return v;
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
}
