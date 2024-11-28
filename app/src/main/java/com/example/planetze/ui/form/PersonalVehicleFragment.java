package com.example.planetze.ui.form;

import android.widget.Button;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentPersonalVehicleBinding;

import java.util.Arrays;
import java.util.List;

public class PersonalVehicleFragment extends BaseFormFragment<FragmentPersonalVehicleBinding> {

    private List<Button> buttons2;
    private List<Button> buttons3;
    private String q2, q3;

    @Override
    protected void setupClickListeners() {
        buttons2 = Arrays.asList(binding.q2Option1, binding.q2Option2, binding.q2Option3, binding.q2Option4, binding.q2Option5);
        buttons3 = Arrays.asList(binding.q3Option1, binding.q3Option2, binding.q3Option3, binding.q3Option4, binding.q3Option5, binding.q3Option6);

        for (Button button : buttons2) {
            button.setOnClickListener(this::handleButtonClick);
        }
        for (Button button : buttons3) {
            button.setOnClickListener(this::handleButtonClick);
        }
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options1 = {"", getString(R.string.gasoline), getString(R.string.diesel), getString(R.string.hybrid), getString(R.string.electric), getString(R.string.dunno)};
        String[] options2 = {"", getString(R.string.dist1), getString(R.string.dist2), getString(R.string.dist3), getString(R.string.dist4), getString(R.string.dist5), getString(R.string.dist6)};


        currentUser.addQuestionnaireAnswer("q2",Arrays.asList(options1).indexOf(q2));
        currentUser.addQuestionnaireAnswer("q3",Arrays.asList(options2).indexOf(q3));

        databaseManager.add(currentUser);
        loadFragment(new PublicTransportFragment());
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        if (buttons2.contains(clickedButton)) {
            setButtons(buttons2, clickedButton);
            q2 = clickedButton.getText().toString();
        } else if (buttons3.contains(clickedButton)) {
            setButtons(buttons3, clickedButton);
            q3 = clickedButton.getText().toString();
        }
    }

    @Override
    protected boolean isInputInvalid() {
        return q2 == null || q3 == null;
    }
}