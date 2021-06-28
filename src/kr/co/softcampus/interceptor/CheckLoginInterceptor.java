package kr.co.softcampus.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softcampus.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{

	//Interceptor -->java 프로젝트 경우 빈을 주입 받지 못함
	//생성자로 
	
	private UserBean loginUserBean;
	
	public CheckLoginInterceptor(UserBean loginUserBean) {
		// TODO Auto-generated constructor stub
		this.loginUserBean = loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(loginUserBean.isUserLogin()==false) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath+"/user/not_login");
			return false;
		}
		return true; // controller 로 이동
		
	}
}
