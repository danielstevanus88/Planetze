package com.example.planetze.ui.eco_tracker.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.planetze.R;
import com.example.planetze.classes.EcoTracker.Category.Consumption.BuyElectronics;
import com.example.planetze.classes.EcoTracker.DailyActivity;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.databinding.FragmentBuyElectronicsBinding;
import com.example.planetze.ui.eco_tracker.main.EcoTrackerFragment;

public class BuyElectronicsFragment extends BaseActivityFragment {

    private FragmentBuyElectronicsBinding binding;
    private String type;
    private int num;

    private DailyActivity editDailyActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBuyElectronicsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        if (getArguments() != null && getArguments().get("dailyActivity") != null) {
            editDailyActivity = (DailyActivity) getArguments().get("dailyActivity");

            binding.num.setText(String.valueOf(editDailyActivity.getNumberOfPurchase()));
            binding.type.setText(editDailyActivity.getItemName());

        }
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
            Date date = EcoTrackerFragment.getCurrentSelectedDate();
            BuyElectronics activity = new BuyElectronics(type, num);
            currentUser.addActivity(date, activity);

            if(editDailyActivity != null){
                currentUser.removeActivity(editDailyActivity.getUuid());
            }


            navigateToMain();
        }
    }

}
