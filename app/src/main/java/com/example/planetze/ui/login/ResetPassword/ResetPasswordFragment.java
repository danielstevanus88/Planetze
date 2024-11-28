package com.example.planetze.ui.login.ResetPassword;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.planetze.R;
import com.example.planetze.classes.LoginManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResetPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResetPasswordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private LoginManager loginManager;

    public ResetPasswordFragment() {
        // Required empty public constructor
        loginManager = LoginManager.getInstance();

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResetPasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResetPasswordFragment newInstance(String param1, String param2) {
        ResetPasswordFragment fragment = new ResetPasswordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_reset_password, container, false);



        TextView backButton = view.findViewById(R.id.textButtonBack);

        backButton.setOnClickListener(res -> {
            getActivity().onBackPressed();
        });

        EditText textResetEmail = view.findViewById(R.id.editTextEmailResetPassword);

        Button resetButton = view.findViewById(R.id.buttonResetPassword);
        resetButton.setOnClickListener(res -> {
            if(textResetEmail.getText().toString().isEmpty()){
                showMessage("Invalid Input", "Please enter your email");
                Toast.makeText(getContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                return;
            } 

            // Send reset password email
            loginManager.resetPassword(textResetEmail.getText().toString()).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    showMessage("Email sent!", "Reset password email sent");
                    getActivity().onBackPressed();
                } else {
                    showMessage("Error", "Failed to send reset password email");
                }
            });

            
        });
        return view;
    }

    public void showMessage(String title, String message){
        // Show dialog, confirmation of exiting the app
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}