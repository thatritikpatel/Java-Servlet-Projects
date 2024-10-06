package controllers;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import java.sql.*;

public class DeleteServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        String deleteId = request.getParameter("deleteId");
        String table = request.getParameter("table");
        String page = request.getParameter("page");

        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jaishreeram?user=root&password=1234");

            String query = "delete from "+table+" where id="+deleteId;

            PreparedStatement pst = con.prepareStatement(query);

            pst.executeUpdate();
        }catch(SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{   con.close();   }catch(SQLException e){e.printStackTrace(); }
        }
        

        response.sendRedirect(page+".do");  


    }
}