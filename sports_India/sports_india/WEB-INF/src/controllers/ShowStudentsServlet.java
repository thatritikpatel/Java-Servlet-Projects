package controllers;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

import models.*;
import java.util.*;

public class ShowStudentsServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        
        HttpSession session = request.getSession();
        
        ArrayList<Student> students = Student.collectStudents();
        request.setAttribute("students",students);
        
        RequestDispatcher view = request.getRequestDispatcher("student.jsp");
        
        view.forward(request,response);
    }
}
