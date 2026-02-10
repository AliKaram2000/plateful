package com.aeinae.plateful.login.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.credentials.Credential;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aeinae.plateful.MainActivity;
import com.aeinae.plateful.R;
import com.aeinae.plateful.login.presenter.LoginPresenter;
import com.aeinae.plateful.login.presenter.LoginPresenterImp;
import com.aeinae.plateful.login.presenter.LoginView;
import com.aeinae.plateful.model.authentication.AuthenticationService;

import io.reactivex.rxjava3.core.Single;


public class LoginFragment extends Fragment implements LoginView {

    Button login, googleLogin;
    EditText emailEt, passwordEt;
    TextView signUp;
    LoginPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        onLogin();
        onRegister();
        onGoogleLogin();
    }

    public void initUI(View view){
        signUp = view.findViewById(R.id.tv_signup);
        emailEt = view.findViewById(R.id.et_input_email);
        passwordEt = view.findViewById(R.id.et_input_pw);
        login = view.findViewById(R.id.btn_login);
        googleLogin = view.findViewById(R.id.btn_login_with_google);
        presenter = new LoginPresenterImp(this, new AuthenticationService());
    }

    public void onLogin(){
        login.setOnClickListener(v->{
            String email = emailEt.getText().toString();
            String password = passwordEt.getText().toString();
            presenter.loginWithEmailAndPassword(email, password);
        });
    }

    public void onRegister(){
        signUp.setOnClickListener(v->{
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_loginFragment_to_registerFragment);
        });
    }

    @Override
    public Single<Credential> getGoogleCredentials(){
        return ((MainActivity) requireActivity()).launchCredentialManager();
    }
    public void onGoogleLogin(){
        googleLogin.setOnClickListener(
                view -> {
                    presenter.loginWithGoogle();
                });
    }
    @Override
    public void navigateToHomeScreen() {

        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_loginFragment_to_homeActivity);
        requireActivity().finish();

    }

    @Override
    public void showErrorMessage(String localizedMessage) {
        Toast.makeText(requireContext(), localizedMessage, Toast.LENGTH_SHORT).show();
    }
}