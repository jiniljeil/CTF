package com.example.ripapp2;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncData {
    private static final String ALGORITHM = "AES";
    private static final String IV = "1234567890123456";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public static String encrypt(String str) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec("1234567890123456".getBytes(), ALGORITHM);
        Cipher instance = Cipher.getInstance(TRANSFORMATION);
        instance.init(1, secretKeySpec, new IvParameterSpec("1234567890123456".getBytes()));
        return Base64.encodeToString(instance.doFinal(str.getBytes()), 2).replace('+', '-').replace('/', '_').replace("=", "");
    }

    public static String decrypt(String str) throws Exception {
        String replace = (str + "==".substring(0, (4 - (str.length() % 4)) % 4)).replace('-', '+').replace('_', '/');
        SecretKeySpec secretKeySpec = new SecretKeySpec("1234567890123456".getBytes(), ALGORITHM);
        Cipher instance = Cipher.getInstance(TRANSFORMATION);
        instance.init(2, secretKeySpec, new IvParameterSpec("1234567890123456".getBytes()));
        return new String(instance.doFinal(Base64.decode(replace, 0)));
    }
}
