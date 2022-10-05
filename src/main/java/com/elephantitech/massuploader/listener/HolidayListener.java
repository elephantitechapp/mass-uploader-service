package com.elephantitech.massuploader.listener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.elephantitech.massuploader.entity.Holiday;

public class HolidayListener extends AnalysisEventListener<Holiday> {

	private List<Holiday> holidays = new ArrayList<>();

	public List<Holiday> getHolidays() {
		return holidays;
	}

	@Override
	public void invoke(Holiday data, AnalysisContext context) {
		convertStringDateToLocalDate(data);
		holidays.add(data);
		System.out.println("Parsed row and got this data --> " + data);
	}

	private void convertStringDateToLocalDate(Holiday holiday) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate localDate = LocalDate.parse(holiday.getStrDate(), formatter);
		holiday.setDate(localDate);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		System.out.println("All parsing completed");

	}

}
