package models;

import java.sql.*;
import java.util.*;


public class Query {
    private Integer queryId;
    private String name;
    private String email;
    private String phone;
    private String message;

    // ####################### CONSTRUCTORS SECTION ############# FIRST SECTION

    public Query() {
        super();
    }

    public Query(Integer queryId,String name, String email, String phone, String message) {
        this.queryId = queryId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    public Query(String name, String email, String phone, String message) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }
    

    // ####################### CLOSE CONSTRUCTORS SECTION ############# FIRST
    // SECTION

    // ##################### METHODS SECTION #############


    public static  ArrayList<Query> collectQueries(){
        ArrayList<Query> queries = new ArrayList<Query>();

        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jaishreeram?user=root&password=1234");

            String sqlQuery = "select id,name,email,phone,message from queries";
            PreparedStatement pst = con.prepareStatement(sqlQuery);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                
                Query query = new Query(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));

                queries.add(query);
            }

        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }finally{ try{con.close();}catch(SQLException e){ e.printStackTrace();} }

        return queries;
    }

    

    public void addQuery() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jaishreeram?user=root&password=1234");

            String query = "insert into queries(name,email,phone,message) value(?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, phone);
            pst.setString(4, message);

            pst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void setQueryId(Integer queryId) {
        this.queryId = queryId;
    }
    
    public Integer getQueryId() {
        return queryId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    // ####################### CLOSING GETER SETER SECTION ############# LAST
    // SECTION
}
