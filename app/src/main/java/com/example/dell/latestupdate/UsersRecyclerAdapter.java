package com.example.dell.latestupdate;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ralph on 18/03/18.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {
  boolean st = false;

    interface OnItemClickListener {

        void onItemClick(int position);
    }

    ArrayList<genre> genres = new ArrayList<>();
    ArrayList<movie> users=new ArrayList<>();
    Context context;
    UsersRecyclerAdapter.OnItemClickListener listener;

    public UsersRecyclerAdapter(Context context, ArrayList<movie> users, UsersRecyclerAdapter.OnItemClickListener listener){
        this.users = users;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public UsersRecyclerAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.damo,parent,false);
        UsersRecyclerAdapter.UserViewHolder holder = new UsersRecyclerAdapter.UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UsersRecyclerAdapter.UserViewHolder holder, int position) {
        final movie movie = users.get(position);
        int id = movie.id;
        holder.movieTitleTextView.setText(movie.title);
                String s =movie.poster_path;
        Glide.with(context.getApplicationContext()).load(Contract.IMAGE_LOADING_BASE_URL_780 + movie.backdrop_path).into(holder.moviePosterImageView);

       // Picasso.get().load("https://image.tmdb.org/t/p/w500/"+s).resize(650 ,0).into(holder.moviePosterImageView);
                holder.moviePosterImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(holder.getAdapterPosition());
                    }
                });
        if (movie.vote_average != null && movie.vote_average > 0) {
            holder.movieRatingTextView.setVisibility(View.VISIBLE);
            holder.movieRatingTextView.setText(String.format(movie.vote_average + Contract.RATING_SYMBOL));
        } else {
            holder.movieRatingTextView.setVisibility(View.GONE);
        }

                holder.movieFavImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                          st = true;
                        holder.movieFavImageButton.setColorFilter(R.color.colorAccent);
                        ViewOpenHelper openHelper = new ViewOpenHelper(context);
                        SQLiteDatabase database = openHelper.getWritableDatabase();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(Contract.Expenses.TITLE,movie.title);
                        contentValues.put(Contract.Expenses.poster_path,movie.backdrop_path);
                        contentValues.put(Contract.Expenses.ID,movie.id);
                        contentValues.put(Contract.Expenses.overview , movie.overview);
                        contentValues.put(Contract.Expenses.vote_avarage , movie.vote_average);
                        long id = database.insert(Contract.Expenses.TABLE_NAME,null,contentValues);
                        String t = movie.title;
                        Toast.makeText(context,t +"add to favourite" , Toast.LENGTH_SHORT).show();
                    }
                });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<about> call = movieApi.getgenres(id);
        call.enqueue(new Callback<about>() {
            @Override
            public void onResponse(Call<about> call, Response<about> response) {
                about About = response.body();
               genres =  About.genres;
                String genreString = "";
                for (int i = 0; i < About.genres.size(); i++) {
                    if (About.genres.get(i) == null) continue;
                    if (About.genres.get(i).name == null) continue;
                    genreString += About.genres.get(i).name + ", ";
                }
                if (!genreString.isEmpty())
                    holder.movieGenreTextView.setText(genreString.substring(0, genreString.length() - 2));
                else
                    holder.movieGenreTextView.setText("");
            }

            @Override
            public void onFailure(Call<about> call, Throwable t) {

            }



        });


    }

    @Override
    public int getItemCount() {
        return  users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        public CardView movieCard;
        public RelativeLayout imageLayout;
        public ImageView moviePosterImageView;
        public TextView movieTitleTextView;
        public TextView movieRatingTextView;
        public TextView movieGenreTextView;
        public ImageButton movieFavImageButton;


        public UserViewHolder(View itemView) {
            super(itemView);
            movieCard = (CardView) itemView.findViewById(R.id.card_view_show_card);
            imageLayout = (RelativeLayout) itemView.findViewById(R.id.image_layout_show_card);
            moviePosterImageView = (ImageView) itemView.findViewById(R.id.image_view_show_card);
            movieTitleTextView = (TextView) itemView.findViewById(R.id.text_view_title_show_card);
            movieRatingTextView = (TextView) itemView.findViewById(R.id.text_view_rating_show_card);
            movieGenreTextView = (TextView) itemView.findViewById(R.id.text_view_genre_show_card);
            movieFavImageButton = (ImageButton) itemView.findViewById(R.id.image_button_fav_show_card);

            imageLayout.getLayoutParams().width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.9);
            imageLayout.getLayoutParams().height = (int) ((context.getResources().getDisplayMetrics().widthPixels * 0.9) / 1.77);

             }
    }
}
