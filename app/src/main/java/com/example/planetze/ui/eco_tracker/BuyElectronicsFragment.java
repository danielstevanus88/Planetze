package com.example.planetze.ui.eco_tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.planetze.databinding.FragmentBuyElectronicsBinding;
import com.example.planetze.databinding.FragmentBuyNewClothesBinding;

public class BuyElectronicsFragment extends Fragment {

    private FragmentBuyElectronicsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBuyElectronicsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

}
