package com.example.meili;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.regex.Pattern;

public class add_user extends AppCompatActivity {
    DBHelper mydb;
    EditText uname, fname, lname, pwd, pwd2, email;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(add_user.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(add_user.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(add_user.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    Intent profile = new Intent(add_user.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        mydb = new DBHelper(this);

        uname = findViewById(R.id.txtId);
        fname = findViewById(R.id.txtfname);
        lname = findViewById(R.id.txtlname);
        email = findViewById(R.id.txtemail);
        pwd = findViewById(R.id.txtpwd);
        pwd2 = findViewById(R.id.txtpwd2);
    }
//Spinner.getselectedItem().toString);

    public void onclick_addUser(View view){

        if(uname.getText().toString().isEmpty()){
            Toast.makeText(add_user.this, "Please enter Username", Toast.LENGTH_SHORT).show();

        }else if(fname.getText().toString().isEmpty()){
            Toast.makeText(add_user.this, "Please enter First name", Toast.LENGTH_SHORT).show();

        }else if(lname.getText().toString().isEmpty()){
        Toast.makeText(add_user.this, "Please enter Last name", Toast.LENGTH_SHORT).show();

        }else if(email.getText().toString().isEmpty()){
            Toast.makeText(add_user.this, "Please enter Email", Toast.LENGTH_SHORT).show();

        }else if(!isValidEmailId(email.getText().toString())){
            Toast.makeText(add_user.this, "Email Not Valid", Toast.LENGTH_SHORT).show();

        }else if(pwd.getText().toString().isEmpty()){
            Toast.makeText(add_user.this, "Please enter Password", Toast.LENGTH_SHORT).show();

        }else if(pwd2.getText().toString().isEmpty()){
            Toast.makeText(add_user.this, "Please Re-enter Password", Toast.LENGTH_SHORT).show();

        }else if(pwd.getText().toString().equals(pwd2.getText().toString())) {
            boolean isInserted = mydb.adduser(uname.getText().toString(),
                    fname.getText().toString(), lname.getText().toString(),
                    email.getText().toString(), pwd.getText().toString());

            if (isInserted) {
                Toast.makeText(add_user.this, "User Inserted", Toast.LENGTH_SHORT).show();
                uname.setText("");
                fname.setText("");
                lname.setText("");
                email.setText("");
                pwd.setText("");
                pwd2.setText("");

            }else {
                Toast.makeText(add_user.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(add_user.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
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
