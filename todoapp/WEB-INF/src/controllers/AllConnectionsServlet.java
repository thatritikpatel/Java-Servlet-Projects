package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

import models.*;

import com.google.gson.Gson;

public class AllConnectionsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String resp = "expired";

		if(user!=null){
			ArrayList<Connect> connections = Connect.collectAllConnections(user.getUserId());
			resp = new Gson().toJson(connections);
		}

		response.getWriter().write(resp);
	}
}