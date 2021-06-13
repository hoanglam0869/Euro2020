package com.hoanglam0869.euro2020.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.adapter.FixturesAdapter;
import com.hoanglam0869.euro2020.database.DBHelper;
import com.hoanglam0869.euro2020.model.Fixtures;
import com.hoanglam0869.euro2020.utils.Settings;

import java.util.ArrayList;

public class FixturesFragment extends Fragment {

    View view;
    RecyclerView recyclerViewFixtures;
    FixturesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fixtures, container, false);

        recyclerViewFixtures = view.findViewById(R.id.recyclerViewFixtures);

        adapter = new FixturesAdapter(getContext(), MainActivity.fixturesArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerViewFixtures.setLayoutManager(linearLayoutManager);
        recyclerViewFixtures.setAdapter(adapter);

        return view;
    }
}