package com.hoanglam0869.euro2020.utils;

import android.content.Context;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.model.Team;

import java.util.ArrayList;

public class Teams {
    public static final String[] unknown = {"2nd in Group A", "1st in Group A", "1st in Group C", "1st in Group B", "2nd in Group D",
            "1st in Group F", "1st in Group D", "1st in Group E", "Winner R16 match 6", "Winner R16 match 4",
            "Winner R16 match 3", "Winner R16 match 8", "Winner QF2", "Winner QF4", "Winner SF1",
            "2nd in Group B", "2nd in Group C", "3rd in Groups D/E or F", "3rd in Groups A/D/E or F", "2nd in Group E",
            "3rd in Groups A/B or C", "2nd in Group F", "3rd in Groups A/B/C or D", "Winner R16 match 5", "Winner R16 match 2",
            "Winner R16 match 1", "Winner R16 match 7", "Winner QF1", "Winner QF3", "Winner SF2"};

    public static String getTeamName(Context context, String team) {
        for (int i = 0; i < MainActivity.teamArrayList.size(); i++) {
            if (team.equals(MainActivity.teamArrayList.get(i).getTeam())) {
                return context.getResources().getStringArray(R.array.teams)[i];
            }
        }
        for (int i = 0; i < unknown.length; i++) {
            if (team.equals(unknown[i])) {
                return context.getResources().getStringArray(R.array.unknown)[i];
            }
        }
        return null;
    }

    public static int getPositionByTeam(String team) {
        for (int i = 0; i < MainActivity.teamArrayList.size(); i++) {
            if (team.equals(MainActivity.teamArrayList.get(i).getTeam())) {
                return i;
            }
        }
        return 0;
    }

    public static boolean isFinished(ArrayList<Team> teams) {
        int total = 0;
        for (int i = 0; i < teams.size(); i++) {
            total += teams.get(i).getWon() + teams.get(i).getDrawn() + teams.get(i).getLost();
        }
        return total == teams.size() * 3;
    }
}