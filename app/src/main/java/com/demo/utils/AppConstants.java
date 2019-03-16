package com.demo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppConstants {

    public static final String SAMPLE_KEY = "AEp4lgmVaMGYjrJ6SJBJWsbJRV4Vr1rd" ;
    public static final String BASEURL = "http://api.nytimes.com/";//add number 7,30,1 after viewed
    public static final String CONNECT = "svc/mostpopular/v2/viewed/7.json?api-key="+SAMPLE_KEY ;

    public static String formatdate(String publishDate) {
        String date=null;
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat d= new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date convertedDate = inputFormat.parse(publishDate);
            date = d.format(convertedDate);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return  date;
    }
}
