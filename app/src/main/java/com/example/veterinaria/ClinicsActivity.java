package com.example.veterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.veterinaria.databinding.ActivityClinicsBinding;

public class ClinicsActivity extends DrawerBaseActivity {

    ActivityClinicsBinding activityClinicsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityClinicsBinding = ActivityClinicsBinding.inflate(getLayoutInflater());
        setContentView(activityClinicsBinding.getRoot());
        allocateActivityTitle(getString(R.string.clinics));
    }
}