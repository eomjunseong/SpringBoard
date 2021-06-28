package kr.co.softcampus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.softcampus.beans.UserBean;

//프로젝트 작업시 사용할 bean을 정의하는 클래스 
//xml-> root-context.xml 역할 
@Configuration
public class RootAppContext {
	//설정과 관련된 빈 -->ServletAppContext.java
	//데이터 저장 관리 목전 빈 -->RootAppContext.java
	
	@Bean("loginUserBean")
	@SessionScope
	public UserBean loginUserBean() {
		return new UserBean();
	}
}
