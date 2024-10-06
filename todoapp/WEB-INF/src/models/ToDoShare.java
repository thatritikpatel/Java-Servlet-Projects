package models;

import java.sql.*;
import java.util.*;

public class ToDoShare{
	private Integer toDoShareId;
	private ToDoList toDoList;
	private User user;

	public ToDoShare(){
	
	}

	public ToDoShare(ToDoList toDoList,User user){
		this.toDoList = toDoList;
		this.user = user;
	}


	public void saveToDoShare(){
		Connection con = null; 
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");

			String query = "insert into todoshare (todolist_id,user_id) value (?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,toDoList.getTodoListId());
			ps.setInt(2,user.getUserId());

			ps.executeUpdate();

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}


	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public void setToDoList(ToDoList toDoList){
		this.toDoList = toDoList;
	}

	public ToDoList getToDoList(){
		return toDoList;
	}

	public void setToDoShareId(Integer toDoShareId){
		this.toDoShareId = toDoShareId;
	}

	public Integer getToDoShareId(){
		return toDoShareId;
	}
}