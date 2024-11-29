package com.example.planetze.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.example.planetze.R;
import com.example.planetze.classes.DatabaseManager;
import com.example.planetze.classes.LoginManager;
import com.example.planetze.classes.User;
import com.example.planetze.classes.UserDatabaseManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseFormFragment<VB extends ViewBinding> extends Fragment {

    protected VB binding;
    protected DatabaseManager<User> databaseManager = UserDatabaseManager.getInstance();
    protected User currentUser = LoginManager.getCurrentUser();

    protected abstract void setupClickListeners();

    protected abstract void handleNextButtonClick();

    protected abstract void handleOptionButtonClick(Button clickedButton);

    protected abstract boolean isInputInvalid();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = inflateBinding(inflater, container);
        View view = binding.getRoot();
        setupClickListeners();
        return view;
    }

    @SuppressWarnings("unchecked")
    private VB inflateBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        try {
            Type superclass = getClass().getGenericSuperclass();
            if (superclass instanceof ParameterizedType) {
                Type bindingType = ((ParameterizedType) superclass).getActualTypeArguments()[0];
                if (bindingType instanceof Class) {
                    Class<VB> bindingClass = (Class<VB>) bindingType;
                    Method inflateMethod = bindingClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
                    return (VB) inflateMethod.invoke(null, inflater, container, false);
                }
            }
            throw new RuntimeException("Failed to get View Binding class");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to inflate View Binding", e);
        }
    }

    protected void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    protected void setButtons(List<Button> buttons, Button clicked) {
        for (Button button : buttons) {
            button.setBackgroundResource(R.drawable.unclicked_button);
            button.setTextColor(getResources().getColor(R.color.alternativeDarkColor));
        }
        clicked.setBackgroundResource(R.drawable.clicked_button);
        clicked.setTextColor(getResources().getColor(R.color.white));
    }

    protected void showMessage() {
        Toast.makeText(getActivity(), "Please select an option", Toast.LENGTH_SHORT).show();
    }

    protected void handleButtonClick(View view) {
        if (view.getId() == R.id.next) {
            if (isInputInvalid()) {
                showMessage();
            } else {
                handleNextButtonClick();
            }
        } else if (view.getId() == R.id.back) {
            getParentFragmentManager().popBackStack();
        } else {
            handleOptionButtonClick((Button) view);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}