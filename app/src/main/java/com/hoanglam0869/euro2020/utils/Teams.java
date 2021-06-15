package com.hoanglam0869.euro2020.utils;

import android.content.Context;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.R;

public class Teams {
    public static final String[] teams = {"Italy", "Switzerland", "Turkey", "Wales", "Belgium", "Denmark", "Finland", "Russia",
            "Austria", "Netherlands", "North Macedonia", "Ukraine", "Croatia", "Czech Republic", "England", "Scotland",
            "Poland", "Slovakia", "Spain", "Sweden", "France", "Germany", "Hungary", "Portugal"};

    public static String getTeamName(Context context, String team) {
        int position = 0;
        for (int i = 0; i < MainActivity.teamArrayList.size(); i++) {
            if (team.equals(MainActivity.teamArrayList.get(i).getTeam())) {
                position = i;
                break;
            }
        }
        return context.getResources().getStringArray(R.array.teams)[position];
    }

    public static int getPositionByTeam(String team) {
        for (int i = 0; i < MainActivity.teamArrayList.size(); i++) {
            if (team.equals(MainActivity.teamArrayList.get(i).getTeam())) {
                return i;
            }
        }
        return 0;
    }
}