package com.hoanglam0869.euro2020.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.model.Fixtures;
import com.hoanglam0869.euro2020.model.Team;
import com.hoanglam0869.euro2020.utils.Teams;

import java.util.ArrayList;

public class TeamsDBHelper {
    private static final String DATABASE_NAME = "euro2020.sqlite";

    private static final String TEAMS_TABLE = "teams";

    private static final String TEAM_COLUMN = "team";
    private static final String WON_COLUMN = "won";
    private static final String DRAWN_COLUMN = "drawn";
    private static final String LOST_COLUMN = "lost";
    private static final String FORWARD_COLUMN = "forward";
    private static final String AGAINST_COLUMN = "against";
    private static final String POINTS_COLUMN = "points";
    private static final String POSITION_COLUMN = "position";

    public static void updateTeam(Activity activity, ArrayList<Team> teams) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = null;

        for (int i = 0; i < teams.size(); i++) {
            int won = 0, drawn = 0, lost = 0, forward = 0, against = 0, points;
            String team = teams.get(i).getTeam();

            cursor = database.rawQuery("SELECT count() FROM fixtures WHERE (id < 37 AND team1 = '" + team + "' AND fulltime1 > fulltime2) OR (id < 37 AND team2 = '" + team + "' AND fulltime1 < fulltime2)", null);
            cursor.moveToFirst();
            won += cursor.getInt(0);

            cursor = database.rawQuery("SELECT count() FROM fixtures WHERE (id < 37 AND team1 = '" + team + "' AND fulltime1 = fulltime2 AND fulltime1 + fulltime2 <> -2) OR (id < 37 AND team2 = '" + team + "' AND fulltime1 = fulltime2 AND fulltime1 + fulltime2 <> -2)", null);
            cursor.moveToFirst();
            drawn += cursor.getInt(0);

            cursor = database.rawQuery("SELECT count() FROM fixtures WHERE (id < 37 AND team1 = '" + team + "' AND fulltime1 < fulltime2) OR (id < 37 AND team2 = '" + team + "' AND fulltime1 > fulltime2)", null);
            cursor.moveToFirst();
            lost += cursor.getInt(0);

            cursor = database.rawQuery("SELECT sum(fulltime1) FROM fixtures WHERE id < 37 AND team1='" + team + "' AND fulltime1 + fulltime2 <> -2", null);
            cursor.moveToFirst();
            forward += cursor.getInt(0);

            cursor = database.rawQuery("SELECT sum(fulltime2) FROM fixtures WHERE id < 37 AND team2='" + team + "' AND fulltime1 + fulltime2 <> -2", null);
            cursor.moveToFirst();
            forward += cursor.getInt(0);

            cursor = database.rawQuery("SELECT sum(fulltime2) FROM fixtures WHERE id < 37 AND team1='" + team + "' AND fulltime1 + fulltime2 <> -2", null);
            cursor.moveToFirst();
            against += cursor.getInt(0);

            cursor = database.rawQuery("SELECT sum(fulltime1) FROM fixtures WHERE id < 37 AND team2='" + team + "' AND fulltime1 + fulltime2 <> -2", null);
            cursor.moveToFirst();
            against += cursor.getInt(0);

            points = (won * 3 + drawn) * 100000000 + (forward - against) * 10000 + forward * 100 - teams.get(i).getId();

            ContentValues contentValues = new ContentValues();
            contentValues.put(WON_COLUMN, won);
            contentValues.put(DRAWN_COLUMN, drawn);
            contentValues.put(LOST_COLUMN, lost);
            contentValues.put(FORWARD_COLUMN, forward);
            contentValues.put(AGAINST_COLUMN, against);
            contentValues.put(POINTS_COLUMN, points);

            database.update(TEAMS_TABLE, contentValues, TEAM_COLUMN + " = ?", new String[]{team});
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
            teamArrayList.add(new Team(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6),
                    cursor.getInt(7), cursor.getInt(8), cursor.getInt(9)));
        }
        cursor.close();
        database.close();
        return teamArrayList;
    }

    public static ArrayList<Team> getTeamsByGroup(Activity activity, String group) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM teams WHERE grouptable='" + group + "' ORDER BY points DESC", null);

        ArrayList<Team> teams = new ArrayList<>();
        while (cursor.moveToNext()) {
            teams.add(new Team(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6),
                    cursor.getInt(7), cursor.getInt(8), cursor.getInt(9)));
        }
        Team temp = null;
        for (int i = 0; i < teams.size(); i++) {
            int count = 1, position = 0;
            Team team1 = teams.get(i);
            for (int j = 0; j < teams.size(); j++) {
                if (i != j) {
                    Team team2 = teams.get(j);
                    if (team1.getWon() * 3 + team1.getDrawn() == team2.getWon() * 3 + team2.getDrawn()) {
                        count++;
                        position = j;
                        temp = teams.get(j);
                    }
                }
            }
            if (count == 2 && i < position) {
                cursor = database.rawQuery("SELECT * FROM fixtures WHERE (team1='" + team1.getTeam() + "' AND team2='" + temp.getTeam() + "') OR (team1='" + temp.getTeam() + "' AND team2='" + team1.getTeam() + "')", null);
                cursor.moveToFirst();
                Fixtures fixtures = new Fixtures(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9),
                        cursor.getInt(10), cursor.getInt(11));
                if (fixtures.getFtScore1() != -1 && fixtures.getFtScore2() != -1) {
                    if (fixtures.getTeam1().equals(team1.getTeam())) {
                        if (fixtures.getFtScore1() < fixtures.getFtScore2()) {
                            teams.set(i, temp);
                            teams.set(position, team1);
                        }
                    } else {
                        if (fixtures.getFtScore1() > fixtures.getFtScore2()) {
                            teams.set(i, temp);
                            teams.set(position, team1);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < teams.size(); i++) {
            teams.get(i).setPosition(i + 1);

            ContentValues contentValues = new ContentValues();
            contentValues.put(POSITION_COLUMN, teams.get(i).getPosition());

            database.update(TEAMS_TABLE, contentValues, TEAM_COLUMN + " = ?", new String[]{teams.get(i).getTeam()});
        }
        cursor.close();
        database.close();

        return teams;
    }

    public static ArrayList<Team> getThirdPlacedTeams(Activity activity) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM teams WHERE position = 3 ORDER BY points DESC", null);

        ArrayList<Team> teams = new ArrayList<>();
        while (cursor.moveToNext()) {
            teams.add(new Team(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6),
                    cursor.getInt(7), cursor.getInt(8), cursor.getInt(9)));
        }
        cursor.close();
        database.close();
        return teams;
    }
}