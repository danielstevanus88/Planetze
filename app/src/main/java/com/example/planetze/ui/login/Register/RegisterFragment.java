package com.example.planetze.ui.login.Register;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planetze.FormActivity;
import com.example.planetze.MainActivity;
import com.example.planetze.R;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private LoginManager loginManager;
    private UserDatabaseManager userDatabaseManager;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
        loginManager = LoginManager.getInstance();
        userDatabaseManager = UserDatabaseManager.getInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button registerButton = view.findViewById(R.id.buttonRegister);


        EditText editName = view.findViewById(R.id.editTextNameRegister);
        EditText editEmail = view.findViewById(R.id.editTextEmailRegister);
        EditText editPassword = view.findViewById(R.id.editTextPasswordRegister);
        EditText editConfirmPassword = view.findViewById(R.id.editTextConfirmPasswordRegister);


        TextView backButton = view.findViewById(R.id.textButtonBack);
        backButton.setOnClickListener(res -> {
            getActivity().onBackPressed();
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Check field
                    String name = editName.getText().toString();
                    String email = editEmail.getText().toString();
                    String password = editPassword.getText().toString();
                    String confirmPassword = editConfirmPassword.getText().toString();
                    
                    if (name.isEmpty() || email.isEmpty() ||
                            password.isEmpty() || confirmPassword.isEmpty()) {
                        // Show error message
                        Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!password.equals(confirmPassword)){
                        Toast.makeText(getContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    loginManager.register(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = loginManager.getCurrentFirebaseUser();
                            if(user != null){
                                User newUser = new User(user.getUid(), name, email);
                                userDatabaseManager.add(newUser).addOnCompleteListener(task1 -> {
                                    if(task1.isSuccessful()){
                                        // Redirect to main activity
                                        LoginManager.setCurrentUser(newUser);
                                        Intent intent = new Intent(getActivity(), FormActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }else{
                                        // Show error message
                                        Toast.makeText(getActivity(), "Error: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            // Show error message
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        );
        

        // Inflate the layout for this fragment
        return view;
    }
}