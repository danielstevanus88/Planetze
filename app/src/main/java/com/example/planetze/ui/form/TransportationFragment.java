package com.example.planetze.ui.form;

import android.widget.Button;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentTransportationBinding;

import java.util.Arrays;
import java.util.List;

public class TransportationFragment extends BaseFormFragment<FragmentTransportationBinding> {

    private String q1;

    @Override
    protected void setupClickListeners() {
        binding.q1Option1.setOnClickListener(this::handleButtonClick);
        binding.q1Option2.setOnClickListener(this::handleButtonClick);
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options = {"", getString(R.string.yes), getString(R.string.no)};

        currentUser.addQuestionnaireAnswer("q1", Arrays.asList(options).indexOf(q1));

        if (q1.equals(options[1])) {
            loadFragment(new PersonalVehicleFragment());
        } else if (q1.equals(options[2])) {
            currentUser.removeQuestionnaireAnswer("q2");
            currentUser.removeQuestionnaireAnswer("q3");
            loadFragment(new PublicTransportFragment());
        }


        databaseManager.add(currentUser);

    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        List<Button> buttons1 = Arrays.asList(binding.q1Option1, binding.q1Option2);

        if (buttons1.contains(clickedButton)) {
            setButtons(buttons1, clickedButton);
            q1 = clickedButton.getText().toString();
        }
    }

    @Override
    protected boolean isInputInvalid() {
        return q1 == null;
    }

}