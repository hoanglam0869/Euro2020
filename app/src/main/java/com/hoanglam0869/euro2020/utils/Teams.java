package com.hoanglam0869.euro2020.utils;

import android.content.Context;

import com.hoanglam0869.euro2020.R;

public class Teams {
    public static final String[] teams = {"Italy", "Switzerland", "Turkey", "Wales", "Belgium", "Denmark", "Finland", "Russia",
            "Austria", "Netherlands", "North Macedonia", "Ukraine", "Croatia", "Czech Republic", "England", "Scotland",
            "Poland", "Slovakia", "Spain", "Sweden", "France", "Germany", "Hungary", "Portugal"};

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