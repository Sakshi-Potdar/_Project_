package com.example.farmerguide;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ferilizers extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ferilizer_layout);
        Toast.makeText(this, "Ferilizer information", Toast.LENGTH_SHORT).show();
    }
    }
