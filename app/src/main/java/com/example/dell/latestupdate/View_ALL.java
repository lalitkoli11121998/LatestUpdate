package com.example.dell.latestupdate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class View_ALL extends AppCompatActivity {

    ArrayList<movie> arrayList4 = new ArrayList<>();
    RecyclerView recyclerView;
    UsersRecyclerAdapter usersRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__all);
        recyclerView = findViewById(R.id.viewall);
        Intent i = getIntent();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<PopularMovie> call = movieApi.getP(i.getStringExtra("key"));

        call.enqueue(new Callback<PopularMovie>() {
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {

                final PopularMovie popularMovie = response.body();
                for (int i = 0; i < popularMovie.results.size(); i++) {
                    arrayList4.add(popularMovie.results.get(i));
                }

                usersRecyclerAdapter = new UsersRecyclerAdapter(View_ALL.this, arrayList4, new UsersRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(final int position) {

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://api.themoviedb.org/3/movie/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        MovieApi movieApi = retrofit.create(MovieApi.class);
                        final int id = popularMovie.results.get(position).id;
                        Call<FindOverViews> call = movieApi.getoverview(id);
                        call.enqueue(new Callback<FindOverViews>() {
                            @Override
                            public void onResponse(Call<FindOverViews> call, Response<FindOverViews> response) {
                                FindOverViews overViews = response.body();
                                Intent intent = new Intent(View_ALL.this, find.class);
                                intent.putExtra("ovre", overViews.overview);
                                intent.putExtra("service" , 1);
                                intent.putExtra("rating",popularMovie.results.get(position).vote_average);
                                intent.putExtra("pic", overViews.backdrop_path);
                                intent.putExtra("title", overViews.original_title);
                                intent.putExtra("id", id);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<FindOverViews> call, Throwable t) {

                            }
                        });
                    }
                });
                // arrayList.addAll(popularMovie.results);
                recyclerView.setAdapter(usersRecyclerAdapter);
                usersRecyclerAdapter.notifyDataSetChanged();
                //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setLayoutManager(new GridLayoutManager(View_ALL.this, 2,GridLayoutManager.VERTICAL,false));

            }

            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {

                Toast.makeText(View_ALL.this, "failer", Toast.LENGTH_LONG).show();

            }
        });
    }
}
