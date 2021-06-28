package kr.co.softcampus.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softcampus.beans.ContentBean;
import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor {

		//java interceptro : 빈을 주입 받을 수 없음 .. 생성자로 주입
	private UserBean loginUserBean;
	private BoardService boardService;
	
	public CheckWriterInterceptor(UserBean loginUserBean, BoardService boardService) {
		this.loginUserBean=loginUserBean;
		this.boardService = boardService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	// TODO Auto-generated method stub
		String str1 = request.getParameter("content_idx"); // 이방법으로 한 이유가 있음 
		int content_idx = Integer.parseInt(str1);
		
		ContentBean currentContentBean = boardService.getContentInfo(content_idx);
		if(currentContentBean.getContent_writer_idx()!=loginUserBean.getUser_idx()) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath+"/board/not_writer");
			return false;
		}
		return true;
		
	}
}
