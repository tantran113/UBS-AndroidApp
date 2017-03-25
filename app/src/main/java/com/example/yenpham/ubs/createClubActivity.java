package com.example.yenpham.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class createClubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_club_activity);
        Button createClubButton = (Button) findViewById(R.id.createClubButton);
        final EditText createClubInput = (EditText)findViewById(R.id.createClubInput);
        createClubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (createClubInput.getText().toString().equals(""))
                    Toast.makeText(createClubActivity.this, "Please enter a valid club name" ,Toast.LENGTH_SHORT).show();

                // handle database if JoinAClubActivity already exist


                else {
                    Toast.makeText(createClubActivity.this, createClubInput.getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(createClubActivity.this, joinedClubActivity.class));
                }
                // Toast.makeText(JoinAClubActivity.this, listViewAdapter.getItem(position) ,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
