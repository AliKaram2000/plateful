package com.aeinae.plateful.register.view;

import androidx.credentials.Credential;

import io.reactivex.rxjava3.core.Single;

public interface RegisterView {
    void navigateToHomeScreen();
    void showErrorMessage(String localizedMessage);
    Single<Credential> getGoogleCredentials();
}
