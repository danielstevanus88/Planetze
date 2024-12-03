package com.example.planetze.ui.eco_tracker.habit;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.applandeo.materialcalendarview.CalendarView;
import com.example.planetze.R;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;
import com.example.planetze.databinding.FragmentLogHabitBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LogHabitFragment extends Fragment {
    private FragmentLogHabitBinding binding;
    private View view;
    private final UserDatabaseManager databaseManager = UserDatabaseManager.getInstance();
    private final User user = LoginManager.getCurrentUser();
    HashMap<String, List<String>> habits = user.getHabit();
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLogHabitBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        LinearLayout buttonPickADate = binding.buttonPickDate;
        EditText textPickADate = binding.editTextDate;
        textPickADate.setKeyListener(null);

        navController = NavHostFragment.findNavController(this);

        buttonPickADate.setOnClickListener(event -> {
            // Open DatePickerDialog
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (v, selectedYear, selectedMonth, selectedDay) -> {
                        // Month is 0-based, add 1 to display correctly
                        String selectedDate = (selectedMonth + 1) + "-" + (selectedDay) + "-" + selectedYear;
                        textPickADate.setText(selectedDate);
                        Toast.makeText(getActivity(), "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                    },
                    year, month, day);
            datePickerDialog.show();
        });
        textPickADate.setOnClickListener(event -> {
            buttonPickADate.callOnClick();
        });

        Spinner spinner = binding.spinnerHabit;
        ArrayList<String> habitList = new ArrayList<>(habits.keySet());
        habitList.add(0, "Select a habit");
        // Set up the adapter for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, habitList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        TextView completeTextView = binding.completionTextView;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    // Do nothing, as this is just the prompt
                } else {
                    int completionpastweek = 0;
                    Calendar cal = Calendar.getInstance();
                    Date today = cal.getTime();
                    cal.add(Calendar.DAY_OF_YEAR, -7);
                    Date weekAgo = cal.getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                    String selectedHabit = parentView.getItemAtPosition(position).toString();
                    CalendarView calendarView = binding.calendarView;
                    List<Calendar> calendarDays = new ArrayList<>();
                    List<String> habitDates = new ArrayList<>();
                    habitDates = user.habit.get(selectedHabit);
                    // Iterate over the habit keys
                    if (habitDates != null) {
                        for (String dateString : habitDates) {
                            if (!dateString.equals("0")) {
                                try {
                                    Calendar habitDate = Calendar.getInstance();
                                    Date habitDateObject = dateFormat.parse(dateString);
                                    if (habitDateObject != null && habitDateObject.after(weekAgo) && habitDateObject.before(today)) {
                                        // This date is within the past week
                                        completionpastweek++;
                                    }
                                    habitDate.setTime(habitDateObject);
                                    calendarDays.add(habitDate);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                        completeTextView.setText(getString(R.string.habit_completion_message,
                                completionpastweek));
                        // Assign the events to the calendar
                        calendarView.setSelectedDates(calendarDays);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle case when nothing is selected
            }
        });

        RadioButton radioButton = binding.radioButton;
        Button loghabit = binding.logHabit;
        loghabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedHabit = spinner.getSelectedItem().toString();
                String selectedDate = textPickADate.getText().toString();
                if (selectedHabit.equals("N/A") || selectedDate.isEmpty()) {
                    Toast.makeText(getActivity(), "Please select a habit" +
                            " and a date", Toast.LENGTH_SHORT).show();
                } else if (!radioButton.isChecked()) {
                    Toast.makeText(getActivity(), "Please confirm you have " +
                            "completed the habit on the day", Toast.LENGTH_SHORT).show();

                } else {
                    if (!user.habit.containsKey(selectedHabit)) {
                        Toast.makeText(getActivity(), "You have not" +
                                " selected this Habit to adopt", Toast.LENGTH_SHORT).show();
                    } else if (user.habit.get(selectedHabit).contains(selectedDate)) {
                        Toast.makeText(getActivity(), "Habit" +
                                " already logged for this date", Toast.LENGTH_SHORT).show();
                    } else {
                        Calendar calender = Calendar.getInstance();
                        Date date = calender.getTime();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                        Date selectedDateObject;
                        try {
                            selectedDateObject = dateFormat.parse(selectedDate);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        if (selectedDateObject.before(date)) {
                            if (Objects.requireNonNull(user.habit.get(selectedHabit).get(0).equals("0"))) {
                                user.habit.get(selectedHabit).set(0, selectedDate);
                                databaseManager.add(user);
                                Toast.makeText(getActivity(), "Habit" +
                                        " logged successfully", Toast.LENGTH_SHORT).show();
                                navController.navigate(R.id.eco_tracker);
                            } else {
                                user.habit.get(selectedHabit).add(selectedDate);
                                databaseManager.add(user);
                                Toast.makeText(getActivity(), "Habit" +
                                        " logged successfully", Toast.LENGTH_SHORT).show();
                                navController.navigate(R.id.eco_tracker);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Date" +
                                    " cannot be in the future", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });

        Button removehabit = binding.removehabit;
        removehabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedHabit = spinner.getSelectedItem().toString();
                if (selectedHabit.equals("N/A") || selectedHabit.isEmpty()) {
                    Toast.makeText(getActivity(), "Please select a valid habit to remove", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Confirm Removal")
                            .setMessage("Are you sure you want to remove the habit \"" + selectedHabit + "\"?")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                user.habit.remove(selectedHabit);
                                databaseManager.add(user);
                                navController.navigate(R.id.eco_tracker);
                                Toast.makeText(getActivity(), "Habit removed successfully", Toast.LENGTH_SHORT).show();
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            }
        });

        return this.view;
    }
}