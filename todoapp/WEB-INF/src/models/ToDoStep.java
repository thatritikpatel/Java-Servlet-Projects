package models;

import java.sql.*;
import java.util.ArrayList;

public class ToDoStep {
	private Integer todostepId;
	private Integer sequence;
	private String todostep;
	private ToDoList toDoList;
	private Status status;

	public ToDoStep(){
	
	}

	public ToDoStep(Integer todostepId,String todostep){
		this.todostepId = todostepId;
		this.todostep = todostep;
	}

	public ToDoStep(Status status,Integer todostepId,String todostep){
		this.todostepId = todostepId;
		this.todostep = todostep;
		this.status = status;
	}


	public static void setStepActive(int toDoStepId){
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");

			String query = "update todosteps set status_id=1 where todostep_id=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1,toDoStepId);
			ps.executeUpdate();			

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();	
		}finally{
			try{con.close();}catch(SQLException e){e.printStackTrace();	}
		}
	}

	public static void setStepDone(int toDoStepId){
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");

			String query = "update todosteps set status_id=6 where todostep_id=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1,toDoStepId);
			ps.executeUpdate();			

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();	
		}finally{
			try{con.close();}catch(SQLException e){e.printStackTrace();	}
		}
	}

	public static ArrayList<ArrayList<ToDoStep>> collectAllToDoSteps(Integer toDoListId){
		ArrayList<ToDoStep> stepsActive = new ArrayList<ToDoStep>();
		ArrayList<ToDoStep> stepsDone = new ArrayList<ToDoStep>();

		ArrayList<ArrayList<ToDoStep>> allSteps = new ArrayList<ArrayList<ToDoStep>>();
		allSteps.add(stepsActive);
		allSteps.add(stepsDone);

		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");

			String query = "select status_id,todostep_id,todostep from todosteps where todolist_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,toDoListId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				int statusId = rs.getInt(1);
				if(statusId==1){
					stepsActive.add(new ToDoStep(new Status(statusId),rs.getInt(2),rs.getString(3)));
				}else if(statusId==6){
					stepsDone.add(new ToDoStep(new Status(statusId),rs.getInt(2),rs.getString(3)));
				}
			}						

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();	
		}finally{
			try{con.close();}catch(SQLException e){e.printStackTrace();	}
		}

		return allSteps;
	}
	

	public static void saveToDoSteps(Integer todoListId,String[] steps,boolean exists){
		Connection con = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");
			
			String query = null;
			PreparedStatement ps = null;
			
			//Step 1:
			if(exists){
				query = "delete from todosteps where todolist_id=?";
				ps = con.prepareStatement(query);
				ps.setInt(1,todoListId);
				int delCount = ps.executeUpdate();

				//System.out.println("Del Count: "+delCount);
			}	
			
			//Step 2:
			query = "insert into todosteps (todolist_id,todostep) value (?,?)";
			ps = con.prepareStatement(query);
			
			ps.setInt(1,todoListId);
			//int i = 1;
			for(String step : steps){
				//System.out.println("~~~~~~~~~~~~~~~~"+step);
				//ps.setInt(2,i++);
				ps.setString(2,step);
				ps.executeUpdate();
			}			

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();	
		}finally{
			try{con.close();}catch(SQLException e){e.printStackTrace();	}
		}
	}

	public Integer getTodostepId() {
		return todostepId;
	}
	
	public void setTodostepId(Integer todostepId) {
		this.todostepId = todostepId;
	}
	
	public String getTodostep() {
		return todostep;
	}
	
	public void setTodostep(String todostep) {
		this.todostep = todostep;
	}
	
	public ToDoList getToDoList() {
		return toDoList;
	}
	
	public void setToDoList(ToDoList toDoList) {
		this.toDoList = toDoList;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setSequence(Integer sequence){
		this.sequence = sequence;
	}

	public Integer getSequence(){
		return sequence;
	}
}
