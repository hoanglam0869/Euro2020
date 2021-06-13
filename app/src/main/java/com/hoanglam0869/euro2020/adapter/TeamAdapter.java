package com.hoanglam0869.euro2020.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.model.Team;
import com.hoanglam0869.euro2020.utils.Flags;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    Context context;
    ArrayList<Team> teamArrayList;

    public TeamAdapter(Context context, ArrayList<Team> teamArrayList) {
        this.context = context;
        this.teamArrayList = teamArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_team, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Team team = teamArrayList.get(position);

        holder.imgTeam.setImageResource(Flags.getFlag(team.getTeam()));
        holder.txvTeam.setText(team.getTeam());

        holder.txvPlayed.setText(String.valueOf(team.getPlayed()));
        holder.txvWon.setText(String.valueOf(team.getWon()));
        holder.txvDrawn.setText(String.valueOf(team.getDrawn()));
        holder.txvLost.setText(String.valueOf(team.getLost()));
        holder.txvForward.setText(String.valueOf(team.getForward()));
        holder.txvAgainst.setText(String.valueOf(team.getAgainst()));
        holder.txvGoalDifference.setText(String.valueOf(team.getGoalDifference()));
        holder.txvPoints.setText(String.valueOf(team.getPoints()));

        if (position == 0) {
            holder.layoutTitle.setVisibility(View.VISIBLE);
        } else {
            holder.layoutTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (teamArrayList == null) {
            return 0;
        }
        return teamArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTeam;
        TextView txvTeam, txvPlayed, txvWon, txvDrawn, txvLost, txvForward, txvAgainst, txvGoalDifference, txvPoints;
        LinearLayout layoutTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgTeam = itemView.findViewById(R.id.imgTeam);
            txvTeam = itemView.findViewById(R.id.txvTeam);

            txvPlayed = itemView.findViewById(R.id.txvPlayed);
            txvWon = itemView.findViewById(R.id.txvWon);
            txvDrawn = itemView.findViewById(R.id.txvDrawn);
            txvLost = itemView.findViewById(R.id.txvLost);
            txvForward = itemView.findViewById(R.id.txvForward);
            txvAgainst = itemView.findViewById(R.id.txvAgainst);
            txvGoalDifference = itemView.findViewById(R.id.txvGoalDifference);
            txvPoints = itemView.findViewById(R.id.txvPoints);

            layoutTitle = itemView.findViewById(R.id.layoutTitle);
        }
    }
}
