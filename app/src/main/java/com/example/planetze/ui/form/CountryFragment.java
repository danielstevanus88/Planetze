package com.example.planetze.ui.form;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.planetze.R;
import com.example.planetze.classes.GlobalAverages;
import com.example.planetze.databinding.FragmentCountryBinding;

public class CountryFragment extends BaseFormFragment<FragmentCountryBinding> {

    private String country;
    private String[] countries;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AutoCompleteTextView autoCompleteTextView = binding.country;
        countries = GlobalAverages.getCountries();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        requireContext(),
                        R.layout.dropdown_item,
                        countries);
        autoCompleteTextView.setAdapter(adapter);
    }

    @Override
    protected void setupClickListeners() {
        binding.back.setOnClickListener(this::handleButtonClick);
        binding.next.setOnClickListener(this::handleButtonClick);
    }

    @Override
    protected void handleNextButtonClick() {
        currentUser.setCountry(country);
        Toast.makeText(getActivity(), "Country is now " + country , Toast.LENGTH_SHORT).show();
        databaseManager.add(currentUser);
        loadFragment(new TransportationFragment());
    }

    @Override
    protected void handleOptionButtonClick(Button clickedButton) {
        // No options in this fragment
    }

    @Override
    protected void showMessage() {
        Toast.makeText(getActivity(), "Please select a valid country", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected boolean isInputInvalid() {
        country = binding.country.getText().toString();

        if (country.isEmpty()) {
            return true;
        }

        countries = GlobalAverages.getCountries();
        boolean isValidCountry = false;
        for (String validCountry : countries) {
            if (validCountry.equalsIgnoreCase(country)) {
                isValidCountry = true;
                break;
            }
        }

        return !isValidCountry;
    }
}