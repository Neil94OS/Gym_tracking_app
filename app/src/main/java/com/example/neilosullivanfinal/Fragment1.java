package com.example.neilosullivanfinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

//Fragment to log steps
public class Fragment1 extends Fragment implements View.OnClickListener{

    View view;
    Button logButton;
    EditText et1;

    public Fragment1() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.logsteps, container, false);
        logButton = (Button) view.findViewById(R.id.button3);
        et1 = view.findViewById(R.id.editText1);
        logButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        String steps = et1.getText().toString();


        DB_Handler dbHandler = new DB_Handler(getActivity());
        dbHandler.insertSteps(steps);


        Intent intent = new Intent(getActivity(), stepslogged.class);
        startActivity(intent);

    }
}
