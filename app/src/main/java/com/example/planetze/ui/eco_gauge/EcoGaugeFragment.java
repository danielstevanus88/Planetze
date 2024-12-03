package com.example.planetze.ui.eco_gauge;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EcoGaugeFragment extends Fragment {

    private TextView totalEmissionsTextView;
    private Spinner timePeriodSpinner;
    private Button calculateButton;
    private BarChart emissionsBarChart; // BarChart instance


    private LineChart lineChart;
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
        emissionsBarChart = view.findViewById(R.id.emissions_bar_chart);

        lineChart = view.findViewById(R.id.emissionLineChart);

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
                calculateEmissions();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });



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


        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);
        displayLineChart();
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


    private void displayLineChart(){
        User user = LoginManager.getCurrentUser();
        HashMap<Date, List<DailyActivity>> activities = ActivitiesConverter.getActivitiesWithClassDate(user.getActivities());
        // Case for Daily
        // Show the past 4 days emission
        ArrayList<Entry> valuesForTransportation = new ArrayList<>();
        ArrayList<Entry> valuesForFood = new ArrayList<>();
        ArrayList<Entry> valuesForConsumption = new ArrayList<>();

        int i = 0;

        if(selectedTimePeriod.equals("Weekly")){
            for (Date dateIterate = Date.today().getFourWeekBefore(); dateIterate.compareTo(Date.today()) < 0; i++, dateIterate = dateIterate.getOneWeekAfter()) {
                HashMap<Date, List<DailyActivity>> filteredActivities = ActivitiesFilter.filterActivitiesByRangeOfDate(activities, dateIterate, dateIterate.getOneWeekAfter().getOneDayBefore());
                HashMap<String, Double> emissionsByCategory = ActivitiesCalculator.calculateEmissionsByCategory(filteredActivities);
                valuesForTransportation.add(new Entry(i, (float) ((double) emissionsByCategory.get("Transportation"))));
                valuesForFood.add(new Entry(i, (float) ((double) emissionsByCategory.get("Food"))));
                valuesForConsumption.add(new Entry(i, (float) ((double) emissionsByCategory.get("Consumption"))));
            }
        } else if (selectedTimePeriod.equals("Monthly")){
            for (Date dateIterate = Date.today().getTwelveMonthBefore(); dateIterate.compareTo(Date.today()) < 0; i++, dateIterate = dateIterate.getOneMonthAfter()) {
                HashMap<Date, List<DailyActivity>> filteredActivities = ActivitiesFilter.filterActivitiesByRangeOfDate(activities, dateIterate, dateIterate.getOneMonthAfter().getOneDayBefore());
                HashMap<String, Double> emissionsByCategory = ActivitiesCalculator.calculateEmissionsByCategory(filteredActivities);
                valuesForTransportation.add(new Entry(i, (float) ((double) emissionsByCategory.get("Transportation"))));
                valuesForFood.add(new Entry(i, (float) ((double) emissionsByCategory.get("Food"))));
                valuesForConsumption.add(new Entry(i, (float) ((double) emissionsByCategory.get("Consumption"))));

                Log.d("Datess:", dateIterate.toString());
            }
        } else if(selectedTimePeriod.equals("Yearly")){
            for (Date dateIterate = Date.today().getSixYearsBefore(); dateIterate.compareTo(Date.today()) < 0; i++, dateIterate = dateIterate.getOneYearAfter()) {
                HashMap<Date, List<DailyActivity>> filteredActivities = ActivitiesFilter.filterActivitiesByRangeOfDate(activities, dateIterate, dateIterate.getOneYearAfter().getOneDayBefore());
                HashMap<String, Double> emissionsByCategory = ActivitiesCalculator.calculateEmissionsByCategory(filteredActivities);
                valuesForTransportation.add(new Entry(i, (float) ((double) emissionsByCategory.get("Transportation"))));
                valuesForFood.add(new Entry(i, (float) ((double) emissionsByCategory.get("Food"))));
                valuesForConsumption.add(new Entry(i, (float) ((double) emissionsByCategory.get("Consumption"))));
            }
        } else {
            for (Date dateIterate = Date.today().getAWeekBefore(); dateIterate.compareTo(Date.today()) <= 0; i++, dateIterate = dateIterate.getOneDayAfter()) {
                HashMap<Date, List<DailyActivity>> filteredActivities = ActivitiesFilter.filterActivitiesByRangeOfDate(activities, dateIterate, dateIterate);
                HashMap<String, Double> emissionsByCategory = ActivitiesCalculator.calculateEmissionsByCategory(filteredActivities);
                valuesForTransportation.add(new Entry(i, (float) ((double) emissionsByCategory.get("Transportation"))));
                valuesForFood.add(new Entry(i, (float) ((double) emissionsByCategory.get("Food"))));
                valuesForConsumption.add(new Entry(i, (float) ((double) emissionsByCategory.get("Consumption"))));
            }
        }



        LineDataSet setTransportation;
        LineDataSet setFood;
        LineDataSet setConsumption;

            setTransportation = new LineDataSet(valuesForTransportation, "Transportation");
            setTransportation.setDrawIcons(false);
            setTransportation.enableDashedLine(10f, 5f, 0f);
            setTransportation.enableDashedHighlightLine(10f, 5f, 0f);
            setTransportation.setColor(Color.RED);
            setTransportation.setCircleColor(Color.RED);
            setTransportation.setLineWidth(1f);
            setTransportation.setCircleRadius(3f);
            setTransportation.setDrawCircleHole(false);
            setTransportation.setValueTextSize(9f);
            setTransportation.setDrawFilled(true);
            setTransportation.setFormLineWidth(1f);
            setTransportation.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            setTransportation.setFormSize(15.f);
            setTransportation.setFillColor(Color.RED);
//
            setFood = new LineDataSet(valuesForFood, "Food");
            setFood.setDrawIcons(false);
            setFood.enableDashedLine(10f, 5f, 0f);
            setFood.enableDashedHighlightLine(10f, 5f, 0f);
            setFood.setColor(0xFFFFAA00);
            setFood.setCircleColor(0xFFFFAA00);
            setFood.setLineWidth(1f);
            setFood.setCircleRadius(3f);
            setFood.setDrawCircleHole(false);
            setFood.setValueTextSize(9f);
            setFood.setDrawFilled(true);
            setFood.setFormLineWidth(1f);
            setFood.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            setFood.setFormSize(15.f);
            setFood.setFillColor(0xFFFFAA00);

            setConsumption = new LineDataSet(valuesForConsumption, "Consumption");
            setConsumption.setDrawIcons(false);
            setConsumption.enableDashedLine(10f, 5f, 0f);
            setConsumption.enableDashedHighlightLine(10f, 5f, 0f);
            setConsumption.setColor(0xFF009999);
            setConsumption.setCircleColor(0xFF009999);
            setConsumption.setLineWidth(1f);
            setConsumption.setCircleRadius(3f);
            setConsumption.setDrawCircleHole(false);
            setConsumption.setValueTextSize(9f);
            setConsumption.setDrawFilled(true);
            setConsumption.setFormLineWidth(1f);
            setConsumption.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            setConsumption.setFormSize(15.f);
            setConsumption.setFillColor(0xFF009999);


            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(setTransportation);
            dataSets.add(setFood);
            dataSets.add(setConsumption);
            LineData data = new LineData(dataSets);
if(lineChart.getData() != null) {

    lineChart.clear();
}
            lineChart.setData(data);
            lineChart.animateY(1500);
            lineChart.invalidate();




    }






}
