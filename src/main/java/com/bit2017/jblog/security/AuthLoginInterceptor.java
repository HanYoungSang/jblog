package com.bit2017.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit2017.jblog.service.UserService;
import com.bit2017.jblog.vo.UserVo;


public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

//  자동으로 처리할 수 있다.
//	@Autowired
//	private UserService userService; 
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 컨테이너 가져오는 코드
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext( request.getServletContext() );		
		UserService userService = applicationContext.getBean( UserService.class );
		
		UserVo userVo = userService.getUser(id,password);
	
		if(userVo == null) {
			response.sendRedirect(request.getContextPath()+"/user/loginform?result=fail");
			return false;
		}
//		
//		// 인증 처리
		HttpSession session = request.getSession( true );
		session.setAttribute("authUser", userVo);
		response.sendRedirect(request.getContextPath());
		
		return false; 
	}
	
}
