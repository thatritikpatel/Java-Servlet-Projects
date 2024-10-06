package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ShowSigninServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		request.getRequestDispatcher("signin.jsp").forward(request,response);				
	}
}