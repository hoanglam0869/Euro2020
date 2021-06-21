package com.hoanglam0869.euro2020.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.R;

public class TeamFragment extends Fragment {

    View view;

    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, container, false);

        mainActivity = (MainActivity) getContext();
        if (mainActivity != null) {
            mainActivity.txv3RD.setVisibility(View.GONE);
        }

        return view;
    }
}