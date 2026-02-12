package com.aeinae.plateful.profile.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aeinae.plateful.MainActivity;
import com.aeinae.plateful.R;
import com.aeinae.plateful.model.authentication.AuthenticationService;
import com.google.android.material.button.MaterialButton;


public class ProfileFragment extends Fragment implements ProfileView {
    AuthenticationService authenticationService = new AuthenticationService();
    MaterialButton logoutButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);

        logoutButton.setOnClickListener(
        v->{
                authenticationService.logout();
                navigateToMainScreen();
        });
    }
    public void bindView(View view){
        logoutButton = view.findViewById(R.id.btn_logout);
    }
    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(requireContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        requireActivity().finish();
    }
    @Override
    public void showErrorMessage(String localizedMessage) {
        Toast.makeText(requireContext(), localizedMessage, Toast.LENGTH_SHORT).show();
    }
}