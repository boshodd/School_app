package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.autofill.ImageTransformation;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TimetableFunc extends AppCompatActivity {
   /* TextView t11, t12, t13, t14, t15, t16, t21, t22, t23, t24, t25, t26, t51, t52, t53, t54, t55, t56, t61, t62, t63, t64, t65, t66;
    TextView t31, t32, t33, t34, t35, t36, t41, t42, t43, t44, t45, t46, t71, t72, t73, t74, t75, t76, t81, t82, t83, t84, t85, t86;*/

    ImageView iv;
    String sp_class,sp_div;
    String url="https://schoolplusplus.000webhostapp.com/php_files/timetable.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_func);

        iv=findViewById(R.id.iv);
        final SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        sp_class= sp.getString("Clas", "");
        sp_div=sp.getString("Divi","");


        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //https://schoolplusplus.000webhostapp.com/php_files/images/tt/XA.jpg
                //https://schoolplusplus.000webhostapp.com/php_files/tt/XA.jpg
               String imgurl="https://schoolplusplus.000webhostapp.com/php_files/tt/"+sp_class+sp_div+".jpg";
                Toast.makeText(TimetableFunc.this, "url is "+imgurl, Toast.LENGTH_LONG).show();
                Glide.with(getApplicationContext()).load(imgurl).into(iv);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data=new HashMap<>();
                data.put("clas", sp_class);
                data.put("divi", sp_div);

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        /*ImageRequest imageRequest= new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
//Request.Method.POST,
                 iv.setImageBitmap(response);
                    //Toast.makeText(TimetableFunc.this, imgurl, Toast.LENGTH_LONG).show();

                            //Glide.with(getApplicationContext()).load(imgurl).into(iv);
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
                data.put("clas", sp_class);
                data.put("divi", sp_div);

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);*/




    }
}