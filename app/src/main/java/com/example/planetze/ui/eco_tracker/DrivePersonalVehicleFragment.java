package com.example.planetze.ui.eco_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.planetze.MainActivity;
import com.example.planetze.R;
import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.DieselCar;
import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.ElectricCar;
import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.GasolineCar;
import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.HybridCar;
import com.example.planetze.classes.EcoTracker.Category.Transportation.DrivePersonalVehicle;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.databinding.FragmentDrivePersonalVehicleBinding;

import java.util.Arrays;
import java.util.List;

public class DrivePersonalVehicleFragment extends BaseActivityFragment {

    private FragmentDrivePersonalVehicleBinding binding;
    private List<Button> buttons;
    private double distance;
    private int option;
    private DrivePersonalVehicle activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDrivePersonalVehicleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setOnClickListeners();

        if (currentUser.questionnaireAnswers.containsKey("q2")
                && currentUser.questionnaireAnswers.get("q2") >= 1
                && currentUser.questionnaireAnswers.get("q2") <= 4) {

            int originalOption = currentUser.questionnaireAnswers.get("q2");
            buttons.get(originalOption - 1).setBackgroundResource(R.drawable.clicked_button);
            buttons.get(originalOption - 1).setTextColor(getResources().getColor(R.color.white));
            option = originalOption;
        }

        binding.back.setOnClickListener(this::handleBackButtonClick);

        binding.submit.setOnClickListener(this::handleNextButtonClick);

        return view;
    }

    private void setOnClickListeners() {
        buttons = Arrays.asList(binding.option1, binding.option2, binding.option3, binding.option4);
        for (Button button : buttons) {
            button.setOnClickListener(this::handleOptionButtonClick);
        }
    }

    private void handleOptionButtonClick(View view) {
        Button clickedButton = (Button) view;

        String[] options = {"", getString(R.string.gasoline), getString(R.string.diesel), getString(R.string.hybrid), getString(R.string.electric)};

        if (buttons.contains(clickedButton)) {
            setButtons(buttons, clickedButton);
            option = Arrays.asList(options).indexOf(clickedButton.getText().toString());
        }
    }

    private void handleNextButtonClick(View view) {
        try {
            distance = Double.parseDouble(binding.distance.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please enter a valid distance", Toast.LENGTH_SHORT).show();
        }
        if (distance <= 0) {
            Toast.makeText(getActivity(), "Please enter a valid distance", Toast.LENGTH_SHORT).show();
        } else if (option <= 0 || option >= 5) {
            Toast.makeText(getActivity(), "Please select an option", Toast.LENGTH_SHORT).show();
        } else {
            switch (option) {
                case 1:
                    activity = new DrivePersonalVehicle(distance, new GasolineCar());
                case 2:
                    activity = new DrivePersonalVehicle(distance, new DieselCar());
                case 3:
                    activity = new DrivePersonalVehicle(distance, new HybridCar());
                case 4:
                    activity = new DrivePersonalVehicle(distance, new ElectricCar());
            }
            currentUser.addActivity(new Date(), activity);
            databaseManager.add(currentUser);

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }
}