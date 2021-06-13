package com.hoanglam0869.euro2020;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.hoanglam0869.euro2020.database.DBHelper;
import com.hoanglam0869.euro2020.fragment.FixturesFragment;
import com.hoanglam0869.euro2020.fragment.MoreFragment;
import com.hoanglam0869.euro2020.fragment.TableFragment;
import com.hoanglam0869.euro2020.fragment.TeamFragment;
import com.hoanglam0869.euro2020.model.Fixtures;
import com.hoanglam0869.euro2020.model.Team;
import com.hoanglam0869.euro2020.utils.Settings;
import com.hoanglam0869.euro2020.utils.Teams;

import java.util.ArrayList;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    SmoothBottomBar bottomBar;

    public static ArrayList<Fixtures> fixturesArrayList;
    public static ArrayList<Team> groupA;
    public static ArrayList<Team> groupB;
    public static ArrayList<Team> groupC;
    public static ArrayList<Team> groupD;
    public static ArrayList<Team> groupE;
    public static ArrayList<Team> groupF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        actionBar();
        setTimeZone();
        getFixtures();
        getTeamInGroup();
        setupSmoothBottomMenu();
    }

    private void setupSmoothBottomMenu() {
        bottomBar.setItemActiveIndex(0);
        replaceFragment(R.string.fixtures, new FixturesFragment());

        bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                switch (i) {
                    case 0:
                        replaceFragment(R.string.fixtures, new FixturesFragment());
                        break;
                    case 1:
                        replaceFragment(R.string.table, new TableFragment());
                        break;
                    case 2:
                        replaceFragment(R.string.team, new TeamFragment());
                        break;
                    case 3:
                        replaceFragment(R.string.more, new MoreFragment());
                        break;
                }
                return false;
            }
        });
    }

    private void getTeamInGroup() {
        for (int i = 0; i < Teams.teams.length; i++) {
            if (i < 4) {
                groupA.add(getTeam(i + 1, "A", Teams.teams[i]));
            } else if (i < 8) {
                groupB.add(getTeam(i + 1, "B", Teams.teams[i]));
            } else if (i < 12) {
                groupC.add(getTeam(i + 1, "C", Teams.teams[i]));
            } else if (i < 16) {
                groupD.add(getTeam(i + 1, "D", Teams.teams[i]));
            } else if (i < 20) {
                groupE.add(getTeam(i + 1, "E", Teams.teams[i]));
            } else {
                groupF.add(getTeam(i + 1, "F", Teams.teams[i]));
            }
        }
    }

    private void getFixtures() {
        fixturesArrayList = DBHelper.getFixtures(this);
    }

    private void setTimeZone() {
        Settings.getTimeZone(this);
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.fixtures);
    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolBar);
        bottomBar = findViewById(R.id.bottomBar);

        groupA = new ArrayList<>();
        groupB = new ArrayList<>();
        groupC = new ArrayList<>();
        groupD = new ArrayList<>();
        groupE = new ArrayList<>();
        groupF = new ArrayList<>();
    }

    private void replaceFragment(int titleId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        toolbar.setTitle(getResources().getString(titleId));
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    private Team getTeam(int id, String group, String team) {
        int won = 0, drawn = 0, lost = 0, forward = 0, against = 0;
        for (int j = 0; j < fixturesArrayList.size(); j++) {
            if (team.equals(fixturesArrayList.get(j).getTeam1())) {
                if (fixturesArrayList.get(j).getScore1() != -1 && fixturesArrayList.get(j).getScore2() != -1) {
                    if (fixturesArrayList.get(j).getScore1() > fixturesArrayList.get(j).getScore2()) {
                        won++;
                    } else if (fixturesArrayList.get(j).getScore1() < fixturesArrayList.get(j).getScore2()) {
                        lost++;
                    } else {
                        drawn++;
                    }
                    forward += fixturesArrayList.get(j).getScore1();
                    against += fixturesArrayList.get(j).getScore2();
                }
            }
            if (team.equals(fixturesArrayList.get(j).getTeam2())) {
                if (fixturesArrayList.get(j).getScore1() != -1 && fixturesArrayList.get(j).getScore2() != -1) {
                    if (fixturesArrayList.get(j).getScore1() < fixturesArrayList.get(j).getScore2()) {
                        won++;
                    } else if (fixturesArrayList.get(j).getScore1() > fixturesArrayList.get(j).getScore2()) {
                        lost++;
                    } else {
                        drawn++;
                    }
                    forward += fixturesArrayList.get(j).getScore2();
                    against += fixturesArrayList.get(j).getScore1();
                }
            }
        }
        int played = won + drawn + lost;
        int points = won * 3 + drawn;
        return new Team(id, group, team, 1, played, won, drawn, lost, forward, against, forward - against, points);
    }
}