package com.example.yenpham.ubs;

import java.util.*;

/**
 * Created by ThaoNguyen on 11/9/16.
 */

public interface dbInterface {
    int DATABASE_VERSION = 1;// Database Version
    String DATABASE_NAME = "UBSdb";// Database Name

    // Table name
    String TABLE_USER = "USER_TABLE";
    String TABLE_CLUB = "CLUB_TABLE";
    String TABLE_CLUBTOPICS = "CLUBTOPICS_TABLE";
    String TABLE_ADVERTISEMENT = "ADVERTISEMENT_TABLE";
    String TABLE_EVENT = "EVENT_TABLE";
    String TABLE_EVENTCOMMENT = "EVENCOMMENT_TABLE";
    String TABLE_DISCUSSION = "DISCUSSION_TABLE";
    String TABLE_CLUBMEMBER = "CLUBMEMBER_TABLE";
    String TABLE_REPLIES = "REPLIES_TABLE";
    String TABLE_SALES = "SALES_TABLE";
    String TABLE_CATEGORIES = "CATEGORIES_TABLE";
    String TABLE_HOUSING = "HOUSING_TABLE";


    // Attribute names
    String KEY_USER_ID = "ID";
    String FIRSTNAME = "FirstName";
    String LASTNAME = "LastName";
    String DOB = "DOB";
    String PHONENO = "PhoneNumber";
    String MAVEMAIL = "MavEmail";
    String MAV_ID = "MavID";
    String GMAIL = "Gmail";
    String MAJOR = "Major";
    String VERCODE = "VerificationCode";
    String KEY_CLUB_ID = "clubID";
    String CREATOR_ID = "creatorID";
    String CLUB_NAME = "clubName";
    String CLUB_TOPIC = "clubTopic";
    String TOPIC_ID = "topicID";
    String CLUB_DESCRIPTION = "clubDescription";
    String CLUB_PROFILEPIC = "clubProfilePic";
    String CREATED_DATETIME = "createdDateTime";
    String KEY_AD_ID = "adID";
    String URL = "url";
    String KEY_EVENT_ID = "EventID";
    String CLUB_ID = "clubID";
    String EVENT_PICS = "eventPics";
    String EVENT_DESCRIPTION = "eventDescription";
    String EVENT_DATETIME = "eventDateTime";
    String EVENT_LOCATION = "eventLocation";
    String cmID = " commentID";
    String MEMBER_ID = " memberID";
    String EVENT_ID = " eventID";
    String CONTENT = " Content";
    String CMT_DATETIME = " CMT_DATETIME";
    String DISCUSSION_ID = "ID";
    String DISCUSSION_CREATOR = "Creator";
    String USER_ID = "userID";
    String HOUSING_ID = "HousingID";
    String SALES_ID = "SalesID";
    String TITLE = "Title";
    String POSTER_ID = "PosterID";
    String CONTACT_INFO = "ContactInfo";
    String DATETIME = "DateTime";
    String REPLY_ID = "ReplyID";
    String PRODUCT_NAME = "productName";
    String PRICE = "Price";
    String CONDITIONS = "Conditions";
    String PICTURE = "Pictures";
    String CATEGORY = "CategoryName";
    String DESCRIPTION = "Description";
    String NUM_BED = "numBed";
    String NUM_BATH = "numBath";
    String LOCATION = "location";
    String ZIPCODE = "zipcode";
    String FURNISHED = "furnished";
    String SIZE = "size";
    String AVAILABILITY = "availability";

    //Create SQL statements
    String SQL_CREATE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            KEY_USER_ID +    " INTEGER PRIMARY KEY," +
            FIRSTNAME + " TEXT NOT NULL,"+
            LASTNAME +  " TEXT NOT NULL," +
            DOB +       " TEXT," +
            MAV_ID +    " TEXT NOT NULL," +
            PHONENO +   " TEXT NOT NULL," +
            MAVEMAIL +  " TEXT NOT NULL UNIQUE," +
            GMAIL +     " TEXT NOT NULL UNIQUE," +
            MAJOR +     " TEXT," +
            VERCODE +   " TEXT" +
            " )";

    String SQL_CREATE_CLUBTOPICS = "CREATE TABLE " + TABLE_CLUBTOPICS +
            " (" + CLUB_TOPIC +" TEXT"+ " )";

    String SQL_CREATE_CLUB = "CREATE TABLE " + TABLE_CLUB + " (" +
            KEY_CLUB_ID +   " INTEGER PRIMARY KEY," +
            CREATOR_ID +    " INTEGER," +
            CLUB_NAME +     " TEXT NOT NULL," +
            CLUB_TOPIC +    " TEXT NOT NULL," +
        CLUB_DESCRIPTION +  " TEXT," +
        CLUB_PROFILEPIC +   " TEXT," +
        CREATED_DATETIME +  " TEXT," +
            "FOREIGN KEY (" +CLUB_TOPIC+") REFERENCES " +
            TABLE_CLUBTOPICS + "(" + CLUB_TOPIC + ") ON UPDATE CASCADE," +
            "FOREIGN KEY (" +CREATOR_ID+") REFERENCES " +
            TABLE_USER + "(" + KEY_USER_ID + ") " +
            " )";

    //CLUB MEMBER
    String SQL_CREATE_CLUBMEMBER = "CREATE TABLE " + TABLE_CLUBMEMBER+ " (" +
            CLUB_ID + " INTEGER," +
            MEMBER_ID+ " INTEGER," +
            "PRIMARY KEY ("+CLUB_ID +"," +MEMBER_ID + "),"+
            "FOREIGN KEY (" +CLUB_ID+") REFERENCES " +
            TABLE_CLUB + "(" + KEY_CLUB_ID + "), " +
            "FOREIGN KEY (" +MEMBER_ID+") REFERENCES " +
            TABLE_USER + "(" + KEY_USER_ID + ") " +
            " )";

    String SQL_CREATE_EVENT = "CREATE TABLE " + TABLE_EVENT + " (" +
            KEY_EVENT_ID +  " INTEGER PRIMARY KEY," +
            CLUB_ID +       " INTEGER," +
            EVENT_PICS +    " TEXT," +
            EVENT_DESCRIPTION +     " DATE NOT NULL," +
            EVENT_DATETIME +    " TEXT NOT NULL," +
            EVENT_LOCATION  +   " TEXT NOT NULL," +
            "FOREIGN KEY (" +CLUB_ID+") REFERENCES " +
            TABLE_CLUB + "(" + KEY_CLUB_ID + ") " +
            " )";

    String SQL_CREATE_EVENT_COMMENT = "CREATE TABLE " + TABLE_EVENTCOMMENT + " (" +
            cmID +         " INTEGER PRIMARY KEY," +
            CLUB_ID +      " INTEGER, " +
            USER_ID +      " INTEGER," +
            EVENT_ID +     " TEXT," +
            CONTENT +      " TEXT NOT NULL," +
            CMT_DATETIME + " TEXT NOT NULL," +
            "FOREIGN KEY (" +CLUB_ID+") REFERENCES " +
            TABLE_CLUBMEMBER + "(" + KEY_CLUB_ID + ") ON DELETE CASCADE, " +
            "FOREIGN KEY (" +USER_ID+") REFERENCES " +
            TABLE_CLUBMEMBER + "(" + MEMBER_ID + ") ON DELETE CASCADE, " +
            "FOREIGN KEY (" +EVENT_ID+") REFERENCES " +
            TABLE_EVENT + "(" + EVENT_ID + ") ON DELETE CASCADE " +
            " )";

    String SQL_CREATE_ADVERTISEMENT = "CREATE TABLE " + TABLE_ADVERTISEMENT + " (" +
                    KEY_AD_ID + " INTEGER PRIMARY KEY," +
                    URL +       " TEXT" + " )";

    String SQL_CREATE_DISCUSSION = "CREATE TABLE " + TABLE_DISCUSSION + " (" +
            DISCUSSION_ID +     " INTEGER PRIMARY KEY," +
            CLUB_ID +           " INTEGER," +
            DISCUSSION_CREATOR +" INTEGER," +
            CONTENT +           " TEXT NOT NULL," +
            CREATED_DATETIME +  " TEXT NOT NULL," +
            "FOREIGN KEY (" +CLUB_ID+") REFERENCES " +
            TABLE_CLUBMEMBER + "(" + CLUB_ID + "), " +
            "FOREIGN KEY (" +DISCUSSION_CREATOR+") REFERENCES " +
            TABLE_CLUBMEMBER + "(" + MEMBER_ID + ") " +
            " )";

    //SALES
    String SQL_CREATE_SALES = "CREATE TABLE " + TABLE_SALES+ " (" +
            SALES_ID +     " INTEGER NOT NULL," +
            TITLE +             " INTEGER NOT NULL," +
            POSTER_ID       +   " INTEGER," +
            CONTACT_INFO +      " TEXT," +
            CREATED_DATETIME +  " TEXT," +
            PRODUCT_NAME +      " INTEGER NOT NULL," +
            PRICE   +           " REAL NOT NULL," +
            CONDITIONS +        " TEXT," +
            PICTURE +           " TEXT," +
            CATEGORY +          " TEXT NOT NULL," +
            DESCRIPTION +       " TEXT," +
            "FOREIGN KEY (" +POSTER_ID+") REFERENCES " +
            TABLE_USER + "(" + USER_ID + "), " +

            "FOREIGN KEY (" +CATEGORY+") REFERENCES " +
            TABLE_CATEGORIES + "(" + CATEGORY + ") " +
            " )";

    String SQL_CREATE_HOUSING = "CREATE TABLE " + TABLE_HOUSING+ " (" +
            HOUSING_ID + " INTEGER PRIMARY KEY," +
            TITLE +             " INTEGER NOT NULL," +
            POSTER_ID       +   " INTEGER," +
            CONTACT_INFO +      " TEXT," +
            CREATED_DATETIME +  " TEXT," +
            NUM_BED +       " INTEGER NOT NULL," +
            NUM_BATH +      " INTEGER NOT NULL," +
            LOCATION +      " TEXT NOT NULL," +
            ZIPCODE +       " INTEGER," +
            FURNISHED +     " BOOLEAN NOT NULL," +
            DESCRIPTION +   " TEXT NOT NULL," +
            PICTURE +       " TEXT," +
            PRICE +         " REAL NOT NULL," +
            SIZE +          " REAL," +
            AVAILABILITY +  " TEXT NOT NULL," +
            "FOREIGN KEY (" +POSTER_ID+") REFERENCES " +
            TABLE_USER + "(" + USER_ID + ") " +

            " )";

    //REPLIES

    String SQL_CREATE_REPLIES = "CREATE TABLE " + TABLE_REPLIES+ " (" +
            REPLY_ID +      " INTEGER PRIMARY KEY," +
            DISCUSSION_ID + " INTEGER," +
            MEMBER_ID +       " INTEGER," +
            CLUB_ID +       " INTEGER," +
            CONTENT +       " TEXT NOT NULL," +
            CREATED_DATETIME +  " DATETIME NOT NULL " +
            " )";

    String SQL_CREATE_CATEGORIES = "CREATE TABLE " + TABLE_CATEGORIES+ " (" +
            CATEGORY + " TEXT PRIMARY KEY" +
            " )";

    String[] USER_COLUMNS =  {KEY_USER_ID, FIRSTNAME, LASTNAME, DOB,
                                MAV_ID, PHONENO,MAVEMAIL,  GMAIL, MAJOR, VERCODE};
    String[] CLUB_COLUMNS =  {KEY_CLUB_ID, CREATOR_ID, CLUB_NAME, CLUB_TOPIC,
                             CLUB_DESCRIPTION, CLUB_PROFILEPIC, CREATED_DATETIME};
}
