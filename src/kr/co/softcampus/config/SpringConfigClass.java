package kr.co.softcampus.config;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
public class SpringConfigClass implements WebApplicationInitializer {
	//Xml 방식의 Web.xml 과 같은 기능. -->1번 방법 임 
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//Spring MVC 프로젝트 설정을 위해 작성하는 클래스의 객체를 생성한다. 
		//servlet-context.xml 가 필요하다 --> ServletAppContext.java 
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		servletAppContext.register(ServletAppContext.class);
		
		//요청발생시 요청을 처리하는 서블릿을 DispathcerServlet으로 설정해야함 . 
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);//기본 설정 완료 
		
		//부가설정 
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		
		//bean을 정의하는 클래스를 정의한다. 
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);
		
		//리스너를 설정한다. 
		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener);
		
		//파라미터 인코딩 설정 
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.addMappingForServletNames(null, false, "dispatcher");
			
	}	
}*/
public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer{
	//방법 2 
	//AbstractAnnotationConfigDispatcherServletInitializer
	// 쉬운대 제한적임 
	
	// DispatcherServlet에 매핑할 요청 주소를 셋팅한다.
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	// Spring MVC 프로젝트 설정을 위한 클래스를 지정한다.
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletAppContext.class};
	}
	
	// 프로젝트에서 사용할 Bean들을 정의기 위한 클래스를 지정한다.
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootAppContext.class};
	}
	
	// 파라미터 인코딩 필터 설정
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] {encodingFilter};
	}
	
	
	//이미지 등록을위해 //StandardServletMultipartResolver 와 관련(servletAppcontext)
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// TODO Auto-generated method stub
		super.customizeRegistration(registration);
		//임시저장 위치,파일 최대용량50mb,요청정보 최대용량 500mb,0(알아서 저장) 
		MultipartConfigElement config1 = new MultipartConfigElement(null,52428800,524288000,0);
		registration.setMultipartConfig(config1);
	}
}

