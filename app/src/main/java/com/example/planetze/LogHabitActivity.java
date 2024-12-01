package com.example.planetze;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.applandeo.materialcalendarview.CalendarDay;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;
import com.google.android.material.datepicker.DayViewDecorator;
import com.google.android.material.datepicker.MaterialCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LogHabitActivity extends AppCompatActivity {
    private UserDatabaseManager databaseManager = UserDatabaseManager.getInstance();
    private User user = LoginManager.getCurrentUser();
    HashMap<String, List<String>> habits = user.getHabit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_habit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayout buttonPickADate = findViewById(R.id.buttonPickDate);
        EditText textPickADate = findViewById(R.id.editTextDate);
        textPickADate.setKeyListener(null);


            buttonPickADate.setOnClickListener( event -> {
            // Open DatePickerDialog
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (v, selectedYear, selectedMonth, selectedDay) -> {
                        // Month is 0-based, add 1 to display correctly
                        String selectedDate = (selectedMonth + 1) + "-" + (selectedDay) + "-" + selectedYear;
                        textPickADate.setText(selectedDate);
                        Toast.makeText(this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                    },
                    year, month, day);
            datePickerDialog.show();
        });
        textPickADate.setOnClickListener( event -> {
            buttonPickADate.callOnClick();
        });

        Spinner spinner = findViewById(R.id.spinnerHabit);
        ArrayList<String> habitList = new ArrayList<>(habits.keySet());
        habitList.add(0, "Select a habit");
        // Set up the adapter for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, habitList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        TextView completeTextView = findViewById(R.id.completionTextView);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    // Do nothing, as this is just the prompt
                    return;
                }
                else{
                    int completionpastweek = 0;
                    Calendar cal = Calendar.getInstance();
                    Date today = cal.getTime();
                    cal.add(Calendar.DAY_OF_YEAR, -7);
                    Date weekAgo = cal.getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                    String selectedHabit = parentView.getItemAtPosition(position).toString();
                    CalendarView calendarView = findViewById(R.id.calendarView);
                    List<Calendar> calendarDays = new ArrayList<>();
                    List<String> habitDates = new ArrayList<>();
                    habitDates = user.habit.get(selectedHabit);
                    // Iterate over the habit keys
                    if (habitDates != null) {
                        for (String dateString : habitDates) {
                            if(!dateString.equals("0")){
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


        Button backbutton = findViewById(R.id.back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RadioButton radioButton = findViewById(R.id.radioButton);
        Button loghabit = findViewById(R.id.logHabit);
        loghabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedHabit = spinner.getSelectedItem().toString();
                String selectedDate = textPickADate.getText().toString();
                if (selectedHabit.equals("N/A") || selectedDate.isEmpty()) {
                    Toast.makeText(LogHabitActivity.this, "Please select a habit" +
                            " and a date", Toast.LENGTH_SHORT).show();
                }
                else if (!radioButton.isChecked()) {
                    Toast.makeText(LogHabitActivity.this, "Please confirm you have " +
                            "completed the habit on the day", Toast.LENGTH_SHORT).show();

                }
                else {
                    if (!user.habit.containsKey(selectedHabit)) {
                        Toast.makeText(LogHabitActivity.this, "You have not" +
                                " selected this Habit to adopt", Toast.LENGTH_SHORT).show();
                    }
                    else if (user.habit.get(selectedHabit).contains(selectedDate)) {
                        Toast.makeText(LogHabitActivity.this, "Habit" +
                                " already logged for this date", Toast.LENGTH_SHORT).show();
                    }
                    else {
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
                                Toast.makeText(LogHabitActivity.this, "Habit" +
                                        " logged successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LogHabitActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                user.habit.get(selectedHabit).add(selectedDate);
                                databaseManager.add(user);
                                Toast.makeText(LogHabitActivity.this, "Habit" +
                                        " logged successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LogHabitActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                        else{
                            Toast.makeText(LogHabitActivity.this, "Date" +
                                    " cannot be in the future", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });

        Button removehabit = findViewById(R.id.removehabit);
        removehabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedHabit = spinner.getSelectedItem().toString();
                if (selectedHabit.equals("N/A") || selectedHabit.isEmpty()) {
                    Toast.makeText(LogHabitActivity.this, "Please select a valid habit to remove", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    new AlertDialog.Builder(LogHabitActivity.this)
                            .setTitle("Confirm Removal")
                            .setMessage("Are you sure you want to remove the habit \"" + selectedHabit + "\"?")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                user.habit.remove(selectedHabit);
                                databaseManager.add(user);
                                Intent intent = new Intent(LogHabitActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(LogHabitActivity.this, "Habit removed successfully", Toast.LENGTH_SHORT).show();
                            })
                    .setNegativeButton("No", null)
                            .show();
                }
            }
        });


    }
}