package com.gudh.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.media.MediaSync;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    RecyclerView movierecycle;
    ArrayList<ArrayList<String>> arr;
    ArrayList<String> arr1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // this is main activity
        movierecycle = findViewById(R.id.movierecycle);
        arr = new ArrayList<>();
        setTitle("Dashboard");

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        movierecycle.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/now_playing?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US&page=undefined", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0 ; i< jsonArray.length(); i++){
                                arr1 = new ArrayList<>();
                                JSONObject mdetails = jsonArray.getJSONObject(i);
                                String name = mdetails.getString("original_title");
                                String rating = mdetails.getString("vote_average");
                                String image = mdetails.getString("poster_path");
                                String id = mdetails.getString("id");
                                arr1.add(name);
                                arr1.add(rating);
                                arr1.add(image);
                                arr1.add(id);
                                arr.add(arr1);
                            }

                            movierecycle.setAdapter(new movie_adapter(arr));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("the error ");
            }
        });
        requestQueue.add(jsonArrayRequest);


    }

}