package kr.co.softcampus.controller;

import javax.servlet.http.HttpServletRequest;

//import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.softcampus.beans.UserBean;

@Controller
public class HomeController {
	
//	@Resource(name = "loginUserBean")
//	private UserBean loginUserBean;
	
	
	@RequestMapping(value ="/", method=RequestMethod.GET)
	public String home(HttpServletRequest request) {
		//주소만 치고 들어왔을때 실행되는 파일의 위치가 어디인지 체크/
		//업로드 이미지 파일 잡아주려고 체크함
		//System.out.println(request.getServletContext().getRealPath("/"));
		
		//System.out.println(loginUserBean);
		return "redirect:/main";
	}
	
}
