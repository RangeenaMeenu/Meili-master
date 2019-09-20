package com.example.meili.Database;

import android.provider.BaseColumns;

public final class ProductMaster {

    private ProductMaster(){}

    public static class products implements BaseColumns {
        public static final String TABLE_NAME = "Products";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_SIZE = "size";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_CART_ID = "cartId";
        public static final String COLUMN_ORDER_ID = "orderId";
    }

    public  static  class favourite implements BaseColumns{
        public static final String TABLE_NAME = "favourite";
        public static final String FAVOURITE_ID ="favId";
        public static final  String CUSTOMER_ID ="cId";

    }

    public  static  class ShoppingCart implements BaseColumns{
        public static final String TABLE_NAME = "SoppingCart";
        public static final  String CUSTOMER_ID ="cId";
        public  static final String QUNTITY = "qty";
        public  static final String TOTAL = "total";

    }

    public  static  class favourite_Product implements BaseColumns{
        public static final String TABLE_NAME = "favouriteProduct";
        public static final String FAVOURITE_ID ="favId";
        public static final  String PRODUCT_ID ="pId";

    }
}
