package com.example.neilosullivanfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;


public class SecondActivity extends AppCompatActivity {

    //Declare recycler view
    RecyclerView recyclerView;
    // Declare an adapter
    RecyclerView.Adapter pagesAdapter;
    //Layout manager
    RecyclerView.LayoutManager layoutmanager;
    //Tab layout
    TabLayout tabLayout;
    EditText et1,et2,et3,et_chk,et_del;
    Button btn_logworkout,btn_logmeal,btn_logsteps;
    ListView listView;
    Context context;
    TextView textView;
    Button btn;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //varaible for the adapter position, switch case for appropriate layout
        int choice = 0;
        Intent intent = getIntent();// get extra intent, name
        if(intent != null)
            choice = intent.getIntExtra("name", 0);

        // Load the appropriate layout
        switch(choice) {
            //if adapter position 0, load log workouts
            case 0:
                setContentView(R.layout.logworkout);
                btn_logworkout = findViewById(R.id.button3);

                et2 = findViewById(R.id.editText1);
                et3 = findViewById(R.id.editText2);
                btn_logworkout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String workout = et2.getText().toString();
                        String time = et3.getText().toString();

                        DB_Handler dbHandler = new DB_Handler(SecondActivity.this);
                        dbHandler.insertUserWorkout(workout, time);


                        Intent intent = new Intent(SecondActivity.this, workoutLogged.class);
                        startActivity(intent);

                    }
                });
                break;

//if adapter position 1, load show workouts
            case 1:
                setContentView(R.layout.displayworkouts);
                DB_Handler dbHandler2 = new DB_Handler(SecondActivity.this);
                ArrayList<HashMap<String, String>> workoutList = dbHandler2.GetUsersWorkouts();
                listView = findViewById(R.id.listView);
                ListAdapter workoutAdapter = new SimpleAdapter(SecondActivity.this,
                        workoutList,
                        R.layout.workoutlayout,
                        new String[]{"workout","time"},
                        new int[]{R.id.workout,R.id.time});
                listView.setAdapter(workoutAdapter);
                break;

            //if case equals 2 show log meal
            case 2:
                setContentView(R.layout.logmeal);
                btn_logmeal = findViewById(R.id.button3);
                //et1 = findViewById(R.id.editText1);
                et2 = findViewById(R.id.editText1);
                et3 = findViewById(R.id.editText2);
                btn_logmeal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //String name = et1.getText().toString();
                        String meal = et2.getText().toString();
                        String calories = et3.getText().toString();

                        DB_Handler dbHandler = new DB_Handler(SecondActivity.this);
                        dbHandler.insertUserMeal(meal, calories);


                        Intent intent = new Intent(SecondActivity.this, mealLogged.class);
                        startActivity(intent);

                    }
                });
                break;

            //show meals
            case 3:
                setContentView(R.layout.displaymeals);
                DB_Handler dbHandler3 = new DB_Handler(SecondActivity.this);
                ArrayList<HashMap<String, String>> mealList = dbHandler3.GetUsersMeals();
                listView = findViewById(R.id.listView);
                ListAdapter mealAdapter = new SimpleAdapter(SecondActivity.this,
                        mealList,
                        R.layout.mealslayout,
                        //change to 2 without name
                        new String[]{"meal","calories"},
                        new int[]{R.id.meal, R.id.calories});
                listView.setAdapter(mealAdapter);
                break;


            //tutorials
            case 4:
                setContentView(R.layout.tutorials);
                textView = findViewById(R.id.textView);
                registerForContextMenu(textView);
                btn = findViewById(R.id.button);

                //pop up menu
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PopupMenu popup = new PopupMenu(SecondActivity.this,view);
                        popup.setOnMenuItemClickListener(SecondActivity.this::onMenuItemClick);
                        popup.inflate(R.menu.popup_menu);
                        popup.show();
                    }
                });


                break;


            //Steps
            case 5:
                setContentView(R.layout.steps);
                FrameLayout fl = findViewById(R.id.frameLayout);
                tabLayout = findViewById(R.id.tabLayout);

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frameLayout,new Fragment1());
                fragmentTransaction.commit();



                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        switch(tab.getPosition()){

                            case 0:
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.frameLayout, new Fragment1())
                                        .commit();
                                break;

                            case 1:
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.frameLayout, new Fragment2())
                                        .commit();
                                break;


                        }

                    }
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });



                break;



        }


    }


    public boolean onMenuItemClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.first_item:
                Toast.makeText(this,"Selected Item 1 " + item.getTitle(),Toast.LENGTH_LONG).show();
                setContentView(R.layout.tutorial_view);
                recyclerView = findViewById(R.id.tutorial_recycler);
                layoutmanager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutmanager);

        //Stretches for recycler view
                String[] stretchList = {"Groin", "Hamstring", "Back", "Arm"};
                int[] stretchImages = {R.drawable.groinstretch, R.drawable.hamstringstretch,
                        R.drawable.backstretch, R.drawable.armstretch};
                String[] stretchDescription = {this.getString(R.string.groin_stretch), this.getString(R.string.hamstring_stretch), this.getString(R.string.back_stretch), this.getString(R.string.arm_stretch)};

                //set adapter
                pagesAdapter = new TutorialsAdapter(this, stretchList, stretchImages, stretchDescription);
                recyclerView.setAdapter(pagesAdapter);
                return true;
            case R.id.second_item:
                Toast.makeText(this,"Selected Item 2 " + item.getTitle(),Toast.LENGTH_LONG).show();
                setContentView(R.layout.tutorial_view);
                recyclerView = findViewById(R.id.tutorial_recycler);
                layoutmanager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutmanager);

                //Running for recyler view
                String[] runningList = {"Jog", "Sprint"};
                int[] runningImages = {R.drawable.jogimage, R.drawable.sprintimage};
                String[] runningDesc = {this.getString(R.string.jog), this.getString(R.string.sprint)};

                //set adapter
                pagesAdapter = new TutorialsAdapter(this, runningList, runningImages, runningDesc);
                recyclerView.setAdapter(pagesAdapter);
                return true;

            case R.id.third_item:
                Toast.makeText(this,"Selected Item 3 " + item.getTitle(),Toast.LENGTH_LONG).show();
                setContentView(R.layout.tutorial_view);
                recyclerView = findViewById(R.id.tutorial_recycler);
                layoutmanager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutmanager);

                //Weights for recyler view
                String[] weightList = {"Bench Press", "Squat", "Bicep Curl"};
                int[] weightImages = {R.drawable.benchpress, R.drawable.squat, R.drawable.bicepcurl};
                String[] weightDesc = {this.getString(R.string.bench), this.getString(R.string.squat), this.getString(R.string.bicepcurl)};

                //set adapter
                pagesAdapter = new TutorialsAdapter(this, weightList, weightImages, weightDesc);
                recyclerView.setAdapter(pagesAdapter);
                return true;
            case R.id.fourth_item:
                Toast.makeText(this,"Selected Item 4 " + item.getTitle(),Toast.LENGTH_LONG).show();
                setContentView(R.layout.tutorial_view);
                recyclerView = findViewById(R.id.tutorial_recycler);
                layoutmanager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutmanager);

                //Cardio lists for recyler view
                String[] cardioList = {"Threadmill", "Row", "Elliptical"};
                int[]  cardioImages = {R.drawable.threadmill, R.drawable.row, R.drawable.elliptical};
                String[]  cardioDesc = {this.getString(R.string.threadmillstring), this.getString(R.string.rowmachine), this.getString(R.string.elliptical)};

                //set adapter
                pagesAdapter = new TutorialsAdapter(this,  cardioList,  cardioImages,  cardioDesc);
                recyclerView.setAdapter(pagesAdapter);
                return true;
            case R.id.fifth_item:
                Toast.makeText(this,"Selected Item 5 " + item.getTitle(),Toast.LENGTH_LONG).show();
                setContentView(R.layout.tutorial_view);
                recyclerView = findViewById(R.id.tutorial_recycler);
                layoutmanager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutmanager);

                //Interval training for recyler view
                String[] intervalList = {"HIIT", "Full Body"};
                int[]  intervalImages = {R.drawable.hiit, R.drawable.fullbody, R.drawable.elliptical};
                String[]  intervalDesc = {this.getString(R.string.hiit), this.getString(R.string.fullbody)};

                //set adapter
                pagesAdapter = new TutorialsAdapter(this,  intervalList,  intervalImages,  intervalDesc);
                recyclerView.setAdapter(pagesAdapter);
                return true;
            default:
                return false;
        } }



}
