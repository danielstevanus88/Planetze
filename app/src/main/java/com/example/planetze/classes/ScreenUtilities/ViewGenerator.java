package com.example.planetze.classes.ScreenUtilities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.planetze.R;
import com.example.planetze.classes.EcoTracker.DailyActivity;

public class ViewGenerator {
    @SuppressLint("ResourceAsColor")
    public static CardView createDailyACtivityCardView(View view, DailyActivity activity, Context context){
        // Create CardView
        CardView cardView = new CardView(context);

        int marginInPx = 10;
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        params.setMargins(marginInPx, marginInPx, marginInPx, marginInPx);
        cardView.setLayoutParams(params);

        // Create parent LinearLayout inside CardView
        LinearLayout parentLayout = new LinearLayout(context);
        parentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        parentLayout.setOrientation(LinearLayout.HORIZONTAL);

        int paddingInPx = PixelConverter.convertDpToPx(context, 10);
        parentLayout.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);

        // Create inner vertical LinearLayout
        LinearLayout verticalLayout = new LinearLayout(context);
        verticalLayout.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f // Layout weight for equal distribution
        ));
        verticalLayout.setOrientation(LinearLayout.VERTICAL);
        verticalLayout.setPadding(paddingInPx, paddingInPx,paddingInPx,paddingInPx);

        // Create first TextView
        TextView titleTextView = new TextView(context);
        titleTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        titleTextView.setText(activity.toString());
        titleTextView.setTextSize(16f);
        titleTextView.setTypeface(titleTextView.getTypeface() , Typeface.ITALIC);

        // Create second TextView
        TextView co2TextView = new TextView(context);
        co2TextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        co2TextView.setText(activity.getEmission() + "kg CO2");
        co2TextView.setPadding(0, paddingInPx, 0, 0);

        // Add TextViews to vertical layout
        verticalLayout.addView(titleTextView);
        verticalLayout.addView(co2TextView);

        // Create CheckBox
        CheckBox checkBox = new CheckBox(context);
        checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));


        // Add vertical layout and checkbox to parent layout
        parentLayout.addView(verticalLayout);
        parentLayout.addView(checkBox);

        // Add parent layout to CardView
        cardView.addView(parentLayout);

        return cardView;
    }

    public static void removeAllChildExceptTheFirstXChild(ViewGroup layout,  int x){
        int childCount = layout.getChildCount();

        for (int i = childCount - 1; i >= x; i--) {  // Loop from the last child to the second child
            layout.removeViewAt(i);  // Remove each child starting from the second one
        }
    }
}
