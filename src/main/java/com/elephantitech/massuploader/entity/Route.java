package com.elephantitech.massuploader.entity;

import java.util.List;
import java.util.UUID;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

@Data
public class Route {
	
	
	@ExcelProperty("name")
	private String name;
	
	private String busId = UUID.randomUUID().toString();

	@ExcelProperty("address1")
	private String address1;
	
	@ExcelProperty("address2")
	private String address2;
	
	@ExcelProperty("city")
	private String city;
	
	@ExcelProperty("state")
	private String state;
	
	@ExcelProperty("pincode")
	private String pincode;
	
	private List<Location> locations;
}
