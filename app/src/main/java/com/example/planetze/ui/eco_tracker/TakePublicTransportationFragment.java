package com.example.planetze.ui.eco_tracker;

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
import com.example.planetze.classes.EcoTracker.Category.Transportation.TakePublicTransportation;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.databinding.FragmentTakePublicTransportationBinding;

import java.util.Arrays;
import java.util.List;

public class TakePublicTransportationFragment extends BaseActivityFragment {

    private FragmentTakePublicTransportationBinding binding;
    private List<Button> buttons;
    private String type;
    private int hour;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTakePublicTransportationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setOnClickListeners();

        binding.back.setOnClickListener(this::handleBackButtonClick);

        binding.submit.setOnClickListener(this::handleNextButtonClick);

        return view;
    }

    private void setOnClickListeners() {
        buttons = Arrays.asList(binding.option1, binding.option2, binding.option3);
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
            hour = Integer.parseInt(binding.hour.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please enter a valid number of hours", Toast.LENGTH_SHORT).show();
        }
        if (hour <= 0) {
            Toast.makeText(getActivity(), "Please enter a valid number of hours", Toast.LENGTH_SHORT).show();
        } else {
            Date date = Date.today();
            TakePublicTransportation activity = new TakePublicTransportation(type, hour);
            currentUser.addActivity(date, activity);
            databaseManager.add(currentUser);

            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.eco_tracker);
        }
    }
}
