package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

import models.*;

import com.google.gson.Gson;


public class AllToDoStepsServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String resp = "expired";

		if(user!=null){
			int toDoListId = Integer.parseInt(request.getParameter("todoid"));

			ArrayList<ArrayList<ToDoStep>> steps = ToDoStep.collectAllToDoSteps(toDoListId); 

			if(steps.size()!=0){
				resp = new Gson().toJson(steps);
			}else{
				resp = "norecords";
			}
		}

		response.getWriter().write(resp);
	}
}