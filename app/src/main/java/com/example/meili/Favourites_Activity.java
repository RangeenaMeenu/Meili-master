package com.example.meili;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class Favourites_Activity extends AppCompatActivity {
    private TextView mTextMessage;

    ListView mlist;

    String[] itemNames = {"Men Vulcanized Shoes","Air Mesh Women Sneakers","Chunky WOmen's Sneakers","Bunny Kids Shoes"};


    int[] itemImage = {R.drawable.men_vulcanized_shoes,
            R.drawable.air_mesh_women_sneakers,
            R.drawable.chunky_sneakers_women,
            R.drawable.bunny_kids_shoes
            };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(Favourites_Activity.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(Favourites_Activity.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(Favourites_Activity.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(Favourites_Activity.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        mlist = (ListView) findViewById(R.id.list_view);
        Fav_listView_adapter listAdapter = new Fav_listView_adapter(Favourites_Activity.this, itemNames, itemImage);
        mlist.setAdapter(listAdapter);
    }

    public void onItemClick(View view){
        Intent intent = new Intent(Favourites_Activity.this,Item_page.class);
        startActivity(intent);
    }

}
