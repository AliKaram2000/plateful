package com.aeinae.plateful.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.aeinae.plateful.R;
import com.aeinae.plateful.model.authentication.AuthenticationService;


public class RegisterFragment extends Fragment implements RegisterView {

    Button signUp, googleSignUp;
    EditText usernameEt, emailEt, passwordEt, confirmPasswordEt;
    TextView login;
    RegisterPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        onRegister();
        onLogin();
    }

    public void initUI(View view){
        signUp = view.findViewById(R.id.btn_register);
        googleSignUp = view.findViewById(R.id.btn_register_with_google);
        usernameEt = view.findViewById(R.id.et_input_username);
        emailEt = view.findViewById(R.id.et_input_email);
        passwordEt = view.findViewById(R.id.et_input_pw);
        confirmPasswordEt = view.findViewById(R.id.et_input_confirm_pw);
        login = view.findViewById(R.id.tv_login);
        presenter = new RegisterPresenterImp(this,new AuthenticationService());
    }

    public void onRegister(){
        signUp.setOnClickListener(v->{
            String username = usernameEt.getText().toString();
            String email = emailEt.getText().toString();
            String password = passwordEt.getText().toString();
            String confirmPassword = confirmPasswordEt.getText().toString();
            presenter.registerWithEmailAndPassword(username, email, password, confirmPassword);
        });
    }

    public void onLogin(){
        login.setOnClickListener(v->{
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_registerFragment_to_loginFragment);
        });
    }

    @Override
    public void navigateToHomeScreen() {

        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_registerFragment_to_homeActivity);
        requireActivity().finish();

    }

    @Override
    public void showErrorMessage(String localizedMessage) {
        Toast.makeText(requireContext(), localizedMessage, Toast.LENGTH_SHORT).show();
    }
}