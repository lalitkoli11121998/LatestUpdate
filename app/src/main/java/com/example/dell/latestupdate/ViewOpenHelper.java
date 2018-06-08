package com.example.dell.latestupdate;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell on 4/12/2018.
 */

public class ViewOpenHelper extends SQLiteOpenHelper {

    private  static  ViewOpenHelper openHelper;

    public static ViewOpenHelper getInstance(Context context){
        if(openHelper == null){
            openHelper = new ViewOpenHelper(context.getApplicationContext());
        }
        return  openHelper;
    }

    public ViewOpenHelper(Context context) {
        super(context, Contract.DATABASE_NAME, null, Contract.VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String expensesSql = "CREATE TABLE " + Contract.Expenses.TABLE_NAME  + " ( " +
                Contract.Expenses.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.Expenses.TITLE + " TEXT, " +
                Contract.Expenses.poster_path + " TEXT, " +
                Contract.Expenses.AMOUNT + " INTEGER)";



        String expensesSq2 = "CREATE TABLE " + Contract.Expenses1.TABLE_NAME  + " ( " +
                Contract.Expenses1.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.Expenses1.TITLE + " TEXT, " +
                Contract.Expenses1.poster_path + " TEXT, " +
                Contract.Expenses1.AMOUNT + " INTEGER)";

        db.execSQL(expensesSql);
        db.execSQL(expensesSq2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
