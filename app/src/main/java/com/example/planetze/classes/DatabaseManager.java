package com.example.planetze.classes;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public interface DatabaseManager<T> {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public Task<Void> add(T item);
    public Task<Void> delete(String uid);
    public Task<DataSnapshot> find(String uid);

}
