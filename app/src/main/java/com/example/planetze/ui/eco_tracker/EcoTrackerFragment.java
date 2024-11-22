package com.example.planetze.ui.eco_tracker;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.planetze.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EcoTrackerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EcoTrackerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EcoTrackerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EcoTrackerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EcoTrackerFragment newInstance(String param1, String param2) {
        EcoTrackerFragment fragment = new EcoTrackerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eco_tracker, container, false);

        LinearLayout buttonPickADate = view.findViewById(R.id.buttonPickDate);
        EditText textPickADate = view.findViewById(R.id.editTextDate);
        textPickADate.setKeyListener(null);


        buttonPickADate.setOnClickListener( event -> {
            // Open DatePickerDialog
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getActivity(),
                    (v, selectedYear, selectedMonth, selectedDay) -> {
                        // Month is 0-based, add 1 to display correctly
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        textPickADate.setText(selectedDate);
                        Toast.makeText(getActivity(), "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                    },
                    year, month, day);
            datePickerDialog.show();
        });
        textPickADate.setOnClickListener( event -> {
            buttonPickADate.callOnClick();
        });

        Button addhabit = view.findViewById(R.id.addHabit);
        addhabit.setOnClickListener(v -> navigateChooseHabit());
        return view;
    }

    private void navigateChooseHabit() {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        ChooseHabitFragment fragmentB = new ChooseHabitFragment();
        transaction.replace(R.id.fragView, fragmentB);  // Replace with the ID of the container in FragmentA
        transaction.addToBackStack(null);  // Allows back navigation to FragmentA
        transaction.commit();
    }

}

