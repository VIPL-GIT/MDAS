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
public class DcuBean {

    private int dcuId;
    private String dcuName;
    private int subStationId;
    private String subStationName;
    private String divisionName;
    private String mandalName;

    public int getDcuId() {
        return dcuId;
    }

    public void setDcuId(int dcuId) {
        this.dcuId = dcuId;
    }

    public String getDcuName() {
        return dcuName;
    }

    public void setDcuName(String dcuName) {
        this.dcuName = dcuName;
    }

    public int getSubStationId() {
        return subStationId;
    }

    public void setSubStationId(int subStationId) {
        this.subStationId = subStationId;
    }

    public String getSubStationName() {
        return subStationName;
    }

    public void setSubStationName(String subStationName) {
        this.subStationName = subStationName;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getMandalName() {
        return mandalName;
    }

    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
    }


}
