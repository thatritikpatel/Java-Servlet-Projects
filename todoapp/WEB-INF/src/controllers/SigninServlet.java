package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.User;

public class SigninServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		
		String nextPage = "signin.jsp";
		
		String email = request.getParameter("email");
		String password = request.getParameter("pass");

		User user = new User(email,password);

		int signinResp = user.signinUser();
		
		if(signinResp==4){
			session.setAttribute("user",user);
			nextPage = "dashboard.jsp";
		}else if(signinResp==3){
			request.setAttribute("errmsg","Your Account is Inactive! Please Activate Your Account.");
		}else if(signinResp==2){
			request.setAttribute("errmsg","Incorrect Password.");
		}else if(signinResp==1){
			request.setAttribute("errmsg","Invalid Email...");
		}		

		
		request.getRequestDispatcher(nextPage).forward(request,response);				
	}
}