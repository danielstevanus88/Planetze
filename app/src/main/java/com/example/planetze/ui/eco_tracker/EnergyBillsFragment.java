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
import com.example.planetze.classes.EcoTracker.Category.Consumption.EnergyBill;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.databinding.FragmentEnergyBillsBinding;

import java.util.Arrays;
import java.util.List;

public class EnergyBillsFragment extends BaseActivityFragment {

    private FragmentEnergyBillsBinding binding;
    private List<Button> buttons;
    private String type;
    private double amount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEnergyBillsBinding.inflate(inflater, container, false);
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
            amount = Double.parseDouble(binding.amount.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please enter a valid bill amount", Toast.LENGTH_SHORT).show();
        }
        if (amount <= 0) {
            Toast.makeText(getActivity(), "Please enter a valid bill amount", Toast.LENGTH_SHORT).show();
        } else {
            Date date = Date.today();
            EnergyBill activity = new EnergyBill(type, amount);
            currentUser.addActivity(date, activity);
            databaseManager.add(currentUser);

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }

}
