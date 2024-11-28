package com.example.planetze.ui.eco_tracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.planetze.HabitSelectionActivity;
import com.example.planetze.LogHabitActivity;
import com.example.planetze.R;
import com.example.planetze.databinding.FragmentEcoTrackerBinding;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class EcoTrackerFragment extends Fragment {

    PieChart pieChart;
    private FragmentEcoTrackerBinding binding;
    private User user;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEcoTrackerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        User user = LoginManager.getCurrentUser();
        pieChart = binding.piechart;
        setPieChart();

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
                navController.navigate(R.id.activity_list);
            }
        });

        Button myButton = view.findViewById(R.id.addHabit);
        // Set the OnClickListener to handle the button press
        myButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HabitSelectionActivity.class);
            startActivity(intent);
        });
        Button loghabit = view.findViewById(R.id.logHabitButton);
        // Set the OnClickListener to handle the button press
        loghabit.setOnClickListener(v -> {
            if (user.habit == null || user.habit.isEmpty()){
                Toast.makeText(getActivity(), "You have not adopted" +
                        " a habit yet", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(getActivity(), LogHabitActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    protected void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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