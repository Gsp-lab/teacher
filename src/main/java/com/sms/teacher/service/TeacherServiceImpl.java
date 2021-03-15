package com.sms.teacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.teacher.Teacher;
import com.sms.teacher.config.TeacherUserDeatils;
import com.sms.teacher.dao.ITeacherDao;
import com.sms.teacher.to.UserInfo;


@Service
public class TeacherServiceImpl implements ITeacherService, UserDetailsService {

	@Autowired
	private ITeacherDao teacherDao;
    
	@Transactional
	@Override
	public void saveTeacher(Teacher teacher) {
		teacherDao.saveTeacher(teacher);
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherDao.getAllTeachers();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = teacherDao.getUserByUserName(username);
		return new TeacherUserDeatils(userInfo);
	}

}
