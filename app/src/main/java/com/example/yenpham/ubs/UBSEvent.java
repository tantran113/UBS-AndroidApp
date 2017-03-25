package com.example.yenpham.ubs;

/**
 * Created by ThaoNguyen on 11/13/16.

 /* Created by ThaoNguyen on 11/13/16.

*/

public class UBSEvent {
    private static int currentID=0;
    private int eventID;
    private int clubID;
    private String eventPics;
    private String eventDescription;
    private String dateTime;
    private String eventLocation;

    public UBSEvent() {
    }

    public UBSEvent(int clubID, String eventPics,
                    String eventDescription, String dateTime, String eventLocation) {
        eventID = ++currentID;
        this.clubID = clubID;
        this.eventPics = eventPics;
        this.eventDescription = eventDescription;
        this.dateTime = dateTime;
        this.eventLocation = eventLocation;
    }

    @Override
    public String toString() {
        return "UBSEvent{" +
                "eventID=" + eventID +
                ", clubID=" + clubID +
                ", eventPics='" + eventPics + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", eventLocation='" + eventLocation + '\'' +
                '}';
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public String getEventPics() {
        return eventPics;
    }

    public void setEventPics(String eventPics) {
        this.eventPics = eventPics;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
