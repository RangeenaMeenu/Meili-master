package com.example.meili;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.meili.Database.DBHelper;
import com.example.meili.Database.UsersMaster;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile_Activity extends AppCompatActivity {
    private TextView mTextMessage;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    EditText email,pwd;
    String _email,_pwd;
    DBHelper db;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(Profile_Activity.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(Profile_Activity.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(Profile_Activity.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(Profile_Activity.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("loginStatus","").equals("true")){
            if(sharedPreferences.getString("userType","").equals("customer")) {
                Intent intent = new Intent(Profile_Activity.this, user_profile.class);
                startActivity(intent);
            }else if(sharedPreferences.getString("userType","").equals("admin")){
                Intent intent = new Intent(Profile_Activity.this, Admin_Dashboard.class);
                startActivity(intent);
            }else if(sharedPreferences.getString("userType","").equals("sysAdmin")){
                Intent intent = new Intent(Profile_Activity.this, Users.class);
                startActivity(intent);
            }
        }

        db = new DBHelper(this);
        email = findViewById(R.id.editText3);
        pwd = findViewById(R.id.editText4);
    }

    public void onSigninClick(View view){
        _email = email.getText().toString();
        _pwd = pwd.getText().toString();

        int userId = db.signInAsUser(_email,_pwd);
        if(userId > 0){
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            editor = sharedPreferences.edit();

            editor.putString("UserId",String.valueOf(userId));
            editor.putString("loginStatus","true");
            editor.putString("userType","customer");
            editor.commit();
            Log.d("Amal",""+userId);

            Intent fav = new Intent(Profile_Activity.this,user_profile.class);
            startActivity(fav);
        }else{
            Toast.makeText(getApplicationContext(),"User doesn't exsist",Toast.LENGTH_LONG).show();
        }


    }

    public void onRegisterClick(View view){
        Intent fav = new Intent(Profile_Activity.this,Register_user.class);
        startActivity(fav);
    }

    public void onAdminLoginClick(View view){
        _email = email.getText().toString();
        _pwd = pwd.getText().toString();

        int userId = db.signInAsAdmin(_email,_pwd);
        if(userId > 0){
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            editor = sharedPreferences.edit();

            editor.putString("UserId",String.valueOf(userId));
            editor.putString("loginStatus","true");
            editor.putString("userType","admin");
            editor.commit();
            Log.d("Amal",""+userId);

            Intent fav = new Intent(Profile_Activity.this,Admin_Dashboard.class);
            startActivity(fav);
        }else{
            Toast.makeText(getApplicationContext(),"User doesn't exsist",Toast.LENGTH_LONG).show();
        }

    }
    public void onLoginAsSysAdminClick(View view){
        _email = email.getText().toString();
        _pwd = pwd.getText().toString();

        int userId = db.signInAsSysAdmin(_email,_pwd);
        if(userId > 0){
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            editor = sharedPreferences.edit();

            editor.putString("UserId",String.valueOf(userId));
            editor.putString("loginStatus","true");
            editor.putString("userType","sysAdmin");
            editor.commit();
            Log.d("Amal",""+userId);

            Intent fav = new Intent(Profile_Activity.this,Users.class);
            startActivity(fav);
        }else{
            Toast.makeText(getApplicationContext(),"User doesn't exsist",Toast.LENGTH_LONG).show();
        }
    }

}
