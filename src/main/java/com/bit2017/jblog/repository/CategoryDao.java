package com.bit2017.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2017.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public boolean join(String userId) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogID(userId);
		categoryVo.setName("기본 분류");
		categoryVo.setDescription("자동 생성 분류");
		
		return 1 == sqlSession.insert("category.insert", categoryVo);
	}
	
	public List<CategoryVo> getCategoryList(String userId) {
		return sqlSession.selectList("category.select", userId);
	}

	public boolean add(CategoryVo categoryvo) {
		return 1 == sqlSession.insert("category.insert", categoryvo);
	}

	public CategoryVo getInfoByNo(String blogID, Long categoryNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("blogID", blogID);
		map.put("no", categoryNo);
		
		return sqlSession.selectOne("category.selectbyno", map);
	}

	public boolean delete(CategoryVo categoryvo) {
		return 1 == sqlSession.delete("category.delete", categoryvo);
	}

}
