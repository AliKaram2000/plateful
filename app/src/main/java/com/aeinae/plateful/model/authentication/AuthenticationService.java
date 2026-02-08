package com.aeinae.plateful.model.authentication;

import com.google.firebase.auth.FirebaseAuth;
import io.reactivex.rxjava3.core.Single;

public class AuthenticationService {

    public Single<Boolean> loginWithEmailAndPassword(String email, String password) {
        return Single.create(emitter -> {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(
                            authResult -> {
                                emitter.onSuccess(true);
                            })
                    .addOnFailureListener(
                            e -> {
                                emitter.onError(e);
                            })
            ;
        });
    }

    public Single<Boolean> registerWithEmailAndPassword(String email, String password) {
        return Single.create(emitter -> {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(
                            authResult -> {
                                emitter.onSuccess(true);
                            })
                    .addOnFailureListener(
                            e -> {
                                emitter.onError(e);
                            })
            ;
        });
    }

}
