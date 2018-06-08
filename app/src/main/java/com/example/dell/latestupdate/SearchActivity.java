package com.example.dell.latestupdate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    SearchAdapter searchAdapter;
    ArrayList<Similiar> search= new ArrayList<>();
    similarMovie SimiliarMovie;
    String st ;
    RecyclerView recyclerViewsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerViewsearch = findViewById(R.id.searchrecycle);
        Intent i = getIntent();
        st = i.getStringExtra("query");

        Toast.makeText(SearchActivity.this, st, Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<similarMovie> call = movieApi.getsearchreslut( "d3196359d679b29665024601d4aa7482",st);

        call.enqueue(new Callback<similarMovie>() {
            @Override
            public void onResponse(Call<similarMovie> call, Response<similarMovie> response) {
                SimiliarMovie = response.body();
                if(SimiliarMovie.results.size()!= 0) {
                    search = SimiliarMovie.results;
                    searchAdapter = new SearchAdapter(SearchActivity.this, search, new SearchAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(SearchActivity.this, find.class);
                            intent.putExtra("service", 1);
                            intent.putExtra("ovre", SimiliarMovie.results.get(position).overview);
                            intent.putExtra("rating", search.get(position).vote_average);
                            intent.putExtra("pic", SimiliarMovie.results.get(position).backdrop_path);
                            intent.putExtra("title" , SimiliarMovie.results.get(position).original_title);
                            intent.putExtra("id" ,SimiliarMovie.results.get(position).id);
                            intent.putExtra("service", 1);
                            Bundle bundle = new Bundle();
                            startActivity(intent);

                        }
                    });
                    recyclerViewsearch.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();
                    recyclerViewsearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    recyclerViewsearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
                }
                else{
                    Toast.makeText(SearchActivity.this, "OOPS NOT AVAILABLE", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<similarMovie> call, Throwable t) {
                Toast.makeText(SearchActivity.this,"not available", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
