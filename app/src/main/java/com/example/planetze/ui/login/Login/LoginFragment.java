package com.example.planetze.ui.login.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planetze.MainActivity;
import com.example.planetze.R;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.ui.login.IOnSelectionListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements Contract.View{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // MVP - Presenter
    private Contract.Presenter presenter;

    // Current View (This part is necessary as LoginFragment is a Fragment not a class)
    // So that we can findViewById through the view methods.
    private View view;

    private LoginManager loginManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IOnSelectionListener listener;

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

        presenter = new LoginPresenter(LoginManager.getInstance(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_login, container, false);

        Button buttonLogin = view.findViewById(R.id.buttonLogin);
        TextView backButton = view.findViewById(R.id.textButtonBack);
        backButton.setOnClickListener(res -> {
            getActivity().onBackPressed();
        });

        // Login button click listener
        buttonLogin.setOnClickListener(res -> {
            presenter.login();
        });

        TextView buttonResetPassword = view.findViewById(R.id.textButtonResetPassword);

        buttonResetPassword.setOnClickListener(res -> {
            listener.showResetPasswordForm();
        });


        return view;
    }

    @Override
    public String getEmail(){
        EditText email = view.findViewById(R.id.editTextEmailLogin);
        return email.getText().toString();
    }
    @Override
    public String getPassword(){

        EditText password = view.findViewById(R.id.editTextPasswordLogin);
        return password.getText().toString();
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        listener = (IOnSelectionListener) context;

    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }

}