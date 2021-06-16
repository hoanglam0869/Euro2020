package com.hoanglam0869.euro2020.adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.hoanglam0869.euro2020.utils.Teams;

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
        holder.txvTeam.setText(Teams.getTeamName(context, team.getTeam()));

        holder.txvPlayed.setText(String.valueOf(team.getWon() + team.getDrawn() + team.getLost()));
        holder.txvWon.setText(String.valueOf(team.getWon()));
        holder.txvDrawn.setText(String.valueOf(team.getDrawn()));
        holder.txvLost.setText(String.valueOf(team.getLost()));
        String content = team.getForward() + "-" + team.getAgainst();
        holder.txvForward_Against.setText(content);
        holder.txvPoints.setText(String.valueOf(team.getWon() * 3 + team.getDrawn()));

        if (position == 0) {
            holder.layoutTitle.setVisibility(View.VISIBLE);
        } else {
            holder.layoutTitle.setVisibility(View.GONE);
        }

        if (teamArrayList.size() == 4) {
            if (position < 2) {
                holder.layoutTeam.setBackgroundColor(Color.parseColor("#7fffd4"));
            }
        }
        if (teamArrayList.size() == 6) {
            if (position < 4) {
                holder.layoutTeam.setBackgroundColor(Color.parseColor("#7fffd4"));
            }
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
        TextView txvTeam, txvPlayed, txvWon, txvDrawn, txvLost, txvForward_Against, txvPoints;
        LinearLayout layoutTitle, layoutTeam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgTeam = itemView.findViewById(R.id.imgTeam);
            txvTeam = itemView.findViewById(R.id.txvTeam);

            txvPlayed = itemView.findViewById(R.id.txvPlayed);
            txvWon = itemView.findViewById(R.id.txvWon);
            txvDrawn = itemView.findViewById(R.id.txvDrawn);
            txvLost = itemView.findViewById(R.id.txvLost);
            txvForward_Against = itemView.findViewById(R.id.txvForward_Against);
            txvPoints = itemView.findViewById(R.id.txvPoints);

            layoutTitle = itemView.findViewById(R.id.layoutTitle);
            layoutTeam = itemView.findViewById(R.id.layoutTeam);
        }
    }
}