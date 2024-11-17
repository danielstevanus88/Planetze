package com.example.planetze;

import android.os.Bundle;
import android.view.View;

import com.example.planetze.ui.home.CarbonInfoFragment;
import com.example.planetze.ui.login.LoginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.planetze.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CarbonInfoFragment fragment = new CarbonInfoFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Set up navigation with the BottomNavigationView
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    // Method to remove the CarbonInfoFragment
    public void onAcknowledgeButtonClick(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager(); // Or getFragmentManager() if you're in a Fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainerView);

        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }

}