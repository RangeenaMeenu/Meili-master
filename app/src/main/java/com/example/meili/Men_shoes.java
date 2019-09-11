package com.example.meili;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Men_shoes extends AppCompatActivity {
    //public static final String ItemName = "Name";
    //public static final String ItemImage = "Image";

    private TextView mTextMessage;
    ListView mlist;
    String[] itemNames = {"Santimon Men's Shoes","White Men Casual Shoes","Genuine leather Comfortable Men Casual Shoes",
            "Italian Oxford Shoes","Men Vulcanized Shoes","Hollow Men's Shoes",
            "Penny Slip-On Leather Loafer","Men Casual Shoes Summer"
            };


    int[] itemImage = {R.drawable.santimon_mens_shoes,
                        R.drawable.white_men_casual_shoes,
                        R.drawable.leather_shoes,
                        R.drawable.italian_oxford_shoes,
                        R.drawable.men_vulcanized_shoes,
                        R.drawable.hollow_mens_shoes,
                        R.drawable.penny_slip_on_leather_loafer,
                        R.drawable.men_casual_shoes_summer};

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(Men_shoes.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(Men_shoes.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(Men_shoes.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(Men_shoes.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_shoes);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mlist = (ListView) findViewById(R.id.list_view);
        list_adapter listAdapter = new list_adapter(Men_shoes.this, itemNames, itemImage);
        mlist.setAdapter(listAdapter);
        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(Men_shoes.this,Item_page.class);
            //intent.putExtra("ItemName",itemNames[i]);
            //intent.putExtra("ItemImage",itemImage[i]);
            startActivity(intent);
            }
        });
    }

    public void onFilterClick(View view){
        Intent intent = new Intent(Men_shoes.this,Filter_item_page.class);
        startActivity(intent);
    }

}
