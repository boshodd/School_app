package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ForgotPass extends AppCompatActivity {
    EditText usrnm,name;

    String usrnm1,name1;
    String url="https://schoolplusplus.000webhostapp.com/php_files/forgotpass.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        usrnm =findViewById(R.id.contact);
        name=findViewById(R.id.name);
    }

    public void fogotpass(View view) {
        usrnm1= usrnm.getText().toString();
        name1= name.getText().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("SUCCESSS")){
                    Toast.makeText(getApplicationContext(), "Email sucessfully sent check your mail",Toast.LENGTH_LONG).show();

                } else{

                    Toast.makeText(getApplicationContext(),"FAILED",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Please Check Connection",Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("Username",usrnm1);
                params.put("Name", name1);

                return super.getParams();
            }
        };
    }//end of func
}//end of main class