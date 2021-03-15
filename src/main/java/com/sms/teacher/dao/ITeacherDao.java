package com.sms.teacher.dao;

import java.util.List;

import com.sms.teacher.Teacher;
import com.sms.teacher.to.UserInfo;

public interface ITeacherDao {

	public void saveTeacher(Teacher teacher);

	public List<Teacher> getAllTeachers();

	public UserInfo getUserByUserName(String userName);

}
