package com.elephantitech.massuploader.listener;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.elephantitech.massuploader.entity.Bus;

public class BusListener extends AnalysisEventListener<Bus> {

	private List<Bus> buses = new ArrayList<>();

	public List<Bus> getBuses() {
		return buses;
	}

	@Override
	public void invoke(Bus data, AnalysisContext context) {
		buses.add(data);
		System.out.println("Parsed Bus row and got this data --> " + data);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		System.out.println("All parsing completed for Bus sheet");

	}

}
