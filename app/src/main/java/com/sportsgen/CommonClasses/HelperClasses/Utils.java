package com.sportsgen.CommonClasses.HelperClasses;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static void toast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    public static final class DateTimeFormatter {

        public static final String converttodateformat(String format, Date date){
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);
        }

        public static final String converttotimeformat(String format,Date date){
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);

        }
    }

}
