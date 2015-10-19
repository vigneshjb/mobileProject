package cse535.group38.resquebot.model;

/**
 * Created by vignesh.jayabalan on 10/11/15.
 */
public class Task {

    //ID INTEGER, EVENT_ID INTEGER, STATUS INTEGER
    private int id;
    private int eventId;
    private int statusId;

    public Task(){}

    public Task(int id, int eventId, int statusId){
        this.id=id;
        this.eventId=eventId;
        this.statusId=statusId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String toString(){
        return "id = " + id + " eventId = " + eventId + " statusId = " + statusId;
    }

}
