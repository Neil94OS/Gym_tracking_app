package com.example.neilosullivanfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class workoutLogged extends AppCompatActivity {
    Button btn_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutouttlogged);
        btn_home = findViewById(R.id.button2);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(workoutLogged.this, MainActivity.class);
                startActivity(intent);

            }
        });



    }
}
