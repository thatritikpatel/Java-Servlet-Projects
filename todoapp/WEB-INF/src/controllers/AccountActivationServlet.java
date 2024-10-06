package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.User;

public class AccountActivationServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String nextPage = "activation_failed.jsp";

		String email = request.getParameter("email");
		String activationCode = request.getParameter("activation_code");

		if(User.activateAccount(email,activationCode)){
			nextPage = "signin.jsp";
		}
		
		request.getRequestDispatcher(nextPage).forward(request,response);				
	}
}