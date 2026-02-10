package com.aeinae.plateful.login.presenter;

import androidx.credentials.Credential;

import io.reactivex.rxjava3.core.Single;

public interface LoginView {
    void navigateToHomeScreen();
    void showErrorMessage(String localizedMessage);
    Single<Credential> getGoogleCredentials();
}
