package com.example.meili.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.meili.Order;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MeiliInfo.db";

    public DBHelper(Context context) { super(context, DATABASE_NAME, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_USER =

                "CREATE TABLE " + UsersMaster.User.TABLE_NAME + " (" +

                        UsersMaster.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        UsersMaster.User.COLUMN_NAME_fname + " TEXT," +
                        UsersMaster.User.COLUMN_NAME_lname + " TEXT," +
                        UsersMaster.User.COLUMN_NAME_email + " TEXT," +
                        UsersMaster.User.COLUMN_NAME_pwd + " TEXT," +
                        UsersMaster.User.COLUMN_NAME_userName + " TEXT )";

        String SQL_CREATE_ADMIN =

                "CREATE TABLE " + UsersMaster.Admin.TABLE_NAME +" (" +

                        UsersMaster.Admin._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "FOREIGN KEY ("+UsersMaster.System_Manager._ID+") REFERENCES "+ UsersMaster.User.TABLE_NAME+"("+UsersMaster.User._ID+"))";

        String SQL_CREATE_CUSTOMER =

                "CREATE TABLE " + UsersMaster.Customer.TABLE_NAME + "( " +

                        UsersMaster.Customer._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        UsersMaster.Customer.COLUMN_NAME_phone + " TEXT," +
                        UsersMaster.Customer.COLUMN_NAME_address + " TEXT," +
                        UsersMaster.Customer.COLUMN_NAME_postalCode + " TEXT," +
                        "FOREIGN KEY ("+UsersMaster.System_Manager._ID+") REFERENCES "+ UsersMaster.User.TABLE_NAME+"("+UsersMaster.User._ID+"))";

        String SQL_CREATE_SYSTEM_MANAGER = "CREATE TABLE " + UsersMaster.System_Manager.TABLE_NAME + " ("
                + UsersMaster.System_Manager._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FOREIGN KEY ("+UsersMaster.System_Manager._ID+") REFERENCES "+ UsersMaster.User.TABLE_NAME+"("+UsersMaster.User._ID+"))";

        String SQL_CREATE_FAVOURITE = "CREATE TABLE " + ProductMaster.favourite.TABLE_NAME + "(" +
                ProductMaster.favourite.FAVOURITE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ProductMaster.favourite.CUSTOMER_ID + " INTEGER," +
                "FOREIGN KEY ("+ProductMaster.favourite.CUSTOMER_ID+") REFERENCES "+ UsersMaster.Customer.TABLE_NAME+"("+UsersMaster.Customer._ID+"))";

        String SQL_CREATE_SHOPPING_CART = "CREATE TABLE " + ProductMaster.ShoppingCart.TABLE_NAME + "(" +
                ProductMaster.ShoppingCart._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ProductMaster.ShoppingCart.CUSTOMER_ID + " INTEGER, " +
                ProductMaster.ShoppingCart.QUNTITY + " INTEGER, " +
                ProductMaster.ShoppingCart.TOTAL + " REAL," +
                "FOREIGN KEY ("+ProductMaster.ShoppingCart.CUSTOMER_ID+") REFERENCES "+ UsersMaster.Customer.TABLE_NAME+"("+UsersMaster.Customer._ID+"))";

        String SQL_CREATE_PAYMENT = "CREATE TABLE " + OrderMaster.Payment.TABLE_NAME + " ("
                + OrderMaster.Payment._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + OrderMaster.Payment.COLUMN_AMOUNT + " REAL,"
                + OrderMaster.Payment.COLUMN_CARDNO + " TEXT,"
                + OrderMaster.Payment.COLUMN_EXM + " INTEGER,"
                + OrderMaster.Payment.COLUMN_EXY + " INTEGER,"
                + OrderMaster.Payment.COLUMN_CHN + " TEXT,"
                + OrderMaster.Payment.COLUMN_SECURITYCODE + " INTEGER )";

        String SQL_CREATE_BILL = "CREATE TABLE " + OrderMaster.Bill.TABLE_NAME + " ("
                + OrderMaster.Bill._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + OrderMaster.Bill.COLUMN_TOTAL + " REAL )";

        String SQL_CREATE_SHIPPING = "CREATE TABLE " + OrderMaster.Shipping.TABLE_NAME + " ("
                + OrderMaster.Shipping._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + OrderMaster.Shipping.COLUMN_ADDRESS + " TEXT,"
                + OrderMaster.Shipping.COLUMN_EMAIL + " TEXT,"
                + OrderMaster.Shipping.COLUMN_PHONE + " TEXT,"
                + OrderMaster.Shipping.COLUMN_POSTALCODE + " INTEGER )";

        String SQL_CREATE_ORDERS = "CREATE TABLE " + OrderMaster.orders.TABLE_NAME + " ("
                + OrderMaster.orders._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                OrderMaster.orders.COLUMN_DATE + " TEXT," +
                OrderMaster.orders.COLUMN_CID + " INTEGER," +
                OrderMaster.orders.COLUMN_PAY_ID + " INTEGER," +
                OrderMaster.orders.COLUMN_BILL_ID + " INTEGER," +
                OrderMaster.orders.COLUMN_SHIP_ID + " INTEGER," +
                "FOREIGN KEY ("+OrderMaster.orders.COLUMN_CID +") REFERENCES "+ UsersMaster.Customer.TABLE_NAME+"("+UsersMaster.Customer._ID+")," +
                "FOREIGN KEY ("+OrderMaster.orders.COLUMN_PAY_ID +") REFERENCES "+ OrderMaster.Payment.TABLE_NAME+"("+OrderMaster.Payment._ID+")," +
                "FOREIGN KEY ("+OrderMaster.orders.COLUMN_BILL_ID +") REFERENCES  "+ OrderMaster.Bill.TABLE_NAME+"("+OrderMaster.Bill._ID+")," +
                "FOREIGN KEY ("+OrderMaster.orders.COLUMN_SHIP_ID +") REFERENCES  "+ OrderMaster.Shipping.TABLE_NAME+"("+OrderMaster.Shipping._ID+"));";

        String SQL_CREATE_PRODUCTS = "CREATE TABLE " + ProductMaster.products.TABLE_NAME + " ("
                + ProductMaster.products._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ProductMaster.products.COLUMN_NAME + " TEXT," +
                ProductMaster.products.COLUMN_PRICE + " REAL," +
                ProductMaster.products.COLUMN_SIZE + " REAL," +
                ProductMaster.products.COLUMN_TYPE + " TEXT," +
                ProductMaster.products.COLUMN_DESCRIPTION + " TEXT," +
                ProductMaster.products.COLUMN_CART_ID + " INTEGER," +
                ProductMaster.products.COLUMN_ORDER_ID + " INTEGER, " +
                "FOREIGN KEY ("+ProductMaster.products.COLUMN_CART_ID+") REFERENCES "+ ProductMaster.ShoppingCart.TABLE_NAME+"("+ProductMaster.ShoppingCart._ID+")," +
                " FOREIGN KEY ("+ProductMaster.products.COLUMN_ORDER_ID+") REFERENCES "+ OrderMaster.orders.TABLE_NAME+"("+OrderMaster.orders._ID+"));";

        String SQL_CREATE_FAVOURITE_PRODUCT = "CREATE TABLE " + ProductMaster.favourite_Product.TABLE_NAME + "(" +
                ProductMaster.favourite_Product.FAVOURITE_ID +" INTEGER,"+
                ProductMaster.favourite_Product.PRODUCT_ID + " INTEGER," +
                "PRIMARY KEY ("+ProductMaster.favourite_Product.FAVOURITE_ID+","+ProductMaster.favourite_Product.PRODUCT_ID+")," +
                "FOREIGN KEY ("+ProductMaster.favourite_Product.FAVOURITE_ID+")  REFERENCES "+ ProductMaster.favourite.TABLE_NAME+"("+ProductMaster.favourite._ID+")," +
                "FOREIGN KEY ("+ProductMaster.favourite_Product.PRODUCT_ID+")  REFERENCES "+ ProductMaster.products.TABLE_NAME+"("+ProductMaster.products._ID+"))";

        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_ADMIN);
        db.execSQL(SQL_CREATE_SYSTEM_MANAGER);
        db.execSQL(SQL_CREATE_CUSTOMER);
        db.execSQL(SQL_CREATE_FAVOURITE);
        db.execSQL(SQL_CREATE_SHOPPING_CART);
        db.execSQL(SQL_CREATE_PAYMENT);
        db.execSQL(SQL_CREATE_BILL);
        db.execSQL(SQL_CREATE_SHIPPING);
        db.execSQL(SQL_CREATE_PRODUCTS);
        db.execSQL(SQL_CREATE_ORDERS);
        db.execSQL(SQL_CREATE_FAVOURITE_PRODUCT);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean addProduct(String name, double price, double size, String type, String description ){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ProductMaster.products.COLUMN_NAME,name);
        contentValues.put(ProductMaster.products.COLUMN_PRICE,price);
        contentValues.put(ProductMaster.products.COLUMN_SIZE,size);
        contentValues.put(ProductMaster.products.COLUMN_TYPE,type);
        contentValues.put(ProductMaster.products.COLUMN_DESCRIPTION,description);

        long result = db.insert(ProductMaster.products.TABLE_NAME,null ,contentValues);

        if (result ==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllProduct(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * FROM " + ProductMaster.products.TABLE_NAME,null);
        return res;
    }

    public boolean updateProduct(String id,String name, double price, double size, String type, String description ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ProductMaster.products._ID,id);
        contentValues.put(ProductMaster.products.COLUMN_NAME,name);
        contentValues.put(ProductMaster.products.COLUMN_PRICE,price);
        contentValues.put(ProductMaster.products.COLUMN_SIZE,size);
        contentValues.put(ProductMaster.products.COLUMN_TYPE,type);
        contentValues.put(ProductMaster.products.COLUMN_DESCRIPTION,description);

        int num = db.update(ProductMaster.products.TABLE_NAME,contentValues,"_ID = ?",new String[]{id});

        if(num > 0){
            return true;
        }else{
            return false;
        }
    }




    public int deleteProduct(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(ProductMaster.products.TABLE_NAME,"_ID = ?",new String[] {id});
    }
}
