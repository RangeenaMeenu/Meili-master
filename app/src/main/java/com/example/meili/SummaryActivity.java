package com.example.meili;
//import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SummaryActivity extends AppCompatActivity {

    DBHelper db;
    TextView product_name,qty,size,colour,price,total;
    //UserSession usersession;
    int prId;
    float total_price;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;



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

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        //db = new DBHelper(this);

        product_name = findViewById(R.id.textView20);
        qty = findViewById(R.id.textView23);
        size = findViewById(R.id.textView24);
        colour = findViewById(R.id.textView25);
        price = findViewById(R.id.textView27);
        total = findViewById(R.id.txtTotal);

        Intent intent = getIntent();

        //UserSession userSession = UserSession.getInstance(SummaryActivity.this);

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
    }

    public void onConfirmOrderCLick(View view){

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        UserSession userSession = UserSession.getInstance();

        //set values to session
        userSession.setUserId(Integer.parseInt(sharedPreferences.getString("UserId","")));
        userSession.setProductId(prId);
        userSession.setTotal(total_price);

        //proceed to payment
        Intent intent = new Intent(SummaryActivity.this,pay.class);
        startActivity(intent);
    }


}
