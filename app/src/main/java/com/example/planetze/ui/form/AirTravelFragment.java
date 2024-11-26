package com.example.planetze.ui.form;



import android.widget.Button;

import com.example.planetze.R;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.databinding.FragmentAirTravelBinding;

import java.util.Arrays;
import java.util.List;

public class AirTravelFragment extends BaseFormFragment<FragmentAirTravelBinding> {

    private String q6, q7;

    @Override
    protected void setupClickListeners() {
        binding.q6Option1.setOnClickListener(this::handleButtonClick);
        binding.q6Option2.setOnClickListener(this::handleButtonClick);
        binding.q6Option3.setOnClickListener(this::handleButtonClick);
        binding.q6Option4.setOnClickListener(this::handleButtonClick);
        binding.q6Option5.setOnClickListener(this::handleButtonClick);
        binding.q7Option1.setOnClickListener(this::handleButtonClick);
        binding.q7Option2.setOnClickListener(this::handleButtonClick);
        binding.q7Option3.setOnClickListener(this::handleButtonClick);
        binding.q7Option4.setOnClickListener(this::handleButtonClick);
        binding.q7Option5.setOnClickListener(this::handleButtonClick);
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options = {"", getString(R.string.none), getString(R.string.flights1), getString(R.string.flights2), getString(R.string.flights3), getString(R.string.flights4)};

        currentUser.addQuestionnaireAnswer("q6", Arrays.asList(options).indexOf(q6));
        currentUser.addQuestionnaireAnswer("q7", Arrays.asList(options).indexOf(q7));
        loadFragment(new FoodFragment());
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        List<Button> buttons6 = Arrays.asList(binding.q6Option1, binding.q6Option2, binding.q6Option3, binding.q6Option4, binding.q6Option5);
        List<Button> buttons7 = Arrays.asList(binding.q7Option1, binding.q7Option2, binding.q7Option3, binding.q7Option4, binding.q7Option5);

        if (buttons6.contains(clickedButton)) {
            setButtons(buttons6, clickedButton);
            q6 = clickedButton.getText().toString();
        } else if (buttons7.contains(clickedButton)) {
            setButtons(buttons7, clickedButton);
            q7 = clickedButton.getText().toString();
        }
    }

    @Override
    protected boolean isInputInvalid() {
        return q6 == null || q7 == null;
    }
}