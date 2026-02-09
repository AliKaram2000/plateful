package com.aeinae.plateful.login;

public interface LoginPresenter {

    void loginWithEmailAndPassword(String email, String password);
    void loginWithGoogle();

}
