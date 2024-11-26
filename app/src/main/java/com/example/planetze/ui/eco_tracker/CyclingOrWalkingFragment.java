package com.example.planetze.ui.eco_tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.planetze.databinding.FragmentCyclingOrWalkingBinding;
import com.example.planetze.databinding.FragmentDrivePersonalVehicleBinding;

public class CyclingOrWalkingFragment extends Fragment {

    private FragmentCyclingOrWalkingBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCyclingOrWalkingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

}
