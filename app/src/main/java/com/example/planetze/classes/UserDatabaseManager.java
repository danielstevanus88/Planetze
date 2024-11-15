package com.example.planetze.classes;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

public class UserDatabaseManager implements DatabaseManager<User>{
    private static UserDatabaseManager userDatabaseManager;
    private static DatabaseReference dbRef;
    private UserDatabaseManager(){
        dbRef = firebaseDatabase.getReference("users");
    }

    public static UserDatabaseManager getInstance(){
        if (userDatabaseManager == null){
            return new UserDatabaseManager();
        }
        return userDatabaseManager;
    }
    @Override
    public Task<Void> add(User user) {
        return dbRef.child(String.valueOf(user.getUid())).setValue(user);
    }

    @Override
    public Task<Void> delete(int id) {
        return null;
    }

    @Override
    public Task<Void> find(int id) {
        return null;
    }
}
