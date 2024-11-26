package com.example.planetze.ui.eco_tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.planetze.databinding.FragmentBuyNewClothesBinding;
import com.example.planetze.databinding.FragmentOtherPurchasesBinding;

public class OtherPurchasesFragment extends Fragment {

    private FragmentOtherPurchasesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOtherPurchasesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

}
