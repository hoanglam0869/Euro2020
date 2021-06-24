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
            for (int j = 0; j < 4; j++) {
                Team team = MainActivity.groupThirdPlaced.get(j);
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

    public static ArrayList<Team> getThirdPlacedFourTeams() {
        ArrayList<Team> teams = new ArrayList<>();
        Third third = MainActivity.combinations.get(getPositionOfCombinations());
        for (int i = 0; i < 4; i++) {
            if (MainActivity.groupThirdPlaced.get(i).getGroup().equals(third.getFirstB())) {
                teams.add(MainActivity.groupThirdPlaced.get(i));
            }
        }
        for (int i = 0; i < 4; i++) {
            if (MainActivity.groupThirdPlaced.get(i).getGroup().equals(third.getFirstC())) {
                teams.add(MainActivity.groupThirdPlaced.get(i));
            }
        }for (int i = 0; i < 4; i++) {
            if (MainActivity.groupThirdPlaced.get(i).getGroup().equals(third.getFirstE())) {
                teams.add(MainActivity.groupThirdPlaced.get(i));
            }
        }
        for (int i = 0; i < 4; i++) {
            if (MainActivity.groupThirdPlaced.get(i).getGroup().equals(third.getFirstF())) {
                teams.add(MainActivity.groupThirdPlaced.get(i));
            }
        }
        return teams;
    }
}
