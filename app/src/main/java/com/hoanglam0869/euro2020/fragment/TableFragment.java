package com.hoanglam0869.euro2020.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.adapter.GroupAdapter;
import com.hoanglam0869.euro2020.model.Group;

import java.util.ArrayList;

public class TableFragment extends Fragment {

    View view;
    GroupAdapter adapter;
    ArrayList<Group> groupArrayList;
    RecyclerView recyclerViewTable;

    int lastPosition;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_table, container, false);

        setToolbarIcon();

        recyclerViewTable = view.findViewById(R.id.recyclerViewTable);

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

        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        lastPosition = getPrefs.getInt("lastPos", 0);
        recyclerViewTable.scrollToPosition(lastPosition);

        recyclerViewTable.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                lastPosition = linearLayoutManager.findFirstVisibleItemPosition();
            }
        });

        return view;
    }

    private void setToolbarIcon() {
        mainActivity = (MainActivity) getContext();
        if (mainActivity != null) {
            mainActivity.txv3RD.setVisibility(View.VISIBLE);

            mainActivity.txv3RD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor e = getPrefs.edit();
        e.putInt("lastPos", lastPosition);
        e.apply();
    }
}