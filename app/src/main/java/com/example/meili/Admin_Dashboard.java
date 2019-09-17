package com.example.meili;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Admin_Dashboard extends AppCompatActivity {
    private TextView mTextMessage;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(Admin_Dashboard.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(Admin_Dashboard.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(Admin_Dashboard.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(Admin_Dashboard.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__dashboard);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void onAddNewshoe(View view){
        Intent intent = new Intent(Admin_Dashboard.this,adminCategoryActivity.class);
        startActivity(intent);
    }

    public void onUpdate(View view){
        Intent intent = new Intent(Admin_Dashboard.this,UpdateItem.class);
        startActivity(intent);
    }

    public void onDelete(View view){
        Intent intent = new Intent(Admin_Dashboard.this,DeleteItem.class);
        startActivity(intent);
    }

    public void onLogOutClick(View view){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        editor.remove("UserId");
        editor.putString("loginStatus","false");
        editor.commit();

        Intent intent = new Intent(Admin_Dashboard.this,Profile_Activity.class);
        startActivity(intent);
    }

}
