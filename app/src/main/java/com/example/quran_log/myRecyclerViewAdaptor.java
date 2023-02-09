package com.example.quran_log;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myRecyclerViewAdaptor extends RecyclerView.Adapter<myRecyclerViewAdaptor.MyVH> {
    ArrayList<Log> logs;
    Context context;

    public myRecyclerViewAdaptor(ArrayList<Log> dataHolder,Context context) {
        this.logs=dataHolder;
        this.context=context;
    }

    public myRecyclerViewAdaptor(ArrayList<Log> dataHolder) {
        this.logs=dataHolder;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlerow, parent, false);
        return new MyVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        holder.data=logs.get(position);
        holder.Name.setText(holder.data.getName());
        holder.date.setText(String.valueOf(holder.data.getDate()));
        holder.sabaq.setText(String.valueOf(holder.data.getSabaq()));
        holder.sabqi.setText(String.valueOf(holder.data.getSabqi()));
        holder.manzil.setText(String.valueOf(holder.data.getManzil()));


    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public class MyVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView Name;
        TextView  date;
        TextView sabaq;
        TextView sabqi;
        TextView manzil;
        Log data;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener( this);
            context = itemView.getContext();
            Name = itemView.findViewById(R.id.NameView);
            date = itemView.findViewById(R.id.date);
            sabaq= itemView.findViewById(R.id.sabaqView);
            sabqi= itemView.findViewById(R.id.sabqiView);
            manzil=itemView.findViewById(R.id.manzilView);
        }

        @Override
        public void onClick(View view) {
            android.util.Log.d("Clicked","RecyleView");
            int position=this.getAdapterPosition();
            Log data=logs.get(position);
            android.util.Log.d("Clicked",""+data);
            String id= String.valueOf(data.getStd_id());
            String name=data.getName();
            String sabaq=data.getSabaq();
            String sabqi=data.getSabqi();
            String manzil =data.getManzil();
            String date=data.getDate();


            Intent intent = new Intent(context,Edit.class);
            intent.putExtra("name", name);
            intent.putExtra("id", id);
            intent.putExtra("sabaq", sabaq);
            intent.putExtra("sabqi", sabqi);
            intent.putExtra("manzil", manzil);
            intent.putExtra("date", date);
            context.startActivity(intent);

        }

    }
}
