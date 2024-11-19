package com.example.planetze.ui.form;

import android.widget.Button;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentFoodBinding;

import java.util.Arrays;
import java.util.List;

public class FoodFragment extends BaseFormFragment<FragmentFoodBinding> {

    private String q8;

    @Override
    protected void setupClickListeners() {
        binding.q8Option1.setOnClickListener(this::handleButtonClick);
        binding.q8Option2.setOnClickListener(this::handleButtonClick);
        binding.q8Option3.setOnClickListener(this::handleButtonClick);
        binding.q8Option4.setOnClickListener(this::handleButtonClick);
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options = {"", getString(R.string.vegetarian), getString(R.string.vegan), getString(R.string.pescatarian), getString(R.string.meat_based)};

        db.child("q8").setValue(Arrays.asList(options).indexOf(q8));

        if (q8.equals(getString(R.string.meat_based))) {
            loadFragment(new MeatFragment());
        } else {
            db.child("q9a").removeValue();
            db.child("q9b").removeValue();
            db.child("q9c").removeValue();
            db.child("q9d").removeValue();
            loadFragment(new WasteFragment());
        }
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        List<Button> buttons8 = Arrays.asList(binding.q8Option1, binding.q8Option2, binding.q8Option3, binding.q8Option4);

        if (buttons8.contains(clickedButton)) {
            setButtons(buttons8, clickedButton);
            q8 = clickedButton.getText().toString();
        }
    }

    @Override
    protected boolean isInputInvalid() {
        return q8 == null;
    }
}