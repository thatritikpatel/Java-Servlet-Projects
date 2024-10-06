package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ConnectsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		request.getRequestDispatcher("connects.jsp").forward(request,response);				
	}
}