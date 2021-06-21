package com.hoanglam0869.euro2020;

import android.os.Bundle;
import android.widget.TextView;

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

import java.util.ArrayList;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    public TextView txv3RD;
    SmoothBottomBar bottomBar;

    public static ArrayList<Fixtures> fixturesArrayList;
    public static ArrayList<Team> teamArrayList;
    public static ArrayList<Team> groupA;
    public static ArrayList<Team> groupB;
    public static ArrayList<Team> groupC;
    public static ArrayList<Team> groupD;
    public static ArrayList<Team> groupE;
    public static ArrayList<Team> groupF;
    public static ArrayList<Team> groupThirdPlaced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        actionBar();
        setTimeZone();
        getTeams();
        getGroups();
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

    public void getGroups() {
        DBHelper.updateTeam(this);
        DBHelper.setHeadToHeadPoints(this);

        groupA = DBHelper.getTeamsByGroup(this, "A");
        groupB = DBHelper.getTeamsByGroup(this, "B");
        groupC = DBHelper.getTeamsByGroup(this, "C");
        groupD = DBHelper.getTeamsByGroup(this, "D");
        groupE = DBHelper.getTeamsByGroup(this, "E");
        groupF = DBHelper.getTeamsByGroup(this, "F");

        groupThirdPlaced = DBHelper.getThirdPlacedTeams(this, 6);
    }

    private void getTeams() {
        teamArrayList = DBHelper.getTeams(this);
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
        txv3RD = findViewById(R.id.txv3RD);
        bottomBar = findViewById(R.id.bottomBar);
    }

    private void replaceFragment(int titleId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        toolbar.setTitle(getResources().getString(titleId));
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();

        /*for (int j = 0; j < fixturesArrayList.size(); j++) {
            if (fixturesArrayList.get(j).getScore1() != -1 && fixturesArrayList.get(j).getScore2() != -1) {
                if (MainActivity.teamArrayList.get(i).getTeam().equals(fixturesArrayList.get(j).getTeam1())) {
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
                if (MainActivity.teamArrayList.get(i).getTeam().equals(fixturesArrayList.get(j).getTeam2())) {
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
        }*/
    }
}