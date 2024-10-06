package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.*;

public class InviteUserServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String resp = "expired";

		if(user!=null){
			int userId = Integer.parseInt(request.getParameter("userid"));

			Connect con = new Connect(user,new User(userId),Status.PENDING);
			con.inviteUser();

			resp = "{\"conid\":"+con.getConnectId()+"}";
		}

		response.getWriter().write(resp);
	}
}