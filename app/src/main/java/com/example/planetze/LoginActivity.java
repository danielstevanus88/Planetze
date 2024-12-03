package com.example.planetze;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.planetze.classes.DatabaseManager;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;
import com.example.planetze.ui.login.IOnSelectionListener;
import com.example.planetze.ui.login.Login.LoginFragment;
import com.example.planetze.ui.login.LoginOptionFragment;
import com.example.planetze.ui.login.Register.RegisterFragment;
import com.example.planetze.ui.login.ResetPassword.ResetPasswordFragment;
import com.google.firebase.database.DataSnapshot;

public class LoginActivity extends AppCompatActivity implements IOnSelectionListener {

    private LoginManager loginManager;
    private DatabaseManager databaseManager;

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

        if (LoginManager.getCurrentFirebaseUser() != null) {
            handleExistingUser();
        } else {
            showFragment(new LoginOptionFragment());
        }
    }

    private void handleExistingUser() {
        databaseManager = UserDatabaseManager.getInstance();
        databaseManager.find(loginManager.getCurrentUserUid()).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        handleSuccessfulUserRetrieval((DataSnapshot) task.getResult());
                    } else {
                        handleUserRetrievalError(task.getException());
                    }
                }
        );
    }

    private void handleSuccessfulUserRetrieval(DataSnapshot dataSnapshot) {
        User currentUser = dataSnapshot.getValue(User.class);
        if (currentUser != null) {
            LoginManager.setCurrentUser(currentUser);
            redirectToMainActivity(currentUser);
        } else {
            // Handle case where user data is not found
            Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleUserRetrievalError(Exception exception) {
        // Handle error while retrieving user data
        Toast.makeText(this, "Error retrieving user data", Toast.LENGTH_SHORT).show();
    }

    private void redirectToMainActivity(User user) {

        if(!user.hasFilledQuestionnaires()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this , MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    // Display the login fragment inside VerticalLayout
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    @Override
    public void showLoginForm() {
        showFragment(new LoginFragment());
    }

    @Override
    public void showRegisterForm() {
        showFragment(new RegisterFragment());
    }

    @Override
    public void showResetPasswordForm() {
        showFragment(new ResetPasswordFragment());
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (currentFragment instanceof LoginFragment ||
                currentFragment instanceof RegisterFragment ||
                currentFragment instanceof ResetPasswordFragment) {
            showFragment(new LoginOptionFragment());
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Exit");
            builder.setMessage("Are you sure you want to quit the app?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    closeApplication();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


            if (false)
                super.onBackPressed();
        }
    }

    public void closeApplication(){
        // Clear all tasks and exit
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}