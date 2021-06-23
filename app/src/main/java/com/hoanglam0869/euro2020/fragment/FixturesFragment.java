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
import com.hoanglam0869.euro2020.adapter.FixturesAdapter;
import com.hoanglam0869.euro2020.database.DBHelper;

public class FixturesFragment extends Fragment {

    View view;
    RecyclerView recyclerViewFixtures;
    FixturesAdapter adapter;

    private int lastPosition;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fixtures, container, false);

        mainActivity = (MainActivity) getContext();
        if (mainActivity != null) {
            mainActivity.txv3RD.setVisibility(View.GONE);
        }

        recyclerViewFixtures = view.findViewById(R.id.recyclerViewFixtures);

        MainActivity.fixturesArrayList = DBHelper.getFixtures(getActivity());

        adapter = new FixturesAdapter(getContext(), MainActivity.fixturesArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerViewFixtures.setLayoutManager(linearLayoutManager);
        recyclerViewFixtures.setAdapter(adapter);

        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        lastPosition = getPrefs.getInt("lastPos", 0);
        recyclerViewFixtures.scrollToPosition(lastPosition);

        recyclerViewFixtures.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                lastPosition = linearLayoutManager.findFirstVisibleItemPosition();
            }
        });

        return view;
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