package com.example.planetze.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planetze.FormActivity;
import com.example.planetze.MainActivity;
import com.example.planetze.R;
import com.example.planetze.classes.LoginManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LoginManager loginManager;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
        loginManager = LoginManager.getInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button buttonLogin = view.findViewById(R.id.buttonLogin);
        EditText email = view.findViewById(R.id.editTextEmailLogin);
        EditText password = view.findViewById(R.id.editTextPasswordLogin);
        
        // Login button click listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                loginManager.login(emailText, passwordText).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        // Redirect to main activity
                        Intent intent = new Intent(getActivity(), FormActivity.class);
                        startActivity(intent);
                    }else{
                        // Show error message
                        Toast.makeText(getActivity(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}