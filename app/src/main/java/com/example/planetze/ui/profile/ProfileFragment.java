package com.example.planetze.ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.planetze.FormActivity;
import com.example.planetze.R;
import com.example.planetze.classes.DatabaseManager;
import com.example.planetze.classes.EcoTracker.ActivitiesCalculator;
import com.example.planetze.classes.EcoTracker.ActivitiesConverter;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.ScreenUtilities.StringHandler;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private DatabaseManager<User> databaseManager;
    private LoginManager loginManager;

    private User user;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        databaseManager = UserDatabaseManager.getInstance();
        loginManager = LoginManager.getInstance();
        user = LoginManager.getCurrentUser();
        EditText editTextName = view.findViewById(R.id.editTextName);
        EditText editTextGmail = view.findViewById(R.id.editTextEmail);
        // TODO: THIS IS NOT ANNUAL, CHANGE THIS
        EditText editTextAnnual = view.findViewById(R.id.editTextAnnualCarbon);
        EditText editTextCarbonCredit = view.findViewById(R.id.editTextCarbonCredit);

        editTextName.setText(StringHandler.limitString(user.getName(), ProfileConstants.MAX_LENGTH_NAME));
        editTextGmail.setText(StringHandler.limitEmail(user.getEmail(), ProfileConstants.MAX_LENGTH_EMAIL));
        editTextAnnual.setText(String.valueOf(ActivitiesCalculator.calculateTotalEmission(ActivitiesConverter.getActivitiesWithClassDate(user.getActivities()))));
        editTextCarbonCredit.setText(String.valueOf(user.getCarbonCredits()));

        editTextName.setOnEditorActionListener((v, actionId, event) -> {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        // Action when "Done" is pressed
                        String inputText = editTextName.getText().toString();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Edit Name");
                        builder.setMessage("Are you sure you want to change the profile name to " + inputText + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                user.changeName(editTextName.getText().toString());
                                Toast.makeText(getActivity(), "Successfully changed name to" + editTextName.getText().toString(), Toast.LENGTH_SHORT).show();
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

                        // Optionally hide the keyboard
                        return true; // Indicate the event is handled
                    }
                    return false;
                }
        );

        Button logoutButton = view.findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        Button recalculateAnnualCarbonButton = view.findViewById(R.id.buttonRecalculate);
        recalculateAnnualCarbonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FormActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}