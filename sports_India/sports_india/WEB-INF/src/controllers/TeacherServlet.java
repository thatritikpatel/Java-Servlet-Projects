package controllers;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class TeacherServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        
        // response.setContentType("text/html");

        // PrintWriter out = response.getWriter();
        // out.write("Teachers");

        RequestDispatcher view = request.getRequestDispatcher("teacher.jsp");
        view.forward(request,response);
        
    }
}