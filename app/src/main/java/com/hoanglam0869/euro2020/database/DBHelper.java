package com.hoanglam0869.euro2020.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoanglam0869.euro2020.model.Fixtures;

import java.util.ArrayList;

public class DBHelper {
    private static final String DATABASE_NAME = "euro2020.sqlite";
    private static final String FIXTURES_TABLE = "fixtures";
    private static final String SCORE1_COLUMN = "score1";
    private static final String SCORE2_COLUMN = "score2";

    public static ArrayList<Fixtures> getFixtures(Activity activity) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM " + FIXTURES_TABLE, null);

        ArrayList<Fixtures> fixturesArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            fixturesArrayList.add(new Fixtures(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getInt(6), cursor.getInt(7),
                    cursor.getString(8), cursor.getString(9)));
        }
        return fixturesArrayList;
    }

    public static void updateScore(Activity activity, Fixtures fixtures) {
        int score1 = fixtures.getScore1();
        int score2 = fixtures.getScore2();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SCORE1_COLUMN, score1);
        contentValues.put(SCORE2_COLUMN, score2);

        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        database.update(FIXTURES_TABLE, contentValues, "id = ?", new String[]{fixtures.getId() + ""});
    }
}
