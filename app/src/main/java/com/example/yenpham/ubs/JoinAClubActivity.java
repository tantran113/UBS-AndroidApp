package com.example.yenpham.ubs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class JoinAClubActivity extends AppCompatActivity {

    ArrayAdapter<String> listViewAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_activity);

        ArrayList<String> a = new ArrayList<String>();
        a.add("Existing club 1");
        a.add("Existing club 2");
        a.add("Existing club 3");
        a.add("Existing club 4");
        a.add("Existing club 5");
        a.add("Existing club 6");
        a.add("Existing club 7");


        listViewAdapter = new ArrayAdapter<String>(JoinAClubActivity.this,R.layout.listview_custom,R.id.list_item,a);
        final ListView listViewJoinClub = (ListView) findViewById(R.id.sletectClubListView);
        listViewJoinClub.setAdapter(listViewAdapter);
        final Button joinButton = (Button) findViewById(R.id.joinButton);
        listViewJoinClub.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //String items = String.valueOf(parent.getItemAtPosition(position));
                view.setSelected(true);
               // Toast.makeText(JoinAClubActivity.this, listViewAdapter.getItem(position) ,Toast.LENGTH_SHORT).show();
                joinButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(JoinAClubActivity.this, listViewAdapter.getItem(position) ,Toast.LENGTH_SHORT).show();

                        //save into databse here

                    }
                });

            }
        } );



    }


/*
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.club_activity_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.myclub:
               // Toast.makeText(getApplicationContext(),item.toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(JoinAClubActivity.this,myClubActivity.class));
                break;
            case R.id.joinedclub:
              //  Toast.makeText(getApplicationContext(),item.toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(JoinAClubActivity.this,joinedClubActivity.class));
                break;
            case R.id.participate:
                //Toast.makeText(getApplicationContext(),item.toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(JoinAClubActivity.this,participate.class));
                break;

        }



        return super.onOptionsItemSelected(item);
    }
    */
}
