package com.example.fullstackprojekt.model;

public class Gift {
    private int giftID;
    private String giftName;
    private double price;
    private String url;

    public Gift(String giftName, double price, String url){
        setGiftName(giftName);
        setPrice(price);
        setUrl(url);
    }

    public Gift(int giftID, String giftName, double price, String url){
        setGiftID(giftID);
        setGiftName(giftName);
        setPrice(price);
        setUrl(url);
    }

    public int getGiftID() {
        return giftID;
    }

    public void setGiftID(int giftID) {
        this.giftID = giftID;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "giftID=" + giftID +
                ", giftName='" + giftName + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
}
