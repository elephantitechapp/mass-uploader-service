package com.elephantitech.massuploader.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.elephantitech.massuploader.entity.Location;
import com.elephantitech.massuploader.entity.Route;

public class RouteListener extends AnalysisEventListener<Route> {

	private Map<String, Route> routeMap = new HashMap<>();

	public List<Route> getRoutes() {
		return new ArrayList<>(routeMap.values());
	}

	@Override
	public void invoke(Route data, AnalysisContext context) {
		Route route = null;
		if (routeMap.containsKey(data.getName())) {
			route = routeMap.get(data.getName());

		} else {
			routeMap.put(data.getName(), data);
			route = data;
			route.setLocations(new ArrayList<>());
		}
		Location location = new Location();
		location.setAddress1(data.getAddress1());
		location.setAddress2(data.getAddress2());
		location.setCity(data.getCity());
		location.setState(data.getState());
		location.setPincode(data.getPincode());
		route.getLocations().add(location);
		System.out.println("Parsed route row and got this data --> " + data);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		System.out.println("All parsing completed for route sheet");

	}

}
