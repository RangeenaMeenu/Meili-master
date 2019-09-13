package com.example.meili;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class deliver_page extends AppCompatActivity {
    private TextView mTextMessage;
    String fName,lName,phone,address,email,postalCode;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(deliver_page.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(deliver_page.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(deliver_page.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(deliver_page.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_page);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fName = findViewById(R.id.editText).toString();
        lName = findViewById(R.id.editText2).toString();
        phone = findViewById(R.id.editText3).toString();
        address = findViewById(R.id.editText4).toString();
        email = findViewById(R.id.editText9).toString();
        postalCode = findViewById(R.id.editText8).toString();

    }

    public void onConfirmDeliveryClick(View view){

        final UserSession usersession = (UserSession)getApplicationContext();

        usersession.setfName(fName);
        usersession.setlName(lName);
        usersession.setEmail(email);
        usersession.setPhone(phone);
        usersession.setAddress(address);
        usersession.setPostalCode(postalCode);

        Intent profile = new Intent(deliver_page.this,Order.class);
        startActivity(profile);
    }

}
