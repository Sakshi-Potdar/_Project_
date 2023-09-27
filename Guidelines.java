package com.example.farmerguide;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Guidelines extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_layout);
        Toast.makeText(this, "Guidence", Toast.LENGTH_SHORT).show();
    }

}
