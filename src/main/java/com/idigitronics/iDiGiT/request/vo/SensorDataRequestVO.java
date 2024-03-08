/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

import java.util.List;

/**
 * @author vml
 *
 */
public class SensorDataRequestVO {
	
	private String equipment_serial_id;
	private List<Integer> readings;
	private List<Integer> reader_sensor_status;
	private List<Float> per_day_flow_rate;
	private List<Float> live_flow_rate;
	private int record_interval;
	private int sync_interval;
	private int rssi;
	private List<Integer> digital_outputs;
	private List<Float> analog_inputs;
	private List<Float> analog_outputs;
	private List<Float> voltage_outputs;
	private int battery_percentage;
	private int online_powersupply;
	private Alarms alarms;
	private long timestamp;
	
	
	public String getEquipment_serial_id() {
		return equipment_serial_id;
	}
	public void setEquipment_serial_id(String equipment_serial_id) {
		this.equipment_serial_id = equipment_serial_id;
	}
	public List<Integer> getReadings() {
		return readings;
	}
	public void setReadings(List<Integer> readings) {
		this.readings = readings;
	}
	public List<Integer> getReader_sensor_status() {
		return reader_sensor_status;
	}
	public void setReader_sensor_status(List<Integer> reader_sensor_status) {
		this.reader_sensor_status = reader_sensor_status;
	}
	public List<Float> getPer_day_flow_rate() {
		return per_day_flow_rate;
	}
	public void setPer_day_flow_rate(List<Float> per_day_flow_rate) {
		this.per_day_flow_rate = per_day_flow_rate;
	}
	public List<Float> getLive_flow_rate() {
		return live_flow_rate;
	}
	public void setLive_flow_rate(List<Float> live_flow_rate) {
		this.live_flow_rate = live_flow_rate;
	}
	public int getRecord_interval() {
		return record_interval;
	}
	public void setRecord_interval(int record_interval) {
		this.record_interval = record_interval;
	}
	public int getSync_interval() {
		return sync_interval;
	}
	public void setSync_interval(int sync_interval) {
		this.sync_interval = sync_interval;
	}
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	public List<Integer> getDigital_outputs() {
		return digital_outputs;
	}
	public void setDigital_outputs(List<Integer> digital_outputs) {
		this.digital_outputs = digital_outputs;
	}
	public List<Float> getAnalog_inputs() {
		return analog_inputs;
	}
	public void setAnalog_inputs(List<Float> analog_inputs) {
		this.analog_inputs = analog_inputs;
	}
	public List<Float> getAnalog_outputs() {
		return analog_outputs;
	}
	public void setAnalog_outputs(List<Float> analog_outputs) {
		this.analog_outputs = analog_outputs;
	}
	public List<Float> getVoltage_outputs() {
		return voltage_outputs;
	}
	public void setVoltage_outputs(List<Float> voltage_outputs) {
		this.voltage_outputs = voltage_outputs;
	}
	public int getBattery_percentage() {
		return battery_percentage;
	}
	public void setBattery_percentage(int battery_percentage) {
		this.battery_percentage = battery_percentage;
	}
	public int getOnline_powersupply() {
		return online_powersupply;
	}
	public void setOnline_powersupply(int online_powersupply) {
		this.online_powersupply = online_powersupply;
	}
	public Alarms getAlarms() {
		return alarms;
	}
	public void setAlarms(Alarms alarms) {
		this.alarms = alarms;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
