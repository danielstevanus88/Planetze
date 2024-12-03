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
    private NavController navController;

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

        // Initialize LoginManager
        loginManager = LoginManager.getInstance();

        // Set up navigation
        navController = getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Handle top banner
        setupTopBanner();

        // Handle bottom navigation
        setupBottomNavigation();
    }

    private NavController getNavController() {
        try {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
            if (!(fragment instanceof NavHostFragment)) {
                throw new IllegalStateException("Activity " + this + " does not have a NavHostFragment");
            }
            return ((NavHostFragment) fragment).getNavController();
        } catch (IllegalStateException e) {
            // Handle the error gracefully, e.g., log the error and finish the activity
            return null;
        }
    }

    private void setupTopBanner() {
        binding.backButton.setOnClickListener(v -> {
            navController.navigateUp();
        });

        binding.profileButton.setOnClickListener(v -> {
            navController.navigate(R.id.navigation_profile);
        });

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.eco_tracker) {
                binding.pageTitle.setText(getResources().getString(R.string.eco_tracker));
            } else if (destination.getId() == R.id.eco_gauge) {
                binding.pageTitle.setText(getResources().getString(R.string.eco_gauge));
            } else if (destination.getId() == R.id.eco_balance) {
                binding.pageTitle.setText(getResources().getString(R.string.eco_balance));
            } else if (destination.getId() == R.id.navigation_profile) {
                binding.pageTitle.setText(getResources().getString(R.string.profile));
            }
        });
    }

    private void setupBottomNavigation() {
        binding.navView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.eco_tracker) {
                if (navController.getCurrentDestination() != null && navController.getCurrentDestination().getId() == R.id.eco_tracker) {
                    // Already on eco_tracker, do nothing
                    return true;
                } else {
                    // Navigate to eco_tracker
                    navController.navigate(R.id.eco_tracker);
                    return true;
                }
            }
            // ... handle other bottom navigation items ...
            return NavigationUI.onNavDestinationSelected(item, navController);
        });
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