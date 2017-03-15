package com.bit2017.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit2017.jblog.repository.BlogDao;
import com.bit2017.jblog.repository.CategoryDao;
import com.bit2017.jblog.repository.UserDao;
import com.bit2017.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Transactional
	public boolean join (UserVo vo) {
		
		boolean boolUser = userDao.join(vo);
		boolean boolBlog = blogDao.join(vo.getId());
		boolean boolCategory = categoryDao.join(vo.getId());
		
		return boolUser && boolBlog && boolCategory;
	}

	public boolean checkId(String id) {
		
		return userDao.checkId(id);
	}

	public UserVo getUser(String id, String password) {
		
		return userDao.getUser(id, password);
	}
	
}
