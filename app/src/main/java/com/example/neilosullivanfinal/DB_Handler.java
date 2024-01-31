package com.example.neilosullivanfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DB_Handler  extends SQLiteOpenHelper {
    // schema of database

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "trackandgymdbfinal";
    private static final String Table_Workouts = "workouts";
    private static final String Table_Meals = "meals";
    private static final String Table_Steps = "steps";
    private static final String KEY_ID1 = "id";
    private static final String KEY_ID2 = "id";
    private static final String KEY_ID3 = "id";
    private static final String KEY_WORKOUT = "workout";
    private static final String KEY_TIME = "time";
    private static final String KEY_MEAL = "meal";
    private static final String KEY_CALORIES = "calories";
    private static final String KEY_STEPS = "steps";




    public DB_Handler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create three tables
        String CREATE_TABLE = "CREATE TABLE "
                + Table_Workouts + "("
                + KEY_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_WORKOUT + " TEXT,"
                + KEY_TIME + " TEXT"+
                ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);

        String CREATE_TABLE_MEALS = "CREATE TABLE "
                + Table_Meals + "("
                + KEY_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_MEAL + " TEXT,"
                + KEY_CALORIES + " TEXT"+
                ")";
        sqLiteDatabase.execSQL(CREATE_TABLE_MEALS);

        String CREATE_TABLE_STEPS = "CREATE TABLE "
                + Table_Steps + "("
                + KEY_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_STEPS+ " TEXT"+
                ")";


        sqLiteDatabase.execSQL(CREATE_TABLE_STEPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_Workouts);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_Meals);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_Steps);
        onCreate(sqLiteDatabase);
    }
//add workout and time
    void insertUserWorkout(String workout, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_WORKOUT,workout);
        cv.put(KEY_TIME,time);



        long newRowId = db.insert(Table_Workouts,null,cv);
        db.close();
    }

    //add meal and calories
    void insertUserMeal(String meal, String calories){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_MEAL,meal);
        cv.put(KEY_CALORIES,calories);



        long newRowId = db.insert(Table_Meals,null,cv);
        db.close();
    }

    //add steps
    void insertSteps(String steps){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_STEPS,steps);


        long newRowId = db.insert(Table_Steps,null,cv);
        db.close();
    }

    //get workout and time
    public ArrayList<HashMap<String,String>> GetUsersWorkouts(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String,String>> workoutList = new ArrayList<>();

        //String query = "SELECT name, workout, time, steps  FROM " + Table_Users;
        String query = "SELECT workout, time FROM " + Table_Workouts;

        Cursor cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){
            HashMap<String, String> workout= new HashMap<>();
            // workout.put("name",cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
            workout.put("workout",cursor.getString(cursor.getColumnIndexOrThrow(KEY_WORKOUT)));
            workout.put("time",cursor.getString(cursor.getColumnIndexOrThrow(KEY_TIME)));
            workoutList.add(workout);
        }
        return workoutList;

    }
//get meal and calories
    public ArrayList<HashMap<String,String>> GetUsersMeals(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String,String>> mealsList = new ArrayList<>();

        //String query = "SELECT name, meal, calories  FROM " + Table_Users;
        String query = "SELECT meal, calories  FROM " + Table_Meals;

        Cursor cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){
            HashMap<String, String> meal= new HashMap<>();
            //meal.put("name",cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
            meal.put("meal",cursor.getString(cursor.getColumnIndexOrThrow(KEY_MEAL)));
            meal.put("calories",cursor.getString(cursor.getColumnIndexOrThrow(KEY_CALORIES)));
            mealsList.add(meal);
        }
        return mealsList;

    }
//get steps
    public ArrayList<HashMap<String,String>> GetSteps(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String,String>> stepsList = new ArrayList<>();

        //String query = "SELECT name, meal, calories  FROM " + Table_Users;
        String query = "SELECT steps  FROM " + Table_Steps;

        Cursor cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){
            HashMap<String, String> steps= new HashMap<>();
            steps.put("steps",cursor.getString(cursor.getColumnIndexOrThrow(KEY_STEPS)));
            stepsList.add(steps);
        }
        return stepsList;

    }
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ Table_Workouts);
        db.execSQL("delete from "+ Table_Meals);
        db.execSQL("delete from "+ Table_Steps);
        db.close();
    }


}
