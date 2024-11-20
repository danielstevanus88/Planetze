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
        String email = view.getEmail();
        String password = view.getPassword();

        if(email.isEmpty() || password.isEmpty()){
            view.showMessage("Email or password cannot be empty");
            return;
        }

        model.login(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                view.onLoginSuccess();
            }else{
                // Show error message
                view.showMessage("Invalid email or password!");
            }
        });



    }
}
