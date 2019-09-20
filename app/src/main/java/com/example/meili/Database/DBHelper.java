package com.example.meili.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.meili.Order;
import com.example.meili.UserSession;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MeiliInfo.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

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

                        UsersMaster.Admin._ID + " INTEGER PRIMARY KEY," +
                        "FOREIGN KEY ("+UsersMaster.Admin._ID+") REFERENCES "+ UsersMaster.User.TABLE_NAME+"("+UsersMaster.User._ID+") ON DELETE CASCADE)";

        String SQL_CREATE_CUSTOMER =

                "CREATE TABLE " + UsersMaster.Customer.TABLE_NAME + "( " +

                        UsersMaster.Customer._ID +" INTEGER PRIMARY KEY," +
                        UsersMaster.Customer.COLUMN_NAME_phone + " TEXT," +
                        UsersMaster.Customer.COLUMN_NAME_address + " TEXT," +
                        UsersMaster.Customer.COLUMN_NAME_postalCode + " TEXT," +
                        "FOREIGN KEY ("+UsersMaster.Customer._ID+") REFERENCES "+ UsersMaster.User.TABLE_NAME+"("+UsersMaster.User._ID+") ON DELETE CASCADE)";

        String SQL_CREATE_SYSTEM_MANAGER = "CREATE TABLE " + UsersMaster.System_Manager.TABLE_NAME + " ("
                + UsersMaster.System_Manager._ID + " INTEGER PRIMARY KEY," +
                "FOREIGN KEY ("+UsersMaster.System_Manager._ID+") REFERENCES "+ UsersMaster.User.TABLE_NAME+"("+UsersMaster.User._ID+") ON DELETE CASCADE)";

        String SQL_CREATE_FAVOURITE = "CREATE TABLE " + ProductMaster.favourite.TABLE_NAME + "(" +
                ProductMaster.favourite.FAVOURITE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ProductMaster.favourite.CUSTOMER_ID + " INTEGER," +
                "FOREIGN KEY ("+ProductMaster.favourite.CUSTOMER_ID+") REFERENCES "+ UsersMaster.Customer.TABLE_NAME+"("+UsersMaster.Customer._ID+") ON DELETE CASCADE)";

        String SQL_CREATE_SHOPPING_CART = "CREATE TABLE " + ProductMaster.ShoppingCart.TABLE_NAME + "(" +
                ProductMaster.ShoppingCart._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ProductMaster.ShoppingCart.CUSTOMER_ID + " INTEGER, " +
                ProductMaster.ShoppingCart.QUNTITY + " INTEGER, " +
                ProductMaster.ShoppingCart.TOTAL + " REAL," +
                "FOREIGN KEY ("+ProductMaster.ShoppingCart.CUSTOMER_ID+") REFERENCES "+ UsersMaster.Customer.TABLE_NAME+"("+UsersMaster.Customer._ID+") ON DELETE CASCADE)";

        String SQL_CREATE_PAYMENT = "CREATE TABLE " + OrderMaster.Payment.TABLE_NAME + " ("
                + OrderMaster.Payment._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + OrderMaster.Payment.COLUMN_AMOUNT + " REAL,"
                + OrderMaster.Payment.COLUMN_CARD_TYPE + " REAL,"
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
                + OrderMaster.Shipping.COLUMN_FNAME + " TEXT,"
                + OrderMaster.Shipping.COLUMN_LNAME + " TEXT,"
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
                "FOREIGN KEY ("+OrderMaster.orders.COLUMN_CID +") REFERENCES "+ UsersMaster.Customer.TABLE_NAME+"("+UsersMaster.Customer._ID+") ON DELETE CASCADE," +
                "FOREIGN KEY ("+OrderMaster.orders.COLUMN_PAY_ID +") REFERENCES "+ OrderMaster.Payment.TABLE_NAME+"("+OrderMaster.Payment._ID+") ON DELETE CASCADE," +
                "FOREIGN KEY ("+OrderMaster.orders.COLUMN_BILL_ID +") REFERENCES  "+ OrderMaster.Bill.TABLE_NAME+"("+OrderMaster.Bill._ID+") ON DELETE CASCADE," +
                "FOREIGN KEY ("+OrderMaster.orders.COLUMN_SHIP_ID +") REFERENCES  "+ OrderMaster.Shipping.TABLE_NAME+"("+OrderMaster.Shipping._ID+") ON DELETE CASCADE);";

        String SQL_CREATE_PRODUCTS = "CREATE TABLE " + ProductMaster.products.TABLE_NAME + " ("
                + ProductMaster.products._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ProductMaster.products.COLUMN_NAME + " TEXT," +
                ProductMaster.products.COLUMN_PRICE + " REAL," +
                ProductMaster.products.COLUMN_SIZE + " REAL," +
                ProductMaster.products.COLUMN_CATEGORY + " TEXT," +
                ProductMaster.products.COLUMN_TYPE + " TEXT," +
                ProductMaster.products.COLUMN_DESCRIPTION + " TEXT," +
                ProductMaster.products.COLUMN_CART_ID + " INTEGER," +
                ProductMaster.products.COLUMN_ORDER_ID + " INTEGER, " +
                "FOREIGN KEY ("+ProductMaster.products.COLUMN_CART_ID+") REFERENCES "+ ProductMaster.ShoppingCart.TABLE_NAME+"("+ProductMaster.ShoppingCart._ID+") ON DELETE SET NULL," +
                " FOREIGN KEY ("+ProductMaster.products.COLUMN_ORDER_ID+") REFERENCES "+ OrderMaster.orders.TABLE_NAME+"("+OrderMaster.orders._ID+") ON DELETE SET NULL );";

        String SQL_CREATE_FAVOURITE_PRODUCT = "CREATE TABLE " + ProductMaster.favourite_Product.TABLE_NAME + "(" +
                ProductMaster.favourite_Product.FAVOURITE_ID +" INTEGER,"+
                ProductMaster.favourite_Product.PRODUCT_ID + " INTEGER," +
                "PRIMARY KEY ("+ProductMaster.favourite_Product.FAVOURITE_ID+","+ProductMaster.favourite_Product.PRODUCT_ID+")," +
                "FOREIGN KEY ("+ProductMaster.favourite_Product.FAVOURITE_ID+")  REFERENCES "+ ProductMaster.favourite.TABLE_NAME+"("+ProductMaster.favourite._ID+") ON DELETE CASCADE," +
                "FOREIGN KEY ("+ProductMaster.favourite_Product.PRODUCT_ID+")  REFERENCES "+ ProductMaster.products.TABLE_NAME+"("+ProductMaster.products._ID+") ON DELETE CASCADE)";

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

    public long addOrder(String date, int userId, long payId, long billId, long shipId){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderMaster.orders.COLUMN_DATE,date);
        values.put(OrderMaster.orders.COLUMN_CID,userId);
        values.put(OrderMaster.orders.COLUMN_PAY_ID,payId);
        values.put(OrderMaster.orders.COLUMN_BILL_ID,billId);
        values.put(OrderMaster.orders.COLUMN_SHIP_ID,shipId);

        long newRowId = db.insert(OrderMaster.orders.TABLE_NAME,null,values);
        return newRowId;


    }

    public boolean adduser(String uname,String fname,String lname,String email, String pwd){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentvalues = new ContentValues();
        contentvalues.put(UsersMaster.User.COLUMN_NAME_userName,uname);
        contentvalues.put(UsersMaster.User.COLUMN_NAME_fname,fname);
        contentvalues.put(UsersMaster.User.COLUMN_NAME_lname,lname);
        contentvalues.put(UsersMaster.User.COLUMN_NAME_email,email);
        contentvalues.put(UsersMaster.User.COLUMN_NAME_pwd,pwd);

        long newRowId = db.insert(UsersMaster.User.TABLE_NAME,null,contentvalues);
        System.out.println(newRowId);

       // Log.i("test", "test1");

        if(newRowId == 0)
            return false;
        else
            return true;
    }

    public Cursor getUsers() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +UsersMaster.User.TABLE_NAME, null);
        return cursor;
    }

//    public myObject selectUsers(String id) {
//        SQLiteDatabase db = getReadableDatabase();
//        String where = "SELECT * FROM " +UsersMaster.User.TABLE_NAME+ " WHERE " + UsersMaster.User._ID +" = ?";
//        String[] whereArgs = {id};
//        Cursor cursor = db.query();
//    }

    public boolean updateUser(String id, String uname,String fname,String lname,String email, String pwd){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(UsersMaster.User._ID,id);
        contentvalues.put(UsersMaster.User.COLUMN_NAME_userName,uname);
        contentvalues.put(UsersMaster.User.COLUMN_NAME_fname,fname);
        contentvalues.put(UsersMaster.User.COLUMN_NAME_lname,lname);
        contentvalues.put(UsersMaster.User.COLUMN_NAME_email,email);
        contentvalues.put(UsersMaster.User.COLUMN_NAME_pwd,pwd);
        db.update(UsersMaster.User.TABLE_NAME, contentvalues, "id = ?", new String[] { id });
        return true;
    }

    public List<String> getAllUsers()
    {
        List<String> userlist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " +UsersMaster.User._ID+  " FROM " + UsersMaster.User.TABLE_NAME,null);
        if(cursor.moveToFirst())
        {
            do {
                userlist.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userlist;
    }

    public long addPayment(float total, String cardType, String cardNo, String exM, String exY, String nameOnCard, String securityCode){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderMaster.Payment.COLUMN_AMOUNT,total);
        values.put(OrderMaster.Payment.COLUMN_CARD_TYPE,cardType);
        values.put(OrderMaster.Payment.COLUMN_CARDNO,cardNo);
        values.put(OrderMaster.Payment.COLUMN_EXM,exM);
        values.put(OrderMaster.Payment.COLUMN_EXY,exY);
        values.put(OrderMaster.Payment.COLUMN_CHN,nameOnCard);
        values.put(OrderMaster.Payment.COLUMN_SECURITYCODE,securityCode);

        long newRowId = db.insert(OrderMaster.Payment.TABLE_NAME,null,values);

        return newRowId;
    }

    public long addBill(float total){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderMaster.Bill.COLUMN_TOTAL,total);

        long newRowId = db.insert(OrderMaster.Bill.TABLE_NAME,null,values);

        return newRowId;
    }

    public long addShippingInfo(String fName, String lName, String address, String email, String phone, String postalCode){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderMaster.Shipping.COLUMN_FNAME,fName);
        values.put(OrderMaster.Shipping.COLUMN_LNAME,lName);
        values.put(OrderMaster.Shipping.COLUMN_ADDRESS,address);
        values.put(OrderMaster.Shipping.COLUMN_EMAIL,email);
        values.put(OrderMaster.Shipping.COLUMN_PHONE,phone);
        values.put(OrderMaster.Shipping.COLUMN_POSTALCODE,postalCode);

        long newRowId = db.insert(OrderMaster.Shipping.TABLE_NAME,null,values);

        return newRowId;
    }

    public Cursor getOrders(int cusId){
        SQLiteDatabase db = getReadableDatabase();
        String id = String.valueOf(cusId);
        String[] projection = {
                OrderMaster.orders._ID,
                OrderMaster.orders.COLUMN_SHIP_ID
        };

        String selection = OrderMaster.orders.COLUMN_CID + " = ?";
        String[] selsctionargs = {id};
        String sortOrder = OrderMaster.orders._ID+" DESC";

        Cursor cursor = db.query(
                OrderMaster.orders.TABLE_NAME,
                projection,
                selection,
                selsctionargs,
                null,
                null,
                sortOrder
                );

        return cursor;
    }

    public Cursor getOrderDetails(int cusId){
        SQLiteDatabase db = getReadableDatabase();
        String id = String.valueOf(cusId);
        String[] projection = {
                OrderMaster.orders._ID,
                OrderMaster.orders.COLUMN_SHIP_ID
        };

        String selection = OrderMaster.orders.COLUMN_CID + " = ?";
        String[] selsctionargs = {id};
        String sortOrder = OrderMaster.orders._ID+" DESC";

        Cursor cursor = db.query(
                OrderMaster.orders.TABLE_NAME,
                projection,
                selection,
                selsctionargs,
                null,
                null,
                sortOrder
        );

        return cursor;
    }

    //DB method for registering user
    public long registeruser(String firstname, String lname,String username, String phone, String email, String address, String postalCode,String pwd) {

        SQLiteDatabase db = getWritableDatabase();

        //add user to user table
        ContentValues values = new ContentValues();
        values.put(UsersMaster.User.COLUMN_NAME_fname,firstname);
        values.put(UsersMaster.User.COLUMN_NAME_lname,lname);
        values.put(UsersMaster.User.COLUMN_NAME_userName,username);
        values.put(UsersMaster.User.COLUMN_NAME_email,email);
        values.put(UsersMaster.User.COLUMN_NAME_pwd,pwd);

        //Inserting values to DB table
        long userRowId = db.insert(UsersMaster.User.TABLE_NAME,null,values);

        //add user to customer table
        ContentValues values1 = new ContentValues();
        values1.put(UsersMaster.Customer._ID,userRowId);
        values1.put(UsersMaster.Customer.COLUMN_NAME_phone,phone);
        values1.put(UsersMaster.Customer.COLUMN_NAME_address,address);
        values1.put(UsersMaster.Customer.COLUMN_NAME_postalCode,postalCode);

        //Inserting values to DB table
        long cusRowId = db.insert(UsersMaster.Customer.TABLE_NAME,null,values1);

        //check if insertion is successful and return id
        if(userRowId != -1 && cusRowId != -1){
            return userRowId;
        }else
            return 0;

    }

    public boolean update_customer(int id, String firstname, String lname, String username, String phone, String email, String address, String postalCode,String pwd) {
        SQLiteDatabase db = getReadableDatabase();

        //update User table
        ContentValues values = new ContentValues();
        values.put(UsersMaster.User.COLUMN_NAME_fname,firstname);
        values.put(UsersMaster.User.COLUMN_NAME_lname,lname);
        values.put(UsersMaster.User.COLUMN_NAME_userName,username);
        values.put(UsersMaster.User.COLUMN_NAME_email,email);
        values.put(UsersMaster.User.COLUMN_NAME_pwd,pwd);

        String selction = UsersMaster.User._ID  + " = ? ";
        String[] args = {String.valueOf(id)};

        int count1 = db.update(
                UsersMaster.User.TABLE_NAME,
                values,
                selction,
                args
        );

        //update Customer table
        ContentValues values1 = new ContentValues();
        values1.put(UsersMaster.Customer.COLUMN_NAME_phone,phone);
        values1.put(UsersMaster.Customer.COLUMN_NAME_address,address);
        values1.put(UsersMaster.Customer.COLUMN_NAME_postalCode,postalCode);

        String selction2 = UsersMaster.Customer._ID  + " = ? ";

        int count2 = db.update(
                UsersMaster.Customer.TABLE_NAME,
                values1,
                selction2,
                args
        );

        //check if update is successful
        if(count1 > 0 && count2 >0){
            return true;
        }else
            return false;
    }

    public boolean deleteCustomer(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = UsersMaster.User._ID + " = ?";
        String[] args = {String.valueOf(id)};


        //returns number of rows deleted
        int num = db.delete(
                UsersMaster.User.TABLE_NAME,
                selection,
                args
        );

        String selection2 = UsersMaster.Customer._ID + " = ?";

        int num2 = db.delete(
                UsersMaster.Customer.TABLE_NAME,
                selection2,
                args
        );

        if(num > 0 && num2 > 0){
            return true;
        }else{
            return false;
        }
    }

    public int signInAsUser(String email, String pwd) {
        int id = 0;
        int tempId = 0;
        SQLiteDatabase db = getReadableDatabase();

        //check if user exsists
        Cursor cursor = db.rawQuery("SELECT * FROM "+UsersMaster.User.TABLE_NAME+" WHERE "+UsersMaster.User.COLUMN_NAME_email+ " = ? AND "+UsersMaster.User.COLUMN_NAME_pwd+ " = ? ",new String[] {email,pwd});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            tempId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(UsersMaster.Customer._ID)));
        }

        //check if customer exsists
        Cursor cursor2 = db.rawQuery("SELECT * FROM "+UsersMaster.Customer.TABLE_NAME+" WHERE "+UsersMaster.Customer._ID+" = ? ",new String[] {String.valueOf(tempId)});
        if(cursor2.getCount() > 0){
            id = tempId;
        }

        //return customer id
        return id;
    }

    public int signInAsAdmin(String email, String pwd) {
        int id = 0;
        int tempId = 0;
        SQLiteDatabase db = getReadableDatabase();

        //checks if user exists
        Cursor cursor = db.rawQuery("SELECT * FROM "+UsersMaster.User.TABLE_NAME+" WHERE "+UsersMaster.User.COLUMN_NAME_email+ " = ? AND "+UsersMaster.User.COLUMN_NAME_pwd+ " = ? ",new String[] {email,pwd});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            tempId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(UsersMaster.Customer._ID)));
        }

        //check if admin exsists
        Cursor cursor2 = db.rawQuery("SELECT * FROM "+UsersMaster.Admin.TABLE_NAME+" WHERE "+UsersMaster.Admin._ID+" = ? ",new String[] {String.valueOf(tempId)});
        if(cursor2.getCount() > 0){
            id = tempId;
        }
        return id;

    }

    public int signInAsSysAdmin(String email, String pwd) {
        int id = 0;
        int tempId = 0;
        SQLiteDatabase db = getReadableDatabase();

        //check if user exsists
        Cursor cursor = db.rawQuery("SELECT * FROM "+UsersMaster.User.TABLE_NAME+" WHERE "+UsersMaster.User.COLUMN_NAME_email+ " = ? AND "+UsersMaster.User.COLUMN_NAME_pwd+ " = ? ",new String[] {email,pwd});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            tempId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(UsersMaster.Customer._ID)));
        }

        //check if Sysytem admin exsists
        Cursor cursor2 = db.rawQuery("SELECT * FROM "+UsersMaster.System_Manager.TABLE_NAME+" WHERE "+UsersMaster.System_Manager._ID+" = ? ",new String[] {String.valueOf(tempId)});
        if(cursor2.getCount() > 0){
            id = tempId;
        }
        return id;
    }

    //don't need it can use rowID
//    private int getCustomerID(String email) {
//        int id = 0;
//        SQLiteDatabase db = getReadableDatabase();
//        String[] projection = {
//                UsersMaster.User._ID
//        };
//
//        String selection = UsersMaster.User.COLUMN_NAME_email + " = ?";
//        String[] selectionargs = {email};
//
//        Cursor cursor = db.query(
//                UsersMaster.User.TABLE_NAME,
//                projection,
//                selection,
//                selectionargs,
//                null,
//                null,
//                null
//        );
//
//        if(cursor.getCount() == 0) {
//            Log.d("Amal","inside count"+id);
//        }else{
//            cursor.moveToFirst();
//                id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(UsersMaster.Customer._ID)));
//        }
//
//        Log.d("Amal",""+id);
//        return id;
//
//    }

    public Cursor getUserDetails(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+UsersMaster.User.TABLE_NAME+" WHERE "+UsersMaster.User._ID+ " = ?",new String[] {String.valueOf(id)});
        return cursor;
    }

    public Cursor getCustomerDetails(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM "+UsersMaster.Customer.TABLE_NAME+" WHERE "+UsersMaster.Customer._ID+ " = ?",new String[] {String.valueOf(id)});
        return cursor;
    }

    public boolean deleteOrder(int id){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                OrderMaster.orders.COLUMN_PAY_ID,
                OrderMaster.orders.COLUMN_BILL_ID,
                OrderMaster.orders.COLUMN_SHIP_ID
        };

        String selection = OrderMaster.orders._ID + " = ? ";
        String[] args = {String.valueOf(id)};

        Cursor cursor = db.query(
                OrderMaster.orders.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        String shipId = cursor.getString(cursor.getColumnIndexOrThrow(OrderMaster.orders.COLUMN_SHIP_ID));
        String payId = cursor.getString(cursor.getColumnIndexOrThrow(OrderMaster.orders.COLUMN_PAY_ID));
        String billId = cursor.getString(cursor.getColumnIndexOrThrow(OrderMaster.orders.COLUMN_BILL_ID));

        //delete data from Orders
        String selectionOrders = OrderMaster.orders._ID + " = ?";
        String[] argsOrders = {String.valueOf(id)};

        int statusOrder = db.delete(
                OrderMaster.orders.TABLE_NAME,
                selectionOrders,
                argsOrders
        );

        //delete Data from shipping
        String selectionShipping = OrderMaster.Shipping._ID + " = ?";
        String[] argsShipping = {shipId};

        int statusShipping = db.delete(
                OrderMaster.Shipping.TABLE_NAME,
                selectionShipping,
                argsShipping
        );

        //delete from payment
        String selectionPay = OrderMaster.Payment._ID + " = ?";
        String[] argsPay = {payId};

        int statusPay = db.delete(
                OrderMaster.Payment.TABLE_NAME,
                selectionPay,
                argsPay
        );

        //delete data from Bill
        String selectionBill = OrderMaster.Bill._ID + " = ?";
        String[] argsBill = {billId};

        int statusBill = db.delete(
                OrderMaster.Bill.TABLE_NAME,
                selectionBill,
                argsBill
        );

        if(statusOrder > 0 && statusShipping > 0 && statusPay > 0 && statusBill > 0){
            return true;
        }else{
            return false;
        }
    }

    public Cursor getshippingDetails(String id){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                OrderMaster.Shipping.COLUMN_FNAME,
                OrderMaster.Shipping.COLUMN_LNAME,
                OrderMaster.Shipping.COLUMN_PHONE,
                OrderMaster.Shipping.COLUMN_ADDRESS,
                OrderMaster.Shipping.COLUMN_EMAIL,
                OrderMaster.Shipping.COLUMN_POSTALCODE,
        };

        String selection = OrderMaster.Shipping._ID + " = ? ";
        String[] args = {id};

        Cursor cursor = db.query(
                OrderMaster.Shipping.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
        );

        return cursor;
    }

    public boolean updateDelivery(String id, String fName, String lName, String phone, String address, String email, String postalCode) {
        boolean status;

        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(OrderMaster.Shipping.COLUMN_FNAME,fName);
        values.put(OrderMaster.Shipping.COLUMN_LNAME,lName);
        values.put(OrderMaster.Shipping.COLUMN_ADDRESS,address);
        values.put(OrderMaster.Shipping.COLUMN_EMAIL,email);
        values.put(OrderMaster.Shipping.COLUMN_PHONE,phone);
        values.put(OrderMaster.Shipping.COLUMN_POSTALCODE,postalCode);

        String selection = OrderMaster.Shipping._ID  + " = ? ";
        String[] args = {id};

        long newRowId = db.update(
                OrderMaster.Shipping.TABLE_NAME,
                values,
                selection,
                args);

        if(newRowId > 0){
            status = true;
        }else{
            status = false;
        }

        return status;

    }
}
