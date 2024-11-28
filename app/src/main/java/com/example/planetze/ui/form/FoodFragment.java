package com.example.planetze.ui.form;

import android.widget.Button;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentFoodBinding;

import java.util.Arrays;
import java.util.List;

public class FoodFragment extends BaseFormFragment<FragmentFoodBinding> {

    private List<Button> buttons8;
    private String q8;

    @Override
    protected void setupClickListeners() {
        buttons8 = Arrays.asList(binding.q8Option1, binding.q8Option2, binding.q8Option3, binding.q8Option4);

        for (Button button : buttons8) {
            button.setOnClickListener(this::handleButtonClick);
        }
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options = {"", getString(R.string.vegetarian), getString(R.string.vegan), getString(R.string.pescatarian), getString(R.string.meat_based)};

        currentUser.addQuestionnaireAnswer("q8",Arrays.asList(options).indexOf(q8));

        if (q8.equals(getString(R.string.meat_based))) {
            loadFragment(new MeatFragment());
        } else {
            currentUser.removeQuestionnaireAnswer("q9a");
            currentUser.removeQuestionnaireAnswer("q9b");
            currentUser.removeQuestionnaireAnswer("q9c");
            currentUser.removeQuestionnaireAnswer("q9d");
            loadFragment(new WasteFragment());
        }

        databaseManager.add(currentUser);
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
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