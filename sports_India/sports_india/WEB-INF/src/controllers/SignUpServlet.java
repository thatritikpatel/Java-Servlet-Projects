package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

import models.*;

public class SignUpServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String name = request.getParameter("stname");
        String email = request.getParameter("email");
        String password = request.getParameter("stpass");
        String degree = request.getParameter("degree");
        String branch = request.getParameter("branch");
        String semester = request.getParameter("semester");

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setPassword(password);
        student.setDegree(degree);
        student.setBranch(branch);
        student.setSemester(semester);

        student.saveStudent();

        response.sendRedirect("index.html");
    }
}
