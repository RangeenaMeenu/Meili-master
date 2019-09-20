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
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_user extends AppCompatActivity {
    private TextView mTextMessage;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    EditText firstname,lname,username,phone,email,address,postalCode,password,rePassword;
    String _firstname,_lname,_username,_phone,_email,_address,_postalCode,_password,_rePassword;

    DBHelper db;

    //Bottom Nav Menu
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

        //Assigning the fields to variables
        firstname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        postalCode = findViewById(R.id.postalCode);
        password = findViewById(R.id.pwd);
        rePassword = findViewById(R.id.rePwd);

        //Connect DB
        db = new DBHelper(this);

    }

    public void onConfirmRegisterClick(View view){

        //Validations
        if(firstname.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill in first name.",Toast.LENGTH_SHORT).show();
        }else if(lname.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill in last name.",Toast.LENGTH_SHORT).show();
        }else if(username.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill in username.",Toast.LENGTH_SHORT).show();
        }else if(phone.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill in contact number.",Toast.LENGTH_SHORT).show();
        }else if(phone.getText().toString().length() > 10 || phone.getText().toString().length() < 10){
            Toast.makeText(getApplicationContext(),"Invalid contact number.",Toast.LENGTH_SHORT).show();
        }else if(email.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill in email address.",Toast.LENGTH_SHORT).show();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            Toast.makeText(getApplicationContext(),"Please enter a valid email address.",Toast.LENGTH_SHORT).show();
        }else if(address.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill in address.",Toast.LENGTH_SHORT).show();
        }else if(postalCode.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill in postal code.",Toast.LENGTH_SHORT).show();
        }else if(password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter a password.",Toast.LENGTH_SHORT).show();
        }else if(rePassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please confirm password.",Toast.LENGTH_SHORT).show();
        }else if(!password.getText().toString().equals(rePassword.getText().toString())){
            Toast.makeText(getApplicationContext(),"Passwords do not match.",Toast.LENGTH_SHORT).show();
        }else{

            //Assigning user entered text to relevant variables
            _firstname = firstname.getText().toString();
            _lname = lname.getText().toString();
            _username = username.getText().toString();
            _phone = phone.getText().toString();
            _address = address.getText().toString();
            _email = email.getText().toString();
            _postalCode = postalCode.getText().toString();
            _password = password.getText().toString();
            _rePassword = rePassword.getText().toString();

            long id = db.registeruser(_firstname,_lname,_username,_phone,_email,_address,_postalCode,_password);

            //check if insertiong is successful
            if(id > 0){

                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                editor = sharedPreferences.edit();

                //adding login detals to shared preferences
                editor.putString("UserId",String.valueOf(id));
                editor.putString("loginStatus","true");
                editor.putString("userType","customer");
                editor.commit();

                //Meeenu's function
                UserSession userSession = UserSession.getInstance();
                userSession.setUserId((int)id);
                Log.d("Amal" ,"UserId in usersession register: "+ id + " = " + userSession.getUserId());

                //Navigates user to user profile
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

}
