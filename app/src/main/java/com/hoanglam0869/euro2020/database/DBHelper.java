package com.hoanglam0869.euro2020.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoanglam0869.euro2020.model.Fixtures;
import com.hoanglam0869.euro2020.model.Team;

import java.util.ArrayList;

public class DBHelper {
    private static final String DATABASE_NAME = "euro2020.sqlite";
    private static final String FIXTURES_TABLE = "fixtures";
    private static final String TEAMS_TABLE = "teams";

    private static final String SCORE1_COLUMN = "score1";
    private static final String SCORE2_COLUMN = "score2";
    private static final String RESULT1_COLUMN = "result1";
    private static final String RESULT2_COLUMN = "result2";

    private static final String WON_COLUMN = "won";
    private static final String DRAWN_COLUMN = "drawn";
    private static final String LOST_COLUMN = "lost";
    private static final String FORWARD_COLUMN = "forward";
    private static final String AGAINST_COLUMN = "against";
    private static final String POINTS_COLUMN = "points";

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
        String result1, result2;

        if (score1 > score2) {
            result1 = fixtures.getTeam1() + " won";
            result2 = fixtures.getTeam2() + " lost";
        } else if (score1 < score2) {
            result1 = fixtures.getTeam1() + " lost";
            result2 = fixtures.getTeam2() + " won";
        } else {
            result1 = fixtures.getTeam1() + " drawn";
            result2 = fixtures.getTeam2() + " drawn";
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(SCORE1_COLUMN, score1);
        contentValues.put(SCORE2_COLUMN, score2);
        contentValues.put(RESULT1_COLUMN, result1);
        contentValues.put(RESULT2_COLUMN, result2);

        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        database.update(FIXTURES_TABLE, contentValues, "id = ?", new String[]{fixtures.getId() + ""});
    }

    public static void updateTeam(Activity activity, String team) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM " + FIXTURES_TABLE + " WHERE team1='" + team + "' AND id < 37 OR team2='" + team + "' AND id < 37", null);

        ArrayList<Fixtures> fixturesArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            fixturesArrayList.add(new Fixtures(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getInt(6), cursor.getInt(7),
                    cursor.getString(8), cursor.getString(9)));
        }

        int won = 0, drawn = 0, lost = 0, forward = 0, against = 0, points;
        for (int i = 0; i < fixturesArrayList.size(); i++) {
            if (fixturesArrayList.get(i).getScore1() != -1 && fixturesArrayList.get(i).getScore2() != -1) {
                if (team.equals(fixturesArrayList.get(i).getTeam1())) {
                    if (fixturesArrayList.get(i).getScore1() > fixturesArrayList.get(i).getScore2()) {
                        won++;
                    } else if (fixturesArrayList.get(i).getScore1() < fixturesArrayList.get(i).getScore2()) {
                        lost++;
                    } else {
                        drawn++;
                    }
                    forward += fixturesArrayList.get(i).getScore1();
                    against += fixturesArrayList.get(i).getScore2();
                }
                if (team.equals(fixturesArrayList.get(i).getTeam2())) {
                    if (fixturesArrayList.get(i).getScore1() < fixturesArrayList.get(i).getScore2()) {
                        won++;
                    } else if (fixturesArrayList.get(i).getScore1() > fixturesArrayList.get(i).getScore2()) {
                        lost++;
                    } else {
                        drawn++;
                    }
                    forward += fixturesArrayList.get(i).getScore2();
                    against += fixturesArrayList.get(i).getScore1();
                }
            }
        }
        points = (won * 3 + drawn) * 10000 + (forward - against) * 100 + forward;

        ContentValues contentValues = new ContentValues();
        contentValues.put(WON_COLUMN, won);
        contentValues.put(DRAWN_COLUMN, drawn);
        contentValues.put(LOST_COLUMN, lost);
        contentValues.put(FORWARD_COLUMN, forward);
        contentValues.put(AGAINST_COLUMN, against);
        contentValues.put(POINTS_COLUMN, points);

        database.update(TEAMS_TABLE, contentValues, "team = ?", new String[]{team + ""});
    }

    public static ArrayList<Team> getTeams(Activity activity, String group) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM " + TEAMS_TABLE + " WHERE grouptable='" + group + "' ORDER BY points DESC", null);

        ArrayList<Team> teamArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            teamArrayList.add(new Team(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getInt(4),
                    cursor.getInt(5), cursor.getInt(6), cursor.getInt(7),
                    cursor.getInt(8)));
        }
        return teamArrayList;
    }
}