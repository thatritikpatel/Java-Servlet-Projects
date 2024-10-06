package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyToDosServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		request.getRequestDispatcher("mytodos.jsp").forward(request,response);				
	}
}