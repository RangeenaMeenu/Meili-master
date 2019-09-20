package com.example.meili;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class remove_user extends AppCompatActivity {
    EditText id;
    DBHelper mydb;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(remove_user.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(remove_user.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(remove_user.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(remove_user.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        mydb = new DBHelper(this);
        id = findViewById(R.id.txtId2);
    }

    public void onclick_check2(View view) {


         if(id.getText().toString().isEmpty()){
            Toast.makeText(remove_user.this, "Please enter User ID", Toast.LENGTH_SHORT).show();

        }else{

             int id2 = Integer.parseInt(id.getText().toString());
             Cursor cursor = mydb.selectUser(id2);

             if(cursor.getCount() == 0){

                 Toast.makeText(remove_user.this, "User not found In Database", Toast.LENGTH_SHORT).show();
                 return;
             }
             StringBuffer buffer = new StringBuffer();

             while(cursor.moveToNext()){
                 buffer.append("ID : "+cursor.getString(0)+"\n");
                 buffer.append("FirstName : "+cursor.getString(1)+"\n");
                 buffer.append("Lastname : "+cursor.getString(2)+"\n");
                 buffer.append("Email : "+cursor.getString(3)+"\n");
                 buffer.append("Username : "+cursor.getString(5)+"\n");
                 buffer.append("Password : "+cursor.getString(4)+"\n\n");

             }

             showMessage("User Details",buffer.toString());
         }
    }

    public void onclick_delete(View view){

        if(id.getText().toString().isEmpty()){
            Toast.makeText(remove_user.this, "Please enter User ID", Toast.LENGTH_SHORT).show();

        }
    }

    public void onclick_viewAll(View view) {
        Cursor cursor = mydb.getUsers();
        if(cursor.getCount() == 0){
            Toast.makeText(remove_user.this, "No Users Found In Database", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            buffer.append("ID : "+cursor.getString(0)+"\n");
            buffer.append("FirstName : "+cursor.getString(1)+"\n");
            buffer.append("Lastname : "+cursor.getString(2)+"\n");
            buffer.append("Email : "+cursor.getString(3)+"\n");
            buffer.append("Username : "+cursor.getString(5)+"\n");
            buffer.append("Password : "+cursor.getString(4)+"\n\n");

        }

        showMessage("Users",buffer.toString());
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
