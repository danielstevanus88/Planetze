package com.example.planetze.ui.eco_tracker;

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
import com.example.planetze.classes.EcoTracker.Category.Consumption.BuyOthers;
import com.example.planetze.classes.EcoTracker.DailyActivity;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.databinding.FragmentOtherPurchasesBinding;
import com.example.planetze.ui.eco_tracker.main.EcoTrackerFragment;

public class OtherPurchasesFragment extends BaseActivityFragment {

    private FragmentOtherPurchasesBinding binding;
    private String type;
    private int num;

    private DailyActivity editDailyActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOtherPurchasesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.back.setOnClickListener(this::handleBackButtonClick);

        binding.submit.setOnClickListener(this::handleNextButtonClick);

        if (getArguments() != null && getArguments().get("dailyActivity") != null) {
            editDailyActivity = (DailyActivity) getArguments().get("dailyActivity");

            binding.num.setText(String.valueOf(editDailyActivity.getNumberOfPurchase()));
            binding.type.setText(editDailyActivity.getItemName());
        }
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
            Date date = EcoTrackerFragment.getCurrentSelectedDate();
            BuyOthers activity = new BuyOthers(type, num);
            currentUser.addActivity(date, activity);

            if(editDailyActivity != null){
                currentUser.removeActivity(editDailyActivity.getUuid());
            }

            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.eco_tracker);
        }
    }

}
