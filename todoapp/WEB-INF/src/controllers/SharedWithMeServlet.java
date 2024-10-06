package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

import models.*;

import com.google.gson.Gson;

public class SharedWithMeServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String resp = "expired";

		if(user!=null){
			ArrayList<ToDoList> todos = ToDoList.collectAllSharedToDos(user.getUserId());

			resp = new Gson().toJson(todos);
		}

		response.getWriter().write(resp);
	}
}