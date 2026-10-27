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
public class DtrBean {
    
    private int dtrId;
    private String dtrName;
    private int feederId;
    private String feederName;
    private String subStationName;
    private String discomName;

    public String getSubStationName() {
        return subStationName;
    }

    public void setSubStationName(String subStationName) {
        this.subStationName = subStationName;
    }

    public String getDiscomName() {
        return discomName;
    }

    public void setDiscomName(String discomName) {
        this.discomName = discomName;
    }
    
    

    public int getDtrId() {
        return dtrId;
    }

    public void setDtrId(int dtrId) {
        this.dtrId = dtrId;
    }

    public String getDtrName() {
        return dtrName;
    }

    public void setDtrName(String dtrName) {
        this.dtrName = dtrName;
    }

    public int getFeederId() {
        return feederId;
    }

    public void setFeederId(int feederId) {
        this.feederId = feederId;
    }

    public String getFeederName() {
        return feederName;
    }

    public void setFeederName(String feederName) {
        this.feederName = feederName;
    }
    
    
    
    
}
