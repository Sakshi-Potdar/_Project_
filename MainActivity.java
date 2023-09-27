package com.example.farmerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button guide_btn,predict_btn,disease_btn,fertilizer_btn,about_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guide_btn = (Button) findViewById(R.id.button);
        predict_btn = (Button) findViewById(R.id.button2);
        disease_btn = (Button) findViewById(R.id.button3);
        fertilizer_btn = (Button) findViewById(R.id.button4);
        about_btn = (Button) findViewById(R.id.button5);

        guide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guide = new Intent(MainActivity.this,Guidelines.class);
                startActivity(guide);
            }
        });

        predict_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guide = new Intent(MainActivity.this,Predict.class);
                startActivity(guide);
            }
        });

        disease_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guide = new Intent(MainActivity.this,Disease.class);
                startActivity(guide);
            }
        });

        fertilizer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guide = new Intent(MainActivity.this,Ferilizers.class);
                startActivity(guide);
            }
        });
        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guide = new Intent(MainActivity.this,AboutPage.class);
                startActivity(guide);
            }
        });
    }
}