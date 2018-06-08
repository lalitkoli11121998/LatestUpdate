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

/**
 * Created by dell on 3/27/2018.
 */

public class similiarAdapter extends RecyclerView.Adapter<similiarAdapter.UserViewHolder> {

    interface OnItemClickListener {

        void onItemClick(int position);
    }

    ArrayList<Similiar> users = new ArrayList<>();
    Context context;
    similiarAdapter.OnItemClickListener listener;

    public similiarAdapter(Context context, ArrayList<Similiar> users, similiarAdapter.OnItemClickListener listener) {
        this.users = users;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public similiarAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.row_user, parent, false);
        similiarAdapter.UserViewHolder holder = new similiarAdapter.UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final similiarAdapter.UserViewHolder holder, int position) {
        final Similiar similiar = users.get(position);

        holder.title.setText(similiar.original_title);
        String s = similiar.backdrop_path;

        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + s).into(holder.avatar);
        holder.iconimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                holder.iconimage.setColorFilter(R.color.colorAccent);
                ViewOpenHelper openHelper = new ViewOpenHelper(context);
                SQLiteDatabase database = openHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(Contract.Expenses.TITLE,similiar.original_title);
                contentValues.put(Contract.Expenses.poster_path,similiar.backdrop_path);
                contentValues.put(Contract.Expenses.ID,similiar.id);
                long id = database.insert(Contract.Expenses.TABLE_NAME,null,contentValues);
                String t = similiar.original_title;
                Toast.makeText(context,t +"add to favourite" , Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView avatar;
        View itemView;
        ImageView iconimage;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = itemView.findViewById(R.id.textView);
            avatar = itemView.findViewById(R.id.avatar);
            iconimage = itemView.findViewById(R.id.iconimage);
        }
    }
}

