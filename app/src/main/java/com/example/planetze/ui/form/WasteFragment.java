package com.example.planetze.ui.form;

import android.widget.Button;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentWasteBinding;

import java.util.Arrays;
import java.util.List;

public class WasteFragment extends BaseFormFragment<FragmentWasteBinding> {

    private String q10;

    @Override
    protected void setupClickListeners() {
        binding.q10Option1.setOnClickListener(this::handleButtonClick);
        binding.q10Option2.setOnClickListener(this::handleButtonClick);
        binding.q10Option3.setOnClickListener(this::handleButtonClick);
        binding.q10Option4.setOnClickListener(this::handleButtonClick);
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options = {"", getString(R.string.never), getString(R.string.rarely), getString(R.string.occasion), getString(R.string.frequent)};

        db.child("q10").setValue(Arrays.asList(options).indexOf(q10));

        loadFragment(new HousingFragment());
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        List<Button> buttons = Arrays.asList(binding.q10Option1, binding.q10Option2, binding.q10Option3, binding.q10Option4);

        if (buttons.contains(clickedButton)) {
            setButtons(buttons, clickedButton);
            q10 = clickedButton.getText().toString();
        }
    }

    @Override
    protected boolean isInputInvalid() {
        return q10 == null;
    }

}