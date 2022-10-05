package com.elephantitech.massuploader.entity;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

@Data
public class Student {

	private String studentId;
	private String studentName;
	private String studentAddress;
	private String dob;
	private String studentClass;
	
	@ExcelProperty("strGender")
	private String strGender;
	
	@ExcelProperty("gender")
	private Gender gender;
	private String images;
	private String bloodType;
	private String parentId;
	private String busId;
	private String schoolId;
	
	
}
