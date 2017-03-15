package com.bit2017.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2017.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;

	public boolean insert(PostVo postVo) {
		return 1 == sqlSession.insert("post.insert", postVo);
	}
	
	public PostVo getPost(Long postNo) {
		return sqlSession.selectOne("post.select", postNo);
	}
	
	public List<PostVo> getList(String userId, Long categoryNo) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("categoryNo", categoryNo);
		return sqlSession.selectList("post.getList", map);
		
	}
	
	public Long getMaxPostNoByID(String userId, Long categoryNo){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("categoryNo", categoryNo);
		return sqlSession.selectOne("post.getMaxPostNoByID", map);
	}
	 
}
