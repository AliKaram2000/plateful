package com.aeinae.plateful.profile.view;

import androidx.credentials.Credential;

import io.reactivex.rxjava3.core.Single;

public interface ProfileView {
    void navigateToMainScreen();
    void showErrorMessage(String localizedMessage);
}
