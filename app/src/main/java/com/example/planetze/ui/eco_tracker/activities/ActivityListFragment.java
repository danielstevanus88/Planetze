package com.example.planetze.ui.eco_tracker.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
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
import com.example.planetze.classes.EcoTracker.ActivitiesCalculator;
import com.example.planetze.classes.EcoTracker.ActivitiesConverter;
import com.example.planetze.classes.EcoTracker.ActivitiesFilter;
import com.example.planetze.classes.EcoTracker.Category.EcoTrackerActivityConstant;
import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.classes.EcoTracker.EcoTrackerEmissionConstant;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.databinding.FragmentActivityListBinding;
import com.example.planetze.ui.eco_tracker.main.EcoTrackerFragment;

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

        binding.c4.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  // Check if user has already inputted energy bills
                  Date selectedDate = EcoTrackerFragment.getCurrentSelectedDate();
                  User user = LoginManager.getCurrentUser();
                  // F
                  if (ActivitiesCalculator.getCountOfActivitiesWithType(
                          ActivitiesFilter.filterActivitiesByRangeOfDate(
                                  ActivitiesConverter.getActivitiesWithClassDate(
                                          user.getActivities()),
                                            selectedDate.getFirstDayOfTheMonth(), selectedDate),
                                            EcoTrackerActivityConstant.ID_ENERGY_BILL
                  ) >= 1){
                      showAlertDialog();
                  } else {
                      navigateToFragment(R.id.energy_bills);
                  }
              }
        });

        return view;
    }

    private void navigateToFragment(int fragmentId) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(fragmentId);
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("You've added energy bill activities this month before");
        builder.setMessage("Proceed only if it's a different bill. Press YES to continue");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                navigateToFragment(R.id.energy_bills);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}