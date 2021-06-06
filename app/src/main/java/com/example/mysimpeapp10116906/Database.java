package com.example.mysimpeapp10116906;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    //Nama  : Ilham fadil Azmi
    //Nim   : 10116906
    //Kelas : IF-14-K
    //TGL Pengerjaan : 06-07-2021
    private static final String DATABASE_NAME ="10116906.db";
    private static final int DATABASE_VERSION = 1;
    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="create table catatan(nama text null, kampus text null, isi text null);";
        Log.d("Data", "onCreate:"+sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db0, int db1, int db2) {

    }
}
