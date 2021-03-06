package com.example.school_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;
import android.content.Intent;


import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer_layout;
    NavigationView navigationView;
    MyDbHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawer_layout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar= findViewById(R.id.toolbar);

        db= new MyDbHandler(this);
        //String email=getIntent().getStringExtra("key_username");

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);


    }

    @Override
    public void onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;

            case R.id.nav_profile:
                Intent intent=new Intent(getApplicationContext(),Student_profile.class);

                startActivity(intent);
                break;

            case R.id.nav_login:
                    Toast.makeText(getApplicationContext(), "Working but yet to execute", Toast.LENGTH_SHORT).show();
                    break;
            case R.id.nav_share:
                    Toast.makeText(getApplicationContext(), "Working but yet to execute1", Toast.LENGTH_SHORT).show();
                    break;

            case R.id.nav_rate:
                Toast.makeText(getApplicationContext(), "Working but yet to execute2", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer_layout.closeDrawer(GravityCompat.START);

        return true;
    }


    public void bot_home(View view){
        Intent intent=new Intent(getApplicationContext(),Dashboard.class);
        startActivity(intent);
    }
    public void bot_profile(View view){
        Intent intent=new Intent(getApplicationContext(),Student_profile.class);
        startActivity(intent);
    }
    public void bot_notifications(View view){
        Toast.makeText(this, "No new notifications", Toast.LENGTH_SHORT).show();
    }
    public void bot_settings(View view){
        Toast.makeText(this, "Yes u clicked settings now", Toast.LENGTH_SHORT).show();
    }


    //button click for Result code
    public void Result(View view){
        Intent intent=new Intent(getApplicationContext(),Result.class);
        startActivity(intent);

    }
}