package com.example.meili;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import com.example.meili.Database.DBHelper;
import com.example.meili.Database.UsersMaster;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class user_profile extends AppCompatActivity {
    private TextView mTextMessage;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    TextView fname,lname,userName,email,address,postalCode,phone;
    DBHelper db;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(user_profile.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(user_profile.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(user_profile.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(user_profile.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        db = new DBHelper(this);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.username);
        userName = findViewById(R.id.lname);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.editText2);
        email = findViewById(R.id.email);
        postalCode = findViewById(R.id.postalCode);

        //add default values from db
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        int id = Integer.parseInt(sharedPreferences.getString("UserId", ""));

        Cursor userDetails = db.getUserDetails(id);
        if(userDetails.getCount() > 0) {
            userDetails.moveToFirst();
            fname.setText(userDetails.getString(userDetails.getColumnIndexOrThrow(UsersMaster.User.COLUMN_NAME_fname)));
            lname.setText(userDetails.getString(userDetails.getColumnIndexOrThrow(UsersMaster.User.COLUMN_NAME_lname)));
            userName.setText(userDetails.getString(userDetails.getColumnIndexOrThrow(UsersMaster.User.COLUMN_NAME_userName)));
            email.setText(userDetails.getString(userDetails.getColumnIndexOrThrow(UsersMaster.User.COLUMN_NAME_email)));
        }

        Cursor customerDetails = db.getCustomerDetails(id);
        if(customerDetails.getCount() > 0){
            customerDetails.moveToFirst();
            phone.setText(customerDetails.getString(customerDetails.getColumnIndexOrThrow(UsersMaster.Customer.COLUMN_NAME_phone)));
            address.setText(customerDetails.getString(customerDetails.getColumnIndexOrThrow(UsersMaster.Customer.COLUMN_NAME_address)));
            postalCode.setText(customerDetails.getString(customerDetails.getColumnIndexOrThrow(UsersMaster.Customer.COLUMN_NAME_postalCode)));
        }

    }

    public void onManageMyOrdersClick(View view){
        Intent intent = new Intent(user_profile.this,edit_order_activity.class);
        startActivity(intent);
    }

    public void onLogOutClick(View view){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        //remove existing user ID  from shared preferences
        editor.remove("UserId");
        //Set login status as false
        editor.putString("loginStatus","false");
        editor.commit();

        //Directs user to login page
        Intent intent = new Intent(user_profile.this,Profile_Activity.class);
        startActivity(intent);
    }

    public void onUpdateClick(View view){
        Intent intent = new Intent(user_profile.this,update_user_profile.class);
        startActivity(intent);
    }

    public void onDeleteAccountClick(View view){

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        //ask for confirmation dialog box
        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete account ?")
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //get logged in user's id from shared preferences
                        int id = Integer.parseInt(sharedPreferences.getString("UserId",""));

                        boolean status = db.deleteCustomer(id);
                        if(status == true){
                            //remove existing user ID  from shared preferences
                            editor.remove("UserId");
                            editor.putString("loginStatus","false");
                            editor.commit();

                            Toast.makeText(getApplicationContext(),"Deleted successfully",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(user_profile.this,Profile_Activity.class);
                            startActivity(intent);
                        }
                    }
                }).create().show();
    }

}
