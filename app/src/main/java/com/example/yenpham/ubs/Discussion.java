package com.example.yenpham.ubs;

import java.text.DateFormat;

/**
 * Created by ThaoNguyen on 11/13/16.
 */

public class Discussion {
    private static int currentID =0;
    private int discussionID;
    private int clubID;
    private int disCreator;
    String contents;
    String dateTime;

    public Discussion() {

    }

    public Discussion(int discussionID, int clubID, int disCreator, String contents) {

        clubID= ++currentID;
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new java.util.Date());
        this.discussionID = discussionID;
        this.clubID = clubID;
        this.disCreator = disCreator;
        this.contents = contents;
        this.dateTime = currentDateTimeString;
    }

    public int getDiscussionID() {
        return discussionID;
    }

    public void setDiscussionID(int discussionID) {
        this.discussionID = discussionID;
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public int getDisCreator() {
        return disCreator;
    }

    public void setDisCreator(int disCreator) {
        this.disCreator = disCreator;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Discussion{" +
                "discussionID=" + discussionID +
                ", clubID=" + clubID +
                ", disCreator=" + disCreator +
                ", contents='" + contents + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
