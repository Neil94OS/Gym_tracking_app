package com.example.neilosullivanfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class stepslogged extends AppCompatActivity {
    ListView listView;
    Button btn_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepslogged);
        btn_home = findViewById(R.id.button2);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(stepslogged.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
