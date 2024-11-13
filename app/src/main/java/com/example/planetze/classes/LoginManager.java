package com.example.planetze.classes;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginManager {
    private static LoginManager loginManager;
    private static FirebaseAuth mAuth;

    private LoginManager(){
        mAuth = FirebaseAuth.getInstance();
    }

    public static LoginManager getInstance(){
        if(loginManager == null){
            loginManager = new LoginManager();
            return loginManager;
        }
        return loginManager;
    }

    public static Task<AuthResult> login(String email, String password){
        return mAuth.signInWithEmailAndPassword(email, password);
    }

    public static Task<AuthResult> register(String email, String password){
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    public static Task<Void> resetPassword(String email){
        return mAuth.sendPasswordResetEmail(email);
    }

    public static void logout(){
        mAuth.signOut();
    }

    public static FirebaseUser getCurrentUser(){
        return mAuth.getCurrentUser();
    }



}
