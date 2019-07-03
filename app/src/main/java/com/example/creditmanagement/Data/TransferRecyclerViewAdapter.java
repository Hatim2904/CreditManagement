package com.example.creditmanagement.Data;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.creditmanagement.R;

import java.util.List;

public class TransferRecyclerViewAdapter extends RecyclerView.Adapter<TransferRecyclerViewAdapter.ViewHolder> {
    private List<Transfer> transfersList;
    private Context context;

    public TransferRecyclerViewAdapter(Context ctx, List<Transfer> list) {
        this.context = ctx;
        this.transfersList = list;
    }
    @NonNull
    @Override
    public TransferRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate( R.layout.transfer_list_row, viewGroup, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        Transfer transfer = transfersList.get(i);
        DatabaseHandler db = new DatabaseHandler(context);
        User from = db.getUser(transfer.getFromId());
        User to = db.getUser(transfer.getToId());
        viewHolder.fromUserName.setText(from.toString());
        viewHolder.toUserName.setText(to.toString());
        viewHolder.creditsTransferred.setText(String.valueOf(transfer.getCredit()));
    }

    @Override
    public int getItemCount() {
        return transfersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fromUserName, toUserName, creditsTransferred;
        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;
            fromUserName = itemView.findViewById(R.id.fromUserText);
            toUserName = itemView.findViewById(R.id.toUserText);
            creditsTransferred = itemView.findViewById(R.id.transferred_credits);
        }
    }
}

