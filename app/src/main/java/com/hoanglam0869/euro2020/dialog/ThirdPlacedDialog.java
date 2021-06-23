package com.hoanglam0869.euro2020.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.adapter.CombinationAdapter;
import com.hoanglam0869.euro2020.model.Team;
import com.hoanglam0869.euro2020.utils.Flags;
import com.hoanglam0869.euro2020.utils.Teams;
import com.hoanglam0869.euro2020.utils.ThirdPlaced;

import java.util.ArrayList;

public class ThirdPlacedDialog extends Dialog {
    ImageView imgClose, imgTeam1, imgTeam2, imgTeam3, imgTeam4;
    TextView txvTeam1, txvTeam2, txvTeam3, txvTeam4, txvGroup1, txvGroup2, txvGroup3, txvGroup4;
    RecyclerView recyclerViewThirdPlaced;
    Button btnOK;

    CombinationAdapter adapter;
    int positionOfCombinations;

    public ThirdPlacedDialog(@NonNull Context context, ArrayList<Team> teams) {
        super(context);
        setContentView(R.layout.dialog_third_placed);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);

        anhXa();

        imgTeam1.setImageResource(Flags.getFlag(teams.get(0).getTeam()));
        imgTeam2.setImageResource(Flags.getFlag(teams.get(1).getTeam()));
        imgTeam3.setImageResource(Flags.getFlag(teams.get(2).getTeam()));
        imgTeam4.setImageResource(Flags.getFlag(teams.get(3).getTeam()));

        txvTeam1.setText(Teams.getTeamName(getContext(), teams.get(0).getTeam()));
        txvTeam2.setText(Teams.getTeamName(getContext(), teams.get(1).getTeam()));
        txvTeam3.setText(Teams.getTeamName(getContext(), teams.get(2).getTeam()));
        txvTeam4.setText(Teams.getTeamName(getContext(), teams.get(3).getTeam()));

        txvGroup1.setText(teams.get(0).getGroup());
        txvGroup2.setText(teams.get(1).getGroup());
        txvGroup3.setText(teams.get(2).getGroup());
        txvGroup4.setText(teams.get(3).getGroup());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewThirdPlaced.setLayoutManager(linearLayoutManager);

        positionOfCombinations = ThirdPlaced.getPositionOfCombinations();

        adapter = new CombinationAdapter(context, MainActivity.combinations, positionOfCombinations);
        recyclerViewThirdPlaced.setAdapter(adapter);

        setClick();
    }

    private void setClick() {
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void anhXa() {
        imgClose = findViewById(R.id.imgClose);

        imgTeam1 = findViewById(R.id.imgTeam1);
        imgTeam2 = findViewById(R.id.imgTeam2);
        imgTeam3 = findViewById(R.id.imgTeam3);
        imgTeam4 = findViewById(R.id.imgTeam4);

        txvTeam1 = findViewById(R.id.txvTeam1);
        txvTeam2 = findViewById(R.id.txvTeam2);
        txvTeam3 = findViewById(R.id.txvTeam3);
        txvTeam4 = findViewById(R.id.txvTeam4);

        txvGroup1 = findViewById(R.id.txvGroup1);
        txvGroup2 = findViewById(R.id.txvGroup2);
        txvGroup3 = findViewById(R.id.txvGroup3);
        txvGroup4 = findViewById(R.id.txvGroup4);

        recyclerViewThirdPlaced = findViewById(R.id.recyclerViewThirdPlaced);

        btnOK = findViewById(R.id.btnOK);
    }
}
