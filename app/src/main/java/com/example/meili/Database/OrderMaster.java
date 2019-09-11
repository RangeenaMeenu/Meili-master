package com.example.meili.Database;
import android.provider.BaseColumns;

public final class OrderMaster {

    private OrderMaster(){}



    public static class Payment implements BaseColumns{
        public static final String TABLE_NAME = "payment";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_CARDNO = "creditcardNo";
        public static final String COLUMN_EXM = "ExM";
        public static final String COLUMN_EXY = "ExY";
        public static final String COLUMN_CHN = "chn";
        public static final String COLUMN_SECURITYCODE = "securityCode";
    }

    public static class Bill implements BaseColumns{
        public static final String TABLE_NAME = "bill";
        public static final String COLUMN_TOTAL = "total";
    }

    public static class Shipping implements BaseColumns{
        public static final String TABLE_NAME = "shipping";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_POSTALCODE = "postalCode";
    }

    public static class orders implements BaseColumns{
        public static final String TABLE_NAME = "Orders";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_CID = "cId";
        public static final String COLUMN_PAY_ID = "payId";
        public static final String COLUMN_BILL_ID = "billId";
        public static final String COLUMN_SHIP_ID = "shipId";
    }
}
