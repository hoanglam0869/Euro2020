package com.hoanglam0869.euro2020.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.model.Fixtures;
import com.hoanglam0869.euro2020.utils.Teams;

import java.util.ArrayList;

public class FixturesDBHelper {
    private static final String DATABASE_NAME = "euro2020.sqlite";

    public static final String FIXTURES_TABLE = "fixtures";

    public static final String ID_COLUMN = "id";
    public static final String TEAM1_COLUMN = "team1";
    public static final String TEAM2_COLUMN = "team2";
    public static final String FT_SCORE1_COLUMN = "ft_score1";
    public static final String FT_SCORE2_COLUMN = "ft_score2";

    public static ArrayList<Fixtures> getFixtures(Activity activity) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM fixtures", null);

        ArrayList<Fixtures> fixturesArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            fixturesArrayList.add(new Fixtures(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9),
                    cursor.getInt(10), cursor.getInt(11)));
        }
        cursor.close();
        database.close();
        return fixturesArrayList;
    }

    public static void updateScore(Activity activity, Fixtures fixtures) {
        int ftScore1 = fixtures.getFtScore1();
        int ftScore2 = fixtures.getFtScore2();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FT_SCORE1_COLUMN, ftScore1);
        contentValues.put(FT_SCORE2_COLUMN, ftScore2);

        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        database.update(FIXTURES_TABLE, contentValues, ID_COLUMN + " = ?", new String[]{fixtures.getId() + ""});
        database.close();
    }

    public static void setRoundOf16Teams(Activity activity) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        ContentValues cv1 = new ContentValues();
        ContentValues cv2 = new ContentValues();
        ContentValues cv3 = new ContentValues();
        ContentValues cv4 = new ContentValues();

        if (Teams.isFinished(MainActivity.groupA)) {
            cv1.put(TEAM1_COLUMN, MainActivity.groupA.get(0).getTeam());
            cv2.put(TEAM1_COLUMN, MainActivity.groupA.get(1).getTeam());
        } else {
            cv1.put(TEAM1_COLUMN, "1st in Group A");
            cv2.put(TEAM1_COLUMN, "2nd in Group A");
        }
        database.update(FIXTURES_TABLE, cv1, ID_COLUMN + " = ?", new String[]{38 + ""});
        database.update(FIXTURES_TABLE, cv2, ID_COLUMN + " = ?", new String[]{37 + ""});
        cv1.clear();
        cv2.clear();

        if (Teams.isFinished(MainActivity.groupB)) {
            cv1.put(TEAM1_COLUMN, MainActivity.groupB.get(0).getTeam());
            cv2.put(TEAM2_COLUMN, MainActivity.groupB.get(1).getTeam());
        } else {
            cv1.put(TEAM1_COLUMN, "1st in Group B");
            cv2.put(TEAM2_COLUMN, "2nd in Group B");
        }
        database.update(FIXTURES_TABLE, cv1, ID_COLUMN + " = ?", new String[]{40 + ""});
        database.update(FIXTURES_TABLE, cv2, ID_COLUMN + " = ?", new String[]{37 + ""});
        cv1.clear();
        cv2.clear();

        if (Teams.isFinished(MainActivity.groupC)) {
            cv1.put(TEAM1_COLUMN, MainActivity.groupC.get(0).getTeam());
            cv2.put(TEAM2_COLUMN, MainActivity.groupC.get(1).getTeam());
        } else {
            cv1.put(TEAM1_COLUMN, "1st in Group C");
            cv2.put(TEAM2_COLUMN, "2nd in Group C");
        }
        database.update(FIXTURES_TABLE, cv1, ID_COLUMN + " = ?", new String[]{39 + ""});
        database.update(FIXTURES_TABLE, cv2, ID_COLUMN + " = ?", new String[]{38 + ""});
        cv1.clear();
        cv2.clear();

        if (Teams.isFinished(MainActivity.groupD)) {
            cv1.put(TEAM1_COLUMN, MainActivity.groupD.get(0).getTeam());
            cv2.put(TEAM1_COLUMN, MainActivity.groupD.get(1).getTeam());
        } else {
            cv1.put(TEAM1_COLUMN, "1st in Group D");
            cv2.put(TEAM1_COLUMN, "2nd in Group D");
        }
        database.update(FIXTURES_TABLE, cv1, ID_COLUMN + " = ?", new String[]{43 + ""});
        database.update(FIXTURES_TABLE, cv2, ID_COLUMN + " = ?", new String[]{41 + ""});
        cv1.clear();
        cv2.clear();

        if (Teams.isFinished(MainActivity.groupE)) {
            cv1.put(TEAM1_COLUMN, MainActivity.groupE.get(0).getTeam());
            cv2.put(TEAM2_COLUMN, MainActivity.groupE.get(1).getTeam());
        } else {
            cv1.put(TEAM1_COLUMN, "1st in Group E");
            cv2.put(TEAM2_COLUMN, "2nd in Group E");
        }
        database.update(FIXTURES_TABLE, cv1, ID_COLUMN + " = ?", new String[]{44 + ""});
        database.update(FIXTURES_TABLE, cv2, ID_COLUMN + " = ?", new String[]{41 + ""});
        cv1.clear();
        cv2.clear();

        if (Teams.isFinished(MainActivity.groupF)) {
            cv1.put(TEAM1_COLUMN, MainActivity.groupF.get(0).getTeam());
            cv2.put(TEAM2_COLUMN, MainActivity.groupF.get(1).getTeam());
        } else {
            cv1.put(TEAM1_COLUMN, "1st in Group F");
            cv2.put(TEAM2_COLUMN, "2nd in Group F");
        }
        database.update(FIXTURES_TABLE, cv1, ID_COLUMN + " = ?", new String[]{42 + ""});
        database.update(FIXTURES_TABLE, cv2, ID_COLUMN + " = ?", new String[]{43 + ""});
        cv1.clear();
        cv2.clear();

        if (Teams.isFinished(MainActivity.groupThirdPlaced)) {
            cv1.put(TEAM2_COLUMN, MainActivity.groupThirdPlacedFourTeams.get(1).getTeam());
            cv2.put(TEAM2_COLUMN, MainActivity.groupThirdPlacedFourTeams.get(0).getTeam());
            cv3.put(TEAM2_COLUMN, MainActivity.groupThirdPlacedFourTeams.get(3).getTeam());
            cv4.put(TEAM2_COLUMN, MainActivity.groupThirdPlacedFourTeams.get(2).getTeam());
        } else {
            cv1.put(TEAM2_COLUMN, "3rd in Groups D/E or F");
            cv2.put(TEAM2_COLUMN, "3rd in Groups A/D/E or F");
            cv3.put(TEAM2_COLUMN, "3rd in Groups A/B or C");
            cv4.put(TEAM2_COLUMN, "3rd in Groups A/B/C or D");
        }
        database.update(FIXTURES_TABLE, cv1, ID_COLUMN + " = ?", new String[]{39 + ""});
        database.update(FIXTURES_TABLE, cv2, ID_COLUMN + " = ?", new String[]{40 + ""});
        database.update(FIXTURES_TABLE, cv3, ID_COLUMN + " = ?", new String[]{42 + ""});
        database.update(FIXTURES_TABLE, cv4, ID_COLUMN + " = ?", new String[]{44 + ""});

        database.close();
    }
}
