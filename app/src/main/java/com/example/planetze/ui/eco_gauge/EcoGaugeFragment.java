package com.example.planetze.ui.eco_gauge;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planetze.R;
import com.example.planetze.classes.EcoTracker.ActivitiesCalculator;
import com.example.planetze.classes.EcoTracker.ActivitiesConverter;
import com.example.planetze.classes.EcoTracker.ActivitiesFilter;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.classes.EcoTracker.DailyActivity;

import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EcoGaugeFragment extends Fragment {

    private TextView totalEmissionsTextView;
    private Spinner timePeriodSpinner;
    private Button calculateButton;
    private BarChart emissionsBarChart; // BarChart instance

    private String selectedTimePeriod = "Daily"; // Default selection

    public EcoGaugeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eco_gauge, container, false);

        return inflater.inflate(R.layout.fragment_eco_gauge, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        timePeriodSpinner = view.findViewById(R.id.time_period_spinner);
        calculateButton = view.findViewById(R.id.calculate_button);
        emissionsBarChart = view.findViewById(R.id.emissions_bar_chart);

        // Set up the spinner
        String[] timePeriods = {"Daily", "Weekly", "Monthly", "Yearly"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, timePeriods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timePeriodSpinner.setAdapter(adapter);

        // Listen for spinner selection changes
        timePeriodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTimePeriod = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Handle Calculate button click
        calculateButton.setOnClickListener(v -> calculateEmissions());
    }


    private void calculateEmissions() {
        try {
            Date today = Date.today();
            Date startDate;

            // Calculate the start date based on the selected time period
            switch (selectedTimePeriod) {
                case "Weekly":
                    startDate = new Date(today.getDay() - 7, today.getMonth(), today.getYear());
                    break;
                case "Monthly":
                    startDate = new Date(today.getDay(), today.getMonth() - 1, today.getYear());
                    break;
                case "Yearly":
                    startDate = new Date(today.getDay(), today.getMonth(), today.getYear() - 1);
                    break;
                default: // "Daily"
                    startDate = new Date(today.getDay() - 1, today.getMonth(), today.getYear());
            }

            // Correct for negative days or months
            if (startDate.getDay() <= 0) {
                startDate.setMonth(startDate.getMonth() - 1);
                startDate.setDay(Date.getDaysInMonth(startDate.getMonth(), startDate.getYear()) + startDate.getDay());
            }
            if (startDate.getMonth() <= 0) {
                startDate.setYear(startDate.getYear() - 1);
                startDate.setMonth(12 + startDate.getMonth());
            }

            User user = LoginManager.getCurrentUser();
            HashMap<String, List<DailyActivity>> list = user.getActivities();
            HashMap<Date, List<DailyActivity>> activities = ActivitiesConverter.getActivitiesWithClassDate(list);

            // Filter activities within the selected range
            HashMap<Date, List<DailyActivity>> filteredActivities = ActivitiesFilter.filterActivitiesByRangeOfDate(activities, startDate, today);

            displayBarChart(ActivitiesCalculator.calculateEmissionsByCategory(filteredActivities));

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error calculating emissions", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayBarChart(HashMap<String, Double> activities) {
        HashMap<String, Double> emissionsByCategory = activities;

        List<BarEntry> entries = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        int index = 0;
        for (String category : emissionsByCategory.keySet()) {
            entries.add(new BarEntry(index++, emissionsByCategory.get(category).floatValue()));
            labels.add(category); // Collect category labels for X-axis
        }

        // Create the dataset
        BarDataSet dataSet = new BarDataSet(entries, "Emissions by Category");
        dataSet.setValueTextColor(android.R.color.black); // Set value text color
        dataSet.setValueTextSize(12f); // Set value text size

        // Custom colors for categories
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#009999")); // Red for Transportation
        colors.add(Color.parseColor("#FF0000")); // Yellow for Food Consumption
        colors.add(Color.parseColor("#FFC107")); // Blue for Consumption and Shopping
        dataSet.setColors(colors);

        // Create BarData object
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.9f); // Set bar width for better spacing
        emissionsBarChart.setData(barData);

        // Customize X-axis
        XAxis xAxis = emissionsBarChart.getXAxis();
        xAxis.setGranularity(1f); // Ensure each bar represents one category
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels)); // Set custom labels
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // Position X-axis at the bottom
        xAxis.setDrawGridLines(false); // Disable grid lines for a cleaner look
        xAxis.setTextColor(Color.parseColor("#000000")); // Set X-axis text color
        xAxis.setTextSize(11f); // Set X-axis text size


        // Customize Y-axis
        emissionsBarChart.getAxisLeft().setTextColor(Color.parseColor("#000000")); // Set left Y-axis text color
        emissionsBarChart.getAxisLeft().setTextSize(12f); // Set left Y-axis text size
        emissionsBarChart.getAxisLeft().setGranularity(1f); // Ensure granularity
        emissionsBarChart.getAxisRight().setEnabled(false); // Disable right Y-axis for simplicity
        emissionsBarChart.setExtraBottomOffset(0f);

        // Disable the legend
        emissionsBarChart.getLegend().setEnabled(false); // Hide the legend

        // Refresh the chart
        emissionsBarChart.setFitBars(true); // Make bars fit the X-axis
        emissionsBarChart.invalidate(); // Refresh the chart
        emissionsBarChart.getDescription().setEnabled(false); // Disable description
        emissionsBarChart.animateY(1500);
    }







}
