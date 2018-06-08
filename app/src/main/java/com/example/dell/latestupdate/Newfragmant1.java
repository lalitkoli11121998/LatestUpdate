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
 * Created by dell on 4/6/2018.
 */

public class Newfragmant1 extends android.support.v4.app.Fragment {

    UsersRecyclerAdapter usersRecyclerAdapter;
    UpcomingAdapter upcomingAdapter;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;
    RecyclerView recyclerView4;
    ArrayList<movie> arrayList = new ArrayList<>();
    ArrayList<movie> arrayList2 = new ArrayList<>();
    ArrayList<movie> arrayList3 = new ArrayList<>();
    ArrayList<movie> arrayList4 = new ArrayList<>();
    TextView textViewpopular;
    TextView nowplayingtext;
    TextView upcomingtext;
    TextView totext;
    SearchManager searchManager;
    MaterialSearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_main, container, false);

        recyclerView = view.findViewById(R.id.recyclerView1);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView3 = view.findViewById(R.id.recyclerView3);
        recyclerView4 = view.findViewById(R.id.recyclerView4);
       upcomingtext = view.findViewById(R.id.upcomingtext);
       nowplayingtext = view.findViewById(R.id.nowplayingtext);
       totext = view.findViewById(R.id.topratedtext);
       textViewpopular = view.findViewById(R.id.populartext);

        popularshowdata();
        upcomingShowData();
        TopRatedshowData();
        NowPlayingshowdata();

       textViewpopular.setOnClickListener(new View.OnClickListener() {
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
                intent.putExtra("key", "upcoming");
                startActivity(intent);
            }
        });
        totext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), View_ALL.class);
                intent.putExtra("key","top_rated");
                startActivity(intent);
            }
        });
        nowplayingtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), View_ALL.class);
                intent.putExtra("key","now_playing");
                startActivity(intent);
            }
        });
        return  view;

    }


    private void NowPlayingshowdata() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<PopularMovie> call = movieApi.getNowplaying();

        call.enqueue(new Callback<PopularMovie>() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {

                final PopularMovie popularMovie = response.body();
                Log.d("hey",popularMovie.results.get(0).title);
                for(int i=0;i<popularMovie.results.size();i++){
                    arrayList4.add(popularMovie.results.get(i));
                }

                upcomingAdapter = new UpcomingAdapter(getContext(), arrayList4, new UpcomingAdapter.OnItemClickListener() {
                    @Override
             public void onItemClick(final int position) {
                       Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://api.themoviedb.org/3/movie/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        MovieApi movieApi = retrofit.create(MovieApi.class);
                        final int id =popularMovie.results.get(position).id;
                        Call<FindOverViews> call = movieApi.getoverview(id);
                        call.enqueue(new Callback<FindOverViews>() {
                            @Override
                            public void onResponse(Call<FindOverViews> call, Response<FindOverViews> response) {
                                FindOverViews overViews = response.body();
                                Intent intent = new Intent(getContext(), find.class);
                                intent.putExtra("service", 1);
                                intent.putExtra("ovre", overViews.overview);
                                intent.putExtra("rating", popularMovie.results.get(position).vote_average);
                                intent.putExtra("pic", overViews.backdrop_path);
                                intent.putExtra("title" , overViews.original_title);
                                intent.putExtra("id" ,id);
                                Bundle bundle = new Bundle();
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<FindOverViews> call, Throwable t) {

                            }
                        });
                 }
                });
                // arrayList.addAll(popularMovie.results);
                recyclerView4.setAdapter(upcomingAdapter);
                upcomingAdapter.notifyDataSetChanged();
                //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView4.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL ,false));


            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onFailure(Call<PopularMovie> call, Throwable t) {

                Toast.makeText(getContext(), "in failer", Toast.LENGTH_LONG).show();

            }
        });


    }

    private void TopRatedshowData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<PopularMovie> call = movieApi.getratedmovie();

        call.enqueue(new Callback<PopularMovie>() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {

                final PopularMovie popularMovie = response.body();
                for (int i = 0; i < popularMovie.results.size(); i++) {
                    arrayList3.add(popularMovie.results.get(i));
                }

                usersRecyclerAdapter = new UsersRecyclerAdapter(getContext(), arrayList3, new UsersRecyclerAdapter.OnItemClickListener() {
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
                            @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public void onResponse(Call<FindOverViews> call, Response<FindOverViews> response) {
                                FindOverViews overViews = response.body();
                                Intent intent = new Intent(getContext(), find.class);
                                intent.putExtra("ovre", overViews.overview);
                                intent.putExtra("service", 1);
                               intent.putExtra("rating", popularMovie.results.get(position).vote_average);
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
                recyclerView3.setAdapter(usersRecyclerAdapter);
                usersRecyclerAdapter.notifyDataSetChanged();
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




    private void upcomingShowData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<PopularMovie>call =  movieApi.getupcoming();
            call.enqueue(new Callback<PopularMovie>() {
                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {
                    final PopularMovie popularMovie = response.body();
                    if (popularMovie != null) {

                        arrayList2.addAll(popularMovie.results);

                    }
                    upcomingAdapter = new UpcomingAdapter(getContext(), arrayList2, new UpcomingAdapter.OnItemClickListener() {
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

                                    Intent intent = new Intent(getContext(), find.class);
                                   intent.putExtra("service", 1);
                                   intent.putExtra("ovre", overViews.overview);
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
                    recyclerView2.setAdapter(upcomingAdapter);
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



    private void popularshowdata() {
        arrayList=new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<PopularMovie> call = movieApi.getPopular();

        call.enqueue(new Callback<PopularMovie>() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<PopularMovie> call, Response<PopularMovie> response) {

                final PopularMovie popularMovie = response.body();
                for(int i=0;i<popularMovie.results.size();i++){
                    arrayList.add(popularMovie.results.get(i));
                }

                usersRecyclerAdapter = new UsersRecyclerAdapter(getContext(), arrayList, new UsersRecyclerAdapter.OnItemClickListener() {
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
                                Intent intent = new Intent(getContext(), find.class);
                                intent.putExtra("ovre", overViews.overview);
                                intent.putExtra("service", 1);
                                intent.putExtra("rating",popularMovie.results.get(position).vote_average);
                             intent.putExtra("pic", overViews.backdrop_path);
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
                recyclerView.setAdapter(usersRecyclerAdapter);
                usersRecyclerAdapter.notifyDataSetChanged();
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
