package com.hoanglam0869.euro2020.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.model.Group;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    Context context;
    ArrayList<Group> groupArrayList;

    public GroupAdapter(Context context, ArrayList<Group> groupArrayList) {
        this.context = context;
        this.groupArrayList = groupArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Group group = groupArrayList.get(position);

        if (group.getGroup().equals("Third-placed")) {
            holder.txvGroup.setText(context.getResources().getString(R.string.ranking_of_third_placed_teams));
        } else {
            String content = context.getResources().getString(R.string.group) + " " + group.getGroup();
            holder.txvGroup.setText(content);
        }

        TeamAdapter adapter = new TeamAdapter(context, group.getTeamArrayList());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        holder.recyclerViewGroup.setLayoutManager(linearLayoutManager);

        holder.recyclerViewGroup.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        if (groupArrayList == null) {
            return 0;
        }
        return groupArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvGroup;
        RecyclerView recyclerViewGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txvGroup = itemView.findViewById(R.id.txvTable);
            recyclerViewGroup = itemView.findViewById(R.id.recyclerViewGroup);
        }
    }
}