package com.hoanglam0869.euro2020.model;

public class Fixtures {
    private int id;
    private String dateTime, group, stadium, team1, team2;
    private int ftScore1, ftScore2, etScore1, etScore2, penScore1, penScore2;

    public Fixtures(int id, String dateTime, String group, String stadium, String team1, String team2, int ftScore1, int ftScore2, int etScore1, int etScore2, int penScore1, int penScore2) {
        this.id = id;
        this.dateTime = dateTime;
        this.group = group;
        this.stadium = stadium;
        this.team1 = team1;
        this.team2 = team2;
        this.ftScore1 = ftScore1;
        this.ftScore2 = ftScore2;
        this.etScore1 = etScore1;
        this.etScore2 = etScore2;
        this.penScore1 = penScore1;
        this.penScore2 = penScore2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public int getFtScore1() {
        return ftScore1;
    }

    public void setFtScore1(int ftScore1) {
        this.ftScore1 = ftScore1;
    }

    public int getFtScore2() {
        return ftScore2;
    }

    public void setFtScore2(int ftScore2) {
        this.ftScore2 = ftScore2;
    }

    public int getEtScore1() {
        return etScore1;
    }

    public void setEtScore1(int etScore1) {
        this.etScore1 = etScore1;
    }

    public int getEtScore2() {
        return etScore2;
    }

    public void setEtScore2(int etScore2) {
        this.etScore2 = etScore2;
    }

    public int getPenScore1() {
        return penScore1;
    }

    public void setPenScore1(int penScore1) {
        this.penScore1 = penScore1;
    }

    public int getPenScore2() {
        return penScore2;
    }

    public void setPenScore2(int penScore2) {
        this.penScore2 = penScore2;
    }
}
