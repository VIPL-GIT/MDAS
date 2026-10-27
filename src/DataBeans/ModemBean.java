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
public class ModemBean {
    
    private int modemId;
    private String modemSerialNo;
    private String imeiNo;
    private String mobileNo;
    private String staticIp;

    public int getModemId() {
        return modemId;
    }

    public void setModemId(int modemId) {
        this.modemId = modemId;
    }

    public String getModemSerialNo() {
        return modemSerialNo;
    }

    public void setModemSerialNo(String modemSerialNo) {
        this.modemSerialNo = modemSerialNo;
    }

    public String getImeiNo() {
        return imeiNo;
    }

    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStaticIp() {
        return staticIp;
    }

    public void setStaticIp(String staticIp) {
        this.staticIp = staticIp;
    }
    
    
}
