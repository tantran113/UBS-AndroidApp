package com.example.yenpham.ubs;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ThaoNguyen on 11/9/16.
 */

public class User {



    private int ID;
    private String FirstName;
    private String LastName;
    private String DOB;
    private String mavID;
    private String phoneNumber;
    private String mavEmail;
    private String gmail;
    private String major;
    private String verificationCode;

    public User(){
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User(int ID ,String FirstName, String LastName, String DOB,
                String mavID, String phoneNumber, String mavEmail, String gmail,
                String major, String verificationCode){

        this.ID = ID;
        this.FirstName = FirstName;
        this.LastName= LastName;
        this.DOB= DOB;
        this.mavID = mavID;
        this.phoneNumber= phoneNumber;
        this.mavEmail = mavEmail;
        this.gmail = gmail;
        this.major = major;
        this.verificationCode = verificationCode;
    }

    public String getMavID() {
        return mavID;
    }

    public void setMavID(String mavID) {
        this.mavID = mavID;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMavEmail(String mavEmail) {
        this.mavEmail = mavEmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getDOB() {
        return DOB;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMavEmail() {
        return mavEmail;
    }

    public String getGmail() {
        return gmail;
    }

    public String getMajor() {
        return major;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", DOB='" + DOB + '\'' +
                ", mavID=" + mavID +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mavEmail='" + mavEmail + '\'' +
                ", gmail='" + gmail + '\'' +
                ", major='" + major + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return ID == user.ID &&
                mavID == user.mavID &&
                Objects.equals(FirstName, user.FirstName) &&
                Objects.equals(LastName, user.LastName) &&
                Objects.equals(DOB, user.DOB) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(mavEmail, user.mavEmail) &&
                Objects.equals(gmail, user.gmail) &&
                Objects.equals(major, user.major) &&
                Objects.equals(verificationCode, user.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, FirstName, LastName, DOB, mavID, phoneNumber, mavEmail, gmail, major, verificationCode);
    }



}
