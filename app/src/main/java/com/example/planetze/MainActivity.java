package com.example.planetze;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.planetze.classes.LoginManager;
import com.example.planetze.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String lastLaunchDate = preferences.getString("lastLaunchDate", null);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        if (lastLaunchDate == null || !lastLaunchDate.equals(currentDate)) {
            showDailyPopup(); // Show the pop-up
            // Save the current date as the last launch date
            preferences.edit().putString("lastLaunchDate", currentDate).apply();
        }
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = getNavController();
        binding.navView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.eco_tracker) {
                // Check if already on eco_tracker or its nested fragments
                if (navController.getCurrentDestination() != null &&
                        (navController.getCurrentDestination().getId() == R.id.eco_tracker ||
                                navController.getCurrentDestination().getId() == R.id.activity_list ||
                                navController.getCurrentDestination().getId() == R.id.drive_personal_vehicle ||
                                navController.getCurrentDestination().getId() == R.id.take_public_transportation ||
                                navController.getCurrentDestination().getId() == R.id.cycling_or_walking ||
                                navController.getCurrentDestination().getId() == R.id.flight ||
                                navController.getCurrentDestination().getId() == R.id.meal ||
                                navController.getCurrentDestination().getId() == R.id.buy_new_clothes ||
                                navController.getCurrentDestination().getId() == R.id.buy_electronics ||
                                navController.getCurrentDestination().getId() == R.id.other_purchases ||
                                navController.getCurrentDestination().getId() == R.id.energy_bills
                        )) {
                    // Already on eco_tracker or its nested fragments, pop back stack
                    navController.popBackStack(R.id.eco_tracker, false);
                } else {
                    // Navigate to eco_tracker
                    navController.navigate(R.id.eco_tracker);
                }
                return true; // Indicate that the event was handled
            }
            // ... handle other bottom navigation items ...
            return NavigationUI.onNavDestinationSelected(item, navController);
        });

        loginManager = LoginManager.getInstance();

    }

    private NavController getNavController() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        if (!(fragment instanceof NavHostFragment)) {
            throw new IllegalStateException("Activity " + this
                    + " does not have a NavHostFragment");
        }
        return ((NavHostFragment) fragment).getNavController();
    }

    @Override
    public void onBackPressed() {

        // Show dialog, confirmation of exiting the app
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loginManager.logout();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
//                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        if (false)
            super.onBackPressed();
    }

    private void showDailyPopup() {
        // Create and display an AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Daily Reminder")
                .setMessage("Daily reminder to log your habits!")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}