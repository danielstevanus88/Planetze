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
import com.example.planetze.classes.EcoTracker.Category.Transportation.CyclingOrWalking;
import com.example.planetze.classes.EcoTracker.DailyActivity;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.databinding.FragmentCyclingOrWalkingBinding;
import com.example.planetze.ui.eco_tracker.main.ShowActivityFragment;

public class CyclingOrWalkingFragment extends BaseActivityFragment {

    private FragmentCyclingOrWalkingBinding binding;
    private double distance;
    private DailyActivity editDailyActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCyclingOrWalkingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.back.setOnClickListener(this::handleBackButtonClick);
        binding.submit.setOnClickListener(this::handleNextButtonClick);


        if (getArguments() != null && getArguments().get("dailyActivity") != null) {
            editDailyActivity = (DailyActivity) getArguments().get("dailyActivity");

            binding.distance.setText(String.valueOf(editDailyActivity.getDistance()));
        }
        return view;
    }

    private void handleNextButtonClick(View view) {
        try {
            distance = Double.parseDouble(binding.distance.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please enter a valid distance", Toast.LENGTH_SHORT).show();
        }
        if (distance <= 0) {
            Toast.makeText(getActivity(), "Please enter a valid distance", Toast.LENGTH_SHORT).show();
        } else {
            Date date = ShowActivityFragment.getCurrentSelectedDate();
            CyclingOrWalking activity = new CyclingOrWalking(distance);
            currentUser.addActivity(date, activity);

            if(editDailyActivity != null){
                currentUser.removeActivity(editDailyActivity.getUuid());
            }
            handleBackButtonClick(view);
            handleBackButtonClick(view);
        }
    }
}