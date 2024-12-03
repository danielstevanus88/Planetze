package com.example.planetze.classes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseManager implements DatabaseManager<User>{
    private static UserDatabaseManager userDatabaseManager;
    private static DatabaseReference dbRef;

    private static List<FirebaseListenerDailyActivity> listeners;
    private UserDatabaseManager(){

        dbRef = firebaseDatabase.getReference("users");
        listeners = new ArrayList<>();

    }

    public static UserDatabaseManager getInstance(){
        if (userDatabaseManager == null){
            userDatabaseManager = new UserDatabaseManager();
            return userDatabaseManager;
        }
        return userDatabaseManager;
    }

    @Override
    public Task<Void> add(User user) {
        Log.d("added stuff", "a");
        return dbRef.child(String.valueOf(user.getUid())).setValue(user);
    }

    @Override
    public Task<Void> delete(String uid) {
        return null;
    }


    public Task<DataSnapshot> find(String uid) {
        return dbRef.child(uid).get();
    }

    public DatabaseReference getReference(String uid){
        return dbRef.child(uid);
    }


    // Observer Pattern
    // Set the event we wanted to listen to (in this case, a Firebase data update on a certain user)
    public static void setListenerToUser(User user){
        dbRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("Updated", "user changed");
                if(!listeners.isEmpty()) {
//                    LoginManager.setCurrentUser(snapshot.getValue(User.class));
                    for (FirebaseListenerDailyActivity listener : listeners) {


                        Log.d("Called", "listener update method");
                        listener.update();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void subscribeAsDailyActivityListener(FirebaseListenerDailyActivity listener){
        Log.d("Subscribed", "listener update method");
        listeners.add(listener);
    }

    public static void unsubscribeAsDailyActivityListener(FirebaseListenerDailyActivity listener){
        listeners.remove(listener);
    }

}
