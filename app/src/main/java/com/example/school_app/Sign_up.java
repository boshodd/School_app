package com.example.school_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
public class Sign_up extends AppCompatActivity{
    EditText usrn,pass,cnfpass,name,fname,mname,rollno,dob,phone,address;
    Button fsign_upbtn;
    ImageView img;
    Bitmap bitmap;
    String encodeImageString;
    Spinner spinner,div;
    SharedPreferences sp;

    private String URL="http://schoolplusplus.000webhostapp.com/php_files/db_insert.php";
    private String sname, susername, spassword, sreenterPassword,sfather,smother,sclass,sdob,saddress,sdiv,srollno,sphone ;
    public String droptext1,droptext2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        usrn= findViewById(R.id.username);
        pass= findViewById(R.id.password);
        cnfpass= findViewById(R.id.cnfpassword);
        name=findViewById(R.id.Rname);
        fname=findViewById(R.id.RFather);
        mname=findViewById(R.id.RMother);

        rollno=findViewById(R.id.RRollno);
        dob=findViewById(R.id.RDob);
        phone=findViewById(R.id.RPhoneno);
        address=findViewById(R.id.RAddress);
        img=findViewById(R.id.RImage);

        spinner =findViewById(R.id.spinner1);
        div=findViewById(R.id.RDiv);

        sp=getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        final String sp_db = sp.getString("selectdb", "");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.classes,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.divi,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        div.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                droptext1=parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        div.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                droptext2=parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fsign_upbtn= findViewById(R.id.fsign_upbtn);
        sname = susername = spassword = sreenterPassword = "";


        fsign_upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sname = name.getText().toString().trim();
                susername = usrn.getText().toString().trim();
                spassword = pass.getText().toString().trim();
                sreenterPassword= cnfpass.getText().toString().trim();

                smother=mname.getText().toString();
                sfather=fname.getText().toString();
                sclass=droptext1;
                //srollno=Integer.parseInt(rollno.getText().toString());
                srollno=rollno.getText().toString();
                sdiv=droptext2;
                sdob=dob.getText().toString();
                //sphone=Long.parseLong(phone.getText().toString());
                sphone=phone.getText().toString();
                saddress=address.getText().toString();


                if(!spassword.equals(sreenterPassword)){
                    Toast.makeText(Sign_up.this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
                }
                else if(!sname.equals("") && !susername.equals("") && !spassword.equals("") && !smother.equals("") && !sfather.equals("") && !sclass.equals("") && !srollno.equals("") && !sdiv.equals("") && !sphone.equals("") && !saddress.equals("") && !sdob.equals("")){

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Sign_up.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        public Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data=new HashMap<>();
                            data.put("t1", sname);
                            data.put("t2", susername);
                            data.put("t3", spassword);
                            data.put("t4", sfather);
                            data.put("t5", smother);
                            data.put("t6", sclass);
                            data.put("t7", sdiv);
                            //data.put("t8", String.valueOf(srollno));
                            data.put("t8", srollno);
                            data.put("t9", sdob);
                            data.put("t10", sphone);
                            //data.put("t10", String.valueOf(sphone));
                            data.put("t11", saddress);
                            data.put("upload",encodeImageString);
                            data.put("tablename",sp_db);

                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
                else{
                    Toast.makeText(Sign_up.this, "All Fields are mandatory", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void encodeBitmapImage(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytesofimage=byteArrayOutputStream.toByteArray();
        encodeImageString=android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }

    public void chooseImage(View objectView){
        Dexter.withActivity(Sign_up.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response)
                    {
                        Intent intent=new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Browse Image"),1);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            Uri filepath=data.getData();
            try
            {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);
            }catch (Exception ex)
            {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}




//----------------------------------------------------------OLD CODE BEGINS(SQLITE CODE)--------------------------------------------




/*
public class Sign_up extends AppCompatActivity {
    */
/*EditText e1,e2,e3;
    Button fsign_upbtn;
    MyDbHandler db;*//*


    EditText usrn,pass,cnfpass,name;//,fname,mname,rollno,clas,div,dob,phone,address;
    Button fsign_upbtn;
    */
/*MyDbHandler db;
    ImageView profileimg;*//*


  */
/*  private Uri imageFilePath;
    public static final int PICK_IMG_REQ=99;
    private Bitmap image2store;*//*


   // private String URL="http://10.0.2.2/loginregister/register.php";
   private String URL="https://schoolplusplus.000webhostapp.com/db_insert.php";
    private String sname, susername, spassword, sreenterPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

       // db= new MyDbHandler(Sign_up.this);

        */
/*usrn= findViewById(R.id.username);
        pass= findViewById(R.id.password);
        cnfpass= findViewById(R.id.cnfpassword);
        name=findViewById(R.id.Rname);
        fname=findViewById(R.id.RFather);
        mname=findViewById(R.id.RMother);
        clas=findViewById(R.id.RClass);
        rollno=findViewById(R.id.RRollno);
        div=findViewById(R.id.RDiv);
        dob=findViewById(R.id.RDob);
        phone=findViewById(R.id.RPhoneno);
        address=findViewById(R.id.RAddress);
        profileimg=findViewById(R.id.RImage);
*//*

        usrn= findViewById(R.id.username);
        pass= findViewById(R.id.password);
        cnfpass= findViewById(R.id.cnfpassword);
        name=findViewById(R.id.Rname);
        */
/*fname=findViewById(R.id.RFather);
        mname=findViewById(R.id.RMother);
        clas=findViewById(R.id.RClass);
        rollno=findViewById(R.id.RRollno);
        div=findViewById(R.id.RDiv);
        dob=findViewById(R.id.RDob);
        phone=findViewById(R.id.RPhoneno);
        address=findViewById(R.id.RAddress);
        profileimg=findViewById(R.id.RImage);*//*

        fsign_upbtn= findViewById(R.id.fsign_upbtn);
        sname = susername = spassword = sreenterPassword = "";


        fsign_upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                */
/*String sname,susrn,spass,scnfpass,sfather,smother,sclass,sdob,saddress,sdiv;
                int srollno;
                long sphone;

                sname=name.getText().toString();
                susrn=usrn.getText().toString();
                spass=pass.getText().toString();
                scnfpass=cnfpass.getText().toString();

                smother=mname.getText().toString();
                sfather=fname.getText().toString();
                sclass=clas.getText().toString();
                srollno=Integer.parseInt(rollno.getText().toString());
                sdiv=div.getText().toString();
                sdob=dob.getText().toString();
                sphone=Long.parseLong(phone.getText().toString());
                saddress=address.getText().toString();




                if((susrn.equals("")) || (spass.equals("")) || (scnfpass.equals("")) || (sname.equals("")) || (sfather.equals("")) || (smother.equals("")) || (sclass.equals("")) || (sdiv.equals("")) || (saddress.equals("")) || (sdob.equals(""))){
                    Toast.makeText(Sign_up.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }

                if(spass.equals(scnfpass)){
                    Users user=new Users(srollno, susrn, spass, sname, image2store, smother, sfather, sclass, sdiv, sdob, saddress, sphone);
                    Boolean bo=db.addOne(user);

                    if(bo==true){
                        Toast.makeText(Sign_up.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Sign_up.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(Sign_up.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Sign_up.this, "Passwords doesnt match", Toast.LENGTH_SHORT).show();*//*

                sname = name.getText().toString().trim();
                susername = usrn.getText().toString().trim();
                spassword = pass.getText().toString().trim();
                sreenterPassword= cnfpass.getText().toString().trim();
                if(!spassword.equals(sreenterPassword)){
                    Toast.makeText(Sign_up.this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
                }
                else if(!sname.equals("") && !susername.equals("") && !spassword.equals("") && !sreenterPassword.equals("")){

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("success")) {
                                Intent intent = new Intent(Sign_up.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else if (response.equals("failure")) {
                                Toast.makeText(Sign_up.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Sign_up.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        public Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data=new HashMap<>();
                            data.put("name", sname);
                            data.put("username", susername);
                            data.put("password", spassword);

                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }

            }
        });
    }

*/
/*
    public void chooseImage(View objectView){
        try {
            Intent i=new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(i,PICK_IMG_REQ);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMG_REQ && resultCode==RESULT_OK && data!=null && data.getData()!=null){
                imageFilePath=data.getData();
                image2store= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
                profileimg.setImageBitmap(image2store);
            }
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }*//*


}*/
