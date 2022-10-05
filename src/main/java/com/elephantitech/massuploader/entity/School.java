package com.elephantitech.massuploader.entity;

import javax.validation.constraints.Email;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

@Data
public class School {

	private String schoolId;

	@ExcelProperty("name")
	private String name;

	@ExcelProperty("address")
	private String address;

	@ExcelProperty("phone")
	private String phone;

	@ExcelProperty("City")
	private String city;
	
	@ExcelProperty("State")
	private String state;
	
	@ExcelProperty("PinCode")
	private String pincode;
	
	@ExcelProperty("email")
	@Email(regexp = "^(.+)@(\\\\S+)$")
	private String email;
}
