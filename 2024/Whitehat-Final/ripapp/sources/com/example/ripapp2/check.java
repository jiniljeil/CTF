package com.example.ripapp2;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import kotlin.UByte;

public class check {
    public void checkSecurity(Context context, String str) {
        if (isDeviceRooted() || isRooted()) {
            System.out.println("Device is rooted!");
        } else if (!isIntegrityValid(context, str)) {
            System.out.println("APK integrity compromised!");
        } else {
            System.out.println("Device is secure.");
        }
    }

    public boolean isDeviceRooted() {
        String[] strArr = {"/system/app/Superuser.apk", "/sbin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su"};
        for (int i = 0; i < 8; i++) {
            if (new File(strArr[i]).exists()) {
                return true;
            }
        }
        return false;
    }

    public boolean isRooted() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            exec.getOutputStream().write("exit\n".getBytes());
            exec.getOutputStream().flush();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public String getAPKHash(Context context) {
        try {
            File file = new File(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.sourceDir);
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                instance.update(bArr, 0, read);
            }
            fileInputStream.close();
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isIntegrityValid(Context context, String str) {
        String aPKHash = getAPKHash(context);
        System.out.println("App Hash Value");
        System.out.println(aPKHash);
        return str.equals(aPKHash);
    }
}
