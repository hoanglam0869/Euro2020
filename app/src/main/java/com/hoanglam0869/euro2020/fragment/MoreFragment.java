package com.hoanglam0869.euro2020.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.utils.Settings;
import com.hoanglam0869.euro2020.utils.TimeZone;

public class MoreFragment extends Fragment {

    View view;
    Spinner spnTimeZone;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more, container, false);

        anhXa();
        setTimeZone();
        return view;
    }

    private void setTimeZone() {
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, TimeZone.timeZones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTimeZone.setAdapter(adapter);

        Settings.getTimeZone(getContext());
        spnTimeZone.setSelection(TimeZone.positionTimeZone);

        spnTimeZone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TimeZone.positionTimeZone = position;
                Settings.setTimeZone(getContext());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void anhXa() {
        spnTimeZone = view.findViewById(R.id.spnTimeZone);
    }
}