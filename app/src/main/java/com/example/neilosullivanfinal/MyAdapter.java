package com.example.neilosullivanfinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    // Declare variables to store data from the constructor
    Context context;
    String[] menuList;
    int[] images;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        // Declare member variables for all the Views in a row
        TextView rowName;
        ImageView rowImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.textView1);
            rowImage = itemView.findViewById(R.id.imageView);

        }
    }
    // Provide a suitable constructor
    public MyAdapter(Context context, String[] programNameList,  int[] images){

        this.context = context;
        this.menuList = programNameList;
        this.images = images;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView rowName = v.findViewById(R.id.textView1);
                //Output clicked on row name
                Toast.makeText(context, "Clicked Item: " + rowName.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.rowName.setText(menuList[position]);
        holder.rowImage.setImageResource(images[position]);

        holder.rowName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView rowName = v.findViewById(R.id.textView1);
                Toast.makeText(context, "Clicked Item: " + rowName.getText().toString(), Toast.LENGTH_SHORT).show();

                //Start new activity and intent, send the adapter position as name, as extra intent
                Intent send = new Intent(context, SecondActivity.class);
                send.putExtra("name", holder.getAdapterPosition());
                context.startActivity(send);

            }
        });

    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return menuList.length;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    //get item in list
    String getItem(int id) {
        return menuList[id];
    }
}

