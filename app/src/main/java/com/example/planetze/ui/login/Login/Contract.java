package com.example.planetze.ui.login.Login;

import android.app.Activity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface Contract {
    public interface Model {
        public Task<AuthResult> login(String email, String password);

        public boolean isUserVerified();
    }


    public interface View {
        public String getEmail();
        public String getPassword();
        public void showMessage(String title, String message);
        public void onLoginSuccess();
        public void onLoginNotVerified();

    }

    public interface Presenter {
        public void login();

    }

    

}
