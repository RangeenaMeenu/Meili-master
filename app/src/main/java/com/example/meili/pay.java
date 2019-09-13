package com.example.meili;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class pay extends AppCompatActivity {
    private TextView mTextMessage;

    Spinner spinner;
    Button button;

    String cardType,cardNo,nameOnCard,exM,exY,securityCode;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(pay.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(pay.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(pay.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(pay.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        spinner = (Spinner) findViewById(R.id.spin);

        String[] types = new String[]{"American Express", "Visa", "Master Card", "Discover", "Paypal"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,types);
        spinner.setAdapter(adapter);

        cardType = spinner.getSelectedItem().toString();
        nameOnCard = findViewById(R.id.cname).toString();
        cardNo = findViewById(R.id.editText4).toString();
        securityCode = findViewById(R.id.editText6).toString();
        exM = findViewById(R.id.editText7).toString();
        exY = findViewById(R.id.editText5).toString();

    }

    public void onConfirmPayClick(View view){

        final UserSession usersession = (UserSession)getApplicationContext();

        usersession.setCardType(cardType);
        usersession.setNameOnCard(nameOnCard);
        usersession.setCardNo(cardNo);
        usersession.setSecurityCode(securityCode);
        usersession.setExM(exM);
        usersession.setExY(exY);

        Intent profile = new Intent(pay.this,deliver_page.class);
        startActivity(profile);
    }

}
