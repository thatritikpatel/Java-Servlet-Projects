package controllers;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class StudentServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        
        // response.setContentType("text/html");

        // PrintWriter out = response.getWriter();
        // out.write("Student");


        RequestDispatcher view = request.getRequestDispatcher("student.jsp");
		view.forward(request,response);
    }
}