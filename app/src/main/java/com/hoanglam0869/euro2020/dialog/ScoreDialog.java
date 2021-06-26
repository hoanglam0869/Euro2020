package com.hoanglam0869.euro2020.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.database.TeamsDBHelper;
import com.hoanglam0869.euro2020.database.FixturesDBHelper;
import com.hoanglam0869.euro2020.model.Fixtures;
import com.hoanglam0869.euro2020.utils.Flags;
import com.hoanglam0869.euro2020.utils.Teams;

public class ScoreDialog extends Dialog {
    ImageView imgClose;
    TextView txvTeam1, txvTeam2;
    ImageView imgTeam1, imgTeam2;
    EditText edtScore1, edtScore2;
    Button btnOK;

    public String strScore1, strScore2;

    MainActivity mainActivity;

    public ScoreDialog(@NonNull Context context, Fixtures fixtures) {
        super(context);
        setContentView(R.layout.dialog_score);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);

        mainActivity = (MainActivity) context;
        anhXa();

        txvTeam1.setText(Teams.getTeamName(context, fixtures.getTeam1()));
        txvTeam2.setText(Teams.getTeamName(context, fixtures.getTeam2()));

        imgTeam1.setImageResource(Flags.getFlag(fixtures.getTeam1()));
        imgTeam2.setImageResource(Flags.getFlag(fixtures.getTeam2()));

        if (fixtures.getFtScore1() == -1 && fixtures.getFtScore2() == -1) {
            edtScore1.setText("");
            edtScore2.setText("");
        } else {
            edtScore1.setText(String.valueOf(fixtures.getFtScore1()));
            edtScore2.setText(String.valueOf(fixtures.getFtScore2()));
        }

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strScore1 = edtScore1.getText().toString().trim();
                strScore2 = edtScore2.getText().toString().trim();

                if (strScore1.length() != 0 && strScore2.length() != 0) {
                    int score1 = Integer.parseInt(strScore1);
                    int score2 = Integer.parseInt(strScore2);

                    fixtures.setFtScore1(score1);
                    fixtures.setFtScore2(score2);
                } else {
                    fixtures.setFtScore1(-1);
                    fixtures.setFtScore2(-1);
                }
                FixturesDBHelper.updateScore(mainActivity, fixtures);
                mainActivity.getGroups();
                FixturesDBHelper.setRoundOf16Teams(mainActivity);

                dismiss();
            }
        });
    }

    private void anhXa() {
        imgClose = findViewById(R.id.imgClose);

        txvTeam1 = findViewById(R.id.txvTeam1);
        txvTeam2 = findViewById(R.id.txvTeam2);

        imgTeam1 = findViewById(R.id.imgTeam1);
        imgTeam2 = findViewById(R.id.imgTeam2);

        edtScore1 = findViewById(R.id.edtScore1);
        edtScore2 = findViewById(R.id.edtScore2);

        btnOK = findViewById(R.id.btnOK);
    }

    @Override
    public void onBackPressed() {
        cancel();
    }
}
