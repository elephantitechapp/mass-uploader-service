package com.elephantitech.massuploader.listener;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.elephantitech.massuploader.entity.Gender;
import com.elephantitech.massuploader.entity.Student;

public class StudentListener extends AnalysisEventListener<Student> {

	private List<Student> students = new ArrayList<>();

	public List<Student> getStudents() {
		return students;
	}

	@Override
	public void invoke(Student data, AnalysisContext context) {
		data.setGender(Gender.valueOf(data.getStrGender()));
		students.add(data);
		System.out.println("Parsed Student row and got this data --> " + data);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		System.out.println("All parsing completed for Student sheet");

	}

}
