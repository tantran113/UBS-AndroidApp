package com.example.yenpham.ubs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class myClubActivity extends AppCompatActivity {

    ArrayAdapter<String> listViewAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_club_activity);

        ArrayList<String> a = new ArrayList<String>();
        a.add("my club 1");
        a.add("my club 2");
        a.add("my club 3");
        a.add("my club 4");
        a.add("my club 5");
        a.add("my club 6");

        listViewAdapter = new ArrayAdapter<String>(myClubActivity.this,R.layout.listview_custom,R.id.list_item,a);
        final ListView listViewMyclub = (ListView) findViewById(R.id.sletectMyClubListView);
        listViewMyclub.setAdapter(listViewAdapter);
        listViewMyclub.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //String items = String.valueOf(parent.getItemAtPosition(position));
                view.setSelected(true);
                // Toast.makeText(JoinAClubActivity.this, listViewAdapter.getItem(position) ,Toast.LENGTH_SHORT).show();

                Intent i = new Intent(myClubActivity.this, clubDiscussionActivity.class);
                i.putExtra("item", listViewAdapter.getItem(position));
                startActivity(i);

            }
        } );
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_club_activity_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.createclub:
                // Toast.makeText(getApplicationContext(),item.toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(myClubActivity.this,createClubActivity.class));
                break;


        }



        return super.onOptionsItemSelected(item);
    }
}