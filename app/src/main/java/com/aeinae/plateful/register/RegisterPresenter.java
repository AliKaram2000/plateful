package com.aeinae.plateful.register;


public interface RegisterPresenter {
    void registerWithEmailAndPassword(String username, String email, String password, String confirmPassword);
    void loginWithGoogle();
}
