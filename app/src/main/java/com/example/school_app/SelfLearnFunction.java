package com.example.school_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class SelfLearnFunction extends AppCompatActivity {
TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_learn_function);

        t1= findViewById(R.id.site1);
        t2= findViewById(R.id.site2);
        t3= findViewById(R.id.site3);
        t4= findViewById(R.id.site4);

        t1.setMovementMethod(LinkMovementMethod.getInstance());
        t2.setMovementMethod(LinkMovementMethod.getInstance());
        t3.setMovementMethod(LinkMovementMethod.getInstance());
        t4.setMovementMethod(LinkMovementMethod.getInstance());
    }
}