package com.example.yenpham.ubs;

import java.text.DateFormat;

/**
 * Created by ThaoNguyen on 11/14/16.
 */

public class Housing {
    public static int currentID = 0;
    private int housingID;
    private String title;
    private int posterID;
    private String contactInfo;
    private String dateTime;
    private int numBed;
    private int numBath;
    private String location;
    private int zipCode;
    private boolean furnished;
    private String description;
    private String pictures;
    private double price;
    private int size;
    private String availability;

    public Housing() {
    }

    public Housing(String title, int posterID, String contactInfo,
                   String dateTime, int numBed, int numBath, String location, int zipCode,
                   boolean furnished, String description, String pictures, double price,
                   int size, String availability) {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new java.util.Date());
        housingID = ++currentID;
        this.title = title;
        this.posterID = posterID;
        this.contactInfo = contactInfo;
        this.dateTime = currentDateTimeString;
        this.numBed = numBed;
        this.numBath = numBath;
        this.location = location;
        this.zipCode = zipCode;
        this.furnished = furnished;
        this.description = description;
        this.pictures = pictures;
        this.price = price;
        this.size = size;
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Housing{" +
                "housingID=" + housingID +
                ", title='" + title + '\'' +
                ", posterID=" + posterID +
                ", contactInfo='" + contactInfo + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", numBed=" + numBed +
                ", numBath=" + numBath +
                ", location='" + location + '\'' +
                ", zipCode=" + zipCode +
                ", furnished=" + furnished +
                ", description='" + description + '\'' +
                ", pictures='" + pictures + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", availability='" + availability + '\'' +
                '}';
    }

    public static int getCurrentID() {
        return currentID;
    }

    public static void setCurrentID(int currentID) {
        Housing.currentID = currentID;
    }

    public int getHousingID() {
        return housingID;
    }

    public void setHousingID(int housingID) {
        this.housingID = housingID;
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

    public int getNumBed() {
        return numBed;
    }

    public void setNumBed(int numBed) {
        this.numBed = numBed;
    }

    public int getNumBath() {
        return numBath;
    }

    public void setNumBath(int numBath) {
        this.numBath = numBath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}