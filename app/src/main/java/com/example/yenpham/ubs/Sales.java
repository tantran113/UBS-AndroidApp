package com.example.yenpham.ubs;

import java.text.DateFormat;

/**
 * Created by ThaoNguyen on 11/13/16.
 */

public class Sales {
    public static int currentID=0;
    private int salesID;
    private String title;
    private int posterID;
    private String contactInfo;
    private String dateTime;
    private String productName;
    private double price;
    private String condition;
    private String pictures;
    private String categoryName;
    private String description;

    public Sales() {
    }

    public Sales(String title, int posterID,
                 String contactInfo, String productName,
                 double price, String condition,
                 String pictures, String categoryName, String description) {

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new java.util.Date());
        salesID = ++currentID;
        this.title = title;
        this.posterID = posterID;
        this.contactInfo = contactInfo;
        this.dateTime = currentDateTimeString;
        this.productName = productName;
        this.price = price;
        this.condition = condition;
        this.pictures = pictures;
        this.categoryName = categoryName;
        this.description = description;
    }

    public static int getCurrentID() {
        return currentID;
    }

    public static void setCurrentID(int currentID) {
        Sales.currentID = currentID;
    }

    public int getSalesID() {
        return salesID;
    }

    public void setSalesID(int salesID) {
        this.salesID = salesID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosterID() {
        return posterID;
    }

    public void setPosterID(int posterID) {
        this.posterID = posterID;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "salesID=" + salesID +
                ", title='" + title + '\'' +
                ", posterID=" + posterID +
                ", contactInfo='" + contactInfo + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", condition='" + condition + '\'' +
                ", pictures='" + pictures + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
