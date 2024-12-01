package com.example.planetze;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planetze.classes.EcoTracker.DailyActivity;
import com.example.planetze.classes.EcoTracker.Habit.FoodWasteHabit;
import com.example.planetze.classes.EcoTracker.Habit.Habit;
import com.example.planetze.classes.EcoTracker.Habit.LeftoverHabit;
import com.example.planetze.classes.EcoTracker.Habit.LessPackageHabit;
import com.example.planetze.classes.EcoTracker.Habit.NoCarHabit;
import com.example.planetze.classes.EcoTracker.Habit.NoLightsHabit;
import com.example.planetze.classes.EcoTracker.Habit.PlasticUtensilHabit;
import com.example.planetze.classes.EcoTracker.Habit.PublicTransportHabit;
import com.example.planetze.classes.EcoTracker.Habit.RecycleHabit;
import com.example.planetze.classes.EcoTracker.Habit.RecyclerAdaptor;
import com.example.planetze.classes.EcoTracker.Habit.RedMeatHabit;
import com.example.planetze.classes.EcoTracker.Habit.SecondHandHabit;
import com.example.planetze.classes.EcoTracker.Habit.SustainableProductHabit;
import com.example.planetze.classes.EcoTracker.Habit.ThermostatHabit;
import com.example.planetze.classes.EcoTracker.Habit.UnplugHabit;
import com.example.planetze.classes.EcoTracker.Habit.VeganHabit;
import com.example.planetze.classes.EcoTracker.Habit.WalkorBikeHabit;
import com.example.planetze.classes.EcoTracker.Habit.WaterHabit;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HabitSelectionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdaptor recyclerAdaptor;
    private ArrayList<Habit> habitList;
    private Spinner spinnerCategory;
    private Spinner spinnerImpact;
    private SearchView searchView;
    private User user;
    private UserDatabaseManager databaseManager = UserDatabaseManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_habit_selection);
        searchView = findViewById(R.id.search_view);
        Switch switchButton = findViewById(R.id.switch1);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                switchButton.setChecked(false);
                filterlist(query,spinnerCategory.getSelectedItem().toString(), spinnerImpact.getSelectedItem().toString());
                return true;
            }
        });
        spinnerImpact = findViewById(R.id.spinner_impact);
        spinnerImpact.setSelection(0);
        spinnerImpact.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switchButton.setChecked(false);
                String selectedImpact = parent.getItemAtPosition(position).toString();
                String selectedCategory = spinnerCategory.getSelectedItem().toString();
                String query = searchView.getQuery().toString();
                filterlist(query, selectedCategory,selectedImpact);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerCategory.setSelection(0);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switchButton.setChecked(false);
                String selectedImpact = spinnerImpact.getSelectedItem().toString();
                String selectedCategory = parent.getItemAtPosition(position).toString();
                String query = searchView.getQuery().toString();
                filterlist(query, selectedCategory, selectedImpact);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        recyclerView = findViewById(R.id.recycler_view);  // Reference to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        habitList = new ArrayList<>();
        habitList.add(new FoodWasteHabit());
        habitList.add(new LeftoverHabit());
        habitList.add(new LessPackageHabit());
        habitList.add(new NoCarHabit());
        habitList.add(new NoLightsHabit());
        habitList.add(new PlasticUtensilHabit());
        habitList.add(new PublicTransportHabit());
        habitList.add(new RecycleHabit());
        habitList.add(new RedMeatHabit());
        habitList.add(new SecondHandHabit());
        habitList.add(new SustainableProductHabit());
        habitList.add(new ThermostatHabit());
        habitList.add(new UnplugHabit());
        habitList.add(new VeganHabit());
        habitList.add(new WalkorBikeHabit());
        habitList.add(new WaterHabit());

        recyclerAdaptor = new RecyclerAdaptor(habitList);
        recyclerView.setAdapter(recyclerAdaptor);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HabitSelectionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            user = LoginManager.getCurrentUser();
            int transportcounter = 0;
            int consumptioncounter = 0;
            int energycounter = 0;
            int foodcounter = 0;
            if (isChecked) {
                HashMap<String, List<DailyActivity>> activities = user.getActivities();
                for (Map.Entry<String, List<DailyActivity>> entry : activities.entrySet()) {
                    List<DailyActivity> dailyActivities = entry.getValue();
                    for (DailyActivity activity : dailyActivities) {
                        if (activity.getCategoryName().equals("Transportation")) {
                            transportcounter++;
                        } else if (activity.getCategoryName().equals("Consumption")) {
                            consumptioncounter++;
                        } else if (activity.getCategoryName().equals("Energy")) {
                            energycounter++;
                        }
                        else{
                            foodcounter++;
                        }
                    }
                }
                int maxCount = Math.max(Math.max(transportcounter, consumptioncounter),
                        Math.max(energycounter, foodcounter));
                List<String> recommendations = new ArrayList<>();
                if (transportcounter == maxCount && maxCount > 0) {
                    recommendations.add("Transportation");
                }
                if (consumptioncounter == maxCount && maxCount > 0) {
                    recommendations.add("Consumption");
                }
                if (energycounter == maxCount && maxCount > 0) {
                    recommendations.add("Energy");
                }
                if (foodcounter == maxCount && maxCount > 0) {
                    recommendations.add("Food");
                }
                if(maxCount == 0){
                    Toast.makeText(HabitSelectionActivity.this, "No recommended categories", Toast.LENGTH_LONG).show();
                }
                // Handle filtering and messaging
                else if (!recommendations.isEmpty()) {
                    String message = "Recommended categories: " + String.join(", ", recommendations);
                    Toast.makeText(HabitSelectionActivity.this, message, Toast.LENGTH_LONG).show();
                    String selectedImpact = spinnerImpact.getSelectedItem().toString();
                    String query = searchView.getQuery().toString();
                    filterlist2(query, recommendations, selectedImpact);
                    // Filter the list based on the first recommendation (if needed)
                }
            }
            else{
                filterlist("",spinnerCategory.getSelectedItem().toString(), spinnerImpact.getSelectedItem().toString());
            }
        });

    }

    private void filterlist(String text, String category, String impact) {
        ArrayList<Habit> filtered = new ArrayList<>();
        for (Habit habit : habitList) {
            boolean matchesCategory = category.equals("Select category") || habit.getCategory().equals(category);
            boolean matchesImpact = impact.equals("Select impact level") || habit.getImpactLevel().equals(impact);
            boolean matchesQuery = habit.getName().toLowerCase().contains(text.toLowerCase());

            if (matchesCategory && matchesImpact && matchesQuery) {
                filtered.add(habit);
            }
        }
        if (filtered.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
            recyclerAdaptor.setFilteredList(new ArrayList<Habit>());
        }
        else {
            recyclerAdaptor.setFilteredList(filtered);
        }
    }


    private void filterlist2(String text, List<String> categories, String impacts) {
        ArrayList<Habit> filtered = new ArrayList<>();

        for (Habit habit : habitList) {
            boolean matchesCategory = categories.isEmpty() || categories.contains("Select category") || categories.contains(habit.getCategory());
            boolean matchesImpact = impacts.equals("Select impact level") || habit.getImpactLevel().equals(impacts);
            boolean matchesQuery = habit.getName().toLowerCase().contains(text.toLowerCase());

            if (matchesCategory && matchesImpact && matchesQuery) {
                filtered.add(habit);
            }
        }

        if (filtered.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
            recyclerAdaptor.setFilteredList(new ArrayList<Habit>());
        } else {
            recyclerAdaptor.setFilteredList(filtered);
        }
    }
}