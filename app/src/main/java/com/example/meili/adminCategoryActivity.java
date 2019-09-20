package com.example.meili;

import android.content.Intent;
import android.os.Bundle;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class adminCategoryActivity extends AppCompatActivity {

    DBHelper myDb;
    EditText editName,editPrice,editSize,editDescription,ediID;
    Spinner spinnerType;
    Button btnAdd;


    private TextView mTextMessage;
    Spinner spinner;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(adminCategoryActivity.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(adminCategoryActivity.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(adminCategoryActivity.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(adminCategoryActivity.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        spinner = findViewById(R.id.spinnerItemType);
        ArrayAdapter color_adapter = new ArrayAdapter(adminCategoryActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.shoe_category));
        color_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(color_adapter);

        myDb = new DBHelper(this);

        editName = (EditText)findViewById(R.id.editTextProductName);
        editPrice = (EditText)findViewById(R.id.editTextPrice);
        editSize = (EditText)findViewById(R.id.editTextSize);
        spinnerType = (Spinner)findViewById(R.id.spinnerItemType);
        editDescription=(EditText)findViewById(R.id.editTextItemDescription);
        ediID = (EditText)findViewById(R.id.editTextProductName);

        btnAdd= (Button)findViewById(R.id.btnUpdateProduct);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

         if(editName.getText().toString().isEmpty()){
             Toast.makeText(adminCategoryActivity.this,"Enter Name",Toast.LENGTH_LONG).show();


         }
         else if (editPrice.getText().toString().isEmpty()){
             Toast.makeText(adminCategoryActivity.this,"Enter Price",Toast.LENGTH_LONG).show();
         }

         else if (editSize.getText().toString().isEmpty()){
             Toast.makeText(adminCategoryActivity.this,"Enter Size",Toast.LENGTH_LONG).show();
         }

         else if (editDescription.getText().toString().isEmpty()){
             Toast.makeText(adminCategoryActivity.this,"Enter Description",Toast.LENGTH_LONG).show();
         }
         else{
               boolean isInserted = myDb.addProduct(editName.getText().toString(),
                        Double.parseDouble(editPrice.getText().toString()),
                        Double.parseDouble(editSize.getText().toString()),
                        spinnerType.getSelectedItem().toString(),
                        editDescription.getText().toString() );
               if (isInserted = true)
                   Toast.makeText(adminCategoryActivity.this,"Product Added",Toast.LENGTH_LONG).show();
               else
                   Toast.makeText(adminCategoryActivity.this,"Product not Added",Toast.LENGTH_LONG).show();
         }
            }
        });
    }



}
