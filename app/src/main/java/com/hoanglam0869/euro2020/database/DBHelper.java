package com.hoanglam0869.euro2020.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.model.Fixtures;
import com.hoanglam0869.euro2020.model.Team;

import java.util.ArrayList;

public class DBHelper {
    private static final String DATABASE_NAME = "euro2020.sqlite";
    /*private static final String FIXTURES_TABLE = "fixtures";
    private static final String TEAMS_TABLE = "teams";

    private static final String TEAM1_COLUMN = "team1";
    private static final String TEAM2_COLUMN = "team2";
    private static final String SCORE1_COLUMN = "score1";
    private static final String SCORE2_COLUMN = "score2";
    private static final String RESULT1_COLUMN = "result1";
    private static final String RESULT2_COLUMN = "result2";

    private static final String WON_COLUMN = "won";
    private static final String DRAWN_COLUMN = "drawn";
    private static final String LOST_COLUMN = "lost";
    private static final String FORWARD_COLUMN = "forward";
    private static final String AGAINST_COLUMN = "against";
    private static final String POINTS_COLUMN = "points";*/

    public static ArrayList<Fixtures> getFixtures(Activity activity) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM fixtures", null);

        ArrayList<Fixtures> fixturesArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            fixturesArrayList.add(new Fixtures(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getInt(6), cursor.getInt(7),
                    cursor.getString(8), cursor.getString(9)));
        }
        cursor.close();
        database.close();
        return fixturesArrayList;
    }

    public static void updateScore(Activity activity, Fixtures fixtures) {
        int score1 = fixtures.getScore1();
        int score2 = fixtures.getScore2();
        String result1 = "", result2 = "";

        if (score1 != -1 && score2 != -1) {
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
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("score1", score1);
        contentValues.put("score2", score2);
        contentValues.put("result1", result1);
        contentValues.put("result2", result2);

        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        database.update("fixtures", contentValues, "id = ?", new String[]{fixtures.getId() + ""});
        database.close();
    }

    public static void updateTeam(Activity activity) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = null;

        for (int i = 0; i < MainActivity.teamArrayList.size(); i++) {
            int won = 0, drawn = 0, lost = 0, forward = 0, against = 0, points;
            String team = MainActivity.teamArrayList.get(i).getTeam();

            cursor = database.rawQuery("SELECT count() FROM fixtures WHERE (id < 37 AND result1='" + team + " won') OR (id < 37 AND result2='" + team + " won')", null);
            cursor.moveToFirst();
            won += cursor.getInt(0);

            cursor = database.rawQuery("SELECT count() FROM fixtures WHERE (id < 37 AND result1='" + team + " drawn') OR (id < 37 AND result2='" + team + " drawn')", null);
            cursor.moveToFirst();
            drawn += cursor.getInt(0);

            cursor = database.rawQuery("SELECT count() FROM fixtures WHERE (id < 37 AND result1='" + team + " lost') OR (id < 37 AND result2='" + team + " lost')", null);
            cursor.moveToFirst();
            lost += cursor.getInt(0);

            cursor = database.rawQuery("SELECT sum(score1) FROM fixtures WHERE id < 37 AND team1='" + team + "' AND score1 <> -1 AND score2 <> -1", null);
            cursor.moveToFirst();
            forward += cursor.getInt(0);

            cursor = database.rawQuery("SELECT sum(score2) FROM fixtures WHERE id < 37 AND team2='" + team + "' AND score1 <> -1 AND score2 <> -1", null);
            cursor.moveToFirst();
            forward += cursor.getInt(0);

            cursor = database.rawQuery("SELECT sum(score2) FROM fixtures WHERE id < 37 AND team1='" + team + "' AND score1 <> -1 AND score2 <> -1", null);
            cursor.moveToFirst();
            against += cursor.getInt(0);

            cursor = database.rawQuery("SELECT sum(score1) FROM fixtures WHERE id < 37 AND team2='" + team + "' AND score1 <> -1 AND score2 <> -1", null);
            cursor.moveToFirst();
            against += cursor.getInt(0);

            points = (won * 3 + drawn) * 100000000 + (forward - against) * 10000 + forward * 100 - MainActivity.teamArrayList.get(i).getId();

            MainActivity.teamArrayList.get(i).setWon(won);
            MainActivity.teamArrayList.get(i).setDrawn(drawn);
            MainActivity.teamArrayList.get(i).setLost(lost);
            MainActivity.teamArrayList.get(i).setForward(forward);
            MainActivity.teamArrayList.get(i).setAgainst(against);
            MainActivity.teamArrayList.get(i).setPoints(points);

            ContentValues contentValues = new ContentValues();
            contentValues.put("won", won);
            contentValues.put("drawn", drawn);
            contentValues.put("lost", lost);
            contentValues.put("forward", forward);
            contentValues.put("against", against);
            contentValues.put("points", points);

            database.update("teams", contentValues, "team = ?", new String[]{team});
        }
        if (cursor != null) {
            cursor.close();
        }
        database.close();
    }

    public static ArrayList<Team> getTeams(Activity activity) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM teams", null);

        ArrayList<Team> teamArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            teamArrayList.add(new Team(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getInt(4),
                    cursor.getInt(5), cursor.getInt(6), cursor.getInt(7),
                    cursor.getInt(8), cursor.getInt(9)));
        }
        cursor.close();
        database.close();
        return teamArrayList;
    }

    public static ArrayList<Team> getTeamsByGroup(Activity activity, String group) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM teams WHERE grouptable='" + group + "' ORDER BY points DESC", null);

        ArrayList<Team> teamArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            teamArrayList.add(new Team(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getInt(4),
                    cursor.getInt(5), cursor.getInt(6), cursor.getInt(7),
                    cursor.getInt(8), cursor.getInt(9)));
        }

        for (int i = 0; i < teamArrayList.size(); i++) {
            teamArrayList.get(i).setPosition(i + 1);

            ContentValues contentValues = new ContentValues();
            contentValues.put("position", teamArrayList.get(i).getPosition());

            database.update("teams", contentValues, "team = ?", new String[]{teamArrayList.get(i).getTeam()});
        }

        cursor.close();
        database.close();
        return teamArrayList;
    }

    public static void setHeadToHeadPoints(Activity activity) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = null;

        for (int i = 0; i < MainActivity.teamArrayList.size(); i++) {
            Team team1 = MainActivity.teamArrayList.get(i);
            for (int j = 0; j < MainActivity.teamArrayList.size(); j++) {
                if (i != j) {
                    Team team2 = MainActivity.teamArrayList.get(j);
                    if (team1.getGroup().equals(team2.getGroup())) {
                        if (team1.getWon() * 3 + team1.getDrawn() == team2.getWon() * 3 + team2.getDrawn()) {
                            cursor = database.rawQuery("SELECT * FROM fixtures WHERE (team1='" + team1.getTeam() + "' AND team2='" + team2.getTeam() + "') OR (team1='" + team2.getTeam() + "' AND team2='" + team1.getTeam() + "')", null);
                            cursor.moveToFirst();
                            Fixtures fixtures = new Fixtures(cursor.getInt(0), cursor.getString(1),
                                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                                    cursor.getString(5), cursor.getInt(6), cursor.getInt(7),
                                    cursor.getString(8), cursor.getString(9));
                            if (fixtures.getScore1() != -1 && fixtures.getScore2() != -1) {
                                if (fixtures.getTeam1().equals(team1.getTeam())) {
                                    if (fixtures.getScore1() >= fixtures.getScore2()) {
                                        team1.setPoints(team1.getPoints() + 1000000);
                                    }
                                } else {
                                    if (fixtures.getScore1() <= fixtures.getScore2()) {
                                        team1.setPoints(team1.getPoints() + 1000000);
                                    }
                                }

                                ContentValues contentValues = new ContentValues();
                                contentValues.put("points", team1.getPoints());

                                database.update("teams", contentValues, "team = ?", new String[]{team1.getTeam()});
                            }
                        }
                    }
                }
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        database.close();
    }

    public static ArrayList<Team> getThirdPlacedTeams(Activity activity) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM teams WHERE position = 3 ORDER BY points DESC", null);

        ArrayList<Team> teamArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            teamArrayList.add(new Team(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getInt(4),
                    cursor.getInt(5), cursor.getInt(6), cursor.getInt(7),
                    cursor.getInt(8), cursor.getInt(9)));
        }
        cursor.close();
        database.close();
        return teamArrayList;
    }
}