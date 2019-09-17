package com.example.meili;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class update_user extends AppCompatActivity {
    DBHelper mydb;
    EditText id1, uname, fname, lname, email, pwd, pwd2;

    List<String> users = new ArrayList<>();
    ArrayAdapter<String> adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(update_user.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(update_user.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(update_user.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(update_user.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        id1 = findViewById(R.id.txtId);
        uname = findViewById(R.id.txtId);
        fname = findViewById(R.id.txtfname);
        lname = findViewById(R.id.txtlname);
        email = findViewById(R.id.txtemail);
        pwd = findViewById(R.id.txtpwd);
        pwd2 = findViewById(R.id.txtpwd2);

        mydb = new DBHelper(this);




    }

    public void onclick_updateUser(View view){

        if(id1.getText().toString().isEmpty()){
            Toast.makeText(update_user.this, "Please enter User ID", Toast.LENGTH_SHORT).show();

        }else if(uname.getText().toString().isEmpty()){
            Toast.makeText(update_user.this, "Please enter Username", Toast.LENGTH_SHORT).show();

        }else if(fname.getText().toString().isEmpty()){
            Toast.makeText(update_user.this, "Please enter First name", Toast.LENGTH_SHORT).show();

        }else if(lname.getText().toString().isEmpty()){
            Toast.makeText(update_user.this, "Please enter Last name", Toast.LENGTH_SHORT).show();

        }else if(email.getText().toString().isEmpty()){
            Toast.makeText(update_user.this, "Please enter Email", Toast.LENGTH_SHORT).show();

        }else if(!isValidEmailId(email.getText().toString())){
            Toast.makeText(update_user.this, "Email Not Valid", Toast.LENGTH_SHORT).show();

        }else if(pwd.getText().toString().isEmpty()){
            Toast.makeText(update_user.this, "Please enter Password", Toast.LENGTH_SHORT).show();

        }else if(pwd2.getText().toString().isEmpty()){
            Toast.makeText(update_user.this, "Please Re-enter Password", Toast.LENGTH_SHORT).show();

        }else if(pwd.getText().toString().equals(pwd2.getText().toString())) {
            boolean isUpdated = mydb.updateUser(id1.getText().toString(),
                    uname.getText().toString(),
                    fname.getText().toString(),
                    lname.getText().toString(),
                    email.getText().toString(),
                    pwd.getText().toString());

            if(isUpdated){
                Toast.makeText(update_user.this, "User Updated", Toast.LENGTH_SHORT).show();
                id1.setText("");
                uname.setText("");
                fname.setText("");
                lname.setText("");
                email.setText("");
                pwd.setText("");
                pwd2.setText("");

            }else {
                Toast.makeText(update_user.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(update_user.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

}
