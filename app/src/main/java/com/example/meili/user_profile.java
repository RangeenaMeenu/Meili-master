package com.example.meili;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class user_profile extends AppCompatActivity {
    private TextView mTextMessage;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
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

        //add default values from db

    }

    public void onManageMyOrdersClick(View view){
        Intent intent = new Intent(user_profile.this,edit_order_activity.class);
        startActivity(intent);
    }

    public void onLogOutClick(View view){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        editor.remove("UserId");
        editor.putString("loginStatus","false");
        editor.commit();

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
        //ask for confirmation
        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete account ?")
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = Integer.parseInt(sharedPreferences.getString("UserId",""));
                        boolean status = db.deleteCustomer(id);
                        if(status == true){
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
