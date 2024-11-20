package com.example.planetze.ui.form;

import android.widget.Button;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentEnergyBinding;

import java.util.Arrays;
import java.util.List;

public class EnergyFragment extends BaseFormFragment<FragmentEnergyBinding> { // TODO: Replace with actual fragment name

    private String q14, q15, q16, q17;

    @Override
    protected void setupClickListeners() {
        binding.q14Option1.setOnClickListener(this::handleButtonClick);
        binding.q14Option2.setOnClickListener(this::handleButtonClick);
        binding.q14Option3.setOnClickListener(this::handleButtonClick);
        binding.q14Option4.setOnClickListener(this::handleButtonClick);
        binding.q14Option5.setOnClickListener(this::handleButtonClick);
        binding.q14Option6.setOnClickListener(this::handleButtonClick);
        binding.q15Option1.setOnClickListener(this::handleButtonClick);
        binding.q15Option2.setOnClickListener(this::handleButtonClick);
        binding.q15Option3.setOnClickListener(this::handleButtonClick);
        binding.q15Option4.setOnClickListener(this::handleButtonClick);
        binding.q15Option5.setOnClickListener(this::handleButtonClick);
        binding.q16Option1.setOnClickListener(this::handleButtonClick);
        binding.q16Option2.setOnClickListener(this::handleButtonClick);
        binding.q16Option3.setOnClickListener(this::handleButtonClick);
        binding.q16Option4.setOnClickListener(this::handleButtonClick);
        binding.q16Option5.setOnClickListener(this::handleButtonClick);
        binding.q16Option6.setOnClickListener(this::handleButtonClick);
        binding.q17Option1.setOnClickListener(this::handleButtonClick);
        binding.q17Option2.setOnClickListener(this::handleButtonClick);
        binding.q17Option3.setOnClickListener(this::handleButtonClick);
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options1 = {"", getString(R.string.natural_gas), getString(R.string.electricity), getString(R.string.oil), getString(R.string.propane), getString(R.string.wood), getString(R.string.other)};
        String[] options2 = {"", getString(R.string.bill1), getString(R.string.bill2), getString(R.string.bill3), getString(R.string.bill4), getString(R.string.bill5)};
        String[] options3 = {"", getString(R.string.primarily), getString(R.string.partially), getString(R.string.no)};

        currentUser.addQuestionnaireAnswer("q14",Arrays.asList(options1).indexOf(q14));
        currentUser.addQuestionnaireAnswer("q15",Arrays.asList(options2).indexOf(q15));
        currentUser.addQuestionnaireAnswer("q16",Arrays.asList(options1).indexOf(q16));
        currentUser.addQuestionnaireAnswer("q17",Arrays.asList(options3).indexOf(q17));
        databaseManager.add(currentUser);
        loadFragment(new ConsumptionFragment());
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        List<Button> buttons14 = Arrays.asList(binding.q14Option1, binding.q14Option2, binding.q14Option3, binding.q14Option4, binding.q14Option5, binding.q14Option6);
        List<Button> buttons15 = Arrays.asList(binding.q15Option1, binding.q15Option2, binding.q15Option3, binding.q15Option4, binding.q15Option5);
        List<Button> buttons16 = Arrays.asList(binding.q16Option1, binding.q16Option2, binding.q16Option3, binding.q16Option4, binding.q16Option5, binding.q16Option6);
        List<Button> buttons17 = Arrays.asList(binding.q17Option1, binding.q17Option2, binding.q17Option3);

        if (buttons14.contains(clickedButton)) {
            setButtons(buttons14, clickedButton);
            q14 = clickedButton.getText().toString();
        } else if (buttons15.contains(clickedButton)) {
            setButtons(buttons15, clickedButton);
            q15 = clickedButton.getText().toString();
        } else if (buttons16.contains(clickedButton)) {
            setButtons(buttons16, clickedButton);
            q16 = clickedButton.getText().toString();
        } else if (buttons17.contains(clickedButton)) {
            setButtons(buttons17, clickedButton);
            q17 = clickedButton.getText().toString();
        }
    }

    @Override
    protected boolean isInputInvalid() {
        return q14 == null || q15 == null || q16 == null || q17 == null;
    }
}