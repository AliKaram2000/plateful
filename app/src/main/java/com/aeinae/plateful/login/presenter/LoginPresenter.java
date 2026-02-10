package com.aeinae.plateful.login.presenter;

public interface LoginPresenter {

    void loginWithEmailAndPassword(String email, String password);
    void loginWithGoogle();

}
