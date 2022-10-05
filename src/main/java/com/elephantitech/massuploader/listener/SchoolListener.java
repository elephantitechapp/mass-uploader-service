package com.elephantitech.massuploader.listener;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.elephantitech.massuploader.entity.School;

public class SchoolListener extends AnalysisEventListener<School> {

	private List<School> schools = new ArrayList<>();

	public List<School> getSchools() {
		return schools;
	}

	@Override
	public void invoke(School data, AnalysisContext context) {
		schools.add(data);
		System.out.println("Parsed row and got this data --> " + data);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		System.out.println("All parsing completed");

	}

}
