package com.bit2017.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2017.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean join(String userId){
		BlogVo blogVo = new BlogVo();
		blogVo.setBlogID(userId);
		blogVo.setLogo("logo.jpg");
		blogVo.setTitle(userId + "님의 블로그 이야기");
		
		return 1 == sqlSession.insert("blog.insert", blogVo);
	}
	public BlogVo getInfo(String id) {
		
		return sqlSession.selectOne("blog.select", id);

	}
	public boolean updateInfo ( BlogVo blogVo ) {
		return 1 == sqlSession.update("blog.update", blogVo);
	}
}
