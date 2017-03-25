package com.example.yenpham.ubs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class eventTopicActivity extends AppCompatActivity {
    ArrayAdapter<String> listViewAdapter;
    EditText inputDialogBox;
    AlertDialog ad;
    ArrayList<String> a = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_topic_activity);


        a.add("existing event topic 1");
        a.add("existing event 2");
        a.add("existing event 3");

        listViewAdapter = new ArrayAdapter<String>(eventTopicActivity.this,android.R.layout.simple_list_item_1,a);
        final ListView eventTopicListview = (ListView) findViewById(R.id.eventTopicListview);
        eventTopicListview.setAdapter(listViewAdapter);

        eventTopicListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // startActivity(new Intent(joinedClubActivity.this,clubDetailActivity.class));
                Intent i = new Intent(eventTopicActivity.this, clubEventActivity.class);
                i.putExtra("eventItem",listViewAdapter.getItem(position));
                startActivity(i);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create Event");
        //  builder.setMessage("Enter clubDiscussionActivity subject");
        inputDialogBox = new EditText(this);
        builder.setView(inputDialogBox);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String input = inputDialogBox.getText().toString();

                //handle database

                a.add(input);
                listViewAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad = builder.create();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.event_topic_activity_menu, menu);
        // this.menu = menu;
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.createNewEventTopic:
                // Toast.makeText(getApplicationContext(),item.toString(), Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(clubDiscussionActivity.this,createPost.class));
                ad.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
