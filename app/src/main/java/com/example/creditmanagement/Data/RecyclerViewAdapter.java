package com.example.creditmanagement.Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.creditmanagement.R;
import com.example.creditmanagement.usertransfer;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<User> userList;
    private Context context;

    public RecyclerViewAdapter(Context ctx, List<User> users) {
        this.context = ctx;
        this.userList = users;
    }
    @NonNull
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate( R.layout.list_row, viewGroup, false);
        return new ViewHolder(view, context);
    }

    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int i) {
        User user = userList.get(i);
        viewHolder.userName.setText(user.getName());
        viewHolder.userEmail.setText(user.getEmail());
        viewHolder.currCredit.setText(Integer.toString(user.getCurrCredit()));
    }


    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, userEmail, currCredit;
        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;
            userName = itemView.findViewById(R.id.user_name);
            userEmail = itemView.findViewById(R.id.user_email);
            currCredit = itemView.findViewById(R.id.user_credits);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    User user = userList.get(position);
                    Intent intent = new Intent(context, usertransfer.class);
                    intent.putExtra("id", user.getId());
                    intent.putExtra("name", user.getName());
                    intent.putExtra("email", user.getEmail());
                    intent.putExtra("currCredit", user.getCurrCredit());
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            });
        }
    }
}
