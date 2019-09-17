package com.example.meili;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class update_user_profile extends AppCompatActivity {

    EditText firstname,lname,username,phone,email,address,postalCode,password,rePassword;
    String _firstname,_lname,_username,_phone,_email,_address,_postalCode,_password,_rePassword;
    DBHelper db;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(update_user_profile.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(update_user_profile.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(update_user_profile.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(update_user_profile.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        firstname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        postalCode = findViewById(R.id.postalCode);
        password = findViewById(R.id.pwd);
        rePassword = findViewById(R.id.rePwd);

        db = new DBHelper(this);
    }

    public void onUpdateClick(View view){
        _firstname = firstname.getText().toString();
        _lname = lname.getText().toString();
        _username = username.getText().toString();
        _phone = phone.getText().toString();
        _address = address.getText().toString();
        _email = email.getText().toString();
        _postalCode = postalCode.getText().toString();
        _password = password.getText().toString();
        _rePassword = rePassword.getText().toString();

        //do validations

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        int id = Integer.parseInt(sharedPreferences.getString("UserId",""));

        boolean status = db.update_customer(id,_firstname,_lname,_username,_phone,_email,_address,_postalCode,_password);

        if(status == true){
            Toast.makeText(getApplicationContext(),"Updated successfully",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(update_user_profile.this,user_profile.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Error occurred",Toast.LENGTH_LONG).show();
        }

    }
}
