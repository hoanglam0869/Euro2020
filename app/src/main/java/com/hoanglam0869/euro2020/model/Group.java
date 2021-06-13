package com.hoanglam0869.euro2020.model;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Group {
    String group;
    ArrayList<Team> teamArrayList;

    public Group(String group, ArrayList<Team> teamArrayList) {
        this.group = group;
        this.teamArrayList = teamArrayList;
    }

    public String getGroup() {
        return group;
    }

    public Group setGroup(String group) {
        this.group = group;
        return this;
    }

    public ArrayList<Team> getTeamArrayList() {
        return teamArrayList;
    }

    public Group setTeamArrayList(ArrayList<Team> teamArrayList) {
        this.teamArrayList = teamArrayList;
        return this;
    }
}
