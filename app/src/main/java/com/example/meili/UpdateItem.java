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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateItem extends AppCompatActivity {

    DBHelper myDb;
    Button btnUpdate;
    EditText editName,editPrice,editSize,editDescription,ediID;
    Spinner spinnerType;
    Button btnVIewAll;

    private TextView mTextMessage;
    Spinner spinner;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(UpdateItem.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(UpdateItem.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(UpdateItem.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(UpdateItem.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        spinner = findViewById(R.id.spinnerItemType);
        ArrayAdapter color_adapter = new ArrayAdapter(UpdateItem.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.shoe_category));
        color_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(color_adapter);

        myDb = new DBHelper(this);


        editName = (EditText)findViewById(R.id.editTextProductName);
        editPrice = (EditText)findViewById(R.id.editTextPrice);
        editSize = (EditText)findViewById(R.id.editTextSize);
        spinnerType = (Spinner)findViewById(R.id.spinnerItemType);
        editDescription=(EditText)findViewById(R.id.editTextItemDescription);
        ediID = (EditText)findViewById(R.id.editId);
        btnUpdate= (Button)findViewById(R.id.btnUpdateProduct);
        btnVIewAll = (Button)findViewById(R.id.btnViewAllShoes);
        UpdateProduct();

        viewAllProduct();


    }

    public void UpdateProduct(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if(ediID.getText().toString().isEmpty()){
                            Toast.makeText(UpdateItem.this,"Enter IT",Toast.LENGTH_LONG).show();
                        }

                        else if(editName.getText().toString().isEmpty()){
                            Toast.makeText(UpdateItem.this,"Enter Name",Toast.LENGTH_LONG).show();


                        }
                        else if (editPrice.getText().toString().isEmpty()){
                            Toast.makeText(UpdateItem.this,"Enter Price",Toast.LENGTH_LONG).show();
                        }

                        else if (editSize.getText().toString().isEmpty()){
                            Toast.makeText(UpdateItem.this,"Enter Size",Toast.LENGTH_LONG).show();
                        }

                        else if (editDescription.getText().toString().isEmpty()){
                            Toast.makeText(UpdateItem.this,"Enter Description",Toast.LENGTH_LONG).show();
                        }else {
                            boolean isUpdate = myDb.updateProduct(ediID.getText().toString(),
                                    editName.getText().toString(),
                                    Double.parseDouble(editPrice.getText().toString()),
                                    Double.parseDouble(editSize.getText().toString()),
                                    spinnerType.getSelectedItem().toString(),
                                    editDescription.getText().toString());

                            if (isUpdate == true)
                                Toast.makeText(UpdateItem.this, "Product Updated", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(UpdateItem.this, "Product not Updated", Toast.LENGTH_LONG).show();
                        }


                    }
                }
        );

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
