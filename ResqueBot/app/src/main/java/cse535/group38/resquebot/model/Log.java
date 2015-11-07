package cse535.group38.resquebot.model;

/**
 * Created by nikki on 11/7/2015.
 */
public class Log {

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public Log(String date, String description) {
        this.date = date;
        this.description = description;
    }
    public Log(){}
}
