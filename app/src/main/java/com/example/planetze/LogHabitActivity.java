package com.example.planetze;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.planetze.classes.EcoTracker.Date;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
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

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    // Do nothing, as this is just the prompt
                    return;
                }
                // Handle selected habit
                String selectedHabit = parentView.getItemAtPosition(position).toString();
//                CalendarView calendarView = findViewById(R.id.calendarView);
//                ArrayList<String> dates = new ArrayList<String>();
//                // Iterate over the habit keys
//                for (Map.Entry<String, List<String>> entry : user.habit.entrySet()) {
//                    String habitName = entry.getKey();
//                    List<String> habitDates = entry.getValue();  // The list of dates for the habit
//
//                    // Add all dates for this habit to the 'dates' list
//                    dates.addAll(habitDates);
//                }
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
//                Date date = new Date();
//                for (int i = 0; i < dates.size(); i++) {
//                    String inputString2 = dates.get(i);
//                    String inputString1 = dateFormat.format(date);
//                    try {
//                        //Converting String format to date format
//                        java.util.Date date1 = dateFormat.parse(inputString1);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    calendarView.add
//                    cal.add(Calendar.DATE, day);
//                    holidayDay = cal.getTime();
//                    colors();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle case when nothing is selected
            }
        });

        Button backbutton = findViewById(R.id.logHabitButton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogHabitActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button loghabit = findViewById(R.id.logHabit);
        loghabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedHabit = spinner.getSelectedItem().toString();
                String selectedDate = textPickADate.getText().toString();
                if (selectedHabit.equals("Select a habit") || selectedDate.isEmpty()) {
                    Toast.makeText(LogHabitActivity.this, "Please select a habit" +
                            " and a date", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!user.habit.containsKey(selectedHabit)) {
                        Toast.makeText(LogHabitActivity.this, "You have not" +
                                " selected this Habit to adopt", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        user.habit.get(selectedHabit).add(selectedDate);
                        databaseManager.add(user);
                        Toast.makeText(LogHabitActivity.this, "Habit" +
                                " logged successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LogHabitActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }
}