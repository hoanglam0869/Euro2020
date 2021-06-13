package com.hoanglam0869.euro2020.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

        holder.txvGroup.setText(group.getGroup());
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

            txvGroup = itemView.findViewById(R.id.txvGroupTable);
            recyclerViewGroup = itemView.findViewById(R.id.recyclerViewGroup);
        }
    }
}
