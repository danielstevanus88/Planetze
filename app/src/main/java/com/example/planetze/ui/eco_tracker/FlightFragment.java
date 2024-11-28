package com.example.planetze.ui.eco_tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.planetze.databinding.FragmentCyclingOrWalkingBinding;
import com.example.planetze.databinding.FragmentFlightBinding;

public class FlightFragment extends Fragment {

    private FragmentFlightBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFlightBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

}
