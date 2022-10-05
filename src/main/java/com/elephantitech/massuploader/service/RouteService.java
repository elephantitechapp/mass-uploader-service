package com.elephantitech.massuploader.service;

import java.io.File;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.elephantitech.massuploader.entity.Route;
import com.elephantitech.massuploader.exceptions.ValidationFailedException;
import com.elephantitech.massuploader.listener.RouteListener;

import reactor.core.publisher.Mono;

@Service
public class RouteService {

	private static final String ROUTE_SERVICE_URL = "https://2s2oesp62k.execute-api.us-east-1.amazonaws.com/Prod";
	WebClient webClient = WebClient.create(ROUTE_SERVICE_URL);

	public String validateAndUploadRouteData(File routeFile) {

		RouteListener listener = new RouteListener();
		ExcelReaderBuilder builder = EasyExcel.read(routeFile, Route.class, listener );
		ExcelReaderSheetBuilder sheet1 = builder.sheet("Route");
		// read
		sheet1.doRead();

		// write to database
		List<Route> routes = listener.getRoutes();
		for (Route route: routes) {
			
			System.out.println(route);
			validateRoute(route);
			persistRoute(route);
		}

		return "";

	}

	private void persistRoute(Route route) {
		Mono<Route> createdRoute = webClient.post().uri("/route/create")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(route), Route.class).retrieve().bodyToMono(Route.class);
		System.out.println(createdRoute.block());
	}

	private void validateRoute(@Valid Route route) throws ValidationFailedException {
		// TODO Auto-generated method stub

	}
}
