package com.elephantitech.massuploader.entity;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

@Data
public class Bus {

	private String busId;

	@ExcelProperty("name")
	private String name;

//	@ExcelProperty("location")
//	private String location;

	@ExcelProperty("driverId")
	private String driverId;

	@ExcelProperty("maidId")
	private String maidId;

	@ExcelProperty("effectiveDate")
	private String effectiveDate;

	@ExcelProperty("expiryDate")
	private String expiryDate;

	@ExcelProperty("routeId")
	private String routeId;

//	@ExcelProperty("licensePlate")
//	private String licensePlate;

//	@ExcelProperty("owner")
//	private String owner;
//
//	@ExcelProperty("schoolId")
//	private String schoolId;

}
