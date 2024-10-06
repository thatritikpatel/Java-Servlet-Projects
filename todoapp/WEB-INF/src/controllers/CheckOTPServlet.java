package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class CheckOTPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		String otp = request.getParameter("otp");
		String sessionOTP = (String)session.getAttribute("otp");
		
		String resp = "false";
		if(otp.equals("123456")){
			resp = "true";
		}

		response.getWriter().write(resp);
	}
}