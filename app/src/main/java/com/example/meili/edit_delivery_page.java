package com.example.meili;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meili.Database.DBHelper;
import com.example.meili.Database.OrderMaster;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class edit_delivery_page extends AppCompatActivity {

    DBHelper db;
    String fName,lName,phone,address,email,postalCode;
    EditText _firstName,_lastname,_contact,_address,_email,_postalCode;
    private String id;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(edit_delivery_page.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(edit_delivery_page.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(edit_delivery_page.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(edit_delivery_page.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delivery_page);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        _firstName = findViewById(R.id.fname);
        _lastname = findViewById(R.id.editText2);
        _contact = findViewById(R.id.editText3);
        _address = findViewById(R.id.editText4);
        _email = findViewById(R.id.editText9);
        _postalCode = findViewById(R.id.editText8);

        Intent intent = getIntent();
        this.id = intent.getStringExtra("SHIP_ID");
        Log.d("Rangeena","Shipping id from intent "+id);

        db = new DBHelper(this);

        Cursor shipDetails = db.getshippingDetails(this.id);

        shipDetails.moveToFirst();
        _firstName.setText(shipDetails.getString(shipDetails.getColumnIndexOrThrow(OrderMaster.Shipping.COLUMN_FNAME)));
        _lastname.setText(shipDetails.getString(shipDetails.getColumnIndexOrThrow(OrderMaster.Shipping.COLUMN_LNAME)));
        _contact.setText(shipDetails.getString(shipDetails.getColumnIndexOrThrow(OrderMaster.Shipping.COLUMN_PHONE)));
        _address.setText(shipDetails.getString(shipDetails.getColumnIndexOrThrow(OrderMaster.Shipping.COLUMN_ADDRESS)));
        _email.setText(shipDetails.getString(shipDetails.getColumnIndexOrThrow(OrderMaster.Shipping.COLUMN_EMAIL)));
        _postalCode.setText(shipDetails.getString(shipDetails.getColumnIndexOrThrow(OrderMaster.Shipping.COLUMN_POSTALCODE)));
    }

    public void onupdateOrderClick(View view){
        fName = _firstName.getText().toString();
        lName = _lastname.getText().toString();
        phone = _contact.getText().toString();
        address = _address.getText().toString();
        email = _email.getText().toString();
        postalCode = _postalCode.getText().toString();

        boolean status = db.updateDelivery(this.id,fName,lName,phone,address,email,postalCode);

        if(status == true){
            Toast.makeText(getApplicationContext(),"Successfully updated",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(edit_delivery_page.this,edit_order_activity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Error occurred",Toast.LENGTH_LONG).show();
        }


    }
}
