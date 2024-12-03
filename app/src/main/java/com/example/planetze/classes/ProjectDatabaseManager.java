package com.example.planetze.classes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.planetze.classes.EcoBalance.Project;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectDatabaseManager implements DatabaseManager<Project>{

    private static ProjectDatabaseManager projectDatabaseManager;
    private static DatabaseReference dbRef;
    private static List<FirebaseListenerProject> listeners;

    public static HashMap<String, Project> projects;

    private ProjectDatabaseManager(){

        dbRef = firebaseDatabase.getReference("projects");
        listeners = new ArrayList<>();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                projects = (HashMap<String, Project>) snapshot.getValue();
                for(FirebaseListenerProject listenerProject: listeners){
                    listenerProject.update();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public static ProjectDatabaseManager getInstance(){
        if (projectDatabaseManager == null){
            projectDatabaseManager = new ProjectDatabaseManager();
            return projectDatabaseManager;
        }
        return projectDatabaseManager;
    }
    @Override
    public Task<Void> add(Project item) {
        projects.put(item.getUuid(), item);
        return dbRef.setValue(projects);
    }

    @Override
    public Task<Void> delete(String uid) {
        projects.remove(uid);
        return dbRef.setValue(projects);
    }

    @Override
    public Task<DataSnapshot> find(String uid) {
        return dbRef.child(uid).get();
    }

    public static void subscribeAsProjectListener(FirebaseListenerProject listener){
        Log.d("Subscribed", "listener update method");
        listeners.add(listener);
    }

    public static void unsubscribeAsProjectListener(FirebaseListenerProject listener){
        listeners.remove(listener);
    }
}
