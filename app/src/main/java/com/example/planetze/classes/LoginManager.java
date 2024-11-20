package com.example.planetze.classes;

import com.example.planetze.ui.login.Login.Contract;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginManager implements Contract.Model{
    private static LoginManager loginManager;
    private static FirebaseAuth mAuth;
    private static User currentUser;
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

    public Task<AuthResult> login(String email, String password){
        return mAuth.signInWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> register(String email, String password){
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    public Task<Void> resetPassword(String email){
        return mAuth.sendPasswordResetEmail(email);
    }

    public void logout(){
        mAuth.signOut();
    }

    public String getCurrentUserUid(){
        return mAuth.getUid();
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static FirebaseUser getCurrentFirebaseUser(){
        return mAuth.getCurrentUser();
    }

}
