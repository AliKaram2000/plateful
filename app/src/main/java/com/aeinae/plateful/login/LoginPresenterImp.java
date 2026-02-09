package com.aeinae.plateful.login;

import android.util.Patterns;

import com.aeinae.plateful.model.authentication.AuthenticationService;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
public class LoginPresenterImp implements LoginPresenter{
    LoginView view;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    AuthenticationService authServ;
    public LoginPresenterImp(LoginView view, AuthenticationService authServ){
        this.view = view;
        this.authServ = authServ;
    }

    @Override
    public void loginWithEmailAndPassword(String email, String password) {
        boolean isValid = validateCredentials(email, password);
        if(!isValid){return;}
        Disposable disposable = authServ.loginWithEmailAndPassword(email,password)
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

    boolean validateCredentials(String email, String password){
        if (email == null || email.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            view.showErrorMessage("Invalid email");
            return false;
        }
        if (password == null || password.isEmpty() || password.length() < 8) {
            view.showErrorMessage("Invalid password");
            return false;
        }
        return true;
    }
}
