package com.example.planetze.classes.EcoTracker.Habit;

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

    public RecyclerAdaptor(ArrayList<Habit> habitList) {
        this.habitList = habitList;
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
    public void onBindViewHolder(@NonNull RecyclerAdaptor.MyViewHolder holder, int position) {
        Habit habit = habitList.get(position);
        holder.habitName.setText(habit.getName());  // Set habit name in TextView
        holder.habitDescription.setText(habit.getDescription());  // Set habit description in TextView

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
