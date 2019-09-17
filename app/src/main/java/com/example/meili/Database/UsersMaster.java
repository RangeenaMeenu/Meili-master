package com.example.meili.Database;

import android.provider.BaseColumns;

public final class UsersMaster {

    private UsersMaster(){}


    public static class User implements BaseColumns{

        public static final String TABLE_NAME = "User";
        //public static final String _ID = "id";
        public static final String COLUMN_NAME_fname = "fname";
        public static final String COLUMN_NAME_lname = "lname";
        public static final String COLUMN_NAME_email = "email";
        public static final String COLUMN_NAME_userName = "userName";
        public static final String COLUMN_NAME_pwd = "pwd";

    }


    public static class Admin implements BaseColumns{

        public static final String TABLE_NAME = "Admin";

    }


    public static class Customer implements BaseColumns{

        public static final String TABLE_NAME = "Customer";

        public static final String COLUMN_NAME_phone = "phone";
        public static final String COLUMN_NAME_address = "address";
        public static final String COLUMN_NAME_postalCode = "postalCode";
    }

    public static class System_Manager implements BaseColumns{
        public static final String TABLE_NAME = "System_Manager";
    }

}

