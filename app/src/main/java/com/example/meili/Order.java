package com.example.meili;

import android.content.Intent;
import android.os.Bundle;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order extends AppCompatActivity {
    private TextView mTextMessage;
    TextView address,total;
    Button button;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(Order.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(Order.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(Order.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(Order.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        UserSession userSession = UserSession.getInstance();

        address = findViewById(R.id.textView12);
        total = findViewById(R.id.textView17);

        address.setText(userSession.getAddress());
        total.setText(Float.toString(userSession.getTotal()));

        button = (Button) findViewById(R.id.btn2);
    }

    public void onOrderClick(View view){

        UserSession userSession = UserSession.getInstance();

        DBHelper db = new DBHelper(this);
        long payid = db.addPayment(userSession.getTotal(),userSession.getCardType(),userSession.getCardNo(),userSession.getExM(),userSession.getExY(),userSession.getNameOnCard(),userSession.getSecurityCode());
        userSession.setPayId(payid);
        //Log.d("Rangeena", userSession.getfName());

        long billId = db.addBill(userSession.getTotal());
        userSession.setBillId(billId);
        long shipId = db.addShippingInfo(userSession.getfName(),userSession.getlName(),userSession.getAddress(),userSession.getEmail(),userSession.getPhone(),userSession.getPostalCode());
        userSession.setShipId(shipId);

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String date = currentDate.format(todayDate);
        long newRowId = db.addOrder(date,userSession.getUserId(),userSession.getPayId(),userSession.getBillId(),userSession.getShipId());

        //have to change product table too


        if(newRowId > 0){
            userSession.setOrderId(newRowId);
            Intent intent = new Intent(Order.this,successful_order_message.class);
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),"Error occured", Toast.LENGTH_LONG);
            toast.show();
        }

    }

}
