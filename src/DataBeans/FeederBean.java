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
public class FeederBean {
    
    private int feederId;
    private String feederName;
    private int classVoltage;
    private int dcuId;
    private int substationId;
    private String substationName;
    private String sectionName;
    private String mandalName;

    public String getMandalName() {
        return mandalName;
    }

    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
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

    public int getClassVoltage() {
        return classVoltage;
    }

    public void setClassVoltage(int classVoltage) {
        this.classVoltage = classVoltage;
    }

    public int getDcuId() {
        return dcuId;
    }

    public void setDcuId(int dcuId) {
        this.dcuId = dcuId;
    }

    public int getSubstationId() {
        return substationId;
    }

    public void setSubstationId(int substationId) {
        this.substationId = substationId;
    }

    public String getSubstationName() {
        return substationName;
    }

    public void setSubstationName(String substationName) {
        this.substationName = substationName;
    }
    
    
}
