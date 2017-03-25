package com.example.yenpham.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class clubDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_detail_activity);

        Button goToDiscussionButton = (Button)findViewById(R.id.goToDiscussionButton);
        Button goToEvents  = (Button)findViewById(R.id.goToEventsButton);

        goToDiscussionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(clubDetailActivity.this, discussionTopicActivity.class));
            }
        });

        goToEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(clubDetailActivity.this, "Go to event", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(clubDetailActivity.this, eventTopicActivity.class));
            }
        });
    }
}
