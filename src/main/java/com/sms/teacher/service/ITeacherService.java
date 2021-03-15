package com.sms.teacher.service;

import java.util.List;

import com.sms.teacher.Teacher;

public interface ITeacherService {
	
	public void saveTeacher(Teacher teacher);

	public List<Teacher> getAllTeachers();
}
