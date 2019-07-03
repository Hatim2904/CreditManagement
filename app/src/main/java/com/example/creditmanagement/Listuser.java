package com.example.creditmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.creditmanagement.Data.DatabaseHandler;
import com.example.creditmanagement.Data.RecyclerViewAdapter;
import com.example.creditmanagement.Data.User;
import com.example.creditmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class Listuser extends AppCompatActivity
{
    private DatabaseHandler databaseHandler;
    private List<User> users;
    private RecyclerView rv;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listuser);
        databaseHandler = new DatabaseHandler (this);
        rv = findViewById(R.id.listRV);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager (this));
        addInfoToDB();
        users = new ArrayList<> ();

        users = databaseHandler.getAllUsers();


        adapter = new RecyclerViewAdapter(this, users);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addInfoToDB() {
        int count = databaseHandler.getUserCount();
        if(count == 0) {
            User user = new User();
            user.setName("Hatim");
            user.setEmail("hatim@gmail.com");
            user.setCurrCredit(2000);
            databaseHandler.addUser(user);
            User user1 = new User();
            user1.setName("Anas");
            user1.setEmail("anas@gmail.com");
            user1.setCurrCredit(2000);
            databaseHandler.addUser(user1);
            User user2 = new User();
            user2.setName("Rehan");
            user2.setEmail("rehan@gmail.com");
            user2.setCurrCredit(2000);
            databaseHandler.addUser(user2);
            User user3 = new User();
            user3.setName("Tausif");
            user3.setEmail("tausif@gmail.com");
            user3.setCurrCredit(2000);
            databaseHandler.addUser(user3);
            User user4 = new User();
            user4.setName("Mubin");
            user4.setEmail("modi@gmail.com");
            user4.setCurrCredit(2000);
            databaseHandler.addUser(user4);
            User user5 = new User();
            user5.setName("Abbas");
            user5.setEmail("abbas@gmail.com");
            user5.setCurrCredit(2000);
            databaseHandler.addUser(user5);
            User user6 = new User();
            user6.setName("Rahul");
            user6.setEmail("rahul@gmail.com");
            user6.setCurrCredit(2000);
            databaseHandler.addUser(user6);
            User user7 = new User();
            user7.setName("Harry");
            user7.setEmail("potter@hogwards.com");
            user7.setCurrCredit(2000);
            databaseHandler.addUser(user7);
            User user8 = new User();
            user8.setName("Walter");
            user8.setEmail("White@meth.com");
            user8.setCurrCredit(2000);
            databaseHandler.addUser(user8);
            User user9 = new User();
            user9.setName("Jill");
            user9.setEmail("jack@well.com");
            user9.setCurrCredit(2000);
            databaseHandler.addUser(user9);
        }
    }
    public void onBackPressed(){
        startActivity(new Intent(Listuser.this, HomeActivity.class));
    }
}
