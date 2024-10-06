package models;

import java.sql.*;
import java.util.*;

public class Connect{
	private Integer connectId;
	private User fromUser;
	private User toUser;	
	private Status status;

	public Connect(){
		
	}

	public Connect(Integer connectId,User fromUser,User toUser,Status status){
		this.connectId = connectId;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.status = status;
	}

	public Connect(User fromUser,User toUser,Status status){		
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.status = status;
	}

	public Connect(User toUser,Status status){
		this.toUser = toUser;
		this.status = status;
	}

	public static ArrayList<Connect> activeConnects(User toUser){
		Connection con = null;

		ArrayList<Connect> connects = new ArrayList<Connect>();

		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");
			
			String query = "select connect_id,u1.user_id,u1.user_name,u1.email,"+
			"u1.created,c1.country_id,c1.country,u1.mobile,u1.profpic from "+
			"connects as c inner join users as u1 "+
			"inner join countries as c1 where "+
			"c.from_user_id=u1.user_id and "+
			"u1.country_id=c1.country_id "+
			"and c.status_id=9 and c.to_user_id=?";

			PreparedStatement ps = con.prepareStatement(query); 

			ps.setInt(1,toUser.getUserId());

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				connects.add(new Connect(rs.getInt(1),
										 new User(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),new Country(rs.getInt(6),rs.getString(7)),rs.getString(8),rs.getString(9)),
										 toUser,
										 new Status(9)));
			}

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return connects;
	}
	

	public static void setConnectStatus(int connectId,int statusId){
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");
			
			String query = "update connects set status_id=? where connect_id=?";

			PreparedStatement ps = con.prepareStatement(query); 

			ps.setInt(1,statusId);
			ps.setInt(2,connectId);

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
	
	public static ArrayList<Connect> collectAllInvites(int userId){
		ArrayList<Connect> connects = new ArrayList<Connect>();

		Connection con = null;

		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");
			
			String query = "select connect_id,u1.user_id,u1.user_name,u1.email,"+
			"u1.created,c1.country_id,c1.country,u1.mobile,u1.profpic,u2.user_id,u2.user_name,"+
			"u2.email,u2.created,c2.country_id,c2.country,u2.mobile,u2.profpic,c.status_id from "+
			"connects as c inner join users as u1 inner join users as u2 "+
			"inner join countries as c1 inner join countries as c2 where "+
			"c.from_user_id=u1.user_id and c.to_user_id=u2.user_id and "+
			"u1.country_id=c1.country_id and u2.country_id=c2.country_id "+
			"and c.status_id in (8,9) and (c.from_user_id=? or c.to_user_id=?)";

			PreparedStatement ps = con.prepareStatement(query); 

			ps.setInt(1,userId);
			ps.setInt(2,userId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				connects.add(new Connect(rs.getInt(1),
										 new User(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),new Country(rs.getInt(6),rs.getString(7)),rs.getString(8),rs.getString(9)),
										 new User(rs.getInt(10),rs.getString(11),rs.getString(12),rs.getTimestamp(13),new Country(rs.getInt(14),rs.getString(15)),rs.getString(16),rs.getString(17)),
										 new Status(rs.getInt(18))));
			}
			
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return connects;
	}


	public void inviteUser(){
		Connection con = null;

		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");
			
			String query = "insert into connects (from_user_id,to_user_id,status_id) value (?,?,?)";
			PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS); 

			ps.setInt(1,fromUser.getUserId());
			ps.setInt(2,toUser.getUserId());
			ps.setInt(3,status.getStatusId());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if(rs.next()){
				connectId = rs.getInt(1);
			}
			
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

	public static Connect getConnectInfo(int curUserId, int searchUserId){
		Connect connect = null;
		
		Connection con = null;

		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");
			
			String query = "select "+
							"connect_id,from_user_id,u1.user_name,u1.email,u1.created,"+
							"t1.country,u1.mobile,u1.profpic,to_user_id,u2.user_name,"+
							"u2.email,u2.created,t2.country,u2.mobile,u2.profpic,c.status_id "+
							"from "+
							"connects as c inner join users as u1 "+
							"inner join users as u2 inner join countries as t1 "+
							"inner join countries as t2 "+
							"where "+ 
							"c.from_user_id=u1.user_id and c.to_user_id=u2.user_id and "+
							"u1.country_id=t1.country_id and u2.country_id=t2.country_id "+
							" and ((from_user_id=? and to_user_id=?) or (from_user_id=? and to_user_id=?))";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,curUserId);
			ps.setInt(2,searchUserId);
			ps.setInt(3,searchUserId);
			ps.setInt(4,curUserId);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				connect = new Connect(
									rs.getInt(1),
									new User(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),new Country(rs.getString(6)),rs.getString(7),rs.getString(8)),
									new User(rs.getInt(9),rs.getString(10),rs.getString(11),rs.getTimestamp(12),new Country(rs.getString(13)),rs.getString(14),rs.getString(15)),
									new Status(rs.getInt(16))
								);
			}


		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return connect;
	}


	public static void deleteConnection(int connectId){
		Connection con = null;
		
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");
			
			String query = "delete from connects where connect_id=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,connectId);

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

	public static ArrayList<Connect> collectAllConnections(Integer userId){
		ArrayList<Connect> connections = new ArrayList<Connect>();

		Connection con = null;

		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");
			
			String query = "select "+
							"connect_id,from_user_id,u1.user_name,u1.email,u1.created,"+
							"t1.country,u1.mobile,u1.profpic,to_user_id,u2.user_name,"+
							"u2.email,u2.created,t2.country,u2.mobile,u2.profpic "+
							"from "+
							"connects as c inner join users as u1 "+
							"inner join users as u2 inner join countries as t1 "+
							"inner join countries as t2 "+
							"where "+ 
							"c.from_user_id=u1.user_id and c.to_user_id=u2.user_id and "+
							"u1.country_id=t1.country_id and u2.country_id=t2.country_id "+
							"and c.status_id=7 and (from_user_id=? or to_user_id=?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1,userId);
			ps.setInt(2,userId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				connections.add(new Connect(
									rs.getInt(1),
									new User(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5),new Country(rs.getString(6)),rs.getString(7),rs.getString(8)),
									new User(rs.getInt(9),rs.getString(10),rs.getString(11),rs.getTimestamp(12),new Country(rs.getString(13)),rs.getString(14),rs.getString(15)),
									null
								));
			}

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		
		return connections;
	}


	
	public Integer getConnectId() {
		return connectId;
	}
	
	public void setConnectId(Integer connectId) {
		this.connectId = connectId;
	}	
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setFromUser(User fromUser){
		this.fromUser = fromUser;
	}

	public User getFromUser(){
		return fromUser;
	}

	public void setToUser(User toUser){
		this.toUser = toUser;
	}

	public User getToUser(){
		return toUser;
	}
}
