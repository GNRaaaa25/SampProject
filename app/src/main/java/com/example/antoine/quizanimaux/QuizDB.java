package com.example.antoine.quizanimaux;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Antoine on 04/10/2018.
 */

public class QuizDB extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final String DB_Name = "QuizDB";
    private static final int DB_Version = 1;
    private static final String DB_Create = "CREATE TABLE quiz"+"(_id integer primary key autoincrement, "+"name text not null) ;";


    public QuizDB(Context context) {
        super(context, DB_Name, null, DB_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(DB_Create);
        db.execSQL("INSERT INTO quiz (name) VALUES ('Test')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
