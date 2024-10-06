package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.*;

public class DeleteToDoServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String resp = "expired";

		if(user!=null){
			int toDoId = Integer.parseInt(request.getParameter("todoid"));

			ToDoList.deleteToDo(toDoId);

			resp = "done";
		}

		response.getWriter().write(resp);
	}
}