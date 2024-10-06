package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.*;

import utils.*;

public class SignupServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{				
		String reCaptchaCode = request.getParameter("g-recaptcha-response");

		boolean flag = GoogleCaptcha.testReCaptcha(reCaptchaCode);
		
		String nextPage = "signup.jsp";


		//temp-code
		flag = true;

		if(flag){
			String userName = request.getParameter("uname");
			String email = request.getParameter("email");
			String password = request.getParameter("pass");
			String rePassword = request.getParameter("repass");
			int countryId = Integer.parseInt(request.getParameter("country_id"));
			String mobile = request.getParameter("mobile");	
			
			User user = new User();
			user.setUserName(userName);
			user.setEmail(email);
			user.setPassword(password);
			user.setCountry(new Country(countryId));
			user.setMobile(mobile);
			String activationCode = RandomCodeGenerator.activationCode();
			user.setActivationCode(activationCode);
						
			if(user.signupUser()){
				nextPage = "signup_welcome.jsp";
				
				String userFolderPath = getServletContext().getRealPath("/WEB-INF/uploads/"+email);
				File file = new File(userFolderPath);
				file.mkdir();
				
				//EmailSender.sendEmail(email,userName,activationCode);
				User.activateAccount(email,activationCode);
			}else{
				request.setAttribute("message","Duplicate Email Address...");
			}		
		}else{
			request.setAttribute("message","Captcha Test Failed...");
		}
		
		request.getRequestDispatcher(nextPage).forward(request,response);				
	}
}