package com.bit2017.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2017.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean join(UserVo vo){
		
		return 1 == sqlSession.insert("user.insert", vo);
	}

	public boolean checkId(String id) {
		
		int ret = (int)sqlSession.selectOne("user.checkid", id);
		return 1 == ret;  
	}


	public UserVo getUser(String id, String password) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password",password);
		return sqlSession.selectOne("user.getByIDAndPassword", map);		
	}
}
