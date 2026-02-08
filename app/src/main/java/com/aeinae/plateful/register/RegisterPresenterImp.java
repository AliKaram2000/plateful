package com.aeinae.plateful.register;

import com.aeinae.plateful.model.authentication.AuthenticationService;

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
    public void registerWithEmailAndPassword(String email, String password) {
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
}
