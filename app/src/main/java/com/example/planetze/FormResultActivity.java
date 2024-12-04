package com.example.planetze;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.planetze.classes.AnnualEmission.AnnualEmissionCalculator;
import com.example.planetze.classes.EcoTracker.DailyActivity;
import com.example.planetze.classes.GlobalAverages;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.databinding.FragmentEcoTrackerBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class  FormResultActivity extends AppCompatActivity {

    PieChart pieChart;

    float transportation;
    float foodConsumption;
    float consumptionAndShopping;
    float housing;

    float total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        User user =LoginManager.getCurrentUser();

        transportation = (float) AnnualEmissionCalculator.getTransportationResult(user);
        foodConsumption = (float) AnnualEmissionCalculator.getFoodResult(user);
        consumptionAndShopping = (float) AnnualEmissionCalculator.getConsumptionResult(user);
        housing = (float) AnnualEmissionCalculator.getHousingResult(user);
        total = transportation + foodConsumption + consumptionAndShopping + housing;

        pieChart = findViewById(R.id.annualpiechart);
        setPieChart();

        Button nextButton = findViewById(R.id.buttonNextToMain);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        GlobalAverages.initialize(this);

        TextView textPercentage = findViewById(R.id.textPercentage);
        String country = user.getCountry();
        double ratio = total/GlobalAverages.getAverageOfCountry(country)/1000 * 100;
        String percentage = String.format("%.1f", total/GlobalAverages.getAverageOfCountry(country)/1000 * 100);
        TextView textCountry = findViewById(R.id.textCountry);
        String suffix = "";
        if (ratio > 100) {
            ratio -= 100;
            suffix = "more";
        } else {
            ratio = 100 - ratio;
            suffix = "less";
        }
        String displayPercentage =String.format("%.1f", ratio) + "%";
        textPercentage.setText(displayPercentage);


        String displayCountry =  suffix + " than the average in " + country;
        textCountry.setText(displayCountry);



    }


    public void setPieChart(){
        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(transportation, "Transportation"));
        entries.add(new PieEntry(foodConsumption, "Food Consumption"));
        entries.add(new PieEntry(consumptionAndShopping, "Consumption and Shopping"));
        entries.add(new PieEntry(Math.max(housing, 0), "Housing"));

        PieDataSet dataSet = new PieDataSet(entries, "Daily CO2e Emissions");
        dataSet.setColors(
                Color.parseColor("#FF0000"),
                Color.parseColor("#FFAA00"),
                Color.parseColor("#009999"),
                Color.parseColor("#A9BCD0")
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

        pieChart.setCenterText(String.format("%.2f", total) + " kg");
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
}