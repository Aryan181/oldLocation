package com.example.myapplication;

import android.widget.TextView;

public class Member {
    private String Lat;
    private String Longg;

    public Member( ) {

    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong() {
        return Longg;
    }

    public void setLong(String aLong) {
         Longg = aLong;
    }
}
