package com.example.creditmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.creditmanagement.Data.DatabaseHandler;
import com.example.creditmanagement.Data.User;

import java.util.ArrayList;
import java.util.List;

public class usertransfer extends AppCompatActivity
{
    private  Bundle extras;
    private int userId;
    private TextView nameText, emailText, creditText;
    private Button transferButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usertransfer);
        nameText = findViewById(R.id.details_user_name);
        emailText = findViewById(R.id.details_user_email);
        creditText = findViewById(R.id.details_user_credits);
        transferButton = findViewById(R.id.transferButton);
        extras = getIntent().getExtras();

        if(extras != null) {
            nameText.setText(extras.getString("name"));
            emailText.setText(extras.getString("email"));
            creditText.setText(Integer.toString(extras.getInt("currCredit")));
            userId = extras.getInt("id");
        }

        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(DetailsActivity.this, String.valueOf(userId), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(usertransfer.this, TransferCredit.class);
                if(extras != null) {
                    intent.putExtra("tr_id", extras.getInt("id"));
                    intent.putExtra("tr_name", extras.getString("name"));
                    intent.putExtra("tr_email", extras.getString("email"));
                    intent.putExtra("tr_currCredit", extras.getInt("currCredit"));
                }
                startActivity(intent);
                finish();
            }
        });
    }
}
