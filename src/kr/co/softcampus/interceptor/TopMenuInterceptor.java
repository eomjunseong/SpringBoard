package kr.co.softcampus.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softcampus.beans.BoardInfoBean;
import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.service.TopMenuService;

//topmenu는 항상 있음으로 인터센트로서 처리 
//인터셉터에서는 자동주입으로 빈을 주입받지 못함
public class TopMenuInterceptor implements HandlerInterceptor{

	//@Autowired //인터셉터에서는 자동주입으로 빈을 주입받지 못함 // 인터셉터 등록하는 쪽에서 빈을 주입하고 생성자로 넘겨서 작업해야함
	private TopMenuService topMenuService;
	
	private UserBean loginUserBean;
	
	public TopMenuInterceptor(TopMenuService topMenuService,UserBean loginUserBean) {
		this.topMenuService = topMenuService;
		this.loginUserBean=loginUserBean;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		
		return true;
	}
}
