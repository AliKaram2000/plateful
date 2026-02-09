package com.aeinae.plateful.model.authentication;

import android.os.Bundle;

import androidx.credentials.Credential;

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

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
                            });
        });
    }

    // [START handle_sign_in]
    public Single<Boolean> loginWithGoogle(Credential credential) {

        return Single.create(emitter -> {
            // Create Google ID Token
            Bundle credentialData = credential.getData();
            GoogleIdTokenCredential googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credentialData);

            // Sign in to Firebase with using the token
            AuthCredential authCredential =
                    GoogleAuthProvider.getCredential(googleIdTokenCredential.getIdToken(), null);
            FirebaseAuth.getInstance().signInWithCredential(authCredential)
                    .addOnSuccessListener(
                            authResult -> {
                                emitter.onSuccess(true);
                            })
                    .addOnFailureListener(
                            e -> {
                                emitter.onError(e);
                            });
        });
    }
}
