package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ShowSignupServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		request.getRequestDispatcher("signup.jsp").forward(request,response);				
	}
}