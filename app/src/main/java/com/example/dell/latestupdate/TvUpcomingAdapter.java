package com.example.dell.latestupdate;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.dell.latestupdate.Contract.Expenses1.vote_avarage;

/**
 * Created by dell on 4/13/2018.
 */

public class TvUpcomingAdapter extends RecyclerView.Adapter<TvUpcomingAdapter.UserViewHolder> {

    interface OnItemClickListener {

        void onItemClick(int position);
    }

    ArrayList<movie> users=new ArrayList<>();
    Context context;
    TvUpcomingAdapter.OnItemClickListener listener;

    public TvUpcomingAdapter(Context context, ArrayList<movie> users,TvUpcomingAdapter.OnItemClickListener listener){
        this.users = users;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public TvUpcomingAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.row_user,parent,false);
        TvUpcomingAdapter.UserViewHolder holder = new TvUpcomingAdapter.UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TvUpcomingAdapter.UserViewHolder holder, int position) {
        final movie movie = users.get(position);
        holder.title.setText(movie.name);
        String s =movie.backdrop_path;
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+s).into(holder.avatar);
        holder.iconimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.iconimage2.setColorFilter(R.color.colorAccent);
                ViewOpenHelper openHelper = new ViewOpenHelper(context);
                SQLiteDatabase database = openHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(Contract.Expenses1.TITLE,movie.name);
                contentValues.put(Contract.Expenses1.poster_path,movie.backdrop_path);
                contentValues.put(Contract.Expenses1.ID,movie.id);

                contentValues.put(Contract.Expenses1.overview , movie.overview);
                contentValues.put(Contract.Expenses1.vote_avarage , movie.vote_average);
                long id = database.insert(Contract.Expenses1.TABLE_NAME,null,contentValues);
                String t = movie.name;
                Toast.makeText(context,t +"add to favourite" , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return  users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{


        ImageView iconimage2;
        TextView title;
        ImageView avatar;
        View itemView;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            iconimage2 = itemView.findViewById(R.id.iconimage);
            title = itemView.findViewById(R.id.textView);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}

