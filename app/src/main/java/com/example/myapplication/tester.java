package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;

public class tester extends MainActivity {
    
    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester);
        Toast.makeText(tester.this, "success", Toast.LENGTH_SHORT).show();
    }
}
