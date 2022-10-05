package com.elephantitech.massuploader.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elephantitech.massuploader.service.BusService;
import com.elephantitech.massuploader.service.HolidayService;
import com.elephantitech.massuploader.service.RouteService;
import com.elephantitech.massuploader.service.SchoolService;
import com.elephantitech.massuploader.service.StudentService;

@RestController
public class MassUploadController {

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private BusService busService;

	@Autowired
	private HolidayService holidayService;
	
	@Autowired
	private StudentService studentService;

	@GetMapping("/health")
	public ResponseEntity<?> checkHealth() {
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PostMapping("/upload")
	public String handleUploadForm(@RequestParam("fileType") MultipartFile multipart) {
		String fileName = multipart.getOriginalFilename();
		System.out.println("filename: " + fileName);
		File xlsFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		try {
			multipart.transferTo(xlsFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolService.validateAndUploadSchoolData(xlsFile);
		routeService.validateAndUploadRouteData(xlsFile);
		busService.validateAndUploadBusData(xlsFile);
		holidayService.validateAndUploadHolidayData(xlsFile);
		studentService.validateAndUploadStudentData(xlsFile);
		xlsFile.delete();
		return "upload successful";
	}

}
