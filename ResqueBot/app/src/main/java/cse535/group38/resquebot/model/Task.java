package cse535.group38.resquebot.model;

/**
 * Created by vignesh.jayabalan on 10/11/15.
 */
public class Task {

    //ID, TRIGGER_ID, ACTION_TYPE, TRIGGER_DATA, ACTION_DATA, STATUS_ID
    private int id;
    private int triggerId;
    private int actionType;
    private String triggerData;
    private String actionData;
    private int statusId;

    public Task(){}

    public Task(int id, int triggerId, int actionType, String triggerData, String actionData, int statusId){
        this.id = id;
        this.actionType = actionType;
        this.triggerId = triggerId;
        this.triggerData = triggerData;
        this.actionData = actionData;
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

    public String getTriggerData() {
        return triggerData;
    }

    public void setTriggerData(String triggerData) {
        this.triggerData = triggerData;
    }

    public String getActionData() {
        return actionData;
    }

    public void setActionData(String actionData) {
        this.actionData = actionData;
    }

    public int getStatusId(){
        return statusId;
    }

    public void setStatusId(int statusId){
        this.statusId = statusId;
    }

    public String getDisplayText(){
        return "On Connecting to : " + triggerData + " perform : " + actionType + " to " + actionData;
    }

    public String toString(){
        return "TASK DESP : id=" + id + " triggerId=" + triggerId + " actionType=" + actionType +
                " triggerData=" + triggerData + " actionData=" + actionData + " statusId=" + statusId;
    }

}
