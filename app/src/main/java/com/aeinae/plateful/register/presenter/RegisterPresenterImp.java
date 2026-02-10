package com.aeinae.plateful.register.presenter;

import android.util.Patterns;

import com.aeinae.plateful.model.authentication.AuthenticationService;
import com.aeinae.plateful.register.view.RegisterView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterPresenterImp implements RegisterPresenter {

    RegisterView view;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AuthenticationService authServ;
    public RegisterPresenterImp(RegisterView view, AuthenticationService authServ){
        this.view = view;
        this.authServ = authServ;
    }

    @Override
    public void registerWithEmailAndPassword(String username, String email, String password, String confirmPassword) {
        boolean isValid = validateCredentials(username,email,password,confirmPassword);
        if(!isValid){return;}
        Disposable disposable = authServ.registerWithEmailAndPassword(email,password)
                .subscribeOn(
                        Schedulers.io()
                )
                .observeOn(
                        AndroidSchedulers.mainThread()
                )
                .subscribe(
                isSuccess -> {
                    view.navigateToHomeScreen();
                },
                onError -> {
                    view.showErrorMessage(onError.getLocalizedMessage());
                }
        );
        compositeDisposable.add(disposable);
    }
    @Override
    public void loginWithGoogle() {
        Disposable disposable = view.getGoogleCredentials()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .flatMap(
                        credential -> {
                            return authServ.loginWithGoogle(credential);
                        })
                .observeOn(
                        AndroidSchedulers.mainThread()
                )
                .subscribe(
                        isSuccess -> {
                            view.navigateToHomeScreen();
                        },
                        onError -> {
                            view.showErrorMessage(onError.getLocalizedMessage());
                        });
        compositeDisposable.add(disposable);

    }
    boolean validateCredentials(String username, String email, String password, String confirmPassword){
        if (username.trim().length() < 3) {
            view.showErrorMessage("Username is too short");
            return false;
        }
        if (email == null || email.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            view.showErrorMessage("Invalid email");
            return false;
        }
        if (password == null || password.isEmpty() || password.length() < 8) {
            view.showErrorMessage("Your password is weak, please enter a stronger password");
            return false;
        }
        if(!password.equals(confirmPassword)){
            view.showErrorMessage("Passwords do not match");
            return false;
        }
        return true;
    }
}
