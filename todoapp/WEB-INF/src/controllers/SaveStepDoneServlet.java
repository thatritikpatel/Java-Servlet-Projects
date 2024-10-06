package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.*;

public class SaveStepDoneServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String resp = "expired";

		if(user!=null){
			int toDoStepId = Integer.parseInt(request.getParameter("todostepid"));
			ToDoStep.setStepDone(toDoStepId);
			resp = "done";
		}

		response.getWriter().write(resp);
	}
}