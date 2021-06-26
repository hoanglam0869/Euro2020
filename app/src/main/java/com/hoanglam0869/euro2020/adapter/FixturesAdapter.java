package com.hoanglam0869.euro2020.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam0869.euro2020.MainActivity;
import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.database.FixturesDBHelper;
import com.hoanglam0869.euro2020.dialog.ScoreDialog;
import com.hoanglam0869.euro2020.model.Fixtures;
import com.hoanglam0869.euro2020.utils.Flags;
import com.hoanglam0869.euro2020.utils.Teams;
import com.hoanglam0869.euro2020.utils.TimeZone;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FixturesAdapter extends RecyclerView.Adapter<FixturesAdapter.ViewHolder> {

    Context context;
    ArrayList<Fixtures> fixturesArrayList;

    SimpleDateFormat formatOnlyDate, formatDate, formatTime;

    public FixturesAdapter(Context context, ArrayList<Fixtures> fixturesArrayList) {
        this.context = context;
        this.fixturesArrayList = fixturesArrayList;

        formatOnlyDate = new SimpleDateFormat("dd", Locale.getDefault());
        formatDate = new SimpleDateFormat("EEEEE dd MMM yyyy", Locale.getDefault());
        formatTime = new SimpleDateFormat("HH:mm", Locale.getDefault());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_fixtures, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fixtures fixtures = fixturesArrayList.get(position);
        int id = fixtures.getId();

        holder.txvRound.setVisibility(View.VISIBLE);
        switch (id) {
            case 1:
                holder.txvRound.setText(R.string.group_stage_matchday_1);
                break;
            case 13:
                holder.txvRound.setText(R.string.group_stage_matchday_2);
                break;
            case 25:
                holder.txvRound.setText(R.string.group_stage_matchday_3);
                break;
            case 37:
                holder.txvRound.setText(R.string.round_of_16);
                break;
            case 45:
                holder.txvRound.setText(R.string.quarter_finals);
                break;
            case 49:
                holder.txvRound.setText(R.string.semi_finals);
                break;
            case 51:
                holder.txvRound.setText(R.string.final_match);
                break;
            default:
                holder.txvRound.setVisibility(View.GONE);
                break;
        }

        long dateTime = Long.parseLong(fixtures.getDateTime());
        dateTime += TimeZone.numberTimeZone[TimeZone.positionTimeZone];
        if (id % 12 == 1 || id == 45 || id == 51) {
            holder.txvDate.setVisibility(View.VISIBLE);
            holder.txvDate.setText(formatDate.format(dateTime));
        } else {
            long prevDateTime = Long.parseLong(fixturesArrayList.get(position - 1).getDateTime());
            prevDateTime += TimeZone.numberTimeZone[TimeZone.positionTimeZone];
            int date = Integer.parseInt(formatOnlyDate.format(dateTime));
            int prevDate = Integer.parseInt(formatOnlyDate.format(prevDateTime));

            if (date > prevDate) {
                holder.txvDate.setVisibility(View.VISIBLE);
                holder.txvDate.setText(formatDate.format(dateTime));
            } else {
                holder.txvDate.setVisibility(View.GONE);
            }
        }

        holder.txvMatch.setText(String.valueOf(fixtures.getId()));

        if (id < 37) {
            holder.txvGroup.setVisibility(View.VISIBLE);
            holder.txvGroup.setText(fixtures.getGroup());
        } else {
            holder.txvGroup.setVisibility(View.GONE);
        }

        holder.txvStadium.setText(fixtures.getStadium());
        holder.txvTime.setText(formatTime.format(dateTime));
        holder.txvTeam1.setText(Teams.getTeamName(context, fixtures.getTeam1()));
        holder.txvTeam2.setText(Teams.getTeamName(context, fixtures.getTeam2()));

        if (fixtures.getFtScore1() != -1 && fixtures.getFtScore2() != -1) {
            String score = fixtures.getFtScore1() + "-" + fixtures.getFtScore2();
            holder.txvScore.setText(score);
        } else {
            holder.txvScore.setText("-");
        }

        holder.imgTeam1.setImageResource(Flags.getFlag(fixtures.getTeam1()));
        holder.imgTeam2.setImageResource(Flags.getFlag(fixtures.getTeam2()));
    }

    @Override
    public int getItemCount() {
        if (fixturesArrayList == null) {
            return 0;
        }
        return fixturesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvRound, txvDate, txvMatch, txvGroup, txvStadium, txvTime, txvTeam1, txvTeam2;
        ImageView imgTeam1, imgTeam2;
        TextView txvScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txvRound = itemView.findViewById(R.id.txvRound);
            txvDate = itemView.findViewById(R.id.txvDate);
            txvMatch = itemView.findViewById(R.id.txvMatch);
            txvGroup = itemView.findViewById(R.id.txvGroup);
            txvStadium = itemView.findViewById(R.id.txvStadium);
            txvTime = itemView.findViewById(R.id.txvTime);
            txvTeam1 = itemView.findViewById(R.id.txvTeam1);
            txvTeam2 = itemView.findViewById(R.id.txvTeam2);

            imgTeam1 = itemView.findViewById(R.id.imgTeam1);
            imgTeam2 = itemView.findViewById(R.id.imgTeam2);

            txvScore = itemView.findViewById(R.id.txvScore);

            txvScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScoreDialog scoreDialog = new ScoreDialog(context, fixturesArrayList.get(getAdapterPosition()));
                    scoreDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            fixturesArrayList = FixturesDBHelper.getFixtures((MainActivity) context);
                            notifyDataSetChanged();
                        }
                    });
                    scoreDialog.show();
                    scoreDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                }
            });
        }
    }
}
