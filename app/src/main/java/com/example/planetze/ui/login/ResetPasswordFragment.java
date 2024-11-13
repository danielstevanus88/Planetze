package com.example.planetze.ui.login;

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
import com.example.planetze.classes.UserDatabaseManager;

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
                Toast.makeText(getContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                return;
            } 

            // Send reset password email
            loginManager.resetPassword(textResetEmail.getText().toString()).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Log.d("ResetPassword", "Reset password email sent");
                    getActivity().onBackPressed();
                } else {
                    Log.d("ResetPassword", "Failed to send reset password email");
                }
            });
            Toast.makeText(getContext(), "Reset password email sent", Toast.LENGTH_SHORT).show();
            
        });
        return view;
    }
}