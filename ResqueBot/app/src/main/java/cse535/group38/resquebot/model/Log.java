package cse535.group38.resquebot.model;

/**
 * Created by nikki on 11/7/2015.
 */
public class Log {

    private String timestamp;
    private String description;

    public Log(){}

    public Log(String timestamp, String description) {
        this.timestamp = timestamp;
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
