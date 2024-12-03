package com.example.planetze.classes.ScreenUtilities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class ViewGenerator {
    @SuppressLint({"ResourceAsColor", "DefaultLocale", "SetTextI18n"})
    public static CardView createDailyActivityCardView(View view, DailyActivity activity, Context context) {
        // Create CardView
        CardView cardView = (CardView) LayoutInflater.from(context).inflate(R.layout.card_view, null);

        TextView titleTextView = cardView.findViewById(R.id.activity_title);
        TextView co2TextView = cardView.findViewById(R.id.activity_co2);
        ImageView deleteImageView = cardView.findViewById(R.id.delete_activity);
        TextView editTextView = cardView.findViewById(R.id.edit_activity);

        titleTextView.setText(activity.toString());
        co2TextView.setText(StringHandler.limitDecimal(activity.getEmission(), 2)  + "kg CO2");

        setOnClickListenerForDelete(activity, deleteImageView, context);

        editTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController((AppCompatActivity) context, R.id.nav_host_fragment_activity_main);
                Bundle args = new Bundle();

                args.putSerializable("dailyActivity", activity);
                navController.navigate(R.id.eco_tracker_navigation);

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

        return cardView;

    }

    public static void removeViewsFromIndex(ViewGroup layout, int x) {
        int childCount = layout.getChildCount();

        for (int i = childCount - 1; i >= x; i--) {  // Loop from the last child to the second child
            layout.removeViewAt(i);  // Remove each child starting from the second one
        }
    }

    public static void setOnClickListenerForDelete(DailyActivity activity, ImageView imageView, Context context) {
        imageView.setOnClickListener(event -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure to delete the activity " + activity.toString() + " ?");
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
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        });
    }

    public void showMessage(Context context, String title, String message) {
        // Show dialog, confirmation of exiting the app
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
