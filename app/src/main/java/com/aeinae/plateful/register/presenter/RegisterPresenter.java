package com.aeinae.plateful.register.presenter;


public interface RegisterPresenter {
    void registerWithEmailAndPassword(String username, String email, String password, String confirmPassword);
    void loginWithGoogle();
}
