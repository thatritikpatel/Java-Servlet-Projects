package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.*;

import com.google.gson.Gson;

public class GetToDoServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
		
		String resp = "expired";

		if(user!=null){
			int toDoListId = Integer.parseInt(request.getParameter("todoid"));

			ToDoList todo = new ToDoList(toDoListId);
			todo.getTodo();

			resp = new Gson().toJson(todo);
		}

		response.getWriter().write(resp);
	}
}