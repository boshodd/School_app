package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Student_profile extends AppCompatActivity {
    MyDbHandler db;
    ImageView dp;
    TextView dname2,dparent2,drollno2,dclass2,ddiv2,dphone2,daddress2,ddob2;
    Bitmap dpBitmap;
    Model model;
    private String sp_username;
    String sp_db;
    private String url="http://schoolplusplus.000webhostapp.com/php_files/datafetching.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        dp = findViewById(R.id.deatils_dp);


        dname2 = findViewById(R.id.details_name2);
        dparent2 = findViewById(R.id.details_Pname2);
        drollno2 = findViewById(R.id.details_Rollno2);
        dclass2 = findViewById(R.id.details_Class2);
        ddiv2 = findViewById(R.id.details_Div2);
        daddress2 = findViewById(R.id.details_Address2);
        dphone2 = findViewById(R.id.details_Phone2);
        ddob2 = findViewById(R.id.details_Dob2);


        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        sp_username = sp.getString("Username", "");
        sp_db =sp.getString("selectdb","");


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

                            String id = object.getString("id");
                            String name = object.getString("name");
                            String username = object.getString("username");
                            String password = object.getString("password");
                            String fname = object.getString("father_name");
                            String mname = object.getString("mothers_name");
                            String clas = object.getString("clas");
                            String divi = object.getString("divi");
                            String rollno = object.getString("rollno");
                            String dob = object.getString("dob");
                            String phone = object.getString("phone");
                            String address = object.getString("address");
                            String image = object.getString("photo");

                            String imgurl="https://schoolplusplus.000webhostapp.com/php_files/images/"+image;
                            //Toast.makeText(Student_profile.this, "hi", Toast.LENGTH_LONG).show();
                            dname2.setText(name);
                            String ax = fname + ", " + mname;
                            dparent2.setText(ax);
                            drollno2.setText(rollno);
                            dclass2.setText(clas);
                            ddiv2.setText(divi);
                            ddob2.setText(dob);
                            dphone2.setText(phone);
                            daddress2.setText(address);
                            Glide.with(Student_profile.this).load(imgurl).into(dp);

                            //model = new Model(id, name, username, password, fname, mname, clas, divi, rollno, dob, phone, address);
                        }//end of for
                    }//end if
                }//end try
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Student_profile.this, "Json login error", Toast.LENGTH_SHORT).show();
                }// end of catch
            }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString().trim(), Toast.LENGTH_SHORT).show();
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


}



//--------------------------------------------------------------------------------------------------------
/*
    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Toast.makeText(Student_profile.this, "is:" + response, Toast.LENGTH_SHORT).show();

            try {
                JSONObject jsonObject = new JSONObject(response);
                String success = jsonObject.getString("success");
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                if (success.equals("1")) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String id = object.getString("id");
                        String name = object.getString("name");
                        String username = object.getString("username");
                        String password = object.getString("password");
                        String fname = object.getString("father_name");
                        String mname = object.getString("mothers_name");
                        String clas = object.getString("clas");
                        String divi = object.getString("divi");
                        int rollno = object.getInt("rollno");
                        String dob = object.getString("dob");
                        Long phone = object.getLong("phone");
                        String address = object.getString("address");

                        dname2.setText(name);
                        String ax = fname + ", " + mname;
                        dparent2.setText(ax);
                        drollno2.setText(rollno);
                        dclass2.setText(clas);
                        ddiv2.setText(divi);
                        ddob2.setText(dob);
                        daddress2.setText(address);

                        model = new Model(id, name, username, password, fname, mname, clas, divi, rollno, dob, phone, address);
                    }
                } else
                    Toast.makeText(Student_profile.this, "It wasn't 1", Toast.LENGTH_SHORT).show();


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Student_profile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                Toast.makeText(Student_profile.this, "tt  " + sp_username, Toast.LENGTH_SHORT).show();
                data.put("username11", sp_username);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
*/
//--------------------------------------------------------------------------------------------------------



/*
package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Student_profile extends AppCompatActivity {
    MyDbHandler db;
    ImageView dp;
    TextView dname2,dparent2,drollno2,dclass2,ddiv2,dphone2,daddress2,ddob2;
    Bitmap dpBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        db=new MyDbHandler(this);
        dp=findViewById(R.id.deatils_dp);


        dname2=findViewById(R.id.details_name2);
        dparent2=findViewById(R.id.details_Pname2);
        drollno2=findViewById(R.id.details_Rollno2);
        dclass2=findViewById(R.id.details_Class2);
        ddiv2=findViewById(R.id.details_Div2);
        daddress2=findViewById(R.id.details_Address2);
        dphone2=findViewById(R.id.details_Phone2);
        ddob2=findViewById(R.id.details_Dob2);



        SharedPreferences sp=getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String sp_username=sp.getString("Username","");

        Cursor cursor=db.getAllData(sp_username);
        // Log.d("rick", "email"+email);
        if (cursor.getCount()==0){
            Toast.makeText(this, "No data inserted yet", Toast.LENGTH_SHORT).show();
            //cursor.close();
        }
        else {
            while (cursor.moveToNext()) {

                byte[] image2byte=cursor.getBlob(4);
                dpBitmap= BitmapFactory.decodeByteArray(image2byte,0,image2byte.length);
                dp.setImageBitmap(dpBitmap);

                dname2.setText(cursor.getString(3));
                String ax=(cursor.getString(6)+", "+cursor.getString(5));
                dparent2.setText(ax);
                drollno2.setText(String.valueOf(cursor.getInt(0)));
                dclass2.setText(cursor.getString(7));
                ddiv2.setText(cursor.getString(8));
                ddob2.setText(cursor.getString(9));
                daddress2.setText(cursor.getString(10));

                dphone2.setText(String.valueOf(cursor.getLong(11)));
                cursor.close();
                db.close();


            }

        }


    }
}
*/
