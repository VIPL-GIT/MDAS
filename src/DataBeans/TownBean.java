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
public class TownBean {
    
    private int townId;
    private String townName;
    private int subdivId;
    private String subdivName;

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getSubdivId() {
        return subdivId;
    }

    public void setSubdivId(int subdivId) {
        this.subdivId = subdivId;
    }

    public String getSubdivName() {
        return subdivName;
    }

    public void setSubdivName(String subdivName) {
        this.subdivName = subdivName;
    }
    
    
}
