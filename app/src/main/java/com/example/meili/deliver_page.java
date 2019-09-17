package com.example.meili;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class deliver_page extends AppCompatActivity {
    private TextView mTextMessage;
    String fName,lName,phone,address,email,postalCode;
    EditText _firstName,_lastname,_contact,_address,_email,_postalCode;

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

        _firstName = findViewById(R.id.fname);
        _lastname = findViewById(R.id.editText2);
        _contact = findViewById(R.id.editText3);
        _address = findViewById(R.id.editText4);
        _email = findViewById(R.id.editText9);
        _postalCode = findViewById(R.id.editText8);
    }

    public void onConfirmDeliveryClick(View view){

        fName = _firstName.getText().toString();
        lName = _lastname.getText().toString();
        phone = _contact.getText().toString();
        address = _address.getText().toString();
        email = _email.getText().toString();
        postalCode = _postalCode.getText().toString();

        UserSession userSession = UserSession.getInstance();

        Log.d("Test",fName);

        userSession.setfName(fName);
        userSession.setlName(lName);
        userSession.setEmail(email);
        userSession.setPhone(phone);
        userSession.setAddress(address);
        userSession.setPostalCode(postalCode);

        Intent profile = new Intent(deliver_page.this,Order.class);
        startActivity(profile);
    }

}
