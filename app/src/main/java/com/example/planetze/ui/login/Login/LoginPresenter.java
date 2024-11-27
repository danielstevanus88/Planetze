package com.example.planetze.ui.login.Login;

import com.example.planetze.classes.LoginManager;

public class LoginPresenter implements Contract.Presenter{

    Contract.Model model;
    Contract.View view;
    public LoginPresenter(Contract.Model model, Contract.View view){
        this.model = model;
        this.view = view;
    }


    @Override
    public void login() {
        // Get the email and password from view
        String email = view.getEmail();
        String password = view.getPassword();

        // Check valid input (non-empty)
        if(email.isEmpty() || password.isEmpty()){
            view.showMessage("Invalid Input", "Email or password cannot be empty");
            return;
        }

        // Try logging in with the email and password
        model.login(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                if(model.isUserVerified()) {
                    view.onLoginSuccess();
                } else {
                    model.sendVerificationEmail();
                    model.logout();
                    view.onLoginNotVerified();
                }
                return;
            }

            // Show error message
            view.showMessage("Invalid email or password", "You entered invalid email and/or password");

        });



    }
}
