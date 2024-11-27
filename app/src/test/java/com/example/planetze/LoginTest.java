package com.example.planetze;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.planetze.ui.login.Login.Contract;
import com.example.planetze.ui.login.Login.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    @Mock
    Contract.Model model;

    @Mock
    Contract.View view;

    @Test
    public void testLoginEmptyEmail(){
        // Test the login function
        Contract.Presenter presenter = new LoginPresenter(model, view);
        when(view.getEmail()).thenReturn("");
        when(view.getPassword()).thenReturn("abcdef");

        presenter.login();
        verify(view).showMessage("Invalid Input", "Email or password cannot be empty");
    }

    @Test
    public void testLoginEmptyPassword(){
        // Test the login function
        Contract.Presenter presenter = new LoginPresenter(model, view);
        when(view.getEmail()).thenReturn("abcdef");
        when(view.getPassword()).thenReturn("");

        presenter.login();
        verify(view).showMessage("Invalid Input", "Email or password cannot be empty");
    }


    @Test
    public void testLoginUserSuccess() {

        // Mock dependencies
        FirebaseAuth authMock = mock(FirebaseAuth.class);
        Contract.Presenter presenter = new LoginPresenter(model, view);

        // Set email and password for this test
        String emailForTesting = "accountForTesting@gmail.com";
        String passwordForTesting = "abcdef";
        when(view.getEmail()).thenReturn(emailForTesting);
        when(view.getPassword()).thenReturn(passwordForTesting);

        Task<AuthResult> taskMock = mock(Task.class);
        when(model.login(emailForTesting, passwordForTesting)).thenReturn(taskMock);
        when(taskMock.isSuccessful()).thenReturn(true);
        when(model.isUserVerified()).thenReturn(true);
        presenter.login();

        ArgumentCaptor<OnCompleteListener> captor = ArgumentCaptor.forClass(OnCompleteListener.class);
        verify(taskMock).addOnCompleteListener(captor.capture());

        captor.getValue().onComplete(taskMock);
        verify(view).onLoginSuccess();
    }

    @Test
    public void testLoginUserFail() {

        // Mock dependencies
        FirebaseAuth authMock = mock(FirebaseAuth.class);
        Contract.Presenter presenter = new LoginPresenter(model, view);

        // Set email and password for this test
        String emailForTesting = "accountForTesting@gmail.com";
        String passwordForTesting = "abcdef";
        when(view.getEmail()).thenReturn(emailForTesting);
        when(view.getPassword()).thenReturn(passwordForTesting);

        Task<AuthResult> taskMock = mock(Task.class);
        when(model.login(emailForTesting, passwordForTesting)).thenReturn(taskMock);
        when(taskMock.isSuccessful()).thenReturn(false);

        presenter.login();

        ArgumentCaptor<OnCompleteListener> captor = ArgumentCaptor.forClass(OnCompleteListener.class);
        verify(taskMock).addOnCompleteListener(captor.capture());

        captor.getValue().onComplete(taskMock);
        verify(view).showMessage("Invalid email or password", "You entered invalid email and/or password");
    }

    @Test
    public void testLoginUserNotVerified() {

        // Mock dependencies
        FirebaseAuth authMock = mock(FirebaseAuth.class);
        Contract.Presenter presenter = new LoginPresenter(model, view);

        // Set email and password for this test
        String emailForTesting = "accountForTesting@gmail.com";
        String passwordForTesting = "abcdef";
        when(view.getEmail()).thenReturn(emailForTesting);
        when(view.getPassword()).thenReturn(passwordForTesting);

        Task<AuthResult> taskMock = mock(Task.class);
        when(model.login(emailForTesting, passwordForTesting)).thenReturn(taskMock);
        when(taskMock.isSuccessful()).thenReturn(true);
        when(model.isUserVerified()).thenReturn(false);
        presenter.login();

        ArgumentCaptor<OnCompleteListener> captor = ArgumentCaptor.forClass(OnCompleteListener.class);
        verify(taskMock).addOnCompleteListener(captor.capture());

        captor.getValue().onComplete(taskMock);
        verify(view).onLoginNotVerified();
    }

}