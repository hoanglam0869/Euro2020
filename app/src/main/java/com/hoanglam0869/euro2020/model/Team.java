package com.hoanglam0869.euro2020.model;

public class Team {
    private int id;
    private String group, team;
    private int position, played, won, drawn, lost, forward, against, goalDifference, points;

    public Team(int id, String group, String team, int position, int played, int won, int drawn, int lost, int forward, int against, int goalDifference, int points) {
        this.id = id;
        this.group = group;
        this.team = team;
        this.position = position;
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.forward = forward;
        this.against = against;
        this.goalDifference = goalDifference;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public Team setId(int id) {
        this.id = id;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public Team setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getTeam() {
        return team;
    }

    public Team setTeam(String team) {
        this.team = team;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public Team setPosition(int position) {
        this.position = position;
        return this;
    }

    public int getPlayed() {
        return played;
    }

    public Team setPlayed(int played) {
        this.played = played;
        return this;
    }

    public int getWon() {
        return won;
    }

    public Team setWon(int won) {
        this.won = won;
        return this;
    }

    public int getDrawn() {
        return drawn;
    }

    public Team setDrawn(int drawn) {
        this.drawn = drawn;
        return this;
    }

    public int getLost() {
        return lost;
    }

    public Team setLost(int lost) {
        this.lost = lost;
        return this;
    }

    public int getForward() {
        return forward;
    }

    public Team setForward(int forward) {
        this.forward = forward;
        return this;
    }

    public int getAgainst() {
        return against;
    }

    public Team setAgainst(int against) {
        this.against = against;
        return this;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public Team setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
        return this;
    }

    public int getPoints() {
        return points;
    }

    public Team setPoints(int points) {
        this.points = points;
        return this;
    }
}
