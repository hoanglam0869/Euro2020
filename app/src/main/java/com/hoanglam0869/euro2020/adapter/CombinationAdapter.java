package com.hoanglam0869.euro2020.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoanglam0869.euro2020.R;
import com.hoanglam0869.euro2020.model.Third;

import java.util.ArrayList;

public class CombinationAdapter extends RecyclerView.Adapter<CombinationAdapter.ViewHolder> {

    Context context;
    ArrayList<Third> thirdArrayList;
    int positionOfCombinations;

    public CombinationAdapter(Context context, ArrayList<Third> thirdArrayList, int positionOfCombinations) {
        this.context = context;
        this.thirdArrayList = thirdArrayList;
        this.positionOfCombinations = positionOfCombinations;
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutCombination;
        TextView txvFirstB, txvFirstC, txvFirstE, txvFirstF;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutCombination = itemView.findViewById(R.id.layoutCombination);

            txvFirstB = itemView.findViewById(R.id.txvFirstB);
            txvFirstC = itemView.findViewById(R.id.txvFirstC);
            txvFirstE = itemView.findViewById(R.id.txvFirstE);
            txvFirstF = itemView.findViewById(R.id.txvFirstF);
        }
    }
}
