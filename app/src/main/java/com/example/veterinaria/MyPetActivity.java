package com.example.veterinaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.veterinaria.databinding.ActivityDashboardBinding;
import com.example.veterinaria.databinding.ActivityMyPetBinding;
import com.google.android.material.navigation.NavigationView;

public class MyPetActivity extends DrawerBaseActivity {

    ActivityMyPetBinding activityMyPetBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMyPetBinding = ActivityMyPetBinding.inflate(getLayoutInflater());
        setContentView(activityMyPetBinding.getRoot());
        allocateActivityTitle(getString(R.string.my_pets));

    }




}