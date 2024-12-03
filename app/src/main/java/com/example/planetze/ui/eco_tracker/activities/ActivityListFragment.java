package com.example.planetze.ui.eco_tracker.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.planetze.R;
import com.example.planetze.databinding.FragmentActivityListBinding;

public class ActivityListFragment extends Fragment {

    private FragmentActivityListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentActivityListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.t1.setOnClickListener(v -> navigateToFragment(R.id.drive_personal_vehicle));
        binding.t2.setOnClickListener(v -> navigateToFragment(R.id.take_public_transportation));
        binding.t3.setOnClickListener(v -> navigateToFragment(R.id.cycling_or_walking));
        binding.t4.setOnClickListener(v -> navigateToFragment(R.id.flight));
        binding.f1.setOnClickListener(v -> navigateToFragment(R.id.meal));
        binding.c1.setOnClickListener(v -> navigateToFragment(R.id.buy_new_clothes));
        binding.c2.setOnClickListener(v -> navigateToFragment(R.id.buy_electronics));
        binding.c3.setOnClickListener(v -> navigateToFragment(R.id.other_purchases));
        binding.c4.setOnClickListener(v -> navigateToFragment(R.id.energy_bills));

        return view;
    }

    private void navigateToFragment(int fragmentId) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(fragmentId);
    }
}