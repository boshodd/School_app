package com.example.school_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;
import android.content.Intent;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer_layout;
    NavigationView navigationView;
    MyDbHandler db;

    SharedPreferences sp;

    //For hall of fame
    TabLayout tabLayout;
    TabItem tabItem1,tabItem2,tabItem3,tabItem4;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    public String sp_username,name,clas,rollno,divi;
    private String url="http://schoolplusplus.000webhostapp.com/php_files/datafetching.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel> sliderModels= new ArrayList<>();
        sliderModels.add(new SlideModel("https://schoolplusplus.000webhostapp.com/Website/Image/1.png"));
        sliderModels.add(new SlideModel("https://schoolplusplus.000webhostapp.com/Website/Image/2.png"));
        //sliderModels.add(new SlideModel("https://thumbs.dreamstime.com/b/group-preschool-kids-teacher-reading-kindergarten-cartoon-detailed-colorful-illustrations-isolated-white-154380468.jpg"));
        imageSlider.setImageList(sliderModels,true);

        drawer_layout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar= findViewById(R.id.toolbar);
        sp=getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);



        //Hall of fame
        tabLayout=findViewById(R.id.maintab);
        tabItem1=findViewById(R.id.tab1);
        tabItem2=findViewById(R.id.tab2);
        tabItem3=findViewById(R.id.tab3);
        tabItem4=findViewById(R.id.tab4);

        viewPager=findViewById(R.id.vpager);
        pageAdapter= new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()== 0 || tab.getPosition()== 1 || tab.getPosition()== 2 || tab.getPosition()== 3){
                    pageAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Listen for new scroll or page change
        //hall of fame ends

        db= new MyDbHandler(this);
        //String email=getIntent().getStringExtra("key_username");

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
        // Inittializing bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.btm_nav);

        //setting home as default
        bottomNavigationView.setSelectedItemId(R.id.btmhome);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.btmhome:
                        Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                        startActivity(intent);
                         break;

                    case R.id.btmprofile:
                        Intent i=new Intent(getApplicationContext(),Student_profile.class);
                        startActivity(i);
                        break;

                    case R.id.btmnotificatoin:
                        Toast.makeText(Dashboard.this, "No new notifications", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.btmsettings:
                        Toast.makeText(Dashboard.this, "Settings are upto dated", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        fetchdata();



    }

    private void fetchdata() {
        final SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        sp_username = sp.getString("Username", "");
        final String sp_db=sp.getString("selectdb","");

        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);


                            name = object.getString("name");
                             clas = object.getString("clas");
                             divi = object.getString("divi");
                             rollno = object.getString("rollno");


                            SharedPreferences.Editor editor = sp.edit();


                            editor.putString("Name",name);
                            editor.putString("Clas",clas);
                            editor.putString("Divi",divi);
                            editor.putString("Rollno",rollno);
                            editor.commit();


                        }//end of for
                    }//end if
                }//end try
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json login error", Toast.LENGTH_SHORT).show();
                }// end of catch
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"bruhhhhhhh "+error.toString().trim(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data=new HashMap<>();
                data.put("username", sp_username);
                data.put("tablename",sp_db);

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
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
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),UserSelector.class);
                startActivity(i);
                finish();
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


    public void bot_profile(View view){
        Intent intent=new Intent(getApplicationContext(),Student_profile.class);
        startActivity(intent);
    }


    //button click for Result code
    public void Result(View view){
        Intent intent=new Intent(getApplicationContext(),Result.class);
        startActivity(intent);

    }
    public void feefunction(View view){
        Intent intent=new Intent(getApplicationContext(),FeeFunction.class);
        startActivity(intent);

    }
    public void AssignmentFunc(View view){
        Intent intent=new Intent(getApplicationContext(),Assignmentfunction.class);
        startActivity(intent);
    }
    public void timeTableFunc(View view){
        Intent intent=new Intent(getApplicationContext(),TimetableFunc.class);
        startActivity(intent);
    }

    public void selflearnfunc(View view){
        Intent intent=new Intent(getApplicationContext(),SelfLearnFunction.class);
        startActivity(intent);
    }



}
