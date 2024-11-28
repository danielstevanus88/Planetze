package com.example.planetze.ui.eco_tracker;

import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.planetze.R;
import com.example.planetze.classes.DatabaseManager;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;

import java.util.List;

public abstract class BaseActivityFragment extends Fragment {

    protected DatabaseManager databaseManager = UserDatabaseManager.getInstance();
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
        getParentFragmentManager().popBackStack();
    }
}
