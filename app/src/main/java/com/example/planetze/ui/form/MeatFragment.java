package com.example.planetze.ui.form;

import android.widget.Button;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentMeatBinding;

import java.util.Arrays;
import java.util.List;

public class MeatFragment extends BaseFormFragment<FragmentMeatBinding> {

    private String q9a, q9b, q9c, q9d;

    @Override
    protected void setupClickListeners() {
        binding.q9aOption1.setOnClickListener(this::handleButtonClick);
        binding.q9aOption2.setOnClickListener(this::handleButtonClick);
        binding.q9aOption3.setOnClickListener(this::handleButtonClick);
        binding.q9aOption4.setOnClickListener(this::handleButtonClick);
        binding.q9bOption1.setOnClickListener(this::handleButtonClick);
        binding.q9bOption2.setOnClickListener(this::handleButtonClick);
        binding.q9bOption3.setOnClickListener(this::handleButtonClick);
        binding.q9bOption4.setOnClickListener(this::handleButtonClick);
        binding.q9cOption1.setOnClickListener(this::handleButtonClick);
        binding.q9cOption2.setOnClickListener(this::handleButtonClick);
        binding.q9cOption3.setOnClickListener(this::handleButtonClick);
        binding.q9cOption4.setOnClickListener(this::handleButtonClick);
        binding.q9dOption1.setOnClickListener(this::handleButtonClick);
        binding.q9dOption2.setOnClickListener(this::handleButtonClick);
        binding.q9dOption3.setOnClickListener(this::handleButtonClick);
        binding.q9dOption4.setOnClickListener(this::handleButtonClick);
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options = {"", getString(R.string.daily), getString(R.string.frequently), getString(R.string.occasionally), getString(R.string.never)};

        currentUser.addQuestionnaireAnswer("q9a",Arrays.asList(options).indexOf(q9a));
        currentUser.addQuestionnaireAnswer("q9b",Arrays.asList(options).indexOf(q9b));
        currentUser.addQuestionnaireAnswer("q9c",Arrays.asList(options).indexOf(q9c));
        currentUser.addQuestionnaireAnswer("q9d",Arrays.asList(options).indexOf(q9d));

        databaseManager.add(currentUser);

        loadFragment(new WasteFragment());
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        List<Button> buttons9a = Arrays.asList(binding.q9aOption1, binding.q9aOption2, binding.q9aOption3, binding.q9aOption4);
        List<Button> buttons9b = Arrays.asList(binding.q9bOption1, binding.q9bOption2, binding.q9bOption3, binding.q9bOption4);
        List<Button> buttons9c = Arrays.asList(binding.q9cOption1, binding.q9cOption2, binding.q9cOption3, binding.q9cOption4);
        List<Button> buttons9d = Arrays.asList(binding.q9dOption1, binding.q9dOption2, binding.q9dOption3, binding.q9dOption4);

        if (buttons9a.contains(clickedButton)) {
            setButtons(buttons9a, clickedButton);
            q9a = clickedButton.getText().toString();
        } else if (buttons9b.contains(clickedButton)) {
            setButtons(buttons9b, clickedButton);
            q9b = clickedButton.getText().toString();
        } else if (buttons9c.contains(clickedButton)) {
            setButtons(buttons9c, clickedButton);
            q9c = clickedButton.getText().toString();
        } else if (buttons9d.contains(clickedButton)) {
            setButtons(buttons9d, clickedButton);
            q9d = clickedButton.getText().toString();
        }
    }

    @Override
    protected boolean isInputInvalid() {
        return q9a == null || q9b == null || q9c == null || q9d == null;
    }
}