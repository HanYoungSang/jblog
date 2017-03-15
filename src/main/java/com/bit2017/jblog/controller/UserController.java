package com.bit2017.jblog.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2017.jblog.dto.JSONResult;
import com.bit2017.jblog.exception.GlobalExceptionHandler;
import com.bit2017.jblog.service.UserService;
import com.bit2017.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private static final Log LOG = LogFactory.getLog( GlobalExceptionHandler.class );
	
	@RequestMapping("/joinform")
	public String joinform(@ModelAttribute UserVo userVo) {
		return "/user/join";
	}
	
	@RequestMapping("/join")
	public String join( @ModelAttribute @Valid UserVo vo,
						BindingResult result,
						Model model
						) {
		
		if(result.hasErrors()) {
		       model.addAllAttributes( result.getModel() );
		       return "/user/join";
		}
		
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {

		return "/user/joinsuccess";
	}
	
	@ResponseBody
	@RequestMapping("/checkid")
	public JSONResult checkId(	@RequestParam( value ="id", required=true, defaultValue="")
								String id
								) {
		
		boolean isExists = userService.checkId(id);
		JSONResult json = JSONResult.success(isExists? "exists" : "not exists");

		LOG.info(json);
	
		return json;
	}
	
	@RequestMapping("/loginform")
	public String login() {

		return "/user/login";
	}

	
}
