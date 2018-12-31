package com.example.administrator.card;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "pictures.db", null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql="CREATE TABLE test(account VARCHAR(20),password VARCHAR(20))";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }
    public String queryPassword(String sql, String[] bindArgs){
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(sql, bindArgs);
        String result = null;
        while(cursor.moveToNext()){
            result = cursor.getString(cursor.getColumnIndex("password"));
        }
        return result;
    }


}
