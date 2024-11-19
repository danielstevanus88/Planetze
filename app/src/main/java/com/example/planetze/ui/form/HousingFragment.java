package com.example.planetze.ui.form;

import android.widget.Button;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentHousingBinding;

import java.util.Arrays;
import java.util.List;

public class HousingFragment extends BaseFormFragment<FragmentHousingBinding> {

    private String q11, q12, q13;

    @Override
    protected void setupClickListeners() {
        binding.q11Option1.setOnClickListener(this::handleButtonClick);
        binding.q11Option2.setOnClickListener(this::handleButtonClick);
        binding.q11Option3.setOnClickListener(this::handleButtonClick);
        binding.q11Option4.setOnClickListener(this::handleButtonClick);
        binding.q11Option5.setOnClickListener(this::handleButtonClick);
        binding.q12Option1.setOnClickListener(this::handleButtonClick);
        binding.q12Option2.setOnClickListener(this::handleButtonClick);
        binding.q12Option3.setOnClickListener(this::handleButtonClick);
        binding.q12Option4.setOnClickListener(this::handleButtonClick);
        binding.q13Option1.setOnClickListener(this::handleButtonClick);
        binding.q13Option2.setOnClickListener(this::handleButtonClick);
        binding.q13Option3.setOnClickListener(this::handleButtonClick);
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options1 = {"", getString(R.string.detached), getString(R.string.semi_detached), getString(R.string.townhouse), getString(R.string.condo), getString(R.string.other)};
        String[] options2 = {"", getString(R.string._1), getString(R.string._2), getString(R.string._3_4), getString(R.string._5_or_more)};
        String[] options3 = {"", getString(R.string.under_1000_sq_ft), getString(R.string._1000_2000_sq_ft), getString(R.string.over_2000_sq_ft)};

        db.child("q11").setValue(Arrays.asList(options1).indexOf(q11));
        db.child("q12").setValue(Arrays.asList(options2).indexOf(q12));
        db.child("q13").setValue(Arrays.asList(options3).indexOf(q13));
        loadFragment(new EnergyFragment());
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        List<Button> buttons1 = Arrays.asList(binding.q11Option1, binding.q11Option2, binding.q11Option3, binding.q11Option4, binding.q11Option5);
        List<Button> buttons2 = Arrays.asList(binding.q12Option1, binding.q12Option2, binding.q12Option3, binding.q12Option4);
        List<Button> buttons3 = Arrays.asList(binding.q13Option1, binding.q13Option2, binding.q13Option3);

        if (buttons1.contains(clickedButton)) {
            setButtons(buttons1, clickedButton);
            q11 = clickedButton.getText().toString();
        } else if (buttons2.contains(clickedButton)) {
            setButtons(buttons2, clickedButton);
            q12 = clickedButton.getText().toString();
        } else if (buttons3.contains(clickedButton)) {
            setButtons(buttons3, clickedButton);
            q13 = clickedButton.getText().toString();
        }
    }

    @Override
    protected boolean isInputInvalid() {
        return q11 == null || q12 == null || q13 == null;
    }
}