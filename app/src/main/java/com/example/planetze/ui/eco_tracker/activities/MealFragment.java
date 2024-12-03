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
import com.example.planetze.classes.EcoTracker.Category.Food.EatBeef;
import com.example.planetze.classes.EcoTracker.Category.Food.EatChicken;
import com.example.planetze.classes.EcoTracker.Category.Food.EatFish;
import com.example.planetze.classes.EcoTracker.Category.Food.EatPlantBased;
import com.example.planetze.classes.EcoTracker.Category.Food.EatPork;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.databinding.FragmentMealBinding;

import java.util.Arrays;
import java.util.List;

public class MealFragment extends BaseActivityFragment {

    private FragmentMealBinding binding;
    private List<Button> buttons;
    private int type;
    private int num;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMealBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setOnClickListeners();

        binding.submit.setOnClickListener(this::handleNextButtonClick);

        return view;
    }

    private void setOnClickListeners() {
        buttons = Arrays.asList(binding.option1, binding.option2, binding.option3, binding.option4, binding.option5);
        for (Button button : buttons) {
            button.setOnClickListener(this::handleOptionButtonClick);
        }
    }

    private void handleOptionButtonClick(View view) {
        Button clickedButton = (Button) view;

        String[] options = {"", getString(R.string.beef2), getString(R.string.pork2), getString(R.string.chicken2), getString(R.string.fish2), getString(R.string.plant_based)};

        if (buttons.contains(clickedButton)) {
            setButtons(buttons, clickedButton);
            type = Arrays.asList(options).indexOf(clickedButton.getText().toString());
        }
    }

    private void handleNextButtonClick(View view) {
        try {
            num = Integer.parseInt(binding.num.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Please enter a valid number of servings", Toast.LENGTH_SHORT).show();
        }
        if (num <= 0) {
            Toast.makeText(getActivity(), "Please enter a valid number of servings", Toast.LENGTH_SHORT).show();
        } else {
            Date date = Date.today();
            switch (type) {
                case 1:
                    EatBeef activity1 = new EatBeef(num);
                    currentUser.addActivity(date, activity1);
                    break;
                case 2:
                    EatPork activity2 = new EatPork(num);
                    currentUser.addActivity(date, activity2);
                    break;
                case 3:
                    EatChicken activity3 = new EatChicken(num);
                    currentUser.addActivity(date, activity3);
                    break;
                case 4:
                    EatFish activity4 = new EatFish(num);
                    currentUser.addActivity(date, activity4);
                    break;
                case 5:
                    EatPlantBased activity5 = new EatPlantBased(num);
                    currentUser.addActivity(date, activity5);
                    break;
            }
            databaseManager.add(currentUser);

            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.eco_tracker);
        }
    }

}
