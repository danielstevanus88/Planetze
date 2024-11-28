package com.example.planetze.classes.ScreenUtilities;

import android.content.Context;
import android.util.TypedValue;

public class PixelConverter {
    public static int convertDpToPx(Context context, int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics()
        );
    }
}
