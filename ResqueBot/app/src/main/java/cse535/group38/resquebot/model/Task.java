package cse535.group38.resquebot.model;

/**
 * Created by vignesh.jayabalan on 10/11/15.
 */
public class Task {

    //ID INTEGER, EVENT_ID INTEGER, TRIGGER_ID INTEGER, ACTION_TYPE INTEGER, FROM_STATE VARCHAR(20), TO_STATE VARCHAR(20)
    private int id;
    private int triggerId;
    private int actionType;
    private String fromState;
    private String toState;
    private int statusId;

    public Task(){}

    public Task(int id, int triggerId, int actionType, String fromState, String toState, int statusId){
        this.id = id;
        this.actionType = actionType;
        this.triggerId = triggerId;
        this.fromState = fromState;
        this.toState = toState;
        this.statusId = statusId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(int triggerId) {
        this.triggerId = triggerId;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getFromState() {
        return fromState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public String getToState() {
        return toState;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }

    public int getStatusId(){
        return statusId;
    }

    public void setStatusId(int statusId){
        this.statusId = statusId;
    }

    public String toString(){
        return "TASK DESP : id=" + id + " triggerId=" + triggerId + " actionType=" + actionType +
                " fromState=" + fromState + " toState=" + toState + " statusId=" + statusId;
    }
}
