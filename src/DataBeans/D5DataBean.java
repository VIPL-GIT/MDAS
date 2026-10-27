/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBeans;

/**
 *
 * @author dell
 */
public class D5DataBean {
    
    private int cdfID;
    private int eventType;
    private String eventDesc;
    private String eventStatus;
    private String dataString;
    private String meterSerialNo;
    private String d5TimeStamp;
    private String logTimeStamp;

    public int getCdfID() {
        return cdfID;
    }

    public void setCdfID(int cdfID) {
        this.cdfID = cdfID;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public String getMeterSerialNo() {
        return meterSerialNo;
    }

    public void setMeterSerialNo(String meterSerialNo) {
        this.meterSerialNo = meterSerialNo;
    }

    public String getD5TimeStamp() {
        return d5TimeStamp;
    }

    public void setD5TimeStamp(String d5TimeStamp) {
        this.d5TimeStamp = d5TimeStamp;
    }

    public String getLogTimeStamp() {
        return logTimeStamp;
    }

    public void setLogTimeStamp(String logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }
    
    
}
