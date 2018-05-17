package com.example.vijay.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.jar.Attributes;

/**
 * Created by vijay on 20-02-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public  static final String COL_1="ID";
    public  static final String COL_2="NAME";
    public  static final String COL_3="FEED";
    public  static final String COL_4="MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,FEED TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public  boolean insertData(String name, String feed, String marks){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,feed);
        contentValues.put(COL_4,marks);
      long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
      if(result== -1)
          return false;
        else
          return  true;

    }


    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME;
        Cursor res=sqLiteDatabase.rawQuery(query, null);
        return res;
    }
    public Cursor getAllData1(String name){
        String[] columns={COL_1,COL_2,COL_3,COL_4};
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
     //  String query1 ="SELECT * FROM "+TABLE_NAME+"WHERE NAME LIKE '%"+name+"%'";
        Cursor res1=sqLiteDatabase.query(TABLE_NAME,columns,COL_4+"= '"+name+"'",null,null,null,null);

        return res1;
    }
    public Integer delete(String id){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
       Integer i=sqLiteDatabase.delete(TABLE_NAME ,"NAME =?",new String[]{id});
        return i;
    }

}
