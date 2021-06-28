package kr.co.softcampus.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.softcampus.beans.UserBean;

public class UserValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(clazz); //검사할 객체 주입 --> 공식
	}
	@Override
	public void validate(Object target, Errors errors) {
		//검사 부 
		UserBean userBean = (UserBean)target;
		
		//tempLoginUserBean 과 joinUserBean 분기 
		String beanName = errors.getObjectName();
		
		if(beanName.equals("joinUserBean")||beanName.equals("modifyUserBean")) {
			if(userBean.getUser_pw().equals(userBean.getUser_pw2())==false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
		}
		
		if(beanName.equals("joinUserBean")) {
			if(userBean.isUserIdExist()==false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}
		}
		
	}
	
}
