package com.example.planetze.ui.form;

import android.widget.Button;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentEnergyBinding;

import java.util.Arrays;
import java.util.List;

public class EnergyFragment extends BaseFormFragment<FragmentEnergyBinding> { // TODO: Replace with actual fragment name

    private List<Button> buttons14;
    private List<Button> buttons15;
    private List<Button> buttons16;
    private List<Button> buttons17;
    private String q14, q15, q16, q17;

    @Override
    protected void setupClickListeners() {
        buttons14 = Arrays.asList(binding.q14Option1, binding.q14Option2, binding.q14Option3, binding.q14Option4, binding.q14Option5, binding.q14Option6);
        buttons15 = Arrays.asList(binding.q15Option1, binding.q15Option2, binding.q15Option3, binding.q15Option4, binding.q15Option5);
        buttons16 = Arrays.asList(binding.q16Option1, binding.q16Option2, binding.q16Option3, binding.q16Option4, binding.q16Option5, binding.q16Option6);
        buttons17 = Arrays.asList(binding.q17Option1, binding.q17Option2, binding.q17Option3);

        for (Button button : buttons14) {
            button.setOnClickListener(this::handleButtonClick);
        }
        for (Button button : buttons15) {
            button.setOnClickListener(this::handleButtonClick);
        }
        for (Button button : buttons16) {
            button.setOnClickListener(this::handleButtonClick);
        }
        for (Button button : buttons17) {
            button.setOnClickListener(this::handleButtonClick);
        }
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
        loadFragment(new ConsumptionFragment());
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
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