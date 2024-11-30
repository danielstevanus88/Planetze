package com.example.planetze.classes.ScreenUtilities;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.planetze.R;
import com.example.planetze.classes.EcoTracker.Category.EcoTrackerActivityConstant;
import com.example.planetze.classes.EcoTracker.DailyActivity;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;

import java.io.Serializable;

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

        // Create Edit and Delete
        // Create the parent LinearLayout
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
        linearLayout.setGravity(Gravity.CENTER|Gravity.END);

        // Create the ImageView
        ImageView imageView = new ImageView(context);
        imageView.setId(View.generateViewId());
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                PixelConverter.convertDpToPx(context, 15), PixelConverter.convertDpToPx(context,15));
        imageParams.weight = 1; // Same as android:layout_weight="1"
        imageView.setLayoutParams(imageParams);
        imageView.setImageResource(R.drawable.planetze_trash);  // Replace with your drawable resource
        imageView.setClickable(true);
        setOnClickListenerForDelete(activity, imageView, context);

        // Create the TextView
        TextView textView = new TextView(context);
        textView.setId(View.generateViewId());
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.weight = 1; // Same as android:layout_weight="1"
        textParams.topMargin = PixelConverter.convertDpToPx(context, 5);  // Same as android:layout_marginTop="5dp"
        textView.setLayoutParams(textParams);
        textView.setText("Edit");
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController((AppCompatActivity) context, R.id.nav_host_fragment_activity_main);
                Bundle args = new Bundle();

                args.putSerializable("dailyActivity", activity);

                if (activity.getTypeId() == EcoTrackerActivityConstant.ID_DRIVE_PERSONAL_VEHICLE) {
                    navController.navigate(R.id.drive_personal_vehicle, args);
                } else if (activity.getTypeId() == EcoTrackerActivityConstant.ID_TAKE_PUBLIC_TRANSPORTATION) {
                    navController.navigate(R.id.take_public_transportation, args);
                } else if (activity.getTypeId() == EcoTrackerActivityConstant.ID_CYCLING_OR_WALKING) {
                    navController.navigate(R.id.cycling_or_walking, args);
                } else if (activity.getTypeId() == EcoTrackerActivityConstant.ID_FLIGHT) {
                    navController.navigate(R.id.flight, args);
                } else if (
                        activity.getTypeId() == EcoTrackerActivityConstant.ID_EAT_BEEF
                                || activity.getTypeId() == EcoTrackerActivityConstant.ID_EAT_FISH
                                || activity.getTypeId() == EcoTrackerActivityConstant.ID_EAT_CHICKEN
                                || activity.getTypeId() == EcoTrackerActivityConstant.ID_EAT_PLANT_BASED
                                || activity.getTypeId() == EcoTrackerActivityConstant.ID_EAT_PORK
                ) {
                    navController.navigate(R.id.meal, args);
                } else if (activity.getTypeId() == EcoTrackerActivityConstant.ID_BUY_CLOTHES) {
                    navController.navigate(R.id.buy_new_clothes, args);
                } else if (activity.getTypeId() == EcoTrackerActivityConstant.ID_BUY_ELECTRONICS) {
                    navController.navigate(R.id.buy_electronics, args);
                } else if (activity.getTypeId() == EcoTrackerActivityConstant.ID_BUY_OTHERS) {
                    navController.navigate(R.id.other_purchases, args);
                } else if (activity.getTypeId() == EcoTrackerActivityConstant.ID_ENERGY_BILL) {
                    navController.navigate(R.id.energy_bills, args);
                }

            }
        });

        // Add the ImageView and TextView to the LinearLayout
        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        // Add vertical layout and edit/delete to parent layout
        parentLayout.addView(verticalLayout);
        parentLayout.addView(linearLayout);


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

    public void showMessage(Context context, String title, String message){
        // Show dialog, confirmation of exiting the app
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void setOnClickListenerForDelete(DailyActivity activity, ImageView imageView, Context context){
        imageView.setOnClickListener(event -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure to delete the activity "+ activity.toString() +" ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String uuid = activity.getUuid();
                    User user = LoginManager.getCurrentUser();
                    user.removeActivity(uuid);
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        });
    }

}
