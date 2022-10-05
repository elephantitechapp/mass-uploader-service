package com.elephantitech.massuploader.entity;

import java.time.LocalDate;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

@Data
public class Holiday {
	
	@ExcelProperty("holidayId")
    private String holidayId;
	
	@ExcelProperty("dateInDD-MM-YYYY")
    private String strDate;
	
	@ExcelProperty("schoolId")
    private String schoolId;
	
	@ExcelProperty("description")
    private String description;
    
	private LocalDate date;;
	
}
