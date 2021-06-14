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

        /*MainActivity.groupA.clear();
        MainActivity.groupB.clear();
        MainActivity.groupC.clear();
        MainActivity.groupD.clear();
        MainActivity.groupE.clear();
        MainActivity.groupF.clear();*/

        MainActivity.groupA = DBHelper.getTeams(getActivity(), "A");
        MainActivity.groupB = DBHelper.getTeams(getActivity(), "B");
        MainActivity.groupC = DBHelper.getTeams(getActivity(), "C");
        MainActivity.groupD = DBHelper.getTeams(getActivity(), "D");
        MainActivity.groupE = DBHelper.getTeams(getActivity(), "E");
        MainActivity.groupF = DBHelper.getTeams(getActivity(), "F");

        groupArrayList = new ArrayList<>();
        groupArrayList.add(new Group("A", MainActivity.groupA));
        groupArrayList.add(new Group("B", MainActivity.groupB));
        groupArrayList.add(new Group("C", MainActivity.groupC));
        groupArrayList.add(new Group("D", MainActivity.groupD));
        groupArrayList.add(new Group("E", MainActivity.groupE));
        groupArrayList.add(new Group("F", MainActivity.groupF));

        adapter = new GroupAdapter(getContext(), groupArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerViewTable.setLayoutManager(linearLayoutManager);
        recyclerViewTable.setAdapter(adapter);

        return view;
    }
}