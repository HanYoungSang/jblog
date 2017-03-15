package com.bit2017.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2017.jblog.repository.PostDao;
import com.bit2017.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	
	public boolean write(PostVo postVo) {
		
		return postDao.insert(postVo);
	}
	
	public List<PostVo> getList(String blogId, Long categoryNo) {
		return postDao.getList(blogId, categoryNo);
	}	
	
	public PostVo getLastestPost(String blogId, Long postNo, Long categoryNo) {
		
		// 포스팅 번호가 없는 경우
		if( "".equals(postNo) || postNo == null ){
			postNo = postDao.getMaxPostNoByID(blogId, categoryNo);
			// 포스팅 개수가 0개인 사용자 == 처음 사용자
			if( "".equals(postNo) || postNo == null )
				return null;
			
		} 
		//포스팅 번호가 있을 경우
		return postDao.getPost(postNo);
	}
	

}
