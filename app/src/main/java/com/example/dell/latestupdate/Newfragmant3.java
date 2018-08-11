package com.example.dell.latestupdate;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by dell on 4/12/2018.
 */

public class Newfragmant3 extends android.support.v4.app.Fragment {


    ArrayList<favourite> fabmovie;
    ArrayList<favourite>fabtv;
    RecyclerView movie;
    RecyclerView tv_show;
    ViewOpenHelper openHelper;
    ImageView iconimage;
    int id;
    fabmovieadapter Fabmovieadapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favourite_layout, container, false);
        movie = view.findViewById(R.id.fabmovierecycle);
        tv_show =view.findViewById(R.id.fabtvrecycle);
        iconimage = view.findViewById(R.id.iconimage);
        openHelper = new ViewOpenHelper(getContext());
         fabmovie =fetchmoviefromdb();
         fabtv =fetchtvfromdb();

        setmoviefavourite();
        settvfavourite();

        return  view;

    }

    private ArrayList<favourite> fetchtvfromdb() {
        ArrayList<favourite> expenses = new ArrayList<>();
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor cursor = database.query(Contract.Expenses1.TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){

            int titleColumnIndex = cursor.getColumnIndex(Contract.Expenses1.TITLE);
            String title = cursor.getString(titleColumnIndex);
            String poster_path = cursor.getString(cursor.getColumnIndex(Contract.Expenses1.poster_path));
            int id = cursor.getInt(cursor.getColumnIndex(Contract.Expenses1.ID));
//            String overview = cursor.getString(cursor.getColumnIndex(Contract.Expenses1.overview));
         //   Float vote_avarage = cursor.getFloat(cursor.getColumnIndex(Contract.Expenses1.vote_avarage));
            favourite fvt = new favourite(id, title, poster_path);
            expenses.add(fvt);
        }
        return  expenses;

    }

    private void setmoviefavourite() {

        Fabmovieadapter = new fabmovieadapter(getContext(), fabmovie, new fabmovieadapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                id  = fabmovie.get(position).id;
                                ViewOpenHelper openHelper = new ViewOpenHelper(getContext());
                                SQLiteDatabase database = openHelper.getWritableDatabase();
                                String [] ar = {id +""};
                                database.delete(Contract.Expenses.TABLE_NAME, Contract.Expenses.ID  + " = ?", ar);
                                fabmovie.remove(position);
                                movie.setAdapter(Fabmovieadapter);
                                Fabmovieadapter.notifyDataSetChanged();
                                setmoviefavourite();


                            }
                        })
                        .setNegativeButton("go to find", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(getContext(), find.class);
//                                intent.putExtra("service", 1);
//                                intent.putExtra("ovre", fabmovie.get(position).overview);
//                                intent.putExtra("rating", fabmovie.get(position).vote_average);
//                                intent.putExtra("pic", fabmovie.get(position).poster_path);
//                                intent.putExtra("title" , fabmovie.get(position).original_title);
//                                intent.putExtra("id" ,id);
//                                Bundle bundle = new Bundle();
//                                startActivity(intent);
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                Toast.makeText(getContext() , "inline", Toast.LENGTH_SHORT).show();
            }
        });
        movie.setAdapter(Fabmovieadapter);
        Fabmovieadapter.notifyDataSetChanged();
        //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        movie.setLayoutManager(new GridLayoutManager(getContext(), 2,GridLayoutManager.VERTICAL,false));
    }


    private ArrayList<favourite> fetchmoviefromdb(){
            ArrayList<favourite> expenses = new ArrayList<>();
            SQLiteDatabase database = openHelper.getReadableDatabase();
            Cursor cursor = database.query(Contract.Expenses.TABLE_NAME,null,null,null,null,null,null);
            while (cursor.moveToNext()){

                int titleColumnIndex = cursor.getColumnIndex(Contract.Expenses.TITLE);
                String title = cursor.getString(titleColumnIndex);
                String poster_path = cursor.getString(cursor.getColumnIndex(Contract.Expenses.poster_path));
                int id = cursor.getInt(cursor.getColumnIndex(Contract.Expenses.ID));
               // String overview = cursor.getString(cursor.getColumnIndex(Contract.Expenses.overview));
//                Float vote_avarage = cursor.getFloat(cursor.getColumnIndex(Contract.Expenses.vote_avarage));
                favourite fvt = new favourite(id, title, poster_path );
                expenses.add(fvt);
            }
            return  expenses;
        }


    private void settvfavourite() {

        Fabmovieadapter = new fabmovieadapter(getContext(), fabtv, new fabmovieadapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                id  = fabmovie.get(position).id;
                                ViewOpenHelper openHelper = new ViewOpenHelper(getContext());
                                SQLiteDatabase database = openHelper.getWritableDatabase();
                                String [] ar = {id +""};
                                database.delete(Contract.Expenses1.TABLE_NAME, Contract.Expenses.ID  + " = ?", ar);
                                fabtv.remove(position);
                                tv_show.setAdapter(Fabmovieadapter);
                                Fabmovieadapter.notifyDataSetChanged();
                                setmoviefavourite();
                                // movie.setLayoutManager(new GridLayoutManager(getContext(), 2,GridLayoutManager.VERTICAL,false));

                            }
                        })
                        .setNegativeButton("go to find", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(getContext(), find.class);
//                                intent.putExtra("service", 2);
//                                intent.putExtra("ovre", fabtv.get(position).overview);
//                                intent.putExtra("rating", fabtv.get(position).vote_average);
//                                intent.putExtra("pic", fabtv.get(position).poster_path);
//                                intent.putExtra("title" , fabtv.get(position).original_title);
//                                intent.putExtra("id" ,id);
//                                Bundle bundle = new Bundle();
//                                startActivity(intent);
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                Toast.makeText(getContext() , "inline", Toast.LENGTH_SHORT).show();
            }
        });

        tv_show.setAdapter(Fabmovieadapter);
        Fabmovieadapter.notifyDataSetChanged();
        //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        tv_show.setLayoutManager(new GridLayoutManager(getContext(), 2,GridLayoutManager.VERTICAL,false));
    }

}
