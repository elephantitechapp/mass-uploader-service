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
import com.elephantitech.massuploader.entity.Student;
import com.elephantitech.massuploader.exceptions.ValidationFailedException;
import com.elephantitech.massuploader.listener.StudentListener;

import reactor.core.publisher.Mono;

@Service
public class StudentService {

	private static final String STUDENT_SERVICE_URL = "https://2s2oesp62k.execute-api.us-east-1.amazonaws.com/Prod/";
	WebClient webClient = WebClient.create(STUDENT_SERVICE_URL);

	
	public String validateAndUploadStudentData(File studentFile) {
		StudentListener listener = new StudentListener();
		ExcelReaderBuilder builder = EasyExcel.read(studentFile, Student.class, listener);
		ExcelReaderSheetBuilder sheet1 = builder.sheet("Student");
		sheet1.doRead();

		// iterate, validate and persist to database
		List<Student> students = listener.getStudents();
		for (Student student : students) {
			System.out.println(student);
			validateStudent(student);
			persistStudent(student);
		}

		return "";

	}

	private void persistStudent(Student student) {
		Mono<Student> createdStudent = webClient.post().uri("/student/add")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(student), Student.class).retrieve().bodyToMono(Student.class);
		System.out.println(createdStudent.block());
	}

	private void validateStudent(@Valid Student student) throws ValidationFailedException {
		// TODO Auto-generated method stub

	}
}
