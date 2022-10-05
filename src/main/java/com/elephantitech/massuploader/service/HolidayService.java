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
import com.elephantitech.massuploader.entity.Holiday;
import com.elephantitech.massuploader.exceptions.ValidationFailedException;
import com.elephantitech.massuploader.listener.HolidayListener;

import reactor.core.publisher.Mono;

@Service
public class HolidayService {

	private static final String HOLIDAY_SERVICE_URL = "https://v3rmru1qq7.execute-api.us-east-1.amazonaws.com/Prod/";
	WebClient webClient = WebClient.create(HOLIDAY_SERVICE_URL);

	public String validateAndUploadHolidayData(File holidayFile) {

		HolidayListener listener = new HolidayListener();
		ExcelReaderBuilder builder = EasyExcel.read(holidayFile, Holiday.class, listener);
		ExcelReaderSheetBuilder sheet1 = builder.sheet("Holiday");
		// read
		sheet1.doRead();

		// write to database
		List<Holiday> holidays = listener.getHolidays();
		for (Holiday holiday : holidays) {
			System.out.println(holiday);
			validateHoliday(holiday);
			persistHoliday(holiday);
		}

		return "";

	}

	private void persistHoliday(Holiday holiday) {
		Mono<Holiday> createdHoliday = webClient.post().uri("/holiday/create")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(holiday), Holiday.class).retrieve().bodyToMono(Holiday.class);
		System.out.println(createdHoliday.block());
	}

	private void validateHoliday(@Valid Holiday holiday) throws ValidationFailedException {
		// TODO Auto-generated method stub

	}
}
