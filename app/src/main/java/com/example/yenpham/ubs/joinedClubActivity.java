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

public class joinedClubActivity extends AppCompatActivity {
    ArrayAdapter<String> listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joined_club_activity);


        ArrayList<String> a = new ArrayList<String>();
        a.add("Joined CLub 1");
        a.add("Joined CLub 2");
        a.add("Joined CLub 3");
        a.add("Joined CLub 4");

        listViewAdapter = new ArrayAdapter<String>(joinedClubActivity.this,android.R.layout.simple_list_item_1,a);
        final ListView listViewJoinedClub = (ListView) findViewById(R.id.joinedClubListview);
        listViewJoinedClub.setAdapter(listViewAdapter);

      listViewJoinedClub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              startActivity(new Intent(joinedClubActivity.this,clubDetailActivity.class));
          }
      });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.joined_club_activity_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.joinAnExistingClub:
                // Toast.makeText(getApplicationContext(),item.toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(joinedClubActivity.this, JoinAClubActivity.class));
                break;
            case R.id.createANewClub:
                //  Toast.makeText(getApplicationContext(),item.toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(joinedClubActivity.this,createClubActivity.class));
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}
