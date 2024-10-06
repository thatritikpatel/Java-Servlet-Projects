package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import models.*;

public class InvitationActionServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String resp = "expired";

		if(user!=null){
			int conId = Integer.parseInt(request.getParameter("conid"));
			int sttsId = Integer.parseInt(request.getParameter("sttsid"));

			Connect.setConnectStatus(conId,sttsId);

			resp = "done";
		}

		response.getWriter().write(resp);
	}
}