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
import com.elephantitech.massuploader.entity.Bus;
import com.elephantitech.massuploader.exceptions.ValidationFailedException;
import com.elephantitech.massuploader.listener.BusListener;

import reactor.core.publisher.Mono;

@Service
public class BusService {

	private static final String BUS_SERVICE_URL = "https://ee9v507u7b.execute-api.us-east-1.amazonaws.com/Prod/";
	WebClient webClient = WebClient.create(BUS_SERVICE_URL);

	public String validateAndUploadBusData(File busFile) {

		BusListener listener = new BusListener();
		ExcelReaderBuilder builder = EasyExcel.read(busFile, Bus.class, listener);
		ExcelReaderSheetBuilder sheet1 = builder.sheet("Bus");
		// read
		sheet1.doRead();

		// write to database
		List<Bus> buses = listener.getBuses();
		for (Bus bus : buses) {
			System.out.println(bus);
			validateBus(bus);
			persistBus(bus);
		}

		return "";

	}

	private void persistBus(Bus bus) {
		Mono<Bus> createdBus = webClient.post().uri("/bus/create")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(Mono.just(bus), Bus.class)
				.retrieve().bodyToMono(Bus.class);
		System.out.println(createdBus.block());
	}

	private void validateBus(@Valid Bus bus) throws ValidationFailedException {
		// TODO Auto-generated method stub

	}
}
