package com.example.yenpham.ubs;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.util.Log.d;
/*
 * Created by ThaoNguyen on 11/9/16.
 */

public class UBSdb extends SQLiteOpenHelper implements dbInterface {
    /* Constructor */
    public UBSdb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_CLUBTOPICS);
        db.execSQL(SQL_CREATE_CLUB);
        db.execSQL(SQL_CREATE_CLUBMEMBER);
        db.execSQL(SQL_CREATE_EVENT);
        db.execSQL(SQL_CREATE_EVENT_COMMENT);
        db.execSQL(SQL_CREATE_DISCUSSION);
        db.execSQL(SQL_CREATE_REPLIES);
        db.execSQL(SQL_CREATE_CATEGORIES);
        db.execSQL(SQL_CREATE_SALES);
        db.execSQL(SQL_CREATE_HOUSING);
        db.execSQL(SQL_CREATE_ADVERTISEMENT);
    }

    public void cleanAllTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.delete(TABLE_CLUB, null, null);
        db.delete(TABLE_CLUBTOPICS, null, null);
        db.delete(TABLE_ADVERTISEMENT, null, null);
        db.delete(TABLE_EVENT, null, null);
        db.delete(TABLE_EVENTCOMMENT, null, null);
        db.delete(TABLE_DISCUSSION, null, null);
        db.delete(TABLE_CLUBMEMBER, null, null);
        db.delete(TABLE_REPLIES, null, null);
        db.delete(TABLE_SALES, null, null);
        db.delete(TABLE_CATEGORIES, null, null);
        db.delete(TABLE_HOUSING, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL("DROP TABLE IF EXISTS *");
        // create fresh tables
        this.onCreate(db);
    }

    public void addUser(User user){
        d("addUser", user.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getID());
        values.put(FIRSTNAME, user.getFirstName());
        values.put(LASTNAME, user.getLastName());
        values.put(DOB, user.getDOB());
        values.put(MAV_ID, user.getMavID());
        values.put(PHONENO, user.getPhoneNumber());
        values.put(MAVEMAIL, user.getMavEmail());
        values.put(GMAIL, user.getGmail());
        values.put(MAJOR, user.getMajor());
        values.put(VERCODE, user.getVerificationCode());
        // 3. insert
        db.insert(TABLE_USER, null, values);
        // 4. close
        db.close();
    }
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        // 2. build query
        Cursor cursor = db.query(TABLE_USER,
                USER_COLUMNS, // b. column names
                " ID = ?", // c. selections
                new String[] {String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        // 3. if we got results get the first one
        User user = new User();
        if (cursor != null) {
            cursor.moveToFirst();
            // 4. build user object
            int stringIndex = 0;
            user.setID(Integer.parseInt(cursor.getString(stringIndex)));
            user.setFirstName(cursor.getString(++stringIndex));
            user.setLastName(cursor.getString(++stringIndex));
            user.setDOB(cursor.getString(++stringIndex));
            user.setMavID(cursor.getString(++stringIndex));
            user.setPhoneNumber(cursor.getString(++stringIndex));
            user.setMavEmail(cursor.getString(++stringIndex));
            user.setGmail(cursor.getString(++stringIndex));
            user.setMajor(cursor.getString(++stringIndex));
            user.setVerificationCode(cursor.getString(++stringIndex));
            cursor.close();
        }
        //log
        d("getUser("+id+")", user.toString());
        db.close();
        // 5. return user
        return user;
    }
    public int getUserID(String gmail){
        SQLiteDatabase db = this.getReadableDatabase();
        // 2. build query
        Cursor cursor = db.query(TABLE_USER,
                USER_COLUMNS, // b. column names
                " Gmail = ?", // c. selections
                new String[] {String.valueOf(gmail)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        // 3. if we got results get the first one
        User user = new User();
        if (cursor != null) {
            cursor.moveToFirst();
            // 4. build user object
            int stringIndex = 0;
            user.setID(Integer.parseInt(cursor.getString(stringIndex)));
            user.setFirstName(cursor.getString(++stringIndex));
            user.setLastName(cursor.getString(++stringIndex));
            user.setDOB(cursor.getString(++stringIndex));
            user.setMavID(cursor.getString(++stringIndex));
            user.setPhoneNumber(cursor.getString(++stringIndex));
            user.setMavEmail(cursor.getString(++stringIndex));
            user.setGmail(cursor.getString(++stringIndex));
            user.setMajor(cursor.getString(++stringIndex));
            user.setVerificationCode(cursor.getString(++stringIndex));
            cursor.close();
        }

        db.close();
        // 5. return user
        return user.getID();
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_USER;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build user and add it to list
            User user;
            if (cursor.moveToFirst()) {
                do {
                    user = new User();
                    int stringIndex = 0;
                    user.setID(Integer.parseInt(cursor.getString(0)));
                    user.setFirstName(cursor.getString(1));
                    user.setLastName(cursor.getString(2));
                    user.setDOB(cursor.getString(3));
                    user.setMavID(cursor.getString(4));
                    user.setPhoneNumber(cursor.getString(5));
                    user.setMavEmail(cursor.getString(6));
                    user.setGmail(cursor.getString(7));
                    user.setMajor(cursor.getString(8));
                    user.setVerificationCode(cursor.getString(9));
                    // Add user to userList
                    userList.add(user);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        d("getAllUsers()", userList.toString());
        // return users
        return userList;
    }

    public int getLastUserID(){
        int lastUserID = 1;
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_USER;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. build query
        try( Cursor cursor = db.rawQuery(query, null) ){

            // 3. if we got results get the last one
            if (cursor.getCount()>0) {
                cursor.moveToLast();
                lastUserID = Integer.parseInt(cursor.getString(0));
                cursor.close();
            }
            db.close();
        }
        // 5. return user
        return lastUserID;
    }


    public int updateUser(User user) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getID());
        values.put(FIRSTNAME, user.getFirstName());
        values.put(LASTNAME, user.getLastName());
        values.put(DOB, user.getDOB());
        values.put(MAV_ID, user.getID());
        values.put(PHONENO, user.getPhoneNumber());
        values.put(MAVEMAIL, user.getMavEmail());
        values.put(GMAIL, user.getGmail());
        values.put(MAJOR, user.getMajor());
        values.put(VERCODE, user.getVerificationCode());
        // 3. updating row
        int i = db.update(TABLE_USER, //table
                values, // column/value
                KEY_USER_ID+" = ?", // selections
                new String[] { String.valueOf(user.getID()) }); //selection args
        // 4. close
        db.close();
        return i;
    }
    public void deleteUser(User user) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete
        db.delete(TABLE_USER, //table name
                KEY_USER_ID+" = ?",  // selections
                new String[] { String.valueOf(user.getID()) }); //selections args
        // 3. close
        db.close();
        //log
        d("deleteUser", user.toString());
    }
    public boolean GmailExist(String gmail){
        String query = "SELECT  * FROM " + TABLE_USER+ " WHERE " + GMAIL +  " =\"" + gmail + "\"";
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
            return false;
        }
    }
    public boolean MavMailExist(String MavMmail){
        String query = "SELECT  * FROM " + TABLE_USER+ " WHERE " + MAVEMAIL + " =\"" + MavMmail + "\"";
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
            return false;
        }
    }

    public void addClubTopics(String clubTopics){
        d("addClubTopics", clubTopics);//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(CLUB_TOPIC, clubTopics);
        // 3. insert
        db.insert(TABLE_CLUBTOPICS, null, values);
        // 4. close
        db.close();
    }
    public ArrayList<String> getAllClubTopics(){
        ArrayList<String> clubTopics = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CLUBTOPICS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            if (cursor.moveToFirst()) {
                do {
                    String topic;
                    topic = cursor.getString(0);
                    // Add club to clubList
                    clubTopics.add(topic);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        d("getAllClubTopics()", clubTopics.toString());
        // return clubList
        return clubTopics;
    }
    public void deleteClubTopic(String topic){
        d("DeleteClubTopics", topic);//for logging
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("DElETE FROM " + TABLE_CLUBTOPICS+ " WHERE clubTopic = \'Chess\'", null);
        db.close();
    }
    public int updateClubTopics(String newTopic, String oldTopic){
        d("UpdateClubTopics", oldTopic);//for logging
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(CLUB_TOPIC, newTopic);
        // 3. updating row
        int i = db.update(TABLE_CLUBTOPICS, //table
                values, // column/value
                CLUB_TOPIC+" = ?", // selections
                new String[] { oldTopic }); //selection args
        // 4. close
        db.close();
        return i;
    }

    public void addClub(Club club){
        d("addClub", club.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(CLUB_ID, club.getClubID());
        values.put(CREATOR_ID, club.getCreatorID());
        values.put(CLUB_NAME, club.getClubName());
        values.put(CLUB_TOPIC, club.getClubTopics());
        values.put(CLUB_DESCRIPTION, club.getClubDescription());
        values.put(CLUB_PROFILEPIC, club.getClubProfilePics());
        values.put(CREATED_DATETIME, club.getClubCreatedDateTime());
        // 3. insert
        db.insert(TABLE_CLUB, null, values);
        // 4. close
        db.close();
    }
    public Club getClub(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        // 2. build query
        Cursor cursor = db.query(TABLE_CLUB,
                CLUB_COLUMNS, // b. column names
                CLUB_ID+ " = ?", // c. selections
                new String[] {String.valueOf(1)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        // 3. if we got results get the first one
        Club club = new Club();
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            // 4. build user object
            club.setClubID(Integer.parseInt(cursor.getString(0)));
            club.setCreatorID(Integer.parseInt(cursor.getString(1)));
            club.setClubName(cursor.getString(2));
            club.setClubTopics(cursor.getString(3));
            club.setClubDescription(cursor.getString(4));
            club.setClubProfilePics(cursor.getString(5));
            club.setClubCreatedDateTime(cursor.getString(6));
            cursor.close();
        }
        //log
        d("getClub("+ id +")", club.toString());
        db.close();
        // 5. return user
        return club;
    }
    public ArrayList<Club> getAllClubs() {
        ArrayList<Club> clubList = new ArrayList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CLUB;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            Club club;
            if (cursor.moveToFirst()) {
                do {
                    club = new Club();
                    int stringIndex = 0;
                    club.setClubID(Integer.parseInt(cursor.getString(stringIndex)));
                    club.setCreatorID(Integer.parseInt(cursor.getString(++stringIndex)));
                    club.setClubName(cursor.getString(++stringIndex));
                    club.setClubTopics(cursor.getString(++stringIndex));
                    club.setClubDescription(cursor.getString(++stringIndex));
                    club.setClubProfilePics(cursor.getString(++stringIndex));
                    club.setClubCreatedDateTime(cursor.getString(++stringIndex));
                    cursor.close();
                    // Add club to clubList
                    clubList.add(club);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        d("getAllClubs()", clubList.toString());
        // return clubList
        return clubList;
    }
    public void updateClub(Club club){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(CLUB_ID, club.getClubID());
        values.put(CREATOR_ID, club.getCreatorID());
        values.put(CLUB_NAME, club.getClubName());
        values.put(CLUB_TOPIC, club.getClubTopics());
        values.put(CLUB_DESCRIPTION, club.getClubDescription());
        values.put(CLUB_PROFILEPIC, club.getClubProfilePics());
        values.put(CREATED_DATETIME, club.getClubCreatedDateTime());
        db.update(TABLE_CLUB, values, CLUB_ID, new String[] {String.valueOf(club.getClubID())});
        //log
        Log.d("getClub("+ club.getClubID() +")", club.toString());
        db.close();
    }

    public void addClubMember(int clubID, int memberID){
        Log.d("addClubMember", "Member: " + memberID + " into club " + clubID);//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(CLUB_ID, clubID);
        values.put(MEMBER_ID, memberID);
        // 3. insert
        db.insert(TABLE_CLUBMEMBER, null, values);
        // 4. close
        db.close();
    }
    public ArrayList<User> getAllClubMember(int clubID){
        ArrayList<User> userList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT * FROM " + TABLE_CLUBMEMBER ;
        //+ " WHERE " + CLUB_ID + " = " + clubID ;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build user and add it to list
            if (cursor.moveToFirst()) {
                do {
                    int userID = Integer.parseInt(cursor.getString(0));
                    userList.add(getUser(userID));
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        d("getClubMembers()", userList.toString());
        // return users
        return userList;
    }
    public boolean isMember(int clubID, int userID){
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CLUBMEMBER +
                " WHERE (" + CLUB_ID + " = " + clubID + " AND " + MEMBER_ID + " =" + userID + ")";
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build user and add it to list
            if (cursor.getCount()>0) {
                cursor.close();
                return true;
            }

            else return false;
        }
    }
    public void updateClubMember(){//TO BE IMPLEMENTED
    }

    public void addEvent(UBSEvent event){
        d("addEvent", event.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_ID, event.getEventID());
        values.put(CLUB_ID, event.getClubID());
        values.put(EVENT_PICS, event.getEventPics());
        values.put(EVENT_DESCRIPTION, event.getEventDescription());
        values.put(EVENT_DATETIME, event.getDateTime());
        values.put(EVENT_LOCATION, event.getEventLocation());

        // 3. insert
        db.insert(TABLE_EVENT, null, values);
        // 4. close
        db.close();

    }
    public ArrayList<UBSEvent> getAllEvent(int clubID){
        ArrayList<UBSEvent> eventList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_EVENT +
                " WHERE " + CLUB_ID + " =" + clubID;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            UBSEvent event = new UBSEvent();
            if (cursor.moveToFirst()) {
                do {
                    int stringIndex = 0;
                    event.setEventID(Integer.parseInt(cursor.getString(stringIndex)));
                    event.setClubID(Integer.parseInt(cursor.getString(++stringIndex)));
                    event.setEventPics(cursor.getString(++stringIndex));
                    event.setEventDescription(cursor.getString(++stringIndex));
                    event.setDateTime(cursor.getString(++stringIndex));
                    event.setEventLocation(cursor.getString(++stringIndex));
                    cursor.close();
                    // Add club to clubList
                    eventList.add(event);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        d("getAllEVent()", eventList.toString());
        // return clubList
        return eventList;
    }
    public void updateEvent(){
    }

    public void addEventComment(UBScomment cmt){
        d("addEventComment", cmt.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(cmID, cmt.getEventID());
        values.put(CLUB_ID, cmt.getClubID());
        values.put(USER_ID, cmt.getUserID());
        values.put(EVENT_ID, cmt.getEventID());
        values.put(CONTENT, cmt.getContents());
        values.put(CMT_DATETIME, cmt.getDateTime());
        // 3. insert
        db.insert(TABLE_EVENTCOMMENT, null, values);
        // 4. close
        db.close();
    }
    public ArrayList<UBScomment> getAllEventComment(UBSEvent event){
        ArrayList<UBScomment> cmtList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_EVENTCOMMENT +
                " WHERE " + CLUB_ID + " =" + event.getClubID() +
                " AND " + EVENT_ID + " ="  + event.getEventID() ;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            UBScomment cmt = new UBScomment();
            if (cursor.moveToFirst()) {
                do {
                    int stringIndex = 0;
                    cmt.setCmID(Integer.parseInt(cursor.getString(stringIndex)));
                    cmt.setClubID(Integer.parseInt(cursor.getString(++stringIndex)));
                    cmt.setUserID(Integer.parseInt(cursor.getString(++stringIndex)));
                    cmt.setEventID(Integer.parseInt(cursor.getString(++stringIndex)));
                    cmt.setContents(cursor.getString(++stringIndex));
                    cmt.setDateTime(cursor.getString(++stringIndex));
                    cursor.close();
                    // Add club to clubList
                    cmtList.add(cmt);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        d("getAllEVent()", cmtList.toString());
        // return clubList
        return cmtList;
    }

    public void addAdvertisement(int adID, String url){
        d("addAdvertisement", url);//for logging
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_AD_ID, adID);
        values.put(URL, url);
        // 3. insert
        db.insert(TABLE_ADVERTISEMENT, null, values);
        // 4. close
        db.close();
    }
    public ArrayList<String> getAllAdvertisement(){
        ArrayList<String> adList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_ADVERTISEMENT;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            if (cursor.moveToFirst()) {
                do
                    adList.add(cursor.getString(1));
                while (cursor.moveToNext());
                cursor.close();
            }
        }
        d("getAllAds", adList.toString());
        return adList;

    }

    public void addDiscussion(Discussion discussion){
        d("addDiscussion", discussion.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(DISCUSSION_ID, discussion.getDiscussionID());
        values.put(CLUB_ID, discussion.getClubID());
        values.put(DISCUSSION_CREATOR, discussion.getDisCreator());
        values.put(CONTENT, discussion.getContents());
        values.put(CREATED_DATETIME, discussion.getDateTime());
        // 3. insert
        db.insert(TABLE_DISCUSSION, null, values);
        // 4. close
        db.close();
    }
    public ArrayList<Discussion> getAllDiscussions(int clubID){
        ArrayList<Discussion> discussionList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_DISCUSSION;
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            Discussion discussion = new Discussion();
            if (cursor.moveToFirst()) {
                do {
                    int stringIndex = 0;
                    discussion.setDiscussionID(Integer.parseInt(cursor.getString(stringIndex)));
                    discussion.setClubID(Integer.parseInt(cursor.getString(++stringIndex)));
                    discussion.setDisCreator(Integer.parseInt(cursor.getString(++stringIndex)));
                    discussion.setContents(cursor.getString(++stringIndex));
                    discussion.setDateTime(cursor.getString(++stringIndex));
                    // Add club to clubList
                    discussionList.add(discussion);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        d("getAllDiscussions()", discussionList.toString());
        // return discussionList
        return discussionList;
    }

    public void updateDiscussion(){//TODO
    }

    public void addReply(Reply rep){
        d("addReply", rep.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(CLUB_ID, rep.getClubID());
        values.put(REPLY_ID, rep.getRepID());
        values.put(MEMBER_ID, rep.getUserID());
        values.put(DISCUSSION_ID, rep.getDisID());
        values.put(CONTENT, rep.getContent());
        values.put(CREATED_DATETIME, rep.getDateTime());
        // 3. insert
        db.insert(TABLE_REPLIES, null, values);
        // 4. close
        db.close();
    }
    public ArrayList<Reply> getAllReply(Discussion discussion){
        ArrayList<Reply> replyList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT * FROM " + TABLE_REPLIES +
                " WHERE " + CLUB_ID + " =" + discussion.getClubID() +
                " AND " + DISCUSSION_ID + " =" + discussion.getDiscussionID();

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            Reply reply = new Reply();
            if (cursor.moveToFirst()) {
                do {
                    int stringIndex = 0;
                    reply.setRepID(Integer.parseInt(cursor.getString(stringIndex)));
                    reply.setDisID(Integer.parseInt(cursor.getString(++stringIndex)));
                    reply.setClubID(Integer.parseInt(cursor.getString(++stringIndex)));
                    reply.setDateTime(cursor.getString(++stringIndex));
                    reply.setContent(cursor.getString(++stringIndex));
                    // Add club to clubList
                    replyList.add(reply);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        d("getAllReply()", replyList.toString());
        // return discussionList
        return replyList;
    }
    public void updateReply(){// TO BE IMPLEMENTED
    }

    public void addCategory(String category){
        d("addCategory", category);//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(CATEGORY, category);
        // 3. insert
        db.insert(TABLE_CATEGORIES, null, values);
        // 4. close
        db.close();

    }
    public ArrayList<String> getAllCategory(){
        ArrayList<String> categoryList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CATEGORIES;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            // 3. go over each row, build club and add it to list
            if (cursor.moveToFirst()) {
                do {
                    // Add club to clubList
                    categoryList.add(cursor.getString(0));
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        d("getAllCategories()", categoryList.toString());
        // return discussionList
        return categoryList;
    }

    public void addSales(Sales item){
        Log.d("addSales", item.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues sales_values = new ContentValues();

        sales_values.put(SALES_ID, item.getSalesID());
        sales_values.put(TITLE, item.getTitle());
        sales_values.put(POSTER_ID, item.getPosterID());
        sales_values.put(CONTACT_INFO, item.getContactInfo());
        sales_values.put(CREATED_DATETIME, item.getDateTime());
        sales_values.put(PRODUCT_NAME, item.getProductName());
        sales_values.put(PRICE, item.getPrice());
        sales_values.put(CONDITIONS, item.getCondition());
        sales_values.put(PICTURE, item.getPictures());
        sales_values.put(CATEGORY, item.getCategoryName());
        sales_values.put(DESCRIPTION, item.getDescription());

        // 3. insert
        db.insert(TABLE_SALES, null, sales_values);
        // 4. close
        db.close();
    }
    public ArrayList<Sales> getAllSales(){
        ArrayList<Sales> SalesList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SALES;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            Sales sale = new Sales();
            // 3. go over each row, build club and add it to list
            if (cursor.moveToFirst()) {
                do {
                    sale.setSalesID(cursor.getInt(0));
                    sale.setTitle(cursor.getString(1));
                    sale.setPosterID(cursor.getInt(2));
                    sale.setContactInfo(cursor.getString(3));
                    sale.setDateTime(cursor.getString(4));
                    sale.setProductName(cursor.getString(5));
                    sale.setPrice(cursor.getDouble(6));
                    sale.setCondition(cursor.getString(7));
                    sale.setPictures(cursor.getString(8));
                    sale.setCategoryName(cursor.getString(9));
                    sale.setDescription(cursor.getString(10));
                    SalesList.add(sale);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        Log.d("getAllSales()", SalesList.toString());
        // return
        return SalesList;

    }
    public void updateSales(){

    }

    public void addHousing(Housing item){
        d("addHousing", item.toString());//for logging
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues Housing_values = new ContentValues();
        Housing_values.put(HOUSING_ID, item.getHousingID());
        Housing_values.put(TITLE, item.getTitle());
        Housing_values.put(POSTER_ID, item.getPosterID());
        Housing_values.put(CONTACT_INFO, item.getContactInfo());
        Housing_values.put(DATETIME, item.getDateTime());
        Housing_values.put(NUM_BED, item.getNumBed());
        Housing_values.put(NUM_BATH, item.getNumBath());
        Housing_values.put(LOCATION, item.getLocation());
        Housing_values.put(ZIPCODE, item.getZipCode());
        Housing_values.put(FURNISHED, item.isFurnished());
        Housing_values.put(DESCRIPTION, item.getDescription());
        Housing_values.put(PICTURE, item.getPictures());
        Housing_values.put(PRICE, item.getPrice());
        Housing_values.put(SIZE, item.getSize());
        Housing_values.put(AVAILABILITY, item.getAvailability());

        // 3. insert
        db.insert(TABLE_HOUSING, null, Housing_values);
        // 4. close
        db.close();

    }
    public ArrayList<Housing> getHousing(){
        ArrayList<Housing> HousingList = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_HOUSING;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            Housing housing = new Housing();
            // 3. go over each row, build club and add it to list
            if (cursor.moveToFirst()) {
                do {
                    housing.setHousingID(cursor.getInt(0));
                    housing.setTitle(cursor.getString(1));
                    housing.setPosterID(cursor.getInt(2));
                    housing.setContactInfo(cursor.getString(3));
                    housing.setDateTime(cursor.getString(4));
                    housing.setNumBed(cursor.getInt(5));
                    housing.setNumBath(cursor.getInt(6));
                    housing.setLocation(cursor.getString(7));
                    housing.setZipCode(cursor.getInt(8));
                    housing.setFurnished(Boolean.getBoolean(cursor.getString(9)));
                    housing.setDescription(cursor.getString(10));
                    housing.setPictures(cursor.getString(11));
                    housing.setPrice(cursor.getDouble(12));
                    housing.setSize(cursor.getInt(13));
                    housing.setAvailability(cursor.getString(14));
                    HousingList.add(housing);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        d("getAllHousing()", HousingList.toString());
        // return
        return HousingList;

    }
    public void updateHousing(){

    }

    public void populateTable(){
        cleanAllTables();
        SharePref sharePref = SharePref.getInstance();

        addUser(new User(getLastUserID()+1,"Edward", "Nguyen", "1988-03-18", "1001152476", "214-843-7289",
                "edward.nguyen@mavs.uta.edu", "edward.nguyen100@gmail.com", "SE", "60942"));
        addUser(new User(getLastUserID()+1,"Thach", "Hoang", "1988-05-19", "1001155485", "817-247-8053",
                "thach.hoang@mavs.uta.edu", "hoangngocthachbk@gmail.com", "CS", "32444"));
        addUser(new User(getLastUserID()+1,"Minh", "Le", "1993-06-25", "1001151262", "214-228-7036",
                "mtle@mavs.uta.edu", "minhle93@gmail.com", "CS", "32564"));
        addUser(new User(getLastUserID()+1,"Hoang", "Pham", "1992-10-30", "1001159450", "817-851-5646",
                "hoang.pham35@mavs.uta.edu", "lehoangpham1092@gmail.com", "CS", "32566"));
        addUser(new User(getLastUserID()+1,"Yen", "Pham", "1990-12-06", "1001230920", "682-521-0554",
                "yen.pham@mavs.uta.edu", "phamyen592@gmail.com", "SE", "32565"));

        addClubTopics("Music");
        addClubTopics("Photography");
        addClubTopics("Tennis");
        addClubTopics("Volunteer");
        addClubTopics("Technology");
        /*
        public Club(int creatorID, String clubName, String clubTopics,
                String clubDescription, String clubProfilePics,
                String clubCreatedDateTime) {

         */
        addClub(new Club(1, "UTA Google Developers", "Technology",
                "We meet monthly usually on second Thursdays around " +
                        "6:30pm or 7:00pm.  Meeting formats range from presentations " +
                        "(given either on-site or virtually), coding challenges, " +
                        "workshops, and more.  Topics are about Google technologies, " +
                        "range in skill sets for beginner to advanced, and cover the " +
                        "Android SDK, 3rd party libraries, cloud and server solutions," +
                        " and any other things Google developer facing.", "AndroidClubPic", ""));
        addClub(new Club(2, "Scrum", "Technology", "Welcome to DFW Scrum!\n" +
                "Scrum is a process framework that focuses on delivering the " +
                "highest business value to the customer as early as possible " +
                "through short iterative development cycles that allow rapid and " +
                "repeated inspection of the project. Scrum can of course be used " +
                "on just about any project (if it involves people and it has " +
                "to get done, you can use Scrum).\n" +
                "\n" +
                "Our goal is to help you do better today than you were doing yesterday. " +
                "The best way to do that is through the collective wisdom and experience of " +
                "the crowd (sprinkled with some industry experts).\n" +
                "\n" +
                "Scrum is very simple to understand, but very hard to implement as it takes " +
                "your problems and throws them in your face each and every day. We consider " +
                "ourselves an organic group that is designed to tackle the challenges and issues" +
                " you face regardless of your role in Scrum.\n" +
                "\n" +
                "We meet on the 3rd Tuesday of each month reserving the quarters for industry " +
                "thought leaders / mentors who can provide direction and insight to questions " +
                "we might have raised in past meetings. We have hosted some of the industry", "ScrumClubPic",""));
        addClub(new Club(3, "Photography enthusiasts", "Photography",
                "Do you want a photography group that thinks out of the \"STUDIO\" box? " +
                        "Are you looking to Experience photography and not just learn about it? " +
                        "Then this is a group for you. We learn through experiencing the art, " +
                        "by taking field trips into unconventional area to find subjects that test " +
                        "our skills and make us use our imagination.\n" +
                        "\n" +
                        "\"Family Friendly Photography Group\"\n" +
                        "\n" +
                        "This is a group for all levels of photographers: \n" +
                        "beginners; intermediate; advance; and professionals/experts. This is a group " +
                        "where people can share ideas, experience, knowledge, and express their creativity. " +
                        "It is great for beginners who are eager to explore their new hobby. In addition, " +
                        "it is great for the more advance or professional since they can share their " +
                        "experience and learn about new and exciting techniques being used.\n" +
                        "\n" +
                        "We welcome anyone who enjoys the art and hobby of photography. There is a wealth " +
                        "of knowledge among our members, please join us and share yours.\n" +
                        "\n" +
                        "This is a group for Active members who love field trips, family oriented, " +
                        "and wants to explore new heights with there passion of photography.\n" +
                        "\n" +
                        "Learn, Teach, Explore, and most of all have fun!\n" +
                        "\n" +
                        "I do ask for a $25 annual membership fee to help with the cost of " +
                        "running the group.\n" +
                        "\n" +
                        "It takes a lot of time and effort to get some of these events together " +
                        "and in doing so, some require payments.\n" +
                        "\n" +
                        "Most of what we do is free, and i try to keep as many of these on the board " +
                        "as i can find.\n" +
                        "\n" +
                        "But some do cost, and of those I really try to get the cost down to a reasonable " +
                        "level. BUT i will tell you this, if you do pay for an event, I make sure its a " +
                        "SPECIAL Event well worth paying for.\n" +
                        "\n" +
                        "So Come Have Fun!!\n" +
                        "\n" +
                        "Join the Group! We'd love to have you!", "PhotographyClubTopic",""));
        addClub(new Club(4,"Random Acts of Kindness Volunteer", "Volunteer",
                "The purpose of Random Acts of Kindness is to bring hope and " +
                        "a ray of sunshine to those who perhaps may be in need of some...",
                "volunteerClubPic",""));
        addClubMember(1,2);
        addClubMember(1,3);
        addClubMember(3,1);
        addClubMember(3,4);

        addEvent(new UBSEvent(4, "Making Sandwiches for the Homeless - Presented By TangoTab",
                "Here's all the info you need for Saturday, April 2!\n" +
                        "\n" +
                        "Please arrive between 8:30am and 8:45am. We will begin promptly at 9am.\n" +
                        "\n" +
                        "Who Can Attend: Anyone that can make a sandwich - no kids who have to be " +
                        "tended to. We have had kids there as young as 3yo. \n" +
                        "\n" +
                        "How to Dress: Pull hair back so it doesn't hang in your face or wear " +
                        "ball cap, wear anything you can get bleach spots on. \n" +
                        "\n" +
                        "*** WHAT TO BRING *** \n" +
                        "\n" +
                        "You can bring any of the items in any combination, but this is " +
                        "what we recommend... \n" +
                        "\n" +
                        "For approximately $16 at Aldi, you can get....  \n" +
                        "\n" +
                        "• 3 Packs of Meat (please try to make this turkey, chicken or ham) \n" +
                        "\n" +
                        "• 3 packs (16 slice) cheese  \n" +
                        "\n" +
                        "• 4 Loaves of bread - try to find a loaf that has 20 slices " +
                        "(not including the ends) \n" +
                        "\n" +
                        "• 1 Mustard - plain yellow \n" +
                        "\n" +
                        "• 2 bags of baby carrots \n" +
                        "\n" +
                        "• 1 bag of raisins \n" +
                        "\n" +
                        "• 1 (100 ct.) of Sandwich Bags (make sure they zip) \n" +
                        "\n" +
                        "PLEASE TRY TO BRING AN EQUAL AMOUNT OF MEAT, CHEESE & BREAD... \n" +
                        "\n" +
                        "Example: If you bring enough slices of cheese for 72 sandwiches, bring enough meat and bread for 72 sandwiches. \n" +
                        "\n" +
                        "\n" +
                        "Here is a breakdown of all the items.... \n" +
                        "\n" +
                        "Sliced sandwich bread  \n" +
                        "\n" +
                        "Sliced Meat — DO NOT bring the kind from the deli. " +
                        "If you have questions please ask. \n" +
                        "\n" +
                        "Sliced cheese - Please do not bring spicy cheese \n" +
                        "\n" +
                        "Baby Carrots - Do not bring whole carrots that need to be cut \n" +
                        "\n" +
                        "Raisins - A big bag of Sunmaid Raisins would be great. \n" +
                        "\n" +
                        "Sandwich Bags that zip \n" +
                        "\n" +
                        "Plain yellow mustard \n" +
                        "\n" +
                        "AT THE END OF THE DAY WE WANT TO PRODUCE QUALITY OVER QUANTITY. " +
                        "Please focus on the product you are buying and not just the amount.",
                "2016-12-12", "UC center"));

        addEvent(new UBSEvent(4, "Ashford Hall visiting with Seniors, Laughing, Smiling, Bingo, Happy Times",
                "Ashford Hall visiting with Seniors, Laughing, Smiling, Bingo, Happy Times. You in?\n" +
                        "\n" +
                        "Christmas will be the Theme, please dress with that spirit in mind\n" +
                        "\n" +
                        "If you or anyone would like to donate little inexpensive prizes for bingo feel free, we play Bingo so everyone can win a prize and than we play black out bingo where one women and one man wins the Grand Prize its a blast. The list of ideas on what to bring is in the folder at the top of this page { click on more see files} and we can always use the donations.... Bring only if you can you are the most important part so please come.\n" +
                        "\n" +
                        "Ashford Hall, a skilled nursing facility is designed to provide residents with the care and assistance needed to enjoy life. Volunteering at AH is unlike any other volunteer experience you have ever had. As a group, we enrich the lives of frail or isolated seniors and/or adults, through support and friendship, during friendly visits. We also provide a Bingo game for the residents. This is a very kid friendly event. The residents love kids and dogs. \n" +
                        "\n" +
                        "As always your presence is very valuable. Seniors really enjoy visiting with us and when it comes to organizing the prizes and helping out with bingo, the more the merrier. We also realize that our volunteers come from all walks of life; among us are students, job-seekers, and so on. Not everyone can afford to buy prizes but all volunteers are welcome and valued.\n" +
                        "\n" +
                        "If you'd like to donate small gifts for the Bingo prize bags, consider the upcoming month's theme.  It could be a holiday, like Thanksgiving for November or Valentines Day for February, or it could be related to the season of the year. The Bingo prize bags are filled using the donated gift items from the volunteers. See the \"Files\" section, where is posted a list of possible Bingo prize gift ideas. \n" +
                        "\n" +
                        "** Ashford Hall no longer allows food donations (fruits, candies, cookies, cakes, and drinks) from us.\n" +
                        "\n" +
                        "NOTE ABOUT WHAT WE DO: We meet in the lobby. Then volunteers visit with residents in their rooms for a while, followed by a bingo game for them in the game room.  Volunteers fill prize bags. We also sit at tables with the residents during bingo, help them mark their bingo cards and pick up their prizes for them when they win.\n" +
                        "\n" +
                        "NOTE ABOUT ARRIVING LATE:  \n" +
                        "You can catch up to us. The typical schedule:  \n" +
                        "1) 12:45- 1:00 Meet in foyer  \n" +
                        "2) 1:05: Visit rooms or set up for bingo in atrium  \n" +
                        "3) 2:05: Bingo begins  \n" +
                        "4) 3:00: Finish bingo, group photo, pack up  \n" +
                        "5) 3:20-4:30? Optional late lunch at a nearby restaurant.\n" +
                        "\n" +
                        "NOTE TO THOSE WANTING TO BRING A DOG OR CAT: Ashford Hall has to have copies of up to date shot records for them. Of course, the dog should have the right temperament for visiting with residents.\n" +
                        "\n" +
                        "NOTE TO THOSE TAKING PHOTOS: Photography is welcome at Ashford Hall, and pictures of other volunteers, the facility, the prizes, and so on are totally acceptable. However, we need to remember to respect the privacy of the residents whom we are helping to serve. Please refrain from taking pictures of the clients, volunteers with residents, and so on. If you accidentally include someone, you can crop that person out or use programs such as photoshop or paint.net (free) to blur the face before posting to meetup, Facebook, or other sites. A how-to for blurring faces can be found in the Files section.\n" +
                        "\n" +
                        "OPTIONAL:  \n" +
                        "Some of us go for lunch afterward at a nearby restaurant. \n" +
                        "\n" +
                        "Come join us if you like!  \n" +
                        "\n" +
                        "THANK YOU FOR ALL YOU DO.", "2016-12-23", "Ashford Hall Nursing Home"));

        addEvent(new UBSEvent(3,"Autumn at the Arboretum Photowalk","Autumn at the Arboretum Photowalk\n" +
                "More than 90,000 pumpkins will make their way to the Arboretum to be featured " +
                "in this year’s Autumn at the Arboretum national fall festival, made possible by " +
                "Alliance Data and Texas Instruments. Named one of “America’s Best Pumpkin " +
                "Festivals” by Fodor’s Travel and “One of the World’s 15 Most Breathtaking Gardens” " +
                "by Architectural Digest, this year’s event at the Dallas Arboretum and Botanical " +
                "Garden will highlight the “Art of the Pumpkin.\" It's time for another photowalk" +
                " at the Dallas Arboretum.\n" +
                "\n" +
                "Dallas Arboretum’s Nationally Acclaimed Pumpkin Village\n" +
                "\n" +
                "For the 11th year, Autumn at the Arboretum will include the popular one-acre " +
                "Pumpkin Village in the Pecan Grove, featuring artful arrangements of tens of " +
                "thousands of pumpkins, gourds, squash, bales of hay and cornstalks. They will be " +
                "used together to create pumpkin houses that represent naturally occurring " +
                "patterns in nature to mirror the “Art of the Pumpkin” theme. Cinderella’s " +
                "“Pumpkin” Carriage will also return, courtesy of Baylor Scott & White at " +
                "White Rock. Set amidst the garden’s remaining summer flowers, as well as " +
                "150,000 fall blooms like chrysanthemums, ornamental grasses, copper plants and " +
                "firebush. The popular Haybale Maze will also return with the Tom Thumb Pumpkin " +
                "Patch offering pumpkins for purchase, and the Great Pumpkin Search. Autumn is " +
                "the perfect time of year for a picnic in the gardens or a leisurely walk among " +
                "150,000 fall-blooming flowers.\n" +
                "\n" +
                "\n" +
                "\n" +
                "The Pumpkins look great and the Rory Meyers Children's Adventure Garden is awesome!!!!\n" +
                "\n" +
                "Bring your camera, tripod, and flash and lets go shoot some pumpkins and flowers.\n" +
                "\n" +
                "There's always lots of color and we'll have a great time. \n" +
                "We'll meet at 9:00 am inside the front gate. \n" +
                "Admission is $15 and Parking is also $15, you can go online and get discounted parking for $8\n" +
                "\n" +
                "\n" +
                "I hope to see everyone there!!!","2016-11-15","Dallas Arboretum"));
        addEvent(new UBSEvent(3,"EventPics","Hey everyone, I know it's short notice but we haven't been " +
                "out on a photowalk for awhile. So let's go shoot the Graffiti in Deep Ellum.\n" +
                "\n" +
                " Let's meet at Café Brazil and have some breakfast. If you don't want to " +
                "have breakfast we'll meet outside café Brazil about 10:00.\n" +
                "\n" +
                " After we eat we'll walk around and photograph Deep Ellum's graffiti.  " +
                "Deep Ellum has a variety of different stores, restaurants, music, tattoo " +
                "parlors, etc.. It's a very eclectic part of the city and keeps growing.\n" +
                "\n" +
                "Hope to see you there.","2016-01-01", "Downtown Dallas"));
        addEvent(new UBSEvent(2,"Data Driven Coaching - Turning Team Data Safely into Insights and Actions",
                "Data Driven Coaching - Turning Team Data Safely into Insights and Actions\n" +
                        "November 15 · 6:30 PM\n" +
                        "Team data and dashboards can be misused and cause more pain than results. Having the team run " +
                        "blind to its historical data though is often worse, with solely opinions and gut-feel driving process " +
                        "change. Helping your teams see and understand a holistic balance of their data will give your coaching " +
                        "advice context and encourage team constant improvement through experiments and reflection. " +
                        "Coaching dashboards are about balancing trade-offs. Trading something your team is great at " +
                        "for something they want (or need) to improve. Having the team complete the feedback loop and " +
                        "confirm than an experiment had the intended impact, will process improvement be continuous and " +
                        "sustainable.\n" +
                        "\n" +
                        "This session shows how to expose data to teams in order for them to retrospect productively, " +
                        "determine if a process experiment is panning out as expected, and to vigorously explore process " +
                        "change opportunities. Recent research shows strong relationships of certain metrics to process " +
                        "and practices, and this session demonstrates how these metrics have and can be tied to timely " +
                        "coaching advice.\n" +
                        "\n" +
                        "The real-world dashboards demonstrated in this session show most common problems and how to avoid " +
                        "them with before and after shots and quotes from the teams impacted by them. \n" +
                        "In this session you will – \n" +
                        "• Learn how you can not only gather data, but use it to improve the process, with examples! \n" +
                        "• Learn how your can tie data insights to coaching advice (data driven coaching) \n" +
                        "• Learn how you can detect, predict and avoid data gaming and dashboard misuse \n" +
                        "• Learn from my mistakes, and mistakes I’ve seen others with real examples of Agile coaching " +
                        "dashboards (good and bad) Learning Outcomes: \n" +
                        "• How to reliably expose team data for process improvement \n" +
                        "• How to tie data to coaching advice (data driven coaching) \n" +
                        "• How to avoiding data gaming and dashboard misuse \n" +
                        "• Real examples of Agile coaching dashboards (good and bad) Speaker BIO About Troy - \n",
                "2016-12-10","Sabre Holdings"));
        addEvent(new UBSEvent(2,"Hands-On With Machine Learning APIs","Beyond Requirements: Analysis with an Agile Mindset\n" +
                "September 20 · 6:30 PM\n" +
                "Guest Hosted by Lisa Shoop - Director Agile Coaches ASPD Sabre. From the Book " +
                "by Kent J. McDonald. \n" +
                "Who Is This For? If you find yourself performing analysis on a project in order " +
                "to make sure the project is delivering the right thing, this is for you. " +
                "You may identify yourself as a business analyst (or derivation of that title), " +
                "product owner, product manager, project manager, tester, or developer. \n" +
                "Satisfy Stakeholders by Solving the Right Problems, in the Right Ways \n" +
                "Lisa will talk about technics shared in In Beyond Requirements, of how applying " +
                "analysis techniques with an agile mindset can radically transform analysis " +
                "from merely “gathering and documenting requirements” to an important " +
                "activity teams use to build shared understanding. Review key principles and " +
                "shows how these principles link to effective analysis. \n" +
                "McDonald’s strategies will teach you how to understand stakeholders’ needs, " +
                "identify the best solution for satisfying those needs, and build a shared " +
                "understanding of your solution that persists throughout the product lifecycle. " +
                "This includes taking advantage of what you learn throughout development, testing, " +
                "and deployment so that you can continuously adapt, refine, and improve. \n" +
                "Whether you’re an analysis practitioner or you perform analysis tasks " +
                "as a developer, manager, or tester, these techniques will help your team " +
                "consistently find and deliver better solutions.","2016-12-22","ERB 108"));
        addEvent(new UBSEvent(1,"EventPics","Hands-On With Machine Learning APIs\n" +
                "November 17 · 7:00 PM\n" +
                "Explore Google's machine-learning services, and stay after the presentation " +
                "to solicit help on the examples or your own projects. \n" +
                "\n" +
                "Need a kickstart to your next (or first) machine learning project? " +
                "Lots of tasks are now made simple by APIs offered by various vendors " +
                "including Microsoft, Google, HP, and Amazon.\n" +
                "\n" +
                "This class will walk you through services offered by Google such as TensorFlow, " +
                "the Mobile Vision API, and the Cloud Natural Language API. We will interact with " +
                "these services live through the browser, see compelling demos, and you will learn " +
                "how to make requests to them through your own application. Come prepared with " +
                "an idea or something that's been stumping you, because after the presentation, " +
                "we will have workshop time where we will be free to collaborate and ask questions " +
                "of each other.\n" +
                "\n" +
                "Before the class, it is recommended that you follow the instructions here so that " +
                "you can follow along: https://github.com/mrcity/mlworkshop/","2016-12-09","ERB 108" ));
        addEvent(new UBSEvent(1,"Android Study Jams : Getting Started in Android",
                "Description: Join us to learn how to write your own Android app from scratch! " +
                        "Android Study jams is a great way to get personal attention and a FREE class " +
                        "to learn a new skill. At the end of the class, you will have your own unique app " +
                        "that YOU MADE and that you can show for your work. Personal instructors are here " +
                        "to help as well and we will try to broadcast the classes online as well for those " +
                        "who may not be able to attend some days. http://developerstudyjams.com/\n" +
                        "\n" +
                        "We will be following through the Udacity Android for Beginners coursework " +
                        "as described here: https://www.udacity.com/course/android-development-for-beginners--ud837\n" +
                        "\n" +
                        "STUDENTS ARE REQUIRED TO PARTICIPATE AND ENCOURAGED TO MAKE A SMALL DONATION " +
                        "FOR FOOD/DRINK AND TO THE LOCATION.","2016-12-17","NH 100"));


        addHousing (new Housing("Room Arlington for rent",1,"9728787651","01-01-2015",3,3,"Arlington",760018,true,
                "I am looking for a mature female to do light house work and cooking for me and my husband in exchange for room and board. " +
                        "Must know how to cook simple meals." +
                        " Text me to set up interview. This is by Nebraska furniture mart.","picture",300.00,1000,"yes"));

        addHousing (new Housing("Need a female roomate",1,"9897646876","12-12-2015",3,2,"Mansfield",76063,false,
                "Hi I'm renting out a large bedroom with a nice walk in closet. The room does come with a brand new ceiling fan and nice carpet." +
                        " I am needing this rented out " +
                        "by this weekend. Only serious inquiries only. Have a great day. Gmail or call only. Thank you.","picture",400.00,1000,"NO"));

        addHousing (new Housing("A roomate need asap",1,"9087676543","02-02-2015",3,3,"Dllas",760018,true,
                "Live like you mean it at U Centre at Fry Street, the best in Denton student living! At U Centre (http://ucentreatfry.com)" +
                        " you will have the privacy and lifestyle you deserve in an off campus student community that is built specifically with you, the college student, in mind. \n" +
                        "\n" +
                        "Take advantage of our convenient location and unmatched amenities--everything you want and need in one place makes U Centre at Fry Street the best in college apartments! U Centre at Fry Street is located within walking distance to the University of North Texas and is within close proximity to TWU. Enjoy our amenities including a fitness center, computer center with FREE printing, video gaming room, resort-style swimming pool and private sun deck, wi-fi throughout the clubhouse, and much more. We also have two music rooms, an art studio, and two study rooms open 24 hours!.","picture",321.00,1200,"yes"));


        addHousing (new Housing("Room Arlington for rent",1,"9728787651","01-01-2015",3,3,"Arlington",760018,true,
                "I have a private room that is furnished looking for someone that wants free rent in exchange for helping around the house " +
                        "with cleaning and cooking. Prefer a middle aged woman. My home consists of me and my husband we have 1 teenage son that is never home. The person must now how to cook simple meals..","picture",300.00,1000,"yes"));


        addHousing (new Housing("Room Arlington for rent",1,"9728787651","01-01-2015",3,3,"Arlington",760018,true,
                "Nice room for rent \"ALL BILLS INCLUDED \" (utilities, electric, and Wireless internet), washer/dryer and a separate bathroom . The apartment is roomy, it has a dining room, living room, decent size kitchen, balcony and a fireplace for those cold days in winter time. This is a gated community and has a swimming pool very close to the apartment. The bedroom comes with some of the main furniture, if you already have some furniture we can arrange that. The room will be completely clean and ready to move in. ","picture",320.00,1500,"yes"));

        addHousing (new Housing("Room Arlington for rent",1,"9728787651","01-01-2015",3,3,"Arlington",760018,true,
                "Furnished rooms available in recently remodeled, non-smoking home in Central/South Arlington ranging from $475-650 with all" +
                        " utilities included. Near UTA with convenient access to I-20 and 360. \n" +
                        "The home is located on a quiet, safe street in a good neighborhood. Several restaurants and grocery stores within " +
                        "walking distance.","picture",350.00,1100,"yes"));

        addHousing (new Housing("Room Arlington for rent",1,"9728787651","01-01-2015",3,3,"Arlington",760018,true,
                "We are looking for responsible tenant that can care for this home. No pets or smoking allowed. Call show" +
                        " contact info for an appointment. One person per room only. Room with shared bath $475.","picture",475.00,1000,"no"));







        addDiscussion(new Discussion(1,1,1,"how to implement Listview in android"));
        addDiscussion(new Discussion(2,1,1,"how to add picture into android studio"));
        addDiscussion(new Discussion(3,1,1,"what is the book for program beginer"));
        addDiscussion(new Discussion(4,1,1,"what book is good for java"));
        addDiscussion(new Discussion(5,1,1,"How to build an app"));

        addDiscussion(new Discussion(6,1,1,"what language is the most popular"));
        addDiscussion(new Discussion(7,1,1,"what is oject oriented programing"));
        addDiscussion(new Discussion(8,1,1,"android studio for beginer"));


        addReply(new Reply(1,1,1,"first you need to create an XML and drag in the listview, and then in java file you may refernece the button and then use a proper adapter to display all the listview"));
        addReply(new Reply(1,1,1,"ListView is a view group that displays a list of scrollable items. The list items are automatically inserted to the list using an Adapter that pulls content from a source such as an array or database query and converts each item result into a view that's placed into the list"));
        addReply(new Reply(1,1,1,"Simple click and drag the button and references it in java"));

        addReply(new Reply(2,1,1,"In xml file you drag imge button and in java you reference it"));
        addReply(new Reply(2,1,1,"In Android, you can use “android.widget.ImageView” class to display an image file. Image file is easy to use but hard to master, because of the various screen and dpi in Android devices "));
        addReply(new Reply(2,1,1,"Android Studio uses mipmap folder to load launcher icon in project. as android says It's best practice to place your app icons in mipmap- folders (not the drawable- folders) because they are used at resolutions different from the device's current density."));
        addReply(new Reply(2,1,1,"As you can see my answer to this question is recommanded as a better one by comments and number of votes. You can accept it as the best answer if you think so too"));

        addReply(new Reply(3,1,1,"Introduction to java"));
        addReply(new Reply(3,1,1,"the beauty of programing language"));
        addReply(new Reply(3,1,1,"learn java in 30 days"));


        addReply(new Reply(4,1,1,"think in java"));
        addReply(new Reply(4,1,1,"head first java"));
        addReply(new Reply(4,1,1,"how to program java"));
        addReply(new Reply(4,1,1,"CORE java series"));


        addReply(new Reply(5,1,1,"first you need to learn a language, I suggest start of learn C and java then you will be able to make your first app"));
        addReply(new Reply(5,1,1,"you need to learn object oriented programing"));

        addReply(new Reply(6,1,1,"C language "));
        addReply(new Reply(6,1,1,"C language but I think java is more popular"));
        addReply(new Reply(6,1,1,"C# is high in demand right now"));
        addReply(new Reply(6,1,1,"Java is current number 1 in the industry"));


        addReply(new Reply(7,1,1,"The programming challenge was seen as how to write the logic, not how to define the data. Object-oriented programming takes the view that what we really care about are the objects we want to manipulate rather than the logic required to manipulate them"));
        addReply(new Reply(7,1,1,"The first step in OOP is to identify all the objects the programmer wants to manipulate and how they relate to each other, an exercise often known as data modeling."));
        addReply(new Reply(7,1,1,"Simula was the first object-oriented programming language. Java, Python, C++, Visual Basic .NET and Ruby are the most popular OOP languages today"));



        addReply(new Reply(8,1,1,"Android studio for beginer"));
        addReply(new Reply(8,1,1,"android studio tutorial guide"));
        addReply(new Reply(8,1,1,"android aventure studio"));








    }
}
