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
import com.elephantitech.massuploader.entity.School;
import com.elephantitech.massuploader.exceptions.ValidationFailedException;
import com.elephantitech.massuploader.listener.SchoolListener;

import reactor.core.publisher.Mono;

@Service
public class SchoolService {

	private static final String SCHOOL_SERVICE_URL = "https://26w6nst5s0.execute-api.us-east-1.amazonaws.com/Prod/";
	WebClient webClient = WebClient.create(SCHOOL_SERVICE_URL);

	public String validateAndUploadSchoolData(File schoolFile) {

		SchoolListener listener = new SchoolListener();
		ExcelReaderBuilder builder = EasyExcel.read(schoolFile, School.class, listener);
		ExcelReaderSheetBuilder sheet1 = builder.sheet("School");
		// read
		sheet1.doRead();

		// write to database
		List<School> schools = listener.getSchools();
		for (School school : schools) {
			System.out.println(school);
			validateSchool(school);
			persistSchool(school);
		}

		return "";

	}

	private void persistSchool(School school) {
		Mono<School> createdSchool = webClient.post().uri("/school/create")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(school), School.class).retrieve().bodyToMono(School.class);
		System.out.println(createdSchool.block());
	}

	private void validateSchool(@Valid School school) throws ValidationFailedException {
		// TODO Auto-generated method stub

	}
}
