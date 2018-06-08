package com.example.dell.latestupdate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class castactivity extends AppCompatActivity {

    ArrayList<movie> arrayList2 = new ArrayList<>();
    ArrayList<movie> arrayList3 = new ArrayList<>();
    RecyclerView biolist;
    RecyclerView TV_cast;
    CircleImageView circleImageView;
    RecyclerView movie_cast;
    TextView birthday_date;
    TextView birthplace_;
    Castadapter castadapter;
    TextView casttooltext;
    TextView namet;
    int cast_id;
    overviewAdapter overview_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_castactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        circleImageView = findViewById(R.id.toolbarimage);
        birthday_date = findViewById(R.id.textView12);
        movie_cast = findViewById(R.id.moviecastView);
        TV_cast = findViewById(R.id.recyclerView3);
        biolist = findViewById(R.id.biotextview);
        namet = findViewById(R.id.textView10);
        birthplace_ = findViewById(R.id.textView14);
        casttooltext = findViewById(R.id.casttooltext);
        Intent intent2 = getIntent();
        cast_id = intent2.getIntExtra("cast_id", 0);
        String name = intent2.getStringExtra("name");
        casttooltext.setText(name);
        final int value = intent2.getIntExtra("service" , 0);
        String bithday = intent2.getStringExtra("age");
        String birhplace = intent2.getStringExtra("birthplace");
        String biography = intent2.getStringExtra("biography");
        String pic = intent2.getStringExtra("pic");
        namet.setText(name);
        birthday_date.setText(bithday);
        birthplace_.setText(birhplace);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + pic).into(circleImageView);
        ArrayList<String> biog = new ArrayList<>();
        biog.add(biography);
        overview_adapter = new overviewAdapter(this, biog, new overviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        biolist.setAdapter(overview_adapter);
        overview_adapter.notifyDataSetChanged();
        biolist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Log.d("castid", String.valueOf(cast_id));



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<moviecast> call =  movieApi.getmoviecast(cast_id);
        call.enqueue(new Callback<moviecast>() {
            @Override
            public void onResponse(Call<moviecast> call, Response<moviecast> response) {
                moviecast m = response.body();

                arrayList2.addAll(m.cast);
                castadapter = new Castadapter(castactivity.this, arrayList2, new Castadapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent i = new Intent(castactivity.this , find.class);
                        i.putExtra("service" ,1);
                        i.putExtra("rating" , arrayList2.get(position).vote_average);
                        i.putExtra("id", arrayList2.get(position).id);
                        i.putExtra("pic", arrayList2.get(position).backdrop_path);
                        i.putExtra("ovre", arrayList2.get(position).overview);
                        i.putExtra("title" , arrayList2.get(position).title);
                        startActivity(i);
                    }
                });
                movie_cast.setAdapter(castadapter);
                castadapter.notifyDataSetChanged();
                movie_cast.setLayoutManager( new LinearLayoutManager(castactivity.this, LinearLayoutManager.HORIZONTAL ,false));
            }

            @Override
            public void onFailure(Call<moviecast> call, Throwable t) {

                Toast.makeText(castactivity.this, "OOPS! SORRY", Toast.LENGTH_SHORT).show();

            }
        });
        // recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        setTVCAST();
    }
    private void setTVCAST() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<moviecast> call =  movieApi.getTVcast(cast_id);
        call.enqueue(new Callback<moviecast>() {
            @Override
            public void onResponse(Call<moviecast> call, Response<moviecast> response) {
                moviecast m = response.body();

                arrayList3.addAll(m.cast);
                castadapter = new Castadapter(castactivity.this, arrayList3, new Castadapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent i = new Intent(castactivity.this , find.class);
                        i.putExtra("rating" , arrayList3.get(position).vote_average);
                        i.putExtra("pic", arrayList3.get(position).backdrop_path);
                        i.putExtra("ovre", arrayList3.get(position).overview);
                        i.putExtra("service" , 2);
                        i.putExtra("title" , arrayList3.get(position).title);
                        i.putExtra("id", arrayList3.get(position).id);
                        startActivity(i);
                    }
                });
                TV_cast.setAdapter(castadapter);
                castadapter.notifyDataSetChanged();
                TV_cast.setLayoutManager( new LinearLayoutManager(castactivity.this, LinearLayoutManager.HORIZONTAL ,false));
            }

            @Override
            public void onFailure(Call<moviecast> call, Throwable t) {

                Toast.makeText(castactivity.this, "OOPS! SORRY", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
