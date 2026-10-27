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
public class LsCommDatesBean {
 
    private int appId;
    private String meterNo;
    private String lsDate;
    private int lsCount;
    private String logTimeStamp;

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public String getLsDate() {
        return lsDate;
    }

    public void setLsDate(String lsDate) {
        this.lsDate = lsDate;
    }

    public int getLsCount() {
        return lsCount;
    }

    public void setLsCount(int lsCount) {
        this.lsCount = lsCount;
    }

    public String getLogTimeStamp() {
        return logTimeStamp;
    }

    public void setLogTimeStamp(String logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }
    
    
}
