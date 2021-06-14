package com.hoanglam0869.euro2020.utils;

import com.hoanglam0869.euro2020.R;

public class Flags {
    public static final int[] flags = {
            R.drawable.italy,
            R.drawable.switzerland,
            R.drawable.turkey,
            R.drawable.wales,
            R.drawable.belgium,
            R.drawable.denmark,
            R.drawable.finland,
            R.drawable.russia,
            R.drawable.austria,
            R.drawable.netherlands,
            R.drawable.north_macedonia,
            R.drawable.ukraine,
            R.drawable.croatia,
            R.drawable.czech_republic,
            R.drawable.england,
            R.drawable.scotland,
            R.drawable.poland,
            R.drawable.slovakia,
            R.drawable.spain,
            R.drawable.sweden,
            R.drawable.france,
            R.drawable.germany,
            R.drawable.hungary,
            R.drawable.portugal};

    public static int getFlag(String team) {
        int position = 0;
        for (int i = 0; i < Teams.teams.length; i++) {
            if (team.equals(Teams.teams[i])) {
                position = i;
                break;
            }
        }
        return flags[position];
    }
}