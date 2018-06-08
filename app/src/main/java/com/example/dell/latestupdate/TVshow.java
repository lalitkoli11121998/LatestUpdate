package com.example.dell.latestupdate;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 4/8/2018.
 */

public class TVshow extends android.support.v4.app.Fragment {

    TvUserAdapter tvUserAdapter;
    TvUpcomingAdapter tvUpcomingAdapter;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;
    ArrayList<movie> arrayList = new ArrayList<>();
    ArrayList<movie> arrayList2 = new ArrayList<>();
    ArrayList<movie> arrayList3 = new ArrayList<>();
    ArrayList<movie> arrayList4 = new ArrayList<>();
    SearchManager searchManager;
    MaterialSearchView searchView;
    TextView populartext;
    TextView upcomingtext;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tv_row, container, false);
        searchView = view.findViewById(R.id.search_view);
        recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView3 = view.findViewById(R.id.recyclerView3);
        recyclerView4 = view.findViewById(R.id.recyclerView4);
        populartext = view.findViewById(R.id.populartext);
        upcomingtext = view.findViewById(R.id.upcomingtext);
        populartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), View_ALL.class);
                intent.putExtra("key" , "popular");
                startActivity(intent);
            }
        });

        upcomingtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), View_ALL.class);
                intent.putExtra("key" , "popular");
                startActivity(intent);
            }
        });

        AiringTodayData();
        OntheAir();
        popularTvSHOW();
       toptvshow();
        return  view;
    }

    private void toptvshow() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<PopularMovie> call = movieApi.gettopratedTv();

        call.enqueue(new Callback<PopularMovie>() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {

                final PopularMovie popularMovie = response.body();
                arrayList3 = popularMovie.results;


                tvUserAdapter= new TvUserAdapter(getContext(), arrayList3, new TvUserAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://api.themoviedb.org/3/tv/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        MovieApi movieApi = retrofit.create(MovieApi.class);
                        final int id = popularMovie.results.get(position).id;
                        Call<FindOverViews> call = movieApi.getoverview(id);
                        call.enqueue(new Callback<FindOverViews>() {
                            @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public void onResponse(Call<FindOverViews> call, Response<FindOverViews> response) {
                                FindOverViews overViews = response.body();
                                Intent intent = new Intent(getContext(), find.class);
                                intent.putExtra("ovre", overViews.overview);
                                intent.putExtra("service", 2);
                                intent.putExtra("rating", overViews.vote_average);
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
                recyclerView4.setAdapter(tvUserAdapter);
                tvUserAdapter.notifyDataSetChanged();
                //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView4.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {

                Toast.makeText(getContext(), "failer", Toast.LENGTH_LONG).show();

            }
        });

    }



    private void popularTvSHOW() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<PopularMovie> call = movieApi.getpoupularTv();

        call.enqueue(new Callback<PopularMovie>() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {

                final PopularMovie popularMovie = response.body();
                arrayList3 = popularMovie.results;


                tvUserAdapter= new TvUserAdapter(getContext(), arrayList3, new TvUserAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://api.themoviedb.org/3/tv/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        MovieApi movieApi = retrofit.create(MovieApi.class);
                        final int id = popularMovie.results.get(position).id;
                        Call<FindOverViews> call = movieApi.getoverview(id);
                        call.enqueue(new Callback<FindOverViews>() {
                            @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public void onResponse(Call<FindOverViews> call, Response<FindOverViews> response) {
                                FindOverViews overViews = response.body();
                                Intent intent = new Intent(getContext(), find.class);
                                intent.putExtra("ovre", overViews.overview);
                                intent.putExtra("service", 2);
                                intent.putExtra("rating", overViews.vote_average);
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
                recyclerView3.setAdapter(tvUserAdapter);
                tvUserAdapter.notifyDataSetChanged();
                //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {

                Toast.makeText(getContext(), "failer", Toast.LENGTH_LONG).show();

            }
        });
    }




    private void OntheAir() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<PopularMovie>call =  movieApi.getontheair();
        call.enqueue(new Callback<PopularMovie>() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                final PopularMovie popularMovie = response.body();
                if (popularMovie != null) {

                    arrayList2.addAll(popularMovie.results);

                }
                tvUpcomingAdapter = new TvUpcomingAdapter(getContext(), arrayList2, new TvUpcomingAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        Retrofit retrofit = new Retrofit.Builder()
                               .baseUrl("https://api.themoviedb.org/3/tv/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        MovieApi movieApi = retrofit.create(MovieApi.class);
                        final int id = popularMovie.results.get(position).id;
                        Call<FindOverViews> call = movieApi.getoverview(id);
                        call.enqueue(new Callback<FindOverViews>() {
                            @Override
                            public void onResponse(Call<FindOverViews> call, Response<FindOverViews> response) {
                                FindOverViews overViews = response.body();

                                Intent intent = new Intent(getContext(), find.class);
                                intent.putExtra("ovre", overViews.overview);
                                intent.putExtra("rating", overViews.vote_average);
                                intent.putExtra("service", 2);
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
                recyclerView2.setAdapter(tvUpcomingAdapter);
                // usersRecyclerAdapter.notifyDataSetChanged();
                // recyclerView2.setLayoutManager(new LinearLayoutManager(this));
                recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {


                Toast.makeText(getContext(), "failer", Toast.LENGTH_LONG).show();
            }
        });
    }



    private void AiringTodayData() {
        arrayList=new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<PopularMovie> call = movieApi.getairingToday();

        call.enqueue(new Callback<PopularMovie>() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {

                final PopularMovie popularMovie = response.body();
                for(int i=0;i<popularMovie.results.size();i++){
                    arrayList.add(popularMovie.results.get(i));
                }

                tvUserAdapter = new TvUserAdapter(getContext(), arrayList, new TvUserAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                      Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://api.themoviedb.org/3/tv/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        MovieApi movieApi = retrofit.create(MovieApi.class);
                        final int id = popularMovie.results.get(position).id;
                        Call<FindOverViews> call = movieApi.getoverview(id);
                        call.enqueue(new Callback<FindOverViews>() {
                            @Override
                            public void onResponse(Call<FindOverViews> call, Response<FindOverViews> response) {
                                FindOverViews overViews = response.body();
                                Intent intent = new Intent(getContext(), find.class);
                                intent.putExtra("ovre", overViews.vote_average);
                                intent.putExtra("rating", overViews.vote_average);
                                intent.putExtra("pic", overViews.backdrop_path);
                                intent.putExtra("service", 2);
                                intent.putExtra("title", overViews.original_title);
                                intent.putExtra("id", id);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<FindOverViews> call, Throwable t) {
                                Toast.makeText(getContext(), "in popular failer", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                });
                // arrayList.addAll(popularMovie.results);
                recyclerView.setAdapter(tvUserAdapter);
                tvUserAdapter.notifyDataSetChanged();
                //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL ,false));

            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {

                Toast.makeText(getContext(), "in failer", Toast.LENGTH_LONG).show();

            }
        });

    }

}

