package com.example.planetze.ui.eco_tracker.main;

import static com.example.planetze.classes.EcoTracker.ActivitiesCalculator.calculateTotalEmission;
import static com.example.planetze.classes.EcoTracker.ActivitiesFilter.filterActivitiesByRangeOfDate;
import static com.example.planetze.classes.EcoTracker.Date.today;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.planetze.R;
import com.example.planetze.classes.EcoTracker.ActivitiesConverter;
import com.example.planetze.classes.EcoTracker.ActivitiesFilter;
import com.example.planetze.classes.EcoTracker.Category.Transportation.CyclingOrWalking;
import com.example.planetze.classes.EcoTracker.Category.Transportation.TakePublicTransportation;
import com.example.planetze.classes.EcoTracker.DailyActivity;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.classes.FirebaseListenerDailyActivity;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.ScreenUtilities.ViewGenerator;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;
import com.example.planetze.databinding.FragmentEcoTrackerBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class EcoTrackerFragment extends Fragment implements FirebaseListenerDailyActivity {

    private HashMap<Date, List<DailyActivity>> activities;
    private FragmentEcoTrackerBinding binding;
    private PieChart pieChart;
    private View view;
    private Date currentSelectedDate;

    public EcoTrackerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEcoTrackerBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        activities = ActivitiesConverter.getActivitiesWithClassDate(LoginManager.getCurrentUser().getActivities());

        pieChart = binding.piechart;
        setPieChart();

        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_eco_tracker_to_activity_list);
            }
        });

        UserDatabaseManager.subscribeAsDailyActivityListener(this);
        Log.d("hehe", "subscribed to database manager");

        LinearLayout buttonPickADate = view.findViewById(R.id.buttonPickDate);
        EditText textPickADate = view.findViewById(R.id.editTextDate);
        textPickADate.setText(today().toString());
        textPickADate.setKeyListener(null);

        this.currentSelectedDate = Date.today();
        buttonPickADate.setOnClickListener(event -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getActivity(),
                    (v, selectedYear, selectedMonth, selectedDay) -> {
                        // Month is 0-based, add 1 to display correctly
                        Date selectedDate = new Date(selectedDay, selectedMonth + 1, selectedYear);
                        textPickADate.setText(selectedDate.toString());
                        Toast.makeText(getActivity(), "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                        this.currentSelectedDate = selectedDate;
                        showActivitiesOnDate(selectedDate, getActivity());
                    },
                    year, month, day);
            datePickerDialog.show();

        });
        textPickADate.setOnClickListener(event -> {
            buttonPickADate.callOnClick();
        });

        Button addHabitButton = view.findViewById(R.id.addHabitButton);
        // Set the OnClickListener to handle the button press
        addHabitButton.setOnClickListener(v -> {
            navController.navigate(R.id.habit_selection);
        });

        Button logHabitButton = view.findViewById(R.id.logHabitButton);
        // Set the OnClickListener to handle the button press
        logHabitButton.setOnClickListener(v -> {
            navController.navigate(R.id.log_habit);
        });


        User currentUser = LoginManager.getCurrentUser();
        // TODO: REMOVE THIS
        currentUser.addActivity(new Date(15, 11, 2024), new TakePublicTransportation("aa", 10));
        currentUser.addActivity(new Date(30, 11, 2024), new CyclingOrWalking(10));


        UserDatabaseManager userDatabaseManager = UserDatabaseManager.getInstance();
        userDatabaseManager.add(currentUser);
        showActivitiesOnDate(Date.today(), getActivity());

        return this.view;
    }


    private void showActivitiesOnDate(Date selectedDate, Context context) {
        if (selectedDate == null) return;
        LinearLayout layoutTransportation = view.findViewById(R.id.LayoutTransportation);
        LinearLayout layoutFood = view.findViewById(R.id.LayoutFood);
        LinearLayout layoutConsumption = view.findViewById(R.id.LayoutConsumption);

        // Reset the view (Empty all view, except the first two child (title and "no activity" text)
        ViewGenerator.removeViewsFromIndex(layoutConsumption, 2);
        ViewGenerator.removeViewsFromIndex(layoutFood, 2);
        ViewGenerator.removeViewsFromIndex(layoutTransportation, 2);

        User currentUser = LoginManager.getCurrentUser();
        HashMap<Date, List<DailyActivity>> allActivities =
                ActivitiesFilter.filterActivitiesByRangeOfDate(
                        ActivitiesConverter.getActivitiesWithClassDate(currentUser.getActivities()),
                        selectedDate,
                        selectedDate
                );

        for (Date date : allActivities.keySet()) {
            List<DailyActivity> dailyActivities = allActivities.get(date);
            if (dailyActivities == null) continue;
            for (DailyActivity activity : dailyActivities) {
                CardView cardView = ViewGenerator.createDailyActivityCardView(view, activity, context);
                if (Objects.equals(activity.getCategoryName(), "Food")) {
                    layoutFood.addView(cardView);

                } else if (Objects.equals(activity.getCategoryName(), "Consumption")) {
                    layoutConsumption.addView(cardView);

                } else {
                    layoutTransportation.addView(cardView);
                }

            }
        }

        TextView noConsumptionText = view.findViewById(R.id.noConsumptionText);
        TextView noFoodText = view.findViewById(R.id.noFoodText);
        TextView noTransportationText = view.findViewById(R.id.noTransportationText);

        if (layoutConsumption.getChildCount() > 2) {
            noConsumptionText.setVisibility(View.GONE);
        } else {
            noConsumptionText.setVisibility(View.VISIBLE);
        }
        if (layoutTransportation.getChildCount() > 2) {
            noTransportationText.setVisibility(View.GONE);
        } else {
            noTransportationText.setVisibility(View.VISIBLE);
        }
        if (layoutFood.getChildCount() > 2) {
            noFoodText.setVisibility(View.GONE);
        } else {
            noFoodText.setVisibility(View.VISIBLE);
        }
    }

    private double getTotal() {
        return calculateTotalEmission(filterActivitiesByRangeOfDate(activities, today(), today()));
    }

    @SuppressLint("DefaultLocale")
    private void setPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        float transportation = 0f;
        float foodConsumption = 0f;
        float consumptionAndShopping = 0f;

        List<DailyActivity> dailyActivities = activities.get(this.currentSelectedDate);
        if (dailyActivities != null) {
            for (DailyActivity activity : dailyActivities) {
                switch (activity.getCategoryName()) {
                    case "Transportation":
                        transportation += (float) activity.getEmission();
                        break;
                    case "Food":
                        foodConsumption += (float) activity.getEmission();
                        break;
                    case "Consumption":
                        consumptionAndShopping += (float) activity.getEmission();
                        break;
                }
            }
        }

        entries.add(new PieEntry(transportation, "Transportation"));
        entries.add(new PieEntry(foodConsumption, "Food Consumption"));
        entries.add(new PieEntry(consumptionAndShopping, "Consumption and Shopping"));

        PieDataSet dataSet = new PieDataSet(entries, "Daily CO2e Emissions");
        dataSet.setColors(
                Color.parseColor("#FF0000"),
                Color.parseColor("#FFAA00"),
                Color.parseColor("#009999")
        );

        // Customize text appearance (font, size, color)
        dataSet.setValueTextSize(18f);
        dataSet.setValueTextColor(getResources().getColor(R.color.white));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dataSet.setValueTypeface(getResources().getFont(R.font.poppins_bold));
        }

        // Legend customization
        Legend legend = pieChart.getLegend();
        legend.setTextSize(16f);
        legend.setTextColor(getResources().getColor(R.color.alternativeDarkColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            legend.setTypeface(getResources().getFont(R.font.poppins_regular));
        }

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        pieChart.setCenterText(String.format("%.2f", getTotal()) + " kg");
        pieChart.setCenterTextSize(22f);
        pieChart.setCenterTextColor(getResources().getColor(R.color.alternativeDarkColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pieChart.setCenterTextTypeface(getResources().getFont(R.font.poppins_bold));
        }

        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(false);
        legend.setEnabled(false);
        pieChart.setDrawCenterText(true);
        pieChart.setHoleColor(Color.TRANSPARENT);

        pieChart.invalidate(); // Refresh the chart
    }


    @Override
    public void update() {
//        Log.d("Hi", "update: " + (this.currentSelectedDate == null? "a" : this.currentSelectedDate.toString()) );
        this.activities = ActivitiesConverter.getActivitiesWithClassDate(LoginManager.getCurrentUser().getActivities());
        setPieChart();
        showActivitiesOnDate(this.currentSelectedDate, getActivity());
    }


}