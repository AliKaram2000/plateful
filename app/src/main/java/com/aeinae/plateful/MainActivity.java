package com.aeinae.plateful;

import android.os.Bundle;
import android.os.CancellationSignal;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.credentials.Credential;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;

import com.google.android.libraries.identity.googleid.GetGoogleIdOption;

import java.util.concurrent.Executors;

import io.reactivex.rxjava3.core.Single;

public class MainActivity extends AppCompatActivity {
    private CredentialManager credentialManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentContainerView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        credentialManager = CredentialManager.create(getBaseContext());
    }

    public Single<Credential> launchCredentialManager() {
        return Single.create(emitter -> {
            // [START create_credential_manager_request]
            // Instantiate a Google sign-in request
            GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(getString(R.string.default_web_client_id))
                    .build();

            // Create the Credential Manager request
            GetCredentialRequest request = new GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build();
            // [END create_credential_manager_request]

            // Launch Credential Manager UI
            credentialManager.getCredentialAsync(
                    this,
                    request,
                    new CancellationSignal(),
                    Executors.newSingleThreadExecutor(),
                    new CredentialManagerCallback<>() {
                        @Override
                        public void onResult(GetCredentialResponse result) {
                            // Extract credential from the result returned by Credential Manager
                            emitter.onSuccess(result.getCredential());
                        }

                        @Override
                        public void onError(GetCredentialException e) {
                            emitter.onError(e);
                        }
                    }
            );
        });

    }


}