package com.hoanglam0869.euro2020.utils;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.model.Team;
import com.hoanglam0869.euro2020.model.Third;

import java.util.ArrayList;

public class ThirdPlaced {

    public static ArrayList<Third> getCombinations() {
        ArrayList<Third> thirds = new ArrayList<>();

        thirds.add(new Third("A", "D", "B", "C"));
        thirds.add(new Third("A", "E", "B", "C"));
        thirds.add(new Third("A", "F", "B", "C"));
        thirds.add(new Third("D", "E", "A", "B"));
        thirds.add(new Third("D", "F", "A", "B"));
        thirds.add(new Third("E", "F", "B", "A"));
        thirds.add(new Third("E", "D", "C", "A"));
        thirds.add(new Third("F", "D", "C", "A"));
        thirds.add(new Third("E", "F", "C", "A"));
        thirds.add(new Third("E", "F", "D", "A"));
        thirds.add(new Third("E", "D", "B", "C"));
        thirds.add(new Third("F", "D", "C", "B"));
        thirds.add(new Third("F", "E", "C", "B"));
        thirds.add(new Third("F", "E", "D", "B"));
        thirds.add(new Third("F", "E", "D", "C"));

        return thirds;
    }

    public static int getPositionOfCombinations() {
        for (int i = 0; i < MainActivity.combinations.size(); i++) {
            int count = 0;
            Third third = MainActivity.combinations.get(i);
            for (int j = 0; j < MainActivity.groupThirdPlacedFourTeams.size(); j++) {
                Team team = MainActivity.groupThirdPlacedFourTeams.get(j);
                if (third.getFirstB().equals(team.getGroup())) {
                    count++;
                }
                if (third.getFirstC().equals(team.getGroup())) {
                    count++;
                }
                if (third.getFirstE().equals(team.getGroup())) {
                    count++;
                }
                if (third.getFirstF().equals(team.getGroup())) {
                    count++;
                }
            }
            if (count == 4) {
                return i;
            }
        }
        return 0;
    }

    public static void getThirdPlacedFourTeams() {
        Third third = MainActivity.combinations.get(getPositionOfCombinations());
        Team[] teams = new Team[MainActivity.groupThirdPlacedFourTeams.size()];
        for (int i = 0; i < MainActivity.groupThirdPlacedFourTeams.size(); i++) {
            Team team = MainActivity.groupThirdPlacedFourTeams.get(i);
            if (third.getFirstB().equals(team.getGroup())) {
                teams[0] = team;
            }
            if (third.getFirstC().equals(team.getGroup())) {
                teams[1] = team;
            }
            if (third.getFirstE().equals(team.getGroup())) {
                teams[2] = team;
            }
            if (third.getFirstF().equals(team.getGroup())) {
                teams[3] = team;
            }
        }
        for (int i = 0; i < MainActivity.groupThirdPlacedFourTeams.size(); i++) {
            MainActivity.groupThirdPlacedFourTeams.set(i, teams[i]);
        }
    }
}
