package com.example.planetze;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;
import com.example.planetze.ui.login.IOnSelectionListener;
import com.example.planetze.ui.login.LoginFragment;
import com.example.planetze.ui.login.LoginOptionFragment;
import com.example.planetze.ui.login.RegisterFragment;

public class LoginActivity extends AppCompatActivity implements IOnSelectionListener {

    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginManager = LoginManager.getInstance();

        if (loginManager.getCurrentUser() != null) {
            // Redirect to main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        showFragment(new LoginOptionFragment());
    }

    // Display the login fragment inside VerticalLayout
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer , fragment)
                .commit();
    }

    @Override
    public void onLoginOptionClick() {
        showFragment(new LoginFragment());
    }

    @Override
    public void onRegisterOptionClick() {
        showFragment(new RegisterFragment());
    }
    
    @Override
    public void onBackPressed() {
        showFragment(new LoginOptionFragment());
    }


}