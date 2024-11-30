package com.example.planetze.ui.eco_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.planetze.MainActivity;
import com.example.planetze.classes.EcoTracker.Category.Consumption.BuyElectronics;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.databinding.FragmentBuyElectronicsBinding;
import com.example.planetze.ui.eco_tracker.main.ShowActivityFragment;

public class BuyElectronicsFragment extends BaseActivityFragment {

    private FragmentBuyElectronicsBinding binding;
    private String type;
    private int num;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBuyElectronicsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.back.setOnClickListener(this::handleBackButtonClick);

        binding.submit.setOnClickListener(this::handleNextButtonClick);

        return view;
    }

    private void handleNextButtonClick(View view) {
        try {
            num = Integer.parseInt(binding.num.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please enter a valid number of devices", Toast.LENGTH_SHORT).show();
        }
        try {
            type = binding.type.getText().toString();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please enter a valid type of device", Toast.LENGTH_SHORT).show();
        }
        if (num <= 0) {
            Toast.makeText(getActivity(), "Please enter a valid number of devices", Toast.LENGTH_SHORT).show();
        } else {
            Date date = ShowActivityFragment.getCurrentSelectedDate();
            BuyElectronics activity = new BuyElectronics(type, num);
            currentUser.addActivity(date, activity);

            handleBackButtonClick(view);
            handleBackButtonClick(view);



        }
    }

}
