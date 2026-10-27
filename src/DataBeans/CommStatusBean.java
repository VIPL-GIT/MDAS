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
public class CommStatusBean {
    
    private int recordId;
    private String meterSerialNo;
    private String commDate;
    private int cd;
    private int ip;
    private int ml;
    private int nt;
    private int od;
    private int dd;
    private int crcFail;
    private int cdf;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getMeterSerialNo() {
        return meterSerialNo;
    }

    public void setMeterSerialNo(String meterSerialNo) {
        this.meterSerialNo = meterSerialNo;
    }

    public String getCommDate() {
        return commDate;
    }

    public void setCommDate(String commDate) {
        this.commDate = commDate;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public int getMl() {
        return ml;
    }

    public void setMl(int ml) {
        this.ml = ml;
    }

    public int getNt() {
        return nt;
    }

    public void setNt(int nt) {
        this.nt = nt;
    }

    public int getOd() {
        return od;
    }

    public void setOd(int od) {
        this.od = od;
    }

    public int getDd() {
        return dd;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public int getCrcFail() {
        return crcFail;
    }

    public void setCrcFail(int crcFail) {
        this.crcFail = crcFail;
    }

    public int getCdf() {
        return cdf;
    }

    public void setCdf(int cdf) {
        this.cdf = cdf;
    }
    
    
}
