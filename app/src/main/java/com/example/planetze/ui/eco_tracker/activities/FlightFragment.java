package com.example.planetze.ui.eco_tracker.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.planetze.R;
import com.example.planetze.classes.EcoTracker.Category.Transportation.Flight;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.databinding.FragmentFlightBinding;

import java.util.Arrays;
import java.util.List;

public class FlightFragment extends BaseActivityFragment {

    private FragmentFlightBinding binding;
    private List<Button> buttons;
    private int flights;
    private String type;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFlightBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setOnClickListeners();

        binding.submit.setOnClickListener(this::handleNextButtonClick);

        return view;
    }

    private void setOnClickListeners() {
        buttons = Arrays.asList(binding.option1, binding.option2);
        for (Button button : buttons) {
            button.setOnClickListener(this::handleOptionButtonClick);
        }
    }

    private void handleOptionButtonClick(View view) {
        Button clickedButton = (Button) view;

        if (buttons.contains(clickedButton)) {
            setButtons(buttons, clickedButton);
            type = clickedButton.getText().toString();
        }
    }

    private void handleNextButtonClick(View view) {
        try {
            flights = Integer.parseInt(binding.flights.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please enter a valid number of flights", Toast.LENGTH_SHORT).show();
        }
        if (flights <= 0) {
            Toast.makeText(getActivity(), "Please enter a valid number of flights", Toast.LENGTH_SHORT).show();
        } else {
            Date date = Date.today();
            Flight activity = new Flight(type, flights);
            currentUser.addActivity(date, activity);
            databaseManager.add(currentUser);

            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.eco_tracker);
        }
    }

}
