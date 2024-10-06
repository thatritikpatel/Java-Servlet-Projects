package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import models.*;

public class AddQueryServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String name = request.getParameter("uname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");

        
        Query query = new Query(name, email, phone, message);

        query.addQuery();


    }
}
