package com.example.meili;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Admin_Dashboard extends AppCompatActivity {

    DBHelper myDb;
    Button btnVIewAll;

    private TextView mTextMessage;



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

        myDb = new DBHelper(this);

        btnVIewAll = (Button)findViewById(R.id.btnViewAllShoes);

        viewAllProduct();



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


    public void viewAllProduct(){
        btnVIewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res =myDb.getAllProduct();
                        if (res.getCount() == 0){
                            // Show message
                            showMessage("Error","Nothing found");

                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("ID :" + res.getString(0)+"\n");
                            buffer.append("NAME :" + res.getString(1)+"\n");
                            buffer.append("PRICE :" + res.getString(2)+"\n");
                            buffer.append("SIZE :" + res.getString(3)+"\n");
                            buffer.append("TYPE :" + res.getString(4)+"\n");
                            buffer.append("DESCRIPTION :" + res.getString(5)+"\n\n");
                        }

                        //show all product
                        showMessage("Product",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
