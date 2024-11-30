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
import com.example.planetze.classes.EcoTracker.Category.Consumption.BuyOthers;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.databinding.FragmentOtherPurchasesBinding;
import com.example.planetze.ui.eco_tracker.main.ShowActivityFragment;

public class OtherPurchasesFragment extends BaseActivityFragment {

    private FragmentOtherPurchasesBinding binding;
    private String type;
    private int num;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOtherPurchasesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.back.setOnClickListener(this::handleBackButtonClick);

        binding.submit.setOnClickListener(this::handleNextButtonClick);

        return view;
    }

    private void handleNextButtonClick(View view) {
        try {
            num = Integer.parseInt(binding.num.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please enter a valid number of purchases", Toast.LENGTH_SHORT).show();
        }
        try {
            type = binding.type.getText().toString();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please enter a valid type of purchase", Toast.LENGTH_SHORT).show();
        }
        if (num <= 0) {
            Toast.makeText(getActivity(), "Please enter a valid number of purchases", Toast.LENGTH_SHORT).show();
        } else {
            Date date = ShowActivityFragment.getCurrentSelectedDate();
            BuyOthers activity = new BuyOthers(type, num);
            currentUser.addActivity(date, activity);
            databaseManager.add(currentUser);

            handleBackButtonClick(view);
            handleBackButtonClick(view);
        }
    }

}
