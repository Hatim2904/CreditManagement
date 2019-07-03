package com.example.creditmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.creditmanagement.Data.DatabaseHandler;
import com.example.creditmanagement.Data.Transfer;
import com.example.creditmanagement.Data.TransferRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllTransfers extends AppCompatActivity {

    private List<Transfer> transfers;
    private DatabaseHandler db;
    private RecyclerView recyclerView;
    private TransferRecyclerViewAdapter adapter;
    private TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transfers);
        db = new DatabaseHandler(this);
        recyclerView = findViewById(R.id.transferRV);
        emptyText = findViewById(R.id.emptyList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        transfers = new ArrayList<>();
        transfers = db.getAllTransfers();
        if(transfers.size() == 0) {
            emptyText.setVisibility(View.VISIBLE);
        }

        else {
            emptyText.setVisibility(View.INVISIBLE);
        }
        adapter = new TransferRecyclerViewAdapter(this, transfers);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    public void onBackPressed(){
        startActivity(new Intent (AllTransfers.this, HomeActivity.class));
    }
}
