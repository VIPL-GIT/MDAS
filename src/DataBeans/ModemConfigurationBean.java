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
public class ModemConfigurationBean {
    
    private int configId;
    private String masterMobileNo;
    private String APN;
    private String ftpPort;
    private String ftpUser;
    private String ftpPwd;
    private String serverIP;
    private String socketPort;
    private String consumerId;
    private String meterType;
    private String smsDeliveryMobileNos;
    private String syncMeterRtc;
    private String modemSerialNo;
    private String configUpdateTimeStamp;
    private String configTotalData;
    private String logTimeStamp;
    private String meterId;

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public String getMasterMobileNo() {
        return masterMobileNo;
    }

    public void setMasterMobileNo(String masterMobileNo) {
        this.masterMobileNo = masterMobileNo;
    }

    public String getAPN() {
        return APN;
    }

    public void setAPN(String APN) {
        this.APN = APN;
    }

    public String getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getFtpPwd() {
        return ftpPwd;
    }

    public void setFtpPwd(String ftpPwd) {
        this.ftpPwd = ftpPwd;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getSocketPort() {
        return socketPort;
    }

    public void setSocketPort(String socketPort) {
        this.socketPort = socketPort;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public String getSmsDeliveryMobileNos() {
        return smsDeliveryMobileNos;
    }

    public void setSmsDeliveryMobileNos(String smsDeliveryMobileNos) {
        this.smsDeliveryMobileNos = smsDeliveryMobileNos;
    }

    public String getSyncMeterRtc() {
        return syncMeterRtc;
    }

    public void setSyncMeterRtc(String syncMeterRtc) {
        this.syncMeterRtc = syncMeterRtc;
    }

    public String getModemSerialNo() {
        return modemSerialNo;
    }

    public void setModemSerialNo(String modemSerialNo) {
        this.modemSerialNo = modemSerialNo;
    }

    public String getConfigUpdateTimeStamp() {
        return configUpdateTimeStamp;
    }

    public void setConfigUpdateTimeStamp(String configUpdateTimeStamp) {
        this.configUpdateTimeStamp = configUpdateTimeStamp;
    }

    public String getConfigTotalData() {
        return configTotalData;
    }

    public void setConfigTotalData(String configTotalData) {
        this.configTotalData = configTotalData;
    }

    public String getLogTimeStamp() {
        return logTimeStamp;
    }

    public void setLogTimeStamp(String logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }
    
        
    
}
