package com.example.planetze.ui.eco_tracker;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentEcoTrackerBinding;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class EcoTrackerFragment extends Fragment {

    PieChart pieChart;
    private FragmentEcoTrackerBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEcoTrackerBinding.inflate(inflater, container, false);
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
        float transportation = (float) 1;
        float foodConsumption = (float) 1;
        float consumptionAndShopping = (float) 1;

        pieChart.addPieSlice(new PieModel("Transportation", transportation, Color.parseColor("#FF0000")));
        pieChart.addPieSlice(new PieModel("Food Consumption", foodConsumption, Color.parseColor("#FFAA00")));
        pieChart.addPieSlice(new PieModel("Consumption and Shopping", consumptionAndShopping, Color.parseColor("#009999")));
    }
}
