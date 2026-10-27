package prepaid.beans;

public class TransactionsBean {

	private String meter_serial_no;
	private String from_timestamp;
	private String to_timestamp;
	private String from_readings;
	private String to_readings;
	private String units_consumed;
	private String charge_for_units_consumed;
	private String previous_balance;
	private String revised_balance;
	private String average_unit_price;
	private String log_timestamp;
	
	public String getMeter_serial_no() {
		return meter_serial_no;
	}
	public void setMeter_serial_no(String meter_serial_no) {
		this.meter_serial_no = meter_serial_no;
	}
	public String getFrom_timestamp() {
		return from_timestamp;
	}
	public void setFrom_timestamp(String from_timestamp) {
		this.from_timestamp = from_timestamp;
	}
	public String getTo_timestamp() {
		return to_timestamp;
	}
	public void setTo_timestamp(String to_timestamp) {
		this.to_timestamp = to_timestamp;
	}
	public String getFrom_readings() {
		return from_readings;
	}
	public void setFrom_readings(String from_readings) {
		this.from_readings = from_readings;
	}
	public String getTo_readings() {
		return to_readings;
	}
	public void setTo_readings(String to_readings) {
		this.to_readings = to_readings;
	}
	public String getUnits_consumed() {
		return units_consumed;
	}
	public void setUnits_consumed(String units_consumed) {
		this.units_consumed = units_consumed;
	}
	public String getCharge_for_units_consumed() {
		return charge_for_units_consumed;
	}
	public void setCharge_for_units_consumed(String charge_for_units_consumed) {
		this.charge_for_units_consumed = charge_for_units_consumed;
	}
	public String getPrevious_balance() {
		return previous_balance;
	}
	public void setPrevious_balance(String previous_balance) {
		this.previous_balance = previous_balance;
	}
	public String getRevised_balance() {
		return revised_balance;
	}
	public void setRevised_balance(String revised_balance) {
		this.revised_balance = revised_balance;
	}
	public String getAverage_unit_price() {
		return average_unit_price;
	}
	public void setAverage_unit_price(String average_unit_price) {
		this.average_unit_price = average_unit_price;
	}
	public String getLog_timestamp() {
		return log_timestamp;
	}
	public void setLog_timestamp(String log_timestamp) {
		this.log_timestamp = log_timestamp;
	}
	
	
}
