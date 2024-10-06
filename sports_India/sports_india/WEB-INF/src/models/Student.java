package models;

import java.sql.*;
import java.util.*;


public class Student{
    // ##################### FIELDS #############################
    private Integer studentId;
    private String name;
    private String email;
    private String password;
    private String degree;
    private String branch;
    private String semester;
    
    // ##################### Metthods #############################

    public void saveStudent(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jaishreeram?user=root&password=1234");

            String query = "insert into students(name,email,password,degree,branch,semester) value(?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);


            pst.setString(1,name);
            pst.setString(2,email);
            pst.setString(3,password);
            pst.setString(4,degree);
            pst.setString(5,branch);
            pst.setString(6,semester);

            pst.executeUpdate();
        }catch(SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{   con.close();   }catch(SQLException e){e.printStackTrace(); }
        }

    }


    public static  ArrayList<Student> collectStudents(){
        ArrayList<Student> students = new ArrayList<Student>();

        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jaishreeram?user=root&password=1234");

            String query = "select * from students";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Student student = new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
                students.add(student);
            }

        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }finally{ try{con.close();}catch(SQLException e){ e.printStackTrace();} }

        return students;
    }


    

    // ##################### CONSTRUCTORS #############################
    public Student(){
        super();
    }
    
    public Student(Integer studentId,String name,String email,String password ,String degree,String branch , String semester){
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.degree = degree;
        this.branch = branch;
        this.semester = semester;
    }
    
    // ##################### Geter Seters #############################

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
    public String getSemester(){
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }


    public String getBranch(){
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }


    public String getDegree(){
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}