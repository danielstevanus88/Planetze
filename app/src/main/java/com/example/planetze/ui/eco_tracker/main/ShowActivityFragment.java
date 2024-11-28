package com.example.planetze.ui.eco_tracker.main;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import androidx.navigation.Navigation;

import com.example.planetze.HabitSelectionActivity;
import com.example.planetze.R;
import com.example.planetze.classes.EcoTracker.ActivitiesConverter;
import com.example.planetze.classes.EcoTracker.ActivitiesFilter;
import com.example.planetze.classes.EcoTracker.Category.Consumption.ActivityConsumption;
import com.example.planetze.classes.EcoTracker.Category.Consumption.BuyClothes;
import com.example.planetze.classes.EcoTracker.Category.Food.ActivityFood;
import com.example.planetze.classes.EcoTracker.Category.Food.EatBeef;
import com.example.planetze.classes.EcoTracker.Category.Food.EatPork;
import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.GasolineCar;
import com.example.planetze.classes.EcoTracker.Category.Transportation.CyclingOrWalking;
import com.example.planetze.classes.EcoTracker.Category.Transportation.DrivePersonalVehicle;
import com.example.planetze.classes.EcoTracker.Category.Transportation.TakePublicTransportation;
import com.example.planetze.classes.EcoTracker.DailyActivity;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.ScreenUtilities.ViewGenerator;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;
import com.example.planetze.databinding.FragmentShowActivityBinding;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ShowActivityFragment extends Fragment {

    PieChart pieChart;
    private FragmentShowActivityBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentShowActivityBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        pieChart = binding.piechart;
        setPieChart();

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.activity_list);
            }
        });

        LinearLayout buttonPickADate = view.findViewById(R.id.buttonPickDate);
        EditText textPickADate = view.findViewById(R.id.editTextDate);
        textPickADate.setKeyListener(null);

        buttonPickADate.setOnClickListener(event -> {
            // Open DatePickerDialog
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

                        showActivitiesOnDate(selectedDate, getActivity(), view);
                    },
                    year, month, day);
            datePickerDialog.show();

        });
        textPickADate.setOnClickListener(event -> {
            buttonPickADate.callOnClick();
        });

        Button myButton = view.findViewById(R.id.addHabit);
        // Set the OnClickListener to handle the button press
        myButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HabitSelectionActivity.class);
            startActivity(intent);
        });

        User currentUser = LoginManager.getCurrentUser();
        currentUser.addActivity(new Date(25, 11, 2024), new TakePublicTransportation("Train", 10));
        currentUser.addActivity(new Date(25, 11, 2024), new CyclingOrWalking(2));
        currentUser.addActivity(new Date(25, 11, 2024), new DrivePersonalVehicle(10, new GasolineCar()));
        currentUser.addActivity(new Date(25, 11, 2024), new EatBeef(2));
        currentUser.addActivity(new Date(26, 11, 2024), new EatPork(1));
        currentUser.addActivity(new Date(29, 11, 2024), new BuyClothes(10));


        UserDatabaseManager userDatabaseManager = UserDatabaseManager.getInstance();
        userDatabaseManager.add(currentUser);
        showActivitiesOnDate(Date.today(), getActivity(), view);

        return view;
    }


    private void showActivitiesOnDate(Date selectedDate, Context context, View view) {
        LinearLayout layoutTransportation = view.findViewById(R.id.LayoutTransportation);
        LinearLayout layoutFood = view.findViewById(R.id.LayoutFood);
        LinearLayout layoutConsumption = view.findViewById(R.id.LayoutConsumption);

        // Reset the view (Empty all view, except the first two child (title and "no activity" text)
        ViewGenerator.removeAllChildExceptTheFirstXChild(layoutConsumption, 2);
        ViewGenerator.removeAllChildExceptTheFirstXChild(layoutFood, 2);
        ViewGenerator.removeAllChildExceptTheFirstXChild(layoutTransportation, 2);

        User currentUser = LoginManager.getCurrentUser();
        HashMap<Date, List<DailyActivity>> allActivities =
                ActivitiesFilter.filterActivitiesByRangeOfDate(
                        ActivitiesConverter.getActivitiesWithClassDate(currentUser.getActivities()),
                        selectedDate,
                        selectedDate
                );

        for (Date date : allActivities.keySet()) {
            List<DailyActivity> dailyActivities = allActivities.get(date);
            for (DailyActivity activity : dailyActivities) {
                CardView cardView = ViewGenerator.createDailyACtivityCardView(view, activity, context);
                if (activity instanceof ActivityFood) {
                    layoutFood.addView(cardView);

                } else if (activity instanceof ActivityConsumption) {
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

    private void setPieChart() {
        // TODO: Set the pie chart data from the activities
        float transportation = (float) 10;
        float foodConsumption = (float) 10;
        float consumptionAndShopping = (float) 10;

        pieChart.addPieSlice(new PieModel("Transportation", transportation, Color.parseColor("#FF0000")));
        pieChart.addPieSlice(new PieModel("Food Consumption", foodConsumption, Color.parseColor("#FFAA00")));
        pieChart.addPieSlice(new PieModel("Consumption and Shopping", consumptionAndShopping, Color.parseColor("#009999")));
    }
}