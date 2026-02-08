package com.aeinae.plateful.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aeinae.plateful.R;


public class RegisterFragment extends Fragment implements RegisterView {

    Button signUp, googleSignUp;
    EditText username, email, password, confirmPassword;
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
    }

    public void initUI(View view){
        signUp = view.findViewById(R.id.btn_register);
        googleSignUp = view.findViewById(R.id.btn_register_with_google);
        username = view.findViewById(R.id.et_input_username);
        email = view.findViewById(R.id.et_input_email);
        password = view.findViewById(R.id.et_input_pw);
        confirmPassword = view.findViewById(R.id.et_input_confirm_pw);
        login = view.findViewById(R.id.tv_login);
        presenter = new RegisterPresenterImp(this);
    }
}