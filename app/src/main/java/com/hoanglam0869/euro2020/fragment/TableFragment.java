package com.hoanglam0869.euro2020.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.adapter.GroupAdapter;
import com.hoanglam0869.euro2020.database.DBHelper;
import com.hoanglam0869.euro2020.model.Group;
import com.hoanglam0869.euro2020.model.Team;

import java.util.ArrayList;

public class TableFragment extends Fragment {

    View view;
    GroupAdapter adapter;
    ArrayList<Group> groupArrayList;
    RecyclerView recyclerViewTable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_table, container, false);

        recyclerViewTable = view.findViewById(R.id.recyclerViewTable);

        DBHelper.updateTeam(getActivity());
        DBHelper.setHeadToHeadPoints(getActivity());

        MainActivity.groupA = DBHelper.getTeamsByGroup(getActivity(), "A");
        MainActivity.groupB = DBHelper.getTeamsByGroup(getActivity(), "B");
        MainActivity.groupC = DBHelper.getTeamsByGroup(getActivity(), "C");
        MainActivity.groupD = DBHelper.getTeamsByGroup(getActivity(), "D");
        MainActivity.groupE = DBHelper.getTeamsByGroup(getActivity(), "E");
        MainActivity.groupF = DBHelper.getTeamsByGroup(getActivity(), "F");

        MainActivity.groupThirdPlaced = DBHelper.getThirdPlacedTeams(getActivity());

        groupArrayList = new ArrayList<>();
        groupArrayList.add(new Group("A", MainActivity.groupA));
        groupArrayList.add(new Group("B", MainActivity.groupB));
        groupArrayList.add(new Group("C", MainActivity.groupC));
        groupArrayList.add(new Group("D", MainActivity.groupD));
        groupArrayList.add(new Group("E", MainActivity.groupE));
        groupArrayList.add(new Group("F", MainActivity.groupF));
        groupArrayList.add(new Group("Third-placed", MainActivity.groupThirdPlaced));

        adapter = new GroupAdapter(getContext(), groupArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerViewTable.setLayoutManager(linearLayoutManager);
        recyclerViewTable.setAdapter(adapter);

        return view;
    }
}