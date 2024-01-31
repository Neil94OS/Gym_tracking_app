package com.example.neilosullivanfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

//Fragment to show previous steps
public class Fragment2 extends Fragment {

    View view;

    ListView listView;
    public Fragment2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.showsteps, container, false);
        DB_Handler dbHandler2 = new DB_Handler(getActivity());
        ArrayList<HashMap<String, String>> workoutList = dbHandler2.GetSteps();
        listView = view.findViewById(R.id.listView);
        ListAdapter workoutAdapter = new SimpleAdapter(getActivity(),
                workoutList,
                R.layout.stepslayout,
                new String[]{"steps"},
                new int[]{R.id.steps});
        listView.setAdapter(workoutAdapter);
        return view;
    }
}

