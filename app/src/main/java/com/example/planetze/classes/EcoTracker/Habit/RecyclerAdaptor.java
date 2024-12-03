package com.example.planetze.classes.EcoTracker.Habit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planetze.MainActivity;
import com.example.planetze.R;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.MyViewHolder>{
    private ArrayList<Habit> habitList;
    int selection_position = -1;

    public RecyclerAdaptor(ArrayList<Habit> habitList) {
        this.habitList = habitList;
    }

    public Habit getSelectedHabit() {
        if (selection_position != -1) {
            return habitList.get(selection_position);
        }
        return null;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView habitName;
        public TextView habitDescription;
        public MyViewHolder(final View view) {
            super(view);
            habitName = view.findViewById(R.id.habit_name);  // TextView for habit name
            habitDescription = view.findViewById(R.id.habit_description);  // TextView for habit description
        }
    }

    @NonNull
    @Override
    public RecyclerAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_habit, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdaptor.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Habit habit = habitList.get(position);
        holder.habitName.setText(habit.getName());  // Set habit name in TextView
        holder.habitDescription.setText(habit.getDescription());  // Set habit description in TextView
        if(selection_position == position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#d7d7db"));
        }else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.itemView.setOnClickListener(v -> {
            // Show a confirmation dialog
            new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Confirm Selection")
                    .setMessage("Do you want to select this habit: " + habit.getName() + "?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        User user = LoginManager.getCurrentUser();

                        // Avoid creating a new RecyclerAdaptor instance
                        Habit selectedHabit = habitList.get(position);  // Retrieve the clicked habit
                        if (selectedHabit != null) {
                            if (!user.getHabit().containsKey(selectedHabit.getName())) {
                                List<String> habitData = new ArrayList<>();
                                habitData.add("0");  // Initial progress or default value
                                user.addHabit(selectedHabit, habitData);  // Add habit to user's list

                                // Inform user and navigate to MainActivity
                                Toast.makeText(holder.itemView.getContext(), "Added habit: " + selectedHabit.getName(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(holder.itemView.getContext(), MainActivity.class);
                                holder.itemView.getContext().startActivity(intent);
                                ((Activity) holder.itemView.getContext()).finish();  // Close the current activity
                            } else {
                                // Inform user if habit already exists
                                Toast.makeText(holder.itemView.getContext(), "You have already selected this habit", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Inform user if no habit is selected
                            Toast.makeText(holder.itemView.getContext(), "Please select a habit", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())  // Dismiss the dialog
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public void setFilteredList(ArrayList<Habit> filteredList) {
        this.habitList = filteredList;
        notifyDataSetChanged();
    }
}
