package com.example.yenpham.ubs;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class clubEventActivity extends AppCompatActivity {
    ArrayAdapter<String> listViewAdapter;
    private Menu menu;
    EditText inputDialogBox;

    AlertDialog ad;
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_event_activity);

        final TextView discussionPost = (TextView)findViewById(R.id.eventPost);
        Bundle i = getIntent().getExtras();
        // String item = i.getStringExtra("item");
        // setTitle(item);

        String topic = i.getString("eventItem");
        discussionPost.setText(topic);
        //handle database



        Button commentButton = (Button)findViewById(R.id.commentEventButton);
        final EditText commentInput = (EditText)findViewById(R.id.commentEventInput);


        //hande database
        final ArrayList<String> a = new ArrayList<String>();
        a.add("event comment 1");
        a.add("event comment 2");
        a.add("event comment 3");


        listViewAdapter = new ArrayAdapter<String>(clubEventActivity.this,R.layout.listview_custom,R.id.list_item,a);
        final ListView discussionListView = (ListView) findViewById(R.id.eventListView);
        discussionListView.setAdapter(listViewAdapter);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentInput.getText().toString();
                a.add(comment);
                listViewAdapter.notifyDataSetChanged();
                commentInput.setText("");

                //Handle database

            }
        });

        /*
        //Bundle bd = new Bundle();
       // String text= i.getStringExtra("code");
        //discussionPost.setText(text);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create Discussion");
      //  builder.setMessage("Enter clubDiscussionActivity subject");
        inputDialogBox = new EditText(this);
        builder.setView(inputDialogBox);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String input = inputDialogBox.getText().toString();

                //handle database

                discussionPost.setText(input);
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
        inflater.inflate(R.menu.discussion_topic_activity_menu, menu);
        this.menu = menu;
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.createPost:
                // Toast.makeText(getApplicationContext(),item.toString(), Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(clubDiscussionActivity.this,createPost.class));
                ad.show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
   */
    }}


