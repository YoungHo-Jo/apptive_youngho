package com.example.solar.week4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by solar on 2016-11-16.
 */

public class DBHelper extends SQLiteOpenHelper {


  public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
  {
    super(context, name, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // new table
    db.execSQL("CREATE TABLE FRIENDS (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, state TEXT)");

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }

  public void insert(String name, String state)
  {
    // read and write db
    SQLiteDatabase db = getWritableDatabase();
    // insert
    db.execSQL("INSERT INTO FRIENDS VALUES(null, '" + name + "', '" + state + "');");
    db.close();
  }

  public void update(String name, String state)
  {
    SQLiteDatabase db = getWritableDatabase();
    // change state message
    db.execSQL("UPDATE FRIENDS SET state='" + state + "' WHERE name='" + name + "';");
    db.close();
  }
//
//  public String getResult()
//  {
//    // 읽기가 가능하게 DB 열기
//    SQLiteDatabase db = getReadableDatabase();
//    String result = "";
//
//    // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
//    Cursor cursor = db.rawQuery("SELECT * FROM FRIENDS", null);
//    while (cursor.moveToNext()) {
//      result += cursor.getString(0)
//        + " : "
//        + cursor.getString(1)
//        + " | "
//        + cursor.getInt(2)
//        + "원 "
//        + cursor.getString(3)
//        + "\n";
//    }
//
//    return result;
//
//  }
//
//  public String getName()
//  {
//
//    String result = null;
//    SQLiteDatabase db = getReadableDatabase();
//
//    Cursor cursor = db.rawQuery("SELECT * FROM FRIENDS", null);
//    while(cursor.moveToNext())
//    {
//      result = cursor.getString(0);
//    }
//
//
//    return result;
//  }
//



}
