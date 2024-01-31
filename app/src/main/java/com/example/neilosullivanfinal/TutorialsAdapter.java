package com.example.neilosullivanfinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TutorialsAdapter extends RecyclerView.Adapter<TutorialsAdapter.ViewHolder> {
    // Declare variables to store data from the constructor
    Context context1;
    String[] namesList;
    int[] images;
    String [] descriptionList;




    public static class ViewHolder extends RecyclerView.ViewHolder{
        // Declare variables for all the Views in a row
        TextView rowName;
        ImageView rowImage;
        TextView  rowDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.textView3);
            rowImage = itemView.findViewById(R.id.imageView2);
            rowDescription = itemView.findViewById(R.id.textView4);
        }
    }
    //constructor
    public TutorialsAdapter(Context context, String[] list,  int[] images, String[] description){

        this.context1 = context;
        this.namesList = list;
        this.images = images;
        this.descriptionList = description;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context1);

        View view = inflater.inflate(R.layout.description_layout_row, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView rowName = v.findViewById(R.id.textView3);
                Toast.makeText(context1, "Clicked Item: " + rowName.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });
        // Return a new holder instance
        ViewHolder viewHolder1 = new ViewHolder(view);
        return viewHolder1;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder1, int position1) {

        //Set variables to corresponding parameters in lists based on position
        holder1.rowName.setText(namesList[position1]);
        holder1.rowDescription.setText(descriptionList[position1]);
        holder1.rowImage.setImageResource(images[position1]);


//On click listener for the row name
        holder1.rowName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView rowName = v.findViewById(R.id.textView3);
                Toast.makeText(context1, "Clicked Item: " + rowName.getText().toString(), Toast.LENGTH_SHORT).show();


            }
        });




    }

    // Return the size of list
    @Override
    public int getItemCount() {
        return namesList.length;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
