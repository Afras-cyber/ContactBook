package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    public static String database_naem= "Contect";
    public static String table_naem= "contect";
    public static String col_1="ID";
    public static String col_2="Name";
    public static String col_3="Number";
    public static String col_4="E-mail";


    public DataBase(@Nullable Context context) {
        super(context, database_naem, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contect(`ID` INTEGER PRIMARY KEY AUTOINCREMENT,`Name` TEXT,`Number` TEXT,`E-mail` TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+table_naem);
        onCreate(db);
    }
    //Get Data
    public boolean insertData(String name,String number){
        SQLiteDatabase sdb= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,number);
        long check=sdb.insert(table_naem,null,contentValues);
       // String sql="INSERT INTO"+table_naem+"("+col_2+","+col_3+")VALUE("+name+","+number+")";
        if(check==-1){
            return false;
        }
        else{
            return true;
        }
    }
    //Show Data
    public Cursor allData(){
        SQLiteDatabase sdb= getWritableDatabase();
        Cursor cursor = sdb.rawQuery("SELECT * FROM "+table_naem,null);
        return cursor;
    }

    public Cursor thisContect(String name){
        SQLiteDatabase sdb = getWritableDatabase();

        Cursor c=sdb.rawQuery("SELECT * FROM "+table_naem+" WHERE Name ='"+name+"'",null);
        return c;
    }


}
