package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public static String format_ddMMyyyy(Date date) {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        String result = DATE_FORMAT.format(date);
        return result;
    }
}
