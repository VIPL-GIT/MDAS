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
public class PrepaidInstant {
    private String meter_type;
private String G1;
private String G2;
private String G3;
private String one_PH_VOLTAGE;
private String RPH_VOLTAGE;
private String YPH_VOLTAGE;
private String BPH_VOLTAGE;
private String RPH_LINE_CURRENT;
private String YPH_LINE_CURRENT;
private String BPH_LINE_CURRENT;
private String one_PH_CURRENT;
private String NEUTRAL_CURRENT;
private String ACT_POWER;
private String REACT_POWER;
private String APPARENT_POWER;
private String RPH_POWER_FACTOR;
private String YPH_POWER_FACTOR;
private String BPH_POWER_FACTOR;
private String AVG_POWER_FACTOR;
private String FREQUENCY;
private String KWH;
private String KVAH;
private String KVARH_LAG;
private String KVARH_LEAD;
private String MDKW;
private String MDKVA;
private String MDKWOCCDATE;
private String MDKVAOCCDATE;
private String KWH_E;
private String KVAH_E;
private String POWER_ON_DURATION;
private String TAMPER_COUNT;
private String BILLING_COUNT;
private String PROGRAMMING_COUNT;
private String disc_con;
private String limiter;
private String METER_TIME_STAMP;

private String base_kwh;
private String consumed_units_kwh;
private String charge_for_consumed_units;
private String previous_balance;
private String current_balance;


    public String getBase_kwh() {
	return base_kwh;
}

public void setBase_kwh(String base_kwh) {
	this.base_kwh = base_kwh;
}

public String getConsumed_units_kwh() {
	return consumed_units_kwh;
}

public void setConsumed_units_kwh(String consumed_units_kwh) {
	this.consumed_units_kwh = consumed_units_kwh;
}

public String getCharge_for_consumed_units() {
	return charge_for_consumed_units;
}

public void setCharge_for_consumed_units(String charge_for_consumed_units) {
	this.charge_for_consumed_units = charge_for_consumed_units;
}

public String getPrevious_balance() {
	return previous_balance;
}

public void setPrevious_balance(String previous_balance) {
	this.previous_balance = previous_balance;
}

public String getCurrent_balance() {
	return current_balance;
}

public void setCurrent_balance(String current_balance) {
	this.current_balance = current_balance;
}

	/**
     * @return the meter_type
     */
    public String getMeter_type() {
        return meter_type;
    }

    /**
     * @param meter_type the meter_type to set
     */
    public void setMeter_type(String meter_type) {
        this.meter_type = meter_type;
    }

    /**
     * @return the G1
     */
    public String getG1() {
        return G1;
    }

    /**
     * @param G1 the G1 to set
     */
    public void setG1(String G1) {
        this.G1 = G1;
    }

    /**
     * @return the G2
     */
    public String getG2() {
        return G2;
    }

    /**
     * @param G2 the G2 to set
     */
    public void setG2(String G2) {
        this.G2 = G2;
    }

    /**
     * @return the G3
     */
    public String getG3() {
        return G3;
    }

    /**
     * @param G3 the G3 to set
     */
    public void setG3(String G3) {
        this.G3 = G3;
    }

    /**
     * @return the one_PH_VOLTAGE
     */
    public String getOne_PH_VOLTAGE() {
        return one_PH_VOLTAGE;
    }

    /**
     * @param one_PH_VOLTAGE the one_PH_VOLTAGE to set
     */
    public void setOne_PH_VOLTAGE(String one_PH_VOLTAGE) {
        this.one_PH_VOLTAGE = one_PH_VOLTAGE;
    }

    /**
     * @return the RPH_VOLTAGE
     */
    public String getRPH_VOLTAGE() {
        return RPH_VOLTAGE;
    }

    /**
     * @param RPH_VOLTAGE the RPH_VOLTAGE to set
     */
    public void setRPH_VOLTAGE(String RPH_VOLTAGE) {
        this.RPH_VOLTAGE = RPH_VOLTAGE;
    }

    /**
     * @return the YPH_VOLTAGE
     */
    public String getYPH_VOLTAGE() {
        return YPH_VOLTAGE;
    }

    /**
     * @param YPH_VOLTAGE the YPH_VOLTAGE to set
     */
    public void setYPH_VOLTAGE(String YPH_VOLTAGE) {
        this.YPH_VOLTAGE = YPH_VOLTAGE;
    }

    /**
     * @return the BPH_VOLTAGE
     */
    public String getBPH_VOLTAGE() {
        return BPH_VOLTAGE;
    }

    /**
     * @param BPH_VOLTAGE the BPH_VOLTAGE to set
     */
    public void setBPH_VOLTAGE(String BPH_VOLTAGE) {
        this.BPH_VOLTAGE = BPH_VOLTAGE;
    }

    /**
     * @return the RPH_LINE_CURRENT
     */
    public String getRPH_LINE_CURRENT() {
        return RPH_LINE_CURRENT;
    }

    /**
     * @param RPH_LINE_CURRENT the RPH_LINE_CURRENT to set
     */
    public void setRPH_LINE_CURRENT(String RPH_LINE_CURRENT) {
        this.RPH_LINE_CURRENT = RPH_LINE_CURRENT;
    }

    /**
     * @return the YPH_LINE_CURRENT
     */
    public String getYPH_LINE_CURRENT() {
        return YPH_LINE_CURRENT;
    }

    /**
     * @param YPH_LINE_CURRENT the YPH_LINE_CURRENT to set
     */
    public void setYPH_LINE_CURRENT(String YPH_LINE_CURRENT) {
        this.YPH_LINE_CURRENT = YPH_LINE_CURRENT;
    }

    /**
     * @return the BPH_LINE_CURRENT
     */
    public String getBPH_LINE_CURRENT() {
        return BPH_LINE_CURRENT;
    }

    /**
     * @param BPH_LINE_CURRENT the BPH_LINE_CURRENT to set
     */
    public void setBPH_LINE_CURRENT(String BPH_LINE_CURRENT) {
        this.BPH_LINE_CURRENT = BPH_LINE_CURRENT;
    }

    /**
     * @return the one_PH_CURRENT
     */
    public String getOne_PH_CURRENT() {
        return one_PH_CURRENT;
    }

    /**
     * @param one_PH_CURRENT the one_PH_CURRENT to set
     */
    public void setOne_PH_CURRENT(String one_PH_CURRENT) {
        this.one_PH_CURRENT = one_PH_CURRENT;
    }

    /**
     * @return the NEUTRAL_CURRENT
     */
    public String getNEUTRAL_CURRENT() {
        return NEUTRAL_CURRENT;
    }

    /**
     * @param NEUTRAL_CURRENT the NEUTRAL_CURRENT to set
     */
    public void setNEUTRAL_CURRENT(String NEUTRAL_CURRENT) {
        this.NEUTRAL_CURRENT = NEUTRAL_CURRENT;
    }

    /**
     * @return the ACT_POWER
     */
    public String getACT_POWER() {
        return ACT_POWER;
    }

    /**
     * @param ACT_POWER the ACT_POWER to set
     */
    public void setACT_POWER(String ACT_POWER) {
        this.ACT_POWER = ACT_POWER;
    }

    /**
     * @return the REACT_POWER
     */
    public String getREACT_POWER() {
        return REACT_POWER;
    }

    /**
     * @param REACT_POWER the REACT_POWER to set
     */
    public void setREACT_POWER(String REACT_POWER) {
        this.REACT_POWER = REACT_POWER;
    }

    /**
     * @return the APPARENT_POWER
     */
    public String getAPPARENT_POWER() {
        return APPARENT_POWER;
    }

    /**
     * @param APPARENT_POWER the APPARENT_POWER to set
     */
    public void setAPPARENT_POWER(String APPARENT_POWER) {
        this.APPARENT_POWER = APPARENT_POWER;
    }

    /**
     * @return the RPH_POWER_FACTOR
     */
    public String getRPH_POWER_FACTOR() {
        return RPH_POWER_FACTOR;
    }

    /**
     * @param RPH_POWER_FACTOR the RPH_POWER_FACTOR to set
     */
    public void setRPH_POWER_FACTOR(String RPH_POWER_FACTOR) {
        this.RPH_POWER_FACTOR = RPH_POWER_FACTOR;
    }

    /**
     * @return the YPH_POWER_FACTOR
     */
    public String getYPH_POWER_FACTOR() {
        return YPH_POWER_FACTOR;
    }

    /**
     * @param YPH_POWER_FACTOR the YPH_POWER_FACTOR to set
     */
    public void setYPH_POWER_FACTOR(String YPH_POWER_FACTOR) {
        this.YPH_POWER_FACTOR = YPH_POWER_FACTOR;
    }

    /**
     * @return the BPH_POWER_FACTOR
     */
    public String getBPH_POWER_FACTOR() {
        return BPH_POWER_FACTOR;
    }

    /**
     * @param BPH_POWER_FACTOR the BPH_POWER_FACTOR to set
     */
    public void setBPH_POWER_FACTOR(String BPH_POWER_FACTOR) {
        this.BPH_POWER_FACTOR = BPH_POWER_FACTOR;
    }

    /**
     * @return the AVG_POWER_FACTOR
     */
    public String getAVG_POWER_FACTOR() {
        return AVG_POWER_FACTOR;
    }

    /**
     * @param AVG_POWER_FACTOR the AVG_POWER_FACTOR to set
     */
    public void setAVG_POWER_FACTOR(String AVG_POWER_FACTOR) {
        this.AVG_POWER_FACTOR = AVG_POWER_FACTOR;
    }

    /**
     * @return the FREQUENCY
     */
    public String getFREQUENCY() {
        return FREQUENCY;
    }

    /**
     * @param FREQUENCY the FREQUENCY to set
     */
    public void setFREQUENCY(String FREQUENCY) {
        this.FREQUENCY = FREQUENCY;
    }

    /**
     * @return the KWH
     */
    public String getKWH() {
        return KWH;
    }

    /**
     * @param KWH the KWH to set
     */
    public void setKWH(String KWH) {
        this.KWH = KWH;
    }

    /**
     * @return the KVAH
     */
    public String getKVAH() {
        return KVAH;
    }

    /**
     * @param KVAH the KVAH to set
     */
    public void setKVAH(String KVAH) {
        this.KVAH = KVAH;
    }

    /**
     * @return the KVARH_LAG
     */
    public String getKVARH_LAG() {
        return KVARH_LAG;
    }

    /**
     * @param KVARH_LAG the KVARH_LAG to set
     */
    public void setKVARH_LAG(String KVARH_LAG) {
        this.KVARH_LAG = KVARH_LAG;
    }

    /**
     * @return the KVARH_LEAD
     */
    public String getKVARH_LEAD() {
        return KVARH_LEAD;
    }

    /**
     * @param KVARH_LEAD the KVARH_LEAD to set
     */
    public void setKVARH_LEAD(String KVARH_LEAD) {
        this.KVARH_LEAD = KVARH_LEAD;
    }

    /**
     * @return the MDKW
     */
    public String getMDKW() {
        return MDKW;
    }

    /**
     * @param MDKW the MDKW to set
     */
    public void setMDKW(String MDKW) {
        this.MDKW = MDKW;
    }

    /**
     * @return the MDKVA
     */
    public String getMDKVA() {
        return MDKVA;
    }

    /**
     * @param MDKVA the MDKVA to set
     */
    public void setMDKVA(String MDKVA) {
        this.MDKVA = MDKVA;
    }

    /**
     * @return the MDKWOCCDATE
     */
    public String getMDKWOCCDATE() {
        return MDKWOCCDATE;
    }

    /**
     * @param MDKWOCCDATE the MDKWOCCDATE to set
     */
    public void setMDKWOCCDATE(String MDKWOCCDATE) {
        this.MDKWOCCDATE = MDKWOCCDATE;
    }

    /**
     * @return the MDKVAOCCDATE
     */
    public String getMDKVAOCCDATE() {
        return MDKVAOCCDATE;
    }

    /**
     * @param MDKVAOCCDATE the MDKVAOCCDATE to set
     */
    public void setMDKVAOCCDATE(String MDKVAOCCDATE) {
        this.MDKVAOCCDATE = MDKVAOCCDATE;
    }

    /**
     * @return the KWH_E
     */
    public String getKWH_E() {
        return KWH_E;
    }

    /**
     * @param KWH_E the KWH_E to set
     */
    public void setKWH_E(String KWH_E) {
        this.KWH_E = KWH_E;
    }

    /**
     * @return the KVAH_E
     */
    public String getKVAH_E() {
        return KVAH_E;
    }

    /**
     * @param KVAH_E the KVAH_E to set
     */
    public void setKVAH_E(String KVAH_E) {
        this.KVAH_E = KVAH_E;
    }

    /**
     * @return the POWER_ON_DURATION
     */
    public String getPOWER_ON_DURATION() {
        return POWER_ON_DURATION;
    }

    /**
     * @param POWER_ON_DURATION the POWER_ON_DURATION to set
     */
    public void setPOWER_ON_DURATION(String POWER_ON_DURATION) {
        this.POWER_ON_DURATION = POWER_ON_DURATION;
    }

    /**
     * @return the TAMPER_COUNT
     */
    public String getTAMPER_COUNT() {
        return TAMPER_COUNT;
    }

    /**
     * @param TAMPER_COUNT the TAMPER_COUNT to set
     */
    public void setTAMPER_COUNT(String TAMPER_COUNT) {
        this.TAMPER_COUNT = TAMPER_COUNT;
    }

    /**
     * @return the BILLING_COUNT
     */
    public String getBILLING_COUNT() {
        return BILLING_COUNT;
    }

    /**
     * @param BILLING_COUNT the BILLING_COUNT to set
     */
    public void setBILLING_COUNT(String BILLING_COUNT) {
        this.BILLING_COUNT = BILLING_COUNT;
    }

    /**
     * @return the PROGRAMMING_COUNT
     */
    public String getPROGRAMMING_COUNT() {
        return PROGRAMMING_COUNT;
    }

    /**
     * @param PROGRAMMING_COUNT the PROGRAMMING_COUNT to set
     */
    public void setPROGRAMMING_COUNT(String PROGRAMMING_COUNT) {
        this.PROGRAMMING_COUNT = PROGRAMMING_COUNT;
    }

    /**
     * @return the disc_con
     */
    public String getDisc_con() {
        return disc_con;
    }

    /**
     * @param disc_con the disc_con to set
     */
    public void setDisc_con(String disc_con) {
        this.disc_con = disc_con;
    }

    /**
     * @return the limiter
     */
    public String getLimiter() {
        return limiter;
    }

    /**
     * @param limiter the limiter to set
     */
    public void setLimiter(String limiter) {
        this.limiter = limiter;
    }

    /**
     * @return the METER_TIME_STAMP
     */
    public String getMETER_TIME_STAMP() {
        return METER_TIME_STAMP;
    }

    /**
     * @param METER_TIME_STAMP the METER_TIME_STAMP to set
     */
    public void setMETER_TIME_STAMP(String METER_TIME_STAMP) {
        this.METER_TIME_STAMP = METER_TIME_STAMP;
    }



}
