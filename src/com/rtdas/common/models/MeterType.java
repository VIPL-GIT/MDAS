package com.rtdas.common.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="meter_type")
public class MeterType implements Serializable {
	
private static final long serialVersionUID = 5462223600l;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="slno")
private int slno;
@Column(name="meter_type")
private String meter_type;
@Column(name="type_desc")
private String type_desc;


public int getSlno() {
	return slno;
}
public void setSlno(int slno) {
	this.slno = slno;
}
public String getMeter_type() {
	return meter_type;
}
public void setMeter_type(String meter_type) {
	this.meter_type = meter_type;
}
public String getType_desc() {
	return type_desc;
}
public void setType_desc(String type_desc) {
	this.type_desc = type_desc;
}



}
