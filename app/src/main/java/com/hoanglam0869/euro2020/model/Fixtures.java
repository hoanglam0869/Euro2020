package com.hoanglam0869.euro2020.model;

public class Fixtures {
    private int id;
    private String dateTime, group, stadium, team1, team2;
    private int score1, score2;
    private String result1, result2;

    public Fixtures(int id, String dateTime, String group, String stadium, String team1, String team2, int score1, int score2, String result1, String result2) {
        this.id = id;
        this.dateTime = dateTime;
        this.group = group;
        this.stadium = stadium;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.result1 = result1;
        this.result2 = result2;
    }

    public int getId() {
        return id;
    }

    public Fixtures setId(int id) {
        this.id = id;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Fixtures setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public Fixtures setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getStadium() {
        return stadium;
    }

    public Fixtures setStadium(String stadium) {
        this.stadium = stadium;
        return this;
    }

    public String getTeam1() {
        return team1;
    }

    public Fixtures setTeam1(String team1) {
        this.team1 = team1;
        return this;
    }

    public String getTeam2() {
        return team2;
    }

    public Fixtures setTeam2(String team2) {
        this.team2 = team2;
        return this;
    }

    public int getScore1() {
        return score1;
    }

    public Fixtures setScore1(int score1) {
        this.score1 = score1;
        return this;
    }

    public int getScore2() {
        return score2;
    }

    public Fixtures setScore2(int score2) {
        this.score2 = score2;
        return this;
    }

    public String getResult1() {
        return result1;
    }

    public Fixtures setResult1(String result1) {
        this.result1 = result1;
        return this;
    }

    public String getResult2() {
        return result2;
    }

    public Fixtures setResult2(String result2) {
        this.result2 = result2;
        return this;
    }
}
