package com.example.meili;
//import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SummaryActivity extends AppCompatActivity {

    DBHelper db;
    TextView product_name,qty,size,colour,price,total;
    UserSession usersession;
    int prId;
    float total_price;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(SummaryActivity.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(SummaryActivity.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(SummaryActivity.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(SummaryActivity.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);


        //db = new DBHelper(this);

        product_name = findViewById(R.id.textView20);
        qty = findViewById(R.id.textView21);
        size = findViewById(R.id.textView22);
        colour = findViewById(R.id.textView23);
        price = findViewById(R.id.textView27);
        total = findViewById(R.id.txtTotal);

        Intent intent = getIntent();
/*
        prId = Integer.parseInt(intent.getStringExtra("PRODUCT_ID"));

        product_name.setText(intent.getStringExtra("PRODUCT_NAME"));
        qty.setText(intent.getStringExtra("QTY"));
        size.setText(intent.getStringExtra("SIZE"));
        colour.setText(intent.getStringExtra("COLOUR"));
        price.setText(intent.getStringExtra("PRICE"));

        int qtyInt = Integer.parseInt(intent.getStringExtra("QTY"));
        float priceFloat = Float.parseFloat(intent.getStringExtra("PRICE"));

        total_price = qtyInt * priceFloat;
        total.setText(Float.toString(total_price));
        */
    }

    public void onConfirmOrderCLick(View view){

        prId = 1;
        total_price = (float)77.9;


        final UserSession usersession = (UserSession)getApplicationContext();
        //set values to session
        usersession.setProductId(prId);
        usersession.setTotal(total_price);
        //usersession.setProductId(prId);

        //proceed to payment
        Intent intent = new Intent(SummaryActivity.this,pay.class);
        startActivity(intent);
    }


}
