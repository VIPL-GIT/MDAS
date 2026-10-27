/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prepaid.beans;

/**
 *
 * @author maruthi
 */
public class RechargeBean {
    
    private int record_id;
private String meter_serial_no;
private String present_kwh;
private String updated_datetime;
private String to_kwh;
private String recent_recharge_amount;
private String recharge_timestamp;
private String tampercount;
private String disconnected_timestamp;
private String status;
private String source_of_disconnect;
private java.sql.Timestamp log_timestamp;



    /**
     * @return the record_id
     */
    public int getRecord_id() {
        return record_id;
    }

    /**
     * @param record_id the record_id to set
     */
    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    /**
     * @return the meter_serial_no
     */
    public String getMeter_serial_no() {
        return meter_serial_no;
    }

    /**
     * @param meter_serial_no the meter_serial_no to set
     */
    public void setMeter_serial_no(String meter_serial_no) {
        this.meter_serial_no = meter_serial_no;
    }

    /**
     * @return the present_kwh
     */
    public String getPresent_kwh() {
        return present_kwh;
    }

    /**
     * @param present_kwh the present_kwh to set
     */
    public void setPresent_kwh(String present_kwh) {
        this.present_kwh = present_kwh;
    }

    /**
     * @return the to_kwh
     */
    public String getTo_kwh() {
        return to_kwh;
    }

    /**
     * @param to_kwh the to_kwh to set
     */
    public void setTo_kwh(String to_kwh) {
        this.to_kwh = to_kwh;
    }

    /**
     * @return the recent_recharge_amount
     */
    public String getRecent_recharge_amount() {
        return recent_recharge_amount;
    }

    /**
     * @param recent_recharge_amount the recent_recharge_amount to set
     */
    public void setRecent_recharge_amount(String recent_recharge_amount) {
        this.recent_recharge_amount = recent_recharge_amount;
    }

    /**
     * @return the recharge_timestamp
     */
    public String getRecharge_timestamp() {
        return recharge_timestamp;
    }

    /**
     * @param recharge_timestamp the recharge_timestamp to set
     */
    public void setRecharge_timestamp(String recharge_timestamp) {
        this.recharge_timestamp = recharge_timestamp;
    }

    /**
     * @return the tampercount
     */
    public String getTampercount() {
        return tampercount;
    }

    /**
     * @param tampercount the tampercount to set
     */
    public void setTampercount(String tampercount) {
        this.tampercount = tampercount;
    }

    /**
     * @return the disconnected_timestamp
     */
    public String getDisconnected_timestamp() {
        return disconnected_timestamp;
    }

    /**
     * @param disconnected_timestamp the disconnected_timestamp to set
     */
    public void setDisconnected_timestamp(String disconnected_timestamp) {
        this.disconnected_timestamp = disconnected_timestamp;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the source_of_disconnect
     */
    public String getSource_of_disconnect() {
        return source_of_disconnect;
    }

    /**
     * @param source_of_disconnect the source_of_disconnect to set
     */
    public void setSource_of_disconnect(String source_of_disconnect) {
        this.source_of_disconnect = source_of_disconnect;
    }

    /**
     * @return the log_timestamp
     */
    public java.sql.Timestamp getLog_timestamp() {
        return log_timestamp;
    }

    /**
     * @param log_timestamp the log_timestamp to set
     */
    public void setLog_timestamp(java.sql.Timestamp log_timestamp) {
        this.log_timestamp = log_timestamp;
    }

    /**
     * @return the updated_datetime
     */
    public String getUpdated_datetime() {
        return updated_datetime;
    }

    /**
     * @param updated_datetime the updated_datetime to set
     */
    public void setUpdated_datetime(String updated_datetime) {
        this.updated_datetime = updated_datetime;
    }




}
