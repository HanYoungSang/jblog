package com.bit2017.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bit2017.jblog.dto.JSONResult;
import com.bit2017.jblog.security.Auth;
import com.bit2017.jblog.security.AuthUser;
import com.bit2017.jblog.service.BlogService;
import com.bit2017.jblog.service.CategoryService;
import com.bit2017.jblog.service.PostService;
import com.bit2017.jblog.service.UserService;
import com.bit2017.jblog.vo.BlogVo;
import com.bit2017.jblog.vo.CategoryVo;
import com.bit2017.jblog.vo.PostVo;
import com.bit2017.jblog.vo.UserVo;

@Controller
@RequestMapping("/")
public class BlogController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("{id}")
	public String main(	@PathVariable(value="id")
							String blogID,
						@RequestParam (value="no", required=false, defaultValue="") 
							Long postNo,
						@RequestParam (value="category", required=false, defaultValue="")
							Long categoryNo,
							Model model) {
		
		if (!userService.checkId(blogID) ) {
		//false면 ID가 없음 (True면 아이디가 있음)	
			return "redirect:/";
		} else {
			
			// 기본 정보
			model.addAttribute("blogId", blogID);
			model.addAttribute("blogInfo", blogService.getInfo(blogID));
			
			// 포스트 가져오기
			model.addAttribute("lastestPost", postService.getLastestPost(blogID, postNo, categoryNo) );
			model.addAttribute("postList", postService.getList(blogID, categoryNo) );
			
			// 카테고리 가져오기
			model.addAttribute("categoryList", categoryService.getCategoryList(blogID) );
			
			return "/blog/blog-main";
		}
	
	}
	
	@Auth
	@RequestMapping("{id}/admin/basic")
	public String adminBasic(	@PathVariable(value="id")
									String blogID,
								@AuthUser 
									UserVo authUser,
									Model model) {
		
		if (! blogID.equals(authUser.getId()))
			return "redirect:/"+blogID;
		
		model.addAttribute("vo", blogService.getInfo(blogID) );
		
		model.addAttribute("adminMenu", "basic" );
		model.addAttribute("blogId", blogID);
		model.addAttribute("blogInfo", blogService.getInfo(blogID));
		return "/blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping("{id}/admin/basic/update")
	public String adminBasicUpdate( @PathVariable(value="id")
										String blogID,
									@ModelAttribute
										BlogVo blogVo,
									@AuthUser 
										UserVo authUser,
									@RequestParam("logo-file")
										MultipartFile file,
										Model model) {
		
		if (! blogID.equals(authUser.getId()))
			return "redirect:/"+blogID;
		
		blogVo.setBlogID(blogID);
		blogService.updateInfo(blogVo, file);
		
		model.addAttribute("adminMenu", "basic" );
		model.addAttribute("blogId", blogID);
		model.addAttribute("blogInfo", blogService.getInfo(blogID));
		
		return "redirect:/"+blogID+"/admin/basic";
	}
	
	@Auth
	@RequestMapping("{id}/admin/category")
	public String adminCategory(@PathVariable(value="id")
									String blogID,
								@AuthUser 
									UserVo authUser,
									Model model) {
		
		if (! blogID.equals(authUser.getId()))
			return "redirect:/"+blogID;
		
		model.addAttribute("list", categoryService.getCategoryList(blogID) );
		
		model.addAttribute("adminMenu", "category");
		model.addAttribute("blogId", blogID);
		model.addAttribute("blogInfo", blogService.getInfo(blogID));
		return "/blog/blog-admin-category";
	}

	@Auth
	@ResponseBody
	@RequestMapping("{id}/admin/category/add")
	public JSONResult adminCategoryAdd( @ModelAttribute 
											CategoryVo categoryvo,
										@AuthUser 
											UserVo authUser,
										@PathVariable(value="id")
											String blogID){	
		
		if (! blogID.equals(authUser.getId())) {
			return JSONResult.fail( "auth fail" );
		}

		categoryvo.setBlogID(blogID);
		CategoryVo vo = categoryService.add(categoryvo);
		
		return JSONResult.success(vo);
	}
	
	@Auth
	@ResponseBody
	@RequestMapping("{id}/admin/category/del")
	public JSONResult adminCategoryDel( @ModelAttribute
											CategoryVo categoryVo,
										@PathVariable(value="id")
											String blogID,
										@AuthUser
											UserVo authUser ){	
		if (! blogID.equals(authUser.getId()))
			return JSONResult.fail( "auth fail" );
		
		categoryVo.setBlogID(authUser.getId());
		
		if( categoryService.delete(categoryVo) )
			return JSONResult.success("delete");
		else
			return JSONResult.fail("fail delete");
	}
	
	@Auth
	@RequestMapping("{id}/admin/writeform")
	public String adminWriteForm(@PathVariable(value="id")
									String blogID,
								@AuthUser 
									UserVo authUser,
									Model model) {
		
		if (! blogID.equals(authUser.getId()))
			return "redirect:/"+blogID;
		
		model.addAttribute("categoryList", categoryService.getCategoryList(blogID));
		
		model.addAttribute("adminMenu", "write");
		model.addAttribute("blogId", blogID);
		model.addAttribute("blogInfo", blogService.getInfo(blogID));
		return "/blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping("{id}/admin/write")
	public String adminWrite(	@ModelAttribute
									PostVo postVo,
								@PathVariable(value="id")
									String blogID,
								@AuthUser 
									UserVo authUser,
									Model model) {
		
		if (! blogID.equals(authUser.getId()))
			return "redirect:/"+blogID;
		
		postService.write(postVo);
		
		model.addAttribute("categoryList", categoryService.getCategoryList(blogID));
		
		model.addAttribute("adminMenu", "write");
		model.addAttribute("blogId", blogID);
		model.addAttribute("blogInfo", blogService.getInfo(blogID));
		return "redirect:/"+blogID;
	}
	
	@ResponseBody
	@ExceptionHandler( Exception.class )
	public JSONResult handleException( Exception e  ) {
//		System.out.println( "--------------" + e );
		return JSONResult.fail( e.getMessage() );
	}
}
