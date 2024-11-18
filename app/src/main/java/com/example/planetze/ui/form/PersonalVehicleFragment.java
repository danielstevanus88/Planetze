package com.example.planetze.ui.form;

import android.widget.Button;
import com.example.planetze.R;
import com.example.planetze.databinding.FragmentPersonalVehicleBinding;
import java.util.Arrays;
import java.util.List;

public class PersonalVehicleFragment extends BaseFormFragment<FragmentPersonalVehicleBinding> {

    private String q2, q3;

    @Override
    protected void setupClickListeners() {
        binding.q2Option1.setOnClickListener(this::handleButtonClick);
        binding.q2Option2.setOnClickListener(this::handleButtonClick);
        binding.q2Option3.setOnClickListener(this::handleButtonClick);
        binding.q2Option4.setOnClickListener(this::handleButtonClick);
        binding.q2Option5.setOnClickListener(this::handleButtonClick);
        binding.q3Option1.setOnClickListener(this::handleButtonClick);
        binding.q3Option2.setOnClickListener(this::handleButtonClick);
        binding.q3Option3.setOnClickListener(this::handleButtonClick);
        binding.q3Option4.setOnClickListener(this::handleButtonClick);
        binding.q3Option5.setOnClickListener(this::handleButtonClick);
        binding.q3Option6.setOnClickListener(this::handleButtonClick);
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        String[] options1 = {"", getString(R.string.gasoline), getString(R.string.diesel), getString(R.string.hybrid), getString(R.string.electric), getString(R.string.dunno)};
        String[] options2 = {"", getString(R.string.dist1), getString(R.string.dist2), getString(R.string.dist3), getString(R.string.dist4), getString(R.string.dist5), getString(R.string.dist6)};

        db.child("initial-data").child(uid).child("q2").setValue(Arrays.asList(options1).indexOf(q2));
        db.child("initial-data").child(uid).child("q3").setValue(Arrays.asList(options2).indexOf(q3));
        loadFragment(new PublicTransportFragment());
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        List<Button> buttons2 = Arrays.asList(binding.q2Option1, binding.q2Option2, binding.q2Option3, binding.q2Option4, binding.q2Option5);
        List<Button> buttons3 = Arrays.asList(binding.q3Option1, binding.q3Option2, binding.q3Option3, binding.q3Option4, binding.q3Option5, binding.q3Option6);

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