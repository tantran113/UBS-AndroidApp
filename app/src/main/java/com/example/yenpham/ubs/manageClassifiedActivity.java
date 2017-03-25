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
import android.widget.Toast;

import java.util.ArrayList;

public class manageClassifiedActivity extends AppCompatActivity {


    ArrayAdapter<String> listViewAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_classified_activity);

        ArrayList<String> a = new ArrayList<String>();
        a.add("my classified 1");
        a.add("my classified 2");
        a.add("my classified 3");
        a.add("my classified 4");
        a.add("my classified 5");
        a.add("Iphone 10 ba dao");

        listViewAdapter = new ArrayAdapter<String>(manageClassifiedActivity.this,android.R.layout.simple_list_item_1,a);
        final ListView listViewAds = (ListView) findViewById(R.id.manageClassifiedListView);
        listViewAds.setAdapter(listViewAdapter);

        listViewAds.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(manageClassifiedActivity.this, listViewAdapter.getItem(position).toString(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manage_classified_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.createClassified:
                //Toast.makeText(getApplicationContext(),item.toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(manageClassifiedActivity.this,categoryActivity.class));
                break;

        }



        return super.onOptionsItemSelected(item);
    }
}
