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
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.list;

//import android.support.v7.widget.SearchView;

public class classifiedActivity extends AppCompatActivity {

    // ListAdapter listViewAdapter;
    ArrayAdapter<String> listViewAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classified_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //handle database
        ArrayList<String> a = new ArrayList<String>();
        a.add("Iphone 5s for sale");
        a.add("Samsung Galaxy for sale");
        a.add("Housing for rent");
        a.add("room for rent");
        a.add("Intro to software book for sale");
        a.add("Intro to database for sale");
		   /* Bundle bd = getIntent().getExtras();
			if(bd!= null){

		a.add(bd.getString("nameInputAds"));
			}
	  */
        // String [] b = a.toArray(new String[a.size()]);

        // String[] items = {"quanafaffafaaffa","aoafafafffajfjkajfjahfaffafaf", "quan xifaflafkafjhkfjfaafaf","ao zuafakfnajkfhakbfkjfa"};



        //   listViewAdapter = new customAdapter(this, b);
        listViewAdapter = new ArrayAdapter<String>(classifiedActivity.this,android.R.layout.simple_list_item_1,a);
        final ListView listViewAds = (ListView) findViewById(R.id.listViewAds);
        listViewAds.setAdapter(listViewAdapter);

        listViewAds.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String items = String.valueOf(parent.getItemAtPosition(position));

                Toast.makeText(classifiedActivity.this, listViewAdapter.getItem(position) ,Toast.LENGTH_LONG).show();
                startActivity(new Intent(classifiedActivity.this, displayDetailClassifiedActivity.class));
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater searchInflater = getMenuInflater();
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.search_classified_menu,menu);
        MenuItem item = menu.findItem(R.id.menusearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listViewAdapter.getFilter().filter(newText);
                listViewAdapter.notifyDataSetChanged();


                return false;
            }
        });


        inflater.inflate(R.menu.classified_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.manageAds:
                //Toast.makeText(getApplicationContext(),item.toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(classifiedActivity.this,manageClassifiedActivity.class));
                break;

        }



        return super.onOptionsItemSelected(item);
    }
}
