package cse535.group38.resquebot.dao;

import java.util.ArrayList;
import java.util.List;

import cse535.group38.resquebot.model.Log;
import cse535.group38.resquebot.utils.Constants;

/**
 * Created by vignesh.jayabalan on 11/9/15.
 */
public class UploadLogsDAO {
    List<Log> logs;
    String deviceID;

    public UploadLogsDAO(){
        logs = new ArrayList<>();
        deviceID = Constants.DEVICE_ID; //TODO: use something else ... don't make it a constant
    }

    public void setLogs(List<Log> logs){
        this.logs=logs;
    }

    public List<Log> getLogs(){
        return logs;
    }

    public int size(){
        return logs.size();
    }

    //Used only for Testing
    public void setSomeLogs(){
        this.logs.add(new Log("asdss1","Dsfsdfsdf1"));
        this.logs.add(new Log("asdss2","Dsfsdfsdf2"));
        this.logs.add(new Log("asdss3","Dsfsdfsdf3"));
        this.logs.add(new Log("asdss4","Dsfsdfsdf4"));
        this.logs.add(new Log("asdss5","Dsfsdfsdf5"));
    }

}
