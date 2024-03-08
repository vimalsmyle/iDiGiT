/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import com.idigitronics.iDiGiT.request.vo.Status;

/**
 * @author K Vimal Kumar
 *
 */
public class LastReadingResponseVO {
	
	private long readingID;
	private String miuID;
	private int type;
	private String sync_time;
	private int sync_interval;
	private int pre_post_paid;
	private float bat_volt;
	private int valve_configuration;
	private int valve_live_status;
	private float credit;
	private float tariff;
	private float emergency_credit;
	private int days_elapsed_after_valve_trip;
	private float reading;
	private Status status;
	private int transaction_id;
	private int cmd_status;
	
	private String source;
	private boolean topupSMS;
	private String logDate;
	
	public long getReadingID() {
		return readingID;
	}
	public void setReadingID(long readingID) {
		this.readingID = readingID;
	}
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSync_time() {
		return sync_time;
	}
	public void setSync_time(String sync_time) {
		this.sync_time = sync_time;
	}
	public int getSync_interval() {
		return sync_interval;
	}
	public void setSync_interval(int sync_interval) {
		this.sync_interval = sync_interval;
	}
	public int getPre_post_paid() {
		return pre_post_paid;
	}
	public void setPre_post_paid(int pre_post_paid) {
		this.pre_post_paid = pre_post_paid;
	}
	public float getBat_volt() {
		return bat_volt;
	}
	public void setBat_volt(float bat_volt) {
		this.bat_volt = bat_volt;
	}
	public int getValve_configuration() {
		return valve_configuration;
	}
	public void setValve_configuration(int valve_configuration) {
		this.valve_configuration = valve_configuration;
	}
	public int getValve_live_status() {
		return valve_live_status;
	}
	public void setValve_live_status(int valve_live_status) {
		this.valve_live_status = valve_live_status;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public float getTariff() {
		return tariff;
	}
	public void setTariff(float tariff) {
		this.tariff = tariff;
	}
	public float getEmergency_credit() {
		return emergency_credit;
	}
	public void setEmergency_credit(float emergency_credit) {
		this.emergency_credit = emergency_credit;
	}
	public int getDays_elapsed_after_valve_trip() {
		return days_elapsed_after_valve_trip;
	}
	public void setDays_elapsed_after_valve_trip(int days_elapsed_after_valve_trip) {
		this.days_elapsed_after_valve_trip = days_elapsed_after_valve_trip;
	}
	public float getReading() {
		return reading;
	}
	public void setReading(float reading) {
		this.reading = reading;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getCmd_status() {
		return cmd_status;
	}
	public void setCmd_status(int cmd_status) {
		this.cmd_status = cmd_status;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public boolean isTopupSMS() {
		return topupSMS;
	}
	public void setTopupSMS(boolean topupSMS) {
		this.topupSMS = topupSMS;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	
}
