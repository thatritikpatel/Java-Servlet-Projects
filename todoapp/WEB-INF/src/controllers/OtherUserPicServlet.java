package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.User;

public class OtherUserPicServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		
		String email = request.getParameter("email");
		String profpic = request.getParameter("profpic");

		InputStream is = getServletContext().getResourceAsStream("/WEB-INF/uploads/"+email+"/"+profpic);

		ServletOutputStream sos = response.getOutputStream();
		
		int count = 0;
		byte[] arr = new byte[1024];

		while((count=is.read(arr))!=-1){
			sos.write(arr);
		}
		
		sos.flush();
		sos.close();		
	}
}