package com.bit2017.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2017.jblog.repository.CategoryDao;
import com.bit2017.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> getCategoryList(String blogId){
		return categoryDao.getCategoryList(blogId);
	}
	
	public CategoryVo add(CategoryVo categoryvo) {
		
		if (categoryDao.add(categoryvo)){
			CategoryVo vo = categoryDao.getInfoByNo(categoryvo.getBlogID(), categoryvo.getNo());
			return vo;
		} else {
			return null;
		}
	}

	public boolean delete(CategoryVo categoryvo) {
		
		return categoryDao.delete(categoryvo);
	}



}
