package com.example.ripapp2;

import android.app.Application;

public class MyApp extends Application {
    private String startmsg;

    public void onCreate() {
        super.onCreate();
        this.startmsg = "startapp";
    }

    public String getStartmsg() {
        return this.startmsg;
    }

    public void setStartmsg(String str) {
        this.startmsg = str;
    }
}
