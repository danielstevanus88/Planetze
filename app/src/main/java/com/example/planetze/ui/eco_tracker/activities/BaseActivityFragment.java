package com.example.planetze.ui.eco_tracker.activities;

import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.planetze.R;
import com.example.planetze.classes.DatabaseManager;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;

import java.util.List;

public abstract class BaseActivityFragment extends Fragment {

    protected DatabaseManager<User> databaseManager = UserDatabaseManager.getInstance();
    protected User currentUser = LoginManager.getCurrentUser();

    protected void setButtons(List<Button> buttons, Button clicked) {
        for (Button button : buttons) {
            button.setBackgroundResource(R.drawable.unclicked_button);
            button.setTextColor(getResources().getColor(R.color.alternativeDarkColor));
        }
        clicked.setBackgroundResource(R.drawable.clicked_button);
        clicked.setTextColor(getResources().getColor(R.color.white));
    }

    protected void handleBackButtonClick(View view) {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigateUp();
    }

    protected void navigateToMain(){
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.eco_tracker);
    }


}
