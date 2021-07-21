package com.gudh.movieapp;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class movie_adapter extends RecyclerView.Adapter<movie_adapter.holder> {
    ArrayList<ArrayList<String>> data;

    public movie_adapter(ArrayList<ArrayList<String>> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_adapter,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.tv1.setText(data.get(position).get(0));
        holder.tv1.setTag(data.get(position).get(3));
        holder.tv2.setText(data.get(position).get(1));
        String url = "https://image.tmdb.org/t/p/w780"+data.get(position).get(2);

        Picasso.with(holder.img.getContext()).load(url).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class holder extends RecyclerView.ViewHolder{
        TextView tv1,tv2;
        ImageView img;
        LinearLayout layout;

        public holder(@NonNull View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.lymovie);
            img = (ImageView) itemView.findViewById(R.id.movieimage);

            tv1 = (TextView) itemView.findViewById(R.id.moviename);
            tv2 = (TextView) itemView.findViewById(R.id.movierating);


            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(itemView.getContext(),movieshow.class);
                    i.putExtra("movieid",tv1.getTag().toString());
                    itemView.getContext().startActivity(i);
                }
            });

        }
    }
}