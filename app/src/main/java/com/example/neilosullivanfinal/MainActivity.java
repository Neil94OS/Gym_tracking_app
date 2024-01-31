package com.example.neilosullivanfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;




public class MainActivity extends AppCompatActivity {

    // Declare a RecyclerView
    RecyclerView recyclerView;
    // Declare an adapter
    RecyclerView.Adapter myAdapter;
    //Declare layout manager
    RecyclerView.LayoutManager layoutmanager;

    //Lists to populate recyler view
    String[] menuList = {"Log Workout\n", "Past Workouts\n", "Log Meal\n", "Past Meals\n", "Tutorials\n", "Steps\n"};
    int[] menuImages = {R.drawable.logworkout1, R.drawable.pastworkout1,
            R.drawable.logmeal1, R.drawable.pastmeal1, R.drawable.tutorials1,
            R.drawable.steps};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout to activity main
        setContentView(R.layout.activity_main);

        //set recylcer view, layout manager and adapter
        recyclerView = findViewById(R.id.recycler_view1);
        layoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanager);
        myAdapter = new MyAdapter(this, menuList,  menuImages);
        recyclerView.setAdapter(myAdapter);



    }

}