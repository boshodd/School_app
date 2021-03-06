package com.example.school_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;


public class Sign_up extends AppCompatActivity {
    /*EditText e1,e2,e3;
    Button fsign_upbtn;
    MyDbHandler db;*/

    EditText usrn,pass,cnfpass,name,fname,mname,rollno,clas,div,dob,phone,address;
    Button fsign_upbtn;
    MyDbHandler db;
    ImageView profileimg;

    private Uri imageFilePath;
    public static final int PICK_IMG_REQ=99;
    private Bitmap image2store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db= new MyDbHandler(Sign_up.this);

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
*/
        usrn= findViewById(R.id.username);
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
        fsign_upbtn= findViewById(R.id.fsign_upbtn);

        fsign_upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String Susrn,Spass,Scnfpass,Sname,Sfather,Smother,Sclass,Sdiv,Srollno,Sdob,Sphone,Saddress;

                Susrn=usrn.getText().toString();
                Spass=pass.getText().toString();
                Scnfpass=cnfpass.getText().toString();
                Sname=name.getText().toString();
                Sfather=fname.getText().toString();
                Smother=mname.getText().toString();
                Sclass=clas.getText().toString();
                Sdiv=div.getText().toString();
                Srollno=rollno.getText().toString();
                Sdob=dob.getText().toString();
                Sphone=phone.getText().toString();
                Saddress=address.getText().toString();*/

                String sname,susrn,spass,scnfpass,sfather,smother,sclass,sdob,saddress,sdiv;
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
                    Toast.makeText(Sign_up.this, "Passwords doesnt match", Toast.LENGTH_SHORT).show();

            }
        });

        /*db= new MyDbHandler(Sign_up.this);
        e1= findViewById(R.id.username);
        e2= findViewById(R.id.password);
        e3= findViewById(R.id.cnfpassword);
        fsign_upbtn= findViewById(R.id.fsign_upbtn);

        fsign_upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a,b,c;
                a=e1.getText().toString();
                b=e2.getText().toString();
                c=e3.getText().toString();

                if((a.equals("")) || (b.equals("")) || (c.equals(""))){
                    Toast.makeText(Sign_up.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(b.equals(c)){
                        Users user=new Users(a,b);
                        Boolean bo=db.addOne(user);
                        if(bo==true){Toast.makeText(Sign_up.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Sign_up.this,MainActivity.class);
                        startActivity(intent);
                        }
                        else
                            Toast.makeText(Sign_up.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(Sign_up.this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

    }


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
    }

}