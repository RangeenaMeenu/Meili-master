package com.example.meili;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Item_page extends AppCompatActivity {





    private TextView mTextMessage;
    Spinner spinner_color;
    Spinner spinner_qty;
    Spinner spinner_size;
    TextView title;
    ImageView imageView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(Item_page.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(Item_page.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(Item_page.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(Item_page.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /*imageView = findViewById(R.id.imageView6);
        Bundle mBundle = getIntent().getExtras();
        if(mBundle != null){
            title.setText(mBundle.getString("ItemName"));
            imageView.setImageResource(mBundle.getInt("ItemImage"));
        }*/

        ViewPager viewpager = findViewById(R.id.View_pager);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        viewpager.setAdapter(imageAdapter);

        spinner_color = findViewById(R.id.spinner_color);
        spinner_size = findViewById(R.id.spinner_size);
        spinner_qty = findViewById(R.id.spinner_qty);

        ArrayAdapter color_adapter = new ArrayAdapter(Item_page.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.colors));
        color_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_color.setAdapter(color_adapter);

        ArrayAdapter size_adapter = new ArrayAdapter(Item_page.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.size));
        size_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_size.setAdapter(size_adapter);

        ArrayAdapter qty_adapter = new ArrayAdapter(Item_page.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.qty));
        qty_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_qty.setAdapter(qty_adapter);

        //userSession = new UserSession(Item_page.this);


    }

    public void onBuyNowClick(View view){
        UserSession userSession = UserSession.getInstance();;

        userSession.setProductId(2);
        userSession.setProductName("New shoe");
        userSession.setPrice((float)66.99);
        userSession.setQty(Integer.parseInt(spinner_qty.getSelectedItem().toString()));
        userSession.setSize(Float.parseFloat(spinner_size.getSelectedItem().toString()));

        Intent intent = new Intent(Item_page.this,SummaryActivity.class);
        intent.putExtra("PRODUCT_ID","2");
        intent.putExtra("PRICE","66.99");
        intent.putExtra("PRODUCT_NAME","New shoes");
        intent.putExtra("SIZE",spinner_size.getSelectedItem().toString());
        intent.putExtra("QTY",spinner_qty.getSelectedItem().toString());
        intent.putExtra("COLOUR",spinner_color.getSelectedItem().toString());
        startActivity(intent);
    }

    public void onAddToFavClick(View view){
        Intent intent = new Intent(Item_page.this,Favourites_Activity.class);
        startActivity(intent);
    }

    public void onInfoClick(View view){
        Intent intent = new Intent(Item_page.this,Size_guide_page.class);
        startActivity(intent);
    }

}
