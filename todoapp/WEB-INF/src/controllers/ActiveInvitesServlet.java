package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

import models.*;

import com.google.gson.Gson;

public class ActiveInvitesServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User toUser = (User)session.getAttribute("user");

		String resp = "expired";

		if(toUser!=null){			
			ArrayList<Connect> activeConnects = Connect.activeConnects(toUser);
			resp = new Gson().toJson(activeConnects);
		}

		response.getWriter().write(resp);
	}
}