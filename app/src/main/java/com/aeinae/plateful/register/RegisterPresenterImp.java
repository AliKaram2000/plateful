package com.aeinae.plateful.register;

import com.aeinae.plateful.login.LoginPresenter;
import com.aeinae.plateful.login.LoginView;

public class RegisterPresenterImp implements RegisterPresenter {

    RegisterView view;
    public RegisterPresenterImp(RegisterView view){
        this.view = view;
    }
}
