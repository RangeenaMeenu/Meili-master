package com.example.meili;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Shopping_cart extends AppCompatActivity {
    private TextView mTextMessage;
    Button button;
    ListView listView;

    String[] name = {"Nike Air Max 720 SATURN", "Girls Bunny shoes", "Men's Leather shoes"};
    String[] price = {"Rs 34000", "Rs 4300", "Rs 6700"};
    int[] shoe = {R.drawable.nike,R.drawable.bunny,R.drawable.leath};

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(Shopping_cart.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(Shopping_cart.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(Shopping_cart.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(Shopping_cart.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        button = (Button) findViewById(R.id.btn2);
        listView = (ListView) findViewById(R.id.list1);
        myAdapter myadapter = new myAdapter(Shopping_cart.this, name,price, shoe);
        listView.setAdapter(myadapter);

    }

    public void onContinueShoppingClick(View view){
        Intent profile = new Intent(Shopping_cart.this,Men_shoes.class);
        startActivity(profile);
    }

    public void onProceedClick(View view){
        Intent profile = new Intent(Shopping_cart.this,pay.class);
        startActivity(profile);
    }

}
