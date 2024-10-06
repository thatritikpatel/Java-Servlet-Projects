package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.*;

import com.google.gson.Gson;

public class UserInfoServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		
		String resp = "expired";

		if(user!=null){
			int userId = Integer.parseInt(request.getParameter("userid"));
			
			Connect con = Connect.getConnectInfo(user.getUserId(),userId);
			
			if(con!=null){
				resp = new Gson().toJson(con);
			}else{
				resp = "norecs";
			}
		}

		response.getWriter().write(resp);
	}
}