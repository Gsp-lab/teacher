package com.sms.teacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.teacher.Teacher;
import com.sms.teacher.service.ITeacherService;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins="http://localhost:4200")
public class TeacherController {

	@Autowired
	private ITeacherService teacherService;

	@PostMapping("/saveteacher")
	public ResponseEntity<String> saveTeacher(@RequestBody Teacher teacher) {
		teacherService.saveTeacher(teacher);
		return new ResponseEntity<String>("Teacher Success", HttpStatus.OK);
	}

	@GetMapping("/getallteachers")
	public ResponseEntity<List<Teacher>> getAllTeachers() {
		return new ResponseEntity<List<Teacher>>(teacherService.getAllTeachers(), HttpStatus.OK);
	}
}
