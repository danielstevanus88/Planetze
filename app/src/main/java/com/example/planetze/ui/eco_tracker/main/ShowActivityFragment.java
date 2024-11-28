package com.example.planetze.ui.eco_tracker.main;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planetze.HabitSelectionActivity;
import com.example.planetze.LogHabitActivity;
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
import com.example.planetze.classes.FirebaseListenerDailyActivity;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.ScreenUtilities.ViewGenerator;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowActivityFragment extends Fragment implements FirebaseListenerDailyActivity {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private Date currentSelectedDate;

    public ShowActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowActivityFragment newInstance(String param1, String param2) {
        ShowActivityFragment fragment = new ShowActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_show_activity, container, false);

        UserDatabaseManager.subscribeAsDailyActivityListener(this);
        Log.d("hehe", "subscribed to database manager");

        LinearLayout buttonPickADate = view.findViewById(R.id.buttonPickDate);
        EditText textPickADate = view.findViewById(R.id.editTextDate);
        textPickADate.setKeyListener(null);

        buttonPickADate.setOnClickListener( event -> {
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
        textPickADate.setOnClickListener( event -> {
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

        Button addHabitButton = view.findViewById(R.id.addHabit);
        // Set the OnClickListener to handle the button press
        addHabitButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HabitSelectionActivity.class);
            startActivity(intent);
        });

        Button logHabitButton = view.findViewById(R.id.buttonToLogHabit);
        // Set the OnClickListener to handle the button press
        logHabitButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LogHabitActivity.class);
            startActivity(intent);
        });


        User currentUser = LoginManager.getCurrentUser();
        currentUser.addActivity(new Date(15, 11, 2024), new TakePublicTransportation(10));
        currentUser.addActivity(new Date(30, 11, 2024), new CyclingOrWalking());


        UserDatabaseManager userDatabaseManager = UserDatabaseManager.getInstance();
        userDatabaseManager.add(currentUser);
        showActivitiesOnDate(Date.today(), getActivity());

        return this.view;
    }


    private void showActivitiesOnDate(Date selectedDate, Context context) {
        if(selectedDate == null) return;
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

        for(Date date : allActivities.keySet()){
            List<DailyActivity> dailyActivities = allActivities.get(date);
            if (dailyActivities == null) continue;
            for(DailyActivity activity : dailyActivities){
                CardView cardView = ViewGenerator.createDailyACtivityCardView(view, activity, context);
                if(Objects.equals(activity.getCategoryName(), "Food")) {
                    layoutFood.addView(cardView);

                } else if(Objects.equals(activity.getCategoryName(), "Consumption")) {
                    layoutConsumption.addView(cardView);

                } else {
                    layoutTransportation.addView(cardView);
                }

            }
        }

        TextView noConsumptionText = view.findViewById(R.id.noConsumptionText);
        TextView noFoodText = view.findViewById(R.id.noFoodText);
        TextView noTransportationText = view.findViewById(R.id.noTransportationText);

        if(layoutConsumption.getChildCount() > 2){
            noConsumptionText.setVisibility(View.GONE);
        } else {
            noConsumptionText.setVisibility(View.VISIBLE);
        }
        if(layoutTransportation.getChildCount() > 2){
            noTransportationText.setVisibility(View.GONE);
        } else {
            noTransportationText.setVisibility(View.VISIBLE);
        }
        if(layoutFood.getChildCount() > 2){
            noFoodText.setVisibility(View.GONE);
        } else {
            noFoodText.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void update() {
//        Log.d("Hi", "update: " + (this.currentSelectedDate == null? "a" : this.currentSelectedDate.toString()) );
        showActivitiesOnDate(this.currentSelectedDate, getActivity());
    }




}