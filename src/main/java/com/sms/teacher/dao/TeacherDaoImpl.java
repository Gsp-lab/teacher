package com.sms.teacher.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sms.teacher.Teacher;
import com.sms.teacher.to.UserInfo;


@Repository
public class TeacherDaoImpl implements ITeacherDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	@Autowired
	private EntityManager manager;

	private Session session() {
		return manager.unwrap(Session.class);
	}

	@Override
	public void saveTeacher(Teacher teacher) {
		session().save(teacher);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Teacher> getAllTeachers() {
		return session().createCriteria(Teacher.class).list();
	}

	@Override
	public UserInfo getUserByUserName(String userName) {
		UserInfo userInfo = (UserInfo) session().createCriteria(UserInfo.class).add(Restrictions.eq("email", userName))
				.uniqueResult();
		return userInfo;
	}

}
