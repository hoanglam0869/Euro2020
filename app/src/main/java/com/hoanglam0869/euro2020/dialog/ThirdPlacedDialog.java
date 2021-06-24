package com.hoanglam0869.euro2020.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    ImageView imgClose, imgFirstB, imgFirstC, imgFirstE, imgFirstF;
    RecyclerView recyclerViewThirdPlaced;

    CombinationAdapter adapter;
    int positionOfCombinations;

    public ThirdPlacedDialog(@NonNull Context context, ArrayList<Team> teams) {
        super(context);
        setContentView(R.layout.dialog_third_placed);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);

        anhXa();

        imgFirstB.setImageResource(Flags.getFlag(MainActivity.groupB.get(0).getTeam()));
        imgFirstC.setImageResource(Flags.getFlag(MainActivity.groupC.get(0).getTeam()));
        imgFirstE.setImageResource(Flags.getFlag(MainActivity.groupE.get(0).getTeam()));
        imgFirstF.setImageResource(Flags.getFlag(MainActivity.groupF.get(0).getTeam()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewThirdPlaced.setLayoutManager(linearLayoutManager);

        positionOfCombinations = ThirdPlaced.getPositionOfCombinations();

        adapter = new CombinationAdapter(context, MainActivity.combinations, positionOfCombinations, teams);
        recyclerViewThirdPlaced.setAdapter(adapter);

        setClick(context);
    }

    private void setClick(Context context) {
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        imgFirstB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Teams.getTeamName(context, MainActivity.groupB.get(0).getTeam()), Toast.LENGTH_SHORT).show();
            }
        });
        imgFirstC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Teams.getTeamName(context, MainActivity.groupC.get(0).getTeam()), Toast.LENGTH_SHORT).show();
            }
        });
        imgFirstE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Teams.getTeamName(context, MainActivity.groupE.get(0).getTeam()), Toast.LENGTH_SHORT).show();
            }
        });
        imgFirstF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Teams.getTeamName(context, MainActivity.groupF.get(0).getTeam()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void anhXa() {
        imgClose = findViewById(R.id.imgClose);

        imgFirstB = findViewById(R.id.imgFirstB);
        imgFirstC = findViewById(R.id.imgFirstC);
        imgFirstE = findViewById(R.id.imgFirstE);
        imgFirstF = findViewById(R.id.imgFirstF);

        recyclerViewThirdPlaced = findViewById(R.id.recyclerViewThirdPlaced);
    }
}
