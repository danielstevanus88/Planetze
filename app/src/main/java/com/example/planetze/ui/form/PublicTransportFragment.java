package com.example.planetze.ui.form;

import android.widget.Button;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentPublicTransportBinding;

import java.util.Arrays;
import java.util.List;

public class PublicTransportFragment extends BaseFormFragment<FragmentPublicTransportBinding> {

    private String q4, q5;

    @Override
    protected void setupClickListeners() {
        binding.q4Option1.setOnClickListener(this::handleButtonClick);
        binding.q4Option2.setOnClickListener(this::handleButtonClick);
        binding.q4Option3.setOnClickListener(this::handleButtonClick);
        binding.q4Option4.setOnClickListener(this::handleButtonClick);
        binding.q5Option1.setOnClickListener(this::handleButtonClick);
        binding.q5Option2.setOnClickListener(this::handleButtonClick);
        binding.q5Option3.setOnClickListener(this::handleButtonClick);
        binding.q5Option4.setOnClickListener(this::handleButtonClick);
        binding.q5Option5.setOnClickListener(this::handleButtonClick);
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options1 = {"", getString(R.string.never), getString(R.string.occasionally), getString(R.string.frequently), getString(R.string.always)};
        String[] options2 = {"", getString(R.string.time1), getString(R.string.time2), getString(R.string.time3), getString(R.string.time4), getString(R.string.time5)};

        currentUser.addQuestionnaireAnswer("q4",Arrays.asList(options1).indexOf(q4));
        currentUser.addQuestionnaireAnswer("q5",Arrays.asList(options2).indexOf(q5));
        loadFragment(new AirTravelFragment());
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        List<Button> buttons4 = Arrays.asList(binding.q4Option1, binding.q4Option2, binding.q4Option3, binding.q4Option4);
        List<Button> buttons5 = Arrays.asList(binding.q5Option1, binding.q5Option2, binding.q5Option3, binding.q5Option4, binding.q5Option5);

        if (buttons4.contains(clickedButton)) {
            setButtons(buttons4, clickedButton);
            q4 = clickedButton.getText().toString();
        } else if (buttons5.contains(clickedButton)) {
            setButtons(buttons5, clickedButton);
            q5 = clickedButton.getText().toString();
        }
    }

    @Override
    protected boolean isInputInvalid() {
        return q4 == null || q5 == null;
    }

}