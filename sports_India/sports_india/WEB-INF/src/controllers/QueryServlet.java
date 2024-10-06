package controllers;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.ArrayList;

import models.*;

public class QueryServlet  extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        
        // response.setContentType("text/html");

        // PrintWriter out = response.getWriter();
        // out.write("Student");

        ArrayList<Query> queries = Query.collectQueries();
        request.setAttribute("queries", queries);


        RequestDispatcher view = request.getRequestDispatcher("query.jsp");
		view.forward(request,response);
    }
}