package com.example.meili;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.meili.Database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class edit_order_activity extends AppCompatActivity {

    DBHelper db;
    ListView mlist;
    ArrayList<String> orderId;
    ArrayList<String> shipId;
    UserSession userSession;
    ArrayAdapter adapter;
    ImageButton update, delete;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    String[] itemNames = {"Men Vulcanized Shoes","Air Mesh Women Sneakers","Chunky WOmen's Sneakers","Bunny Kids Shoes"};


    int[] itemImage = {R.drawable.men_vulcanized_shoes,
            R.drawable.air_mesh_women_sneakers,
            R.drawable.chunky_sneakers_women,
            R.drawable.bunny_kids_shoes
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent home = new Intent(edit_order_activity.this,MainActivity.class);
                    startActivity(home);
                    break;
                case R.id.navigation_categories:
                    //mTextMessage.setText(R.string.title_home);
                    Intent categories = new Intent(edit_order_activity.this,Categories_Activity.class);
                    startActivity(categories);
                    break;
                case R.id.navigation_favourites:
                    //mTextMessage.setText(R.string.title_favourites);
                    Intent fav = new Intent(edit_order_activity.this,Favourites_Activity.class);
                    startActivity(fav);
                    break;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_profile);
                    Intent profile = new Intent(edit_order_activity.this,Profile_Activity.class);
                    startActivity(profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order_activity);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        db = new DBHelper(this);
        userSession = UserSession.getInstance();
        mlist = findViewById(R.id.list_view);

        update = findViewById(R.id.imageButton9);
        delete = findViewById(R.id.imageButton10);

        orderId = new ArrayList<>();
        shipId = new ArrayList<>();

        //get Userid from Sharedpreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        userSession.setUserId(Integer.parseInt(sharedPreferences.getString("UserId","")));


        Cursor cursor = db.getOrders(userSession.getUserId());
        Cursor cursor1 = db.getOrderDetails(userSession.getUserId());

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"No orders available",Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()){
                orderId.add(cursor.getString(0));
                shipId.add(cursor.getString(1));

                //Log.i("shipid", "" + shipId);
            }
//
//            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,orderId);
//            mlist.setAdapter(adapter);

            editOrders_list_adapter listAdapter = new editOrders_list_adapter(edit_order_activity.this, orderId, shipId);
            mlist.setAdapter(listAdapter);
        }

        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final CharSequence[] options ={"Update","Delete"};
                AlertDialog.Builder dialog =new  AlertDialog.Builder(edit_order_activity.this);

                dialog.setTitle("Chose an acyion");
                dialog.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i == 0){
                            Toast.makeText(getApplicationContext(),"Update ...",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Update ...",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                dialog.show();
            }
        });

    }

}
