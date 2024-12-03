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
import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.DieselCar;
import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.ElectricCar;
import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.GasolineCar;
import com.example.planetze.classes.EcoTracker.Category.Transportation.CarType.HybridCar;
import com.example.planetze.classes.EcoTracker.Category.Transportation.DrivePersonalVehicle;
import com.example.planetze.classes.EcoTracker.DailyActivity;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.databinding.FragmentDrivePersonalVehicleBinding;
import com.example.planetze.ui.eco_tracker.main.EcoTrackerFragment;

import java.util.Arrays;
import java.util.List;

public class DrivePersonalVehicleFragment extends BaseActivityFragment {

    private FragmentDrivePersonalVehicleBinding binding;
    private List<Button> buttons;
    private double distance;
    private int type;
    private DrivePersonalVehicle activity;

    private DailyActivity editDailyActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDrivePersonalVehicleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setOnClickListeners();

        binding.submit.setOnClickListener(this::handleNextButtonClick);



        if (getArguments() != null && getArguments().get("dailyActivity") != null) {
            editDailyActivity = (DailyActivity) getArguments().get("dailyActivity");

            binding.distance.setText(String.valueOf(editDailyActivity.getDistance()));

            int originalOption = editDailyActivity.getCar().getCarTypeId();
            buttons.get(originalOption - 1).setBackgroundResource(R.drawable.clicked_button);
            buttons.get(originalOption - 1).setTextColor(getResources().getColor(R.color.white));
            type = originalOption;
        }
        else if (currentUser.getQuestionnaireAnswers().containsKey("q2")
                && currentUser.getQuestionnaireAnswers().get("q2") >= 1
                && currentUser.getQuestionnaireAnswers().get("q2") <= 4) {

        binding.submit.setOnClickListener(this::handleNextButtonClick);
            int originalOption = currentUser.getQuestionnaireAnswers().get("q2");
            handleOptionButtonClick(buttons.get(originalOption - 1));
            type = originalOption;
        }


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
            type = Arrays.asList(options).indexOf(clickedButton.getText().toString());
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
        } else if (type <= 0 || type >= 5) {
            Toast.makeText(getActivity(), "Please select an option", Toast.LENGTH_SHORT).show();
        } else {
            switch (type) {
                case 1:
                    activity = new DrivePersonalVehicle(distance, new GasolineCar());
                    break;
                case 2:
                    activity = new DrivePersonalVehicle(distance, new DieselCar());
                    break;
                case 3:
                    activity = new DrivePersonalVehicle(distance, new HybridCar());
                    break;
                case 4:
                    activity = new DrivePersonalVehicle(distance, new ElectricCar());
                    break;
            }
            Date date = EcoTrackerFragment.getCurrentSelectedDate();
            currentUser.addActivity(date, activity);
            if(editDailyActivity != null){
                currentUser.removeActivity(editDailyActivity.getUuid());
            }

            currentUser.addQuestionnaireAnswer("q2", type);

            navigateToMain();
        }
    }
}