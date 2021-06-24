package com.hoanglam0869.euro2020.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.model.Team;
import com.hoanglam0869.euro2020.model.Third;
import com.hoanglam0869.euro2020.utils.Flags;
import com.hoanglam0869.euro2020.utils.Teams;

import java.util.ArrayList;

public class CombinationAdapter extends RecyclerView.Adapter<CombinationAdapter.ViewHolder> {

    Context context;
    ArrayList<Third> thirdArrayList;
    int positionOfCombinations;
    ArrayList<Team> teams;

    public CombinationAdapter(Context context, ArrayList<Third> thirdArrayList, int positionOfCombinations, ArrayList<Team> teams) {
        this.context = context;
        this.thirdArrayList = thirdArrayList;
        this.positionOfCombinations = positionOfCombinations;
        this.teams = teams;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_combination, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CombinationAdapter.ViewHolder holder, int position) {
        Third third = thirdArrayList.get(position);

        if (position == positionOfCombinations) {
            holder.imgFirstB.setImageResource(Flags.getFlag(teams.get(0).getTeam()));
            holder.imgFirstC.setImageResource(Flags.getFlag(teams.get(1).getTeam()));
            holder.imgFirstE.setImageResource(Flags.getFlag(teams.get(2).getTeam()));
            holder.imgFirstF.setImageResource(Flags.getFlag(teams.get(3).getTeam()));

            holder.txvFirstB.setTextColor(Color.WHITE);
            holder.txvFirstC.setTextColor(Color.WHITE);
            holder.txvFirstE.setTextColor(Color.WHITE);
            holder.txvFirstF.setTextColor(Color.WHITE);
            holder.layoutCombination.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }

        String content = "3" + third.getFirstB();
        holder.txvFirstB.setText(content);

        content = "3" + third.getFirstC();
        holder.txvFirstC.setText(content);

        content = "3" + third.getFirstE();
        holder.txvFirstE.setText(content);

        content = "3" + third.getFirstF();
        holder.txvFirstF.setText(content);
    }

    @Override
    public int getItemCount() {
        if (thirdArrayList == null) {
            return 0;
        }
        return thirdArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutCombination;
        ImageView imgFirstB, imgFirstC, imgFirstE, imgFirstF;
        TextView txvFirstB, txvFirstC, txvFirstE, txvFirstF;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutCombination = itemView.findViewById(R.id.layoutCombination);

            imgFirstB = itemView.findViewById(R.id.imgFirstB);
            imgFirstC = itemView.findViewById(R.id.imgFirstC);
            imgFirstE = itemView.findViewById(R.id.imgFirstE);
            imgFirstF = itemView.findViewById(R.id.imgFirstF);

            txvFirstB = itemView.findViewById(R.id.txvFirstB);
            txvFirstC = itemView.findViewById(R.id.txvFirstC);
            txvFirstE = itemView.findViewById(R.id.txvFirstE);
            txvFirstF = itemView.findViewById(R.id.txvFirstF);

            imgFirstB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() == positionOfCombinations) {
                        Toast.makeText(context, Teams.getTeamName(context, teams.get(0).getTeam()), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            imgFirstC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() == positionOfCombinations) {
                        Toast.makeText(context, Teams.getTeamName(context, teams.get(1).getTeam()), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            imgFirstE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() == positionOfCombinations) {
                        Toast.makeText(context, Teams.getTeamName(context, teams.get(2).getTeam()), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            imgFirstF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() == positionOfCombinations) {
                        Toast.makeText(context, Teams.getTeamName(context, teams.get(3).getTeam()), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
