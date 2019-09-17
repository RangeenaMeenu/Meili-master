package com.example.meili;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {

    private int userId;
    private String userName;
    private int productId;
    private float total;
    private String cardType;
    private String nameOnCard;
    private String exM;
    private String exY;
    private String cardNo;
    private String securityCode;
    private String fName;
    private String lName;
    private String address;
    private String email;
    private String phone;
    private String postalCode;
    private long payId;
    private long billId;
    private long shipId;
    private long orderId;
    private int qty;
    private float size;
    private String productName;
    private float price;
    private String colour;

    private static UserSession userSession = null;

    private UserSession(){
    }

    public static UserSession getInstance(){
        if (userSession == null)
            userSession = new UserSession();

        return userSession;
    }

    public String getColour() {

        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getQty() {

        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getSize() {

        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {

        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getOrderId() {

        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;;
    }

    public long getPayId() {

        return payId;
    }

    public void setPayId(long payId) {
        this.payId = payId;;
    }

    public long getBillId() {

        return billId;
    }

    public void setBillId(long billId) {

        this.billId = billId;;
    }

    public long getShipId() {

        return shipId;

    }

    public void setShipId(long shipId) {

        this.shipId = shipId;;
    }

    public String getfName() {

        return fName;
    }

    public void setfName(String fName) {

        this.fName = fName;
    }

    public String getlName() {

        return lName;
    }

    public void setlName(String lName) {

        this.lName = lName;
    }

    public String getNameOnCard() {

        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {

        this.nameOnCard = nameOnCard;
    }

    public int getProductId() {

        return productId;
    }

    public void setProductId(int productId) {

        this.productId = productId;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getTotal() {

        return total;
    }

    public void setTotal(float total) {

        this.total = total;;
    }

    public String getCardType() {

        return cardType;
    }

    public void setCardType(String cardType) {

        this.cardType = cardType;
    }

    public String getExM() {

        return exM;
    }

    public void setExM(String exM) {

        this.exM = exM;
    }

    public String getExY() {

        return exY;
    }

    public void setExY(String exY) {

        this.exY = exY;
    }

    public String getCardNo() {

        return cardNo;
    }

    public void setCardNo(String cardNo) {

        this.cardNo = cardNo;
    }

    public String getSecurityCode() {

        return securityCode;
    }

    public void setSecurityCode(String securityCode) {

        this.securityCode = securityCode;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getPostalCode() {

        return postalCode;
    }

    public void setPostalCode(String postalCode) {

        this.postalCode = postalCode;
    }
}
