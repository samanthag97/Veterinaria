package com.example.veterinaria;

import android.os.Bundle;

import com.example.veterinaria.databinding.ActivityClinicsBinding;
import com.example.veterinaria.databinding.ActivitySettingsBinding;

public class SettingsActivity extends DrawerBaseActivity {

    ActivitySettingsBinding activitySettingsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySettingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(activitySettingsBinding.getRoot());
        allocateActivityTitle(getString(R.string.settings));
    }
}