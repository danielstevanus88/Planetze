package com.example.planetze.classes.ScreenUtilities;

import android.annotation.SuppressLint;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StringHandler {
    public static String limitString(String text, int maxLength){
        String suffix = "";
        if(text.length() > maxLength){
            suffix = "...";
        }
        return text.substring(0,Math.min(text.length(), maxLength)) + suffix;
    }

    public static String limitEmail(String email, int maxLength){
        String [] email_domain = email.split("@");
        return limitString(email_domain[0], maxLength) + email_domain[1];
    }

    @SuppressLint("DefaultLocale")
    public static String limitDecimal(double num, int maxDecimalPLaces){
        BigDecimal bd = new BigDecimal(Double.toString(num));
        bd = bd.setScale(maxDecimalPLaces, RoundingMode.HALF_UP);
        return String.valueOf(bd.doubleValue());
    }

}
