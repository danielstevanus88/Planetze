package com.example.planetze.classes.EcoTracker.Habit;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planetze.R;

import java.util.ArrayList;

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
            int previousPosition = selection_position;  // Store the previous selected position
            selection_position = position;  // Update the selected position
            notifyItemChanged(previousPosition);  // Notify the previous item to update its background
            notifyItemChanged(position);  // Notify the current item to update its background
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
