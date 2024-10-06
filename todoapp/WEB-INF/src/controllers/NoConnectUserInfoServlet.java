package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.User;

import com.google.gson.Gson;

public class NoConnectUserInfoServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		
		String resp = "expired";
		
		if(user!=null){
			int userId = Integer.parseInt(request.getParameter("userid"));

			User usr = new User(userId);
			usr.getUserInfo();

			resp = new Gson().toJson(usr);
		}

		response.getWriter().write(resp);
	}
}