package com.gudh.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class movieshow extends AppCompatActivity {
    TextView mname,mcategory,mrating,mrelasedate,mpopularity,mlanguage,mdetails;
    ArrayList<String> arcategory;
    ImageView movieimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieshow);


        movieimage = findViewById(R.id.movie_image);
        mname = findViewById(R.id.movie_name);
        mcategory = findViewById(R.id.movie_category);
        mrating = findViewById(R.id.movie_rating);
        mrelasedate = findViewById(R.id.release_date);
        mpopularity = findViewById(R.id.popularity);
        mlanguage = findViewById(R.id.language);
        mdetails = findViewById(R.id.movie_details);

        String movieid = getIntent().getStringExtra("movieid");
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.setCancelable(false);
        dialog.show();


        String url = String.format("https://api.themoviedb.org/3/movie/%s?api_key=55957fcf3ba81b137f8fc01ac5a31fb5",movieid);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            dialog.dismiss();
                            mname.setText(response.getString("original_title"));
                            setTitle(response.getString("original_title"));
                            mrating.setText(response.getString("vote_average"));
                            mpopularity.setText(response.getString("popularity"));
                            mlanguage.setText(response.getString("original_language"));
                            mdetails.setText(response.getString("overview"));
                            mrelasedate.setText(response.getString("release_date"));

                            String imgurl = "https://image.tmdb.org/t/p/w780"+response.getString("poster_path");
                            Picasso.with(movieshow.this).load(imgurl).into(movieimage);


                            String category = "";
                            JSONArray jsonArray = response.getJSONArray("genres");
                            for (int i = 0 ; i< jsonArray.length(); i++){
                                JSONObject mdetails = jsonArray.getJSONObject(i);
                                String name = mdetails.getString("name");
                                category = category+" * "+name;
                            }
                            mcategory.setText(category);
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                System.out.println("the error ");
            }
        });
        requestQueue.add(jsonArrayRequest);

    }
}