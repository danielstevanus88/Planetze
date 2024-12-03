package com.example.planetze.ui.form;

import android.content.Intent;
import android.widget.Button;

import com.example.planetze.FormResultActivity;
import com.example.planetze.MainActivity;
import com.example.planetze.R;
import com.example.planetze.databinding.FragmentConsumptionBinding;

import java.util.Arrays;
import java.util.List;

public class ConsumptionFragment extends BaseFormFragment<FragmentConsumptionBinding> {

    private List<Button> buttons18;
    private List<Button> buttons19;
    private List<Button> buttons20;
    private List<Button> buttons21;
    private String q18, q19, q20, q21;

    @Override
    protected void setupClickListeners() {
        buttons18 = Arrays.asList(binding.q18Option1, binding.q18Option2, binding.q18Option3, binding.q18Option4);
        buttons19 = Arrays.asList(binding.q19Option1, binding.q19Option2, binding.q19Option3);
        buttons20 = Arrays.asList(binding.q20Option1, binding.q20Option2, binding.q20Option3, binding.q20Option4);
        buttons21 = Arrays.asList(binding.q21Option1, binding.q21Option2, binding.q21Option3, binding.q21Option4);

        for (Button button : buttons18) {
            button.setOnClickListener(this::handleButtonClick);
        }
        for (Button button : buttons19) {
            button.setOnClickListener(this::handleButtonClick);
        }
        for (Button button : buttons20) {
            button.setOnClickListener(this::handleButtonClick);
        }
        for (Button button : buttons21) {
            button.setOnClickListener(this::handleButtonClick);
        }
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options1 = {"", getString(R.string.monthly), getString(R.string.quarterly), getString(R.string.annually), getString(R.string.rarely)};
        String[] options2 = {"", getString(R.string.yes_regularly), getString(R.string.yes_occasionally), getString(R.string.no)};
        String[] options3 = {"", getString(R.string.none), getString(R.string._1), getString(R.string._2), getString(R.string._3_or_more)};
        String[] options4 = {"", getString(R.string.never), getString(R.string.occasion), getString(R.string.frequent), getString(R.string.always)};

        currentUser.addQuestionnaireAnswer("q18", Arrays.asList(options1).indexOf(q18));
        currentUser.addQuestionnaireAnswer("q19", Arrays.asList(options2).indexOf(q19));
        currentUser.addQuestionnaireAnswer("q20", Arrays.asList(options3).indexOf(q20));
        currentUser.addQuestionnaireAnswer("q21", Arrays.asList(options4).indexOf(q21));

        Intent intent = new Intent(getActivity(), FormResultActivity.class);
        startActivity(intent);
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        if (buttons18.contains(clickedButton)) {
            setButtons(buttons18, clickedButton);
            q18 = clickedButton.getText().toString();
        } else if (buttons19.contains(clickedButton)) {
            setButtons(buttons19, clickedButton);
            q19 = clickedButton.getText().toString();
        } else if (buttons20.contains(clickedButton)) {
            setButtons(buttons20, clickedButton);
            q20 = clickedButton.getText().toString();
        } else if (buttons21.contains(clickedButton)) {
            setButtons(buttons21, clickedButton);
            q21 = clickedButton.getText().toString();
        }
    }

    @Override
    protected boolean isInputInvalid() {
        return q18 == null || q19 == null || q20 == null || q21 == null;
    }
}