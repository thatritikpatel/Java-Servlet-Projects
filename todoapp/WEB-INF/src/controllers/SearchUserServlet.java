package controllers;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.ArrayList;

import models.User;

import com.google.gson.Gson;

public class SearchUserServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		
		String resp = "expired";

		if(user!=null){
			String searchKey = request.getParameter("srch_key");

			ArrayList<User> users = User.searchUsers(searchKey);

			resp = new Gson().toJson(users);
		}
		
		response.getWriter().write(resp);
	}
}