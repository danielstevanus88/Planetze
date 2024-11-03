package com.example.planetze.ui.login;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.planetze.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginOptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginOptionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IOnSelectionListener listener;

    public LoginOptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginOptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginOptionFragment newInstance(String param1, String param2) {
        LoginOptionFragment fragment = new LoginOptionFragment();
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

        View view = inflater.inflate(R.layout.fragment_login_option, container, false);
        Button loginButton = view.findViewById(R.id.buttonLoginOption);
        Button registerButton = view.findViewById(R.id.buttonRegisterOption);

        loginButton.setOnClickListener(v -> {
            listener.onLoginOptionClick();
        });

        registerButton.setOnClickListener(v -> {
            listener.onRegisterOptionClick();
        });

        return view;
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