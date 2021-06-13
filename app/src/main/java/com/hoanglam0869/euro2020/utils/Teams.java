package com.hoanglam0869.euro2020.utils;

import android.content.Context;

import com.hoanglam0869.euro2020.R;

public class Teams {
    public static final String[] teams = {"Austria", "Belgium", "Croatia", "Czech Republic", "Denmark", "England", "Finland", "France",
            "Germany", "Hungary", "Italy", "Netherlands", "North Macedonia", "Poland", "Portugal", "Russia",
            "Scotland", "Slovakia", "Spain", "Sweden", "Switzerland", "Turkey", "Ukraine", "Wales"};

    public static String getTeamName(Context context, String team) {
        int position = 0;
        for (int i = 0; i < teams.length; i++) {
            if (team.equals(teams[i])) {
                position = i;
                break;
            }
        }
        return context.getResources().getStringArray(R.array.teams)[position];
    }
}
