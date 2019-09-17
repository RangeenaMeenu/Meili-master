package com.example.meili;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class Register_user extends AppCompatActivity {
    private TextView mTextMessage;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    EditText firstname,lname,username,phone,email,address,postalCode,password,rePassword;
    String _firstname,_lname,_username,_phone,_email,_address,_postalCode,_password,_rePassword;

    DBHelper db;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(Register_user.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(Register_user.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(Register_user.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(Register_user.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
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

    public void onConfirmRegisterClick(View view){

        _firstname = firstname.getText().toString();
        _lname = lname.getText().toString();
        _username = username.getText().toString();
        _phone = phone.getText().toString();
        _address = address.getText().toString();
        _email = email.getText().toString();
        _postalCode = postalCode.getText().toString();
        _password = password.getText().toString();
        _rePassword = rePassword.getText().toString();

        //Do validations


        long id = db.registeruser(_firstname,_lname,_username,_phone,_email,_address,_postalCode,_password);

        if(id > -1){

            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            editor = sharedPreferences.edit();

            editor.putString("UserId",String.valueOf(id));
            editor.putString("loginStatus","true");
            editor.putString("userType","customer");
            editor.commit();

            UserSession userSession = UserSession.getInstance();
            userSession.setUserId((int)id);
            //Log.d("Amal" ,"UserId in usersession register: "+ id + " = " + userSession.getUserId());

            Intent intent = new Intent(Register_user.this,user_profile.class);
            intent.putExtra("FIRSTNAME",_firstname);
            intent.putExtra("LASTNAME",_lname);
            intent.putExtra("USERNAME",_username);
            intent.putExtra("PHONE",_phone);
            intent.putExtra("ADDRESS",_address);
            intent.putExtra("EMAIL",_email);
            intent.putExtra("POSTALCODE",_postalCode);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Error occured",Toast.LENGTH_LONG).show();
        }



    }

}
