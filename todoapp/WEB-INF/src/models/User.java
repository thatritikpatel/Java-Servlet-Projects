package models;

import java.sql.*;
import java.util.ArrayList;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class User{
	//########## VARIABLES #############	
	private Integer userId;
	private String userName;
	private String email;
	private String password;
	private Timestamp created;
	private Country country;
	private Status status;
	private String mobile;
	private String profpic;
	private String activationCode;

	//static StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
	
	//########## Constructors #############
	public User(){
	
	}

	public User(String userName){
		this.userName = userName;
	}

	public User(Integer userId){
		this.userId = userId;
	}

	public User(String email,String password){
		this.email = email;
		this.password = password;
	}

	public User(Integer userId,String userName,String email,Timestamp created,Country country,String mobile,String profpic){
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.created = created;
		this.country = country;
		this.mobile = mobile;
		this.profpic = profpic;
	}

	public User(Integer userId,String userName){
		this.userId = userId;
		this.userName = userName;	
	}

	
	
	//########## OTHER METHODS #############
	public void getUserInfo(){
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");

			String query = "select user_name,email,created,mobile,"+
						   "profpic,u.country_id,country from users as u inner join "+
						   "countries as c where u.country_id=c.country_id "+
					       "and u.user_id=?";
			System.out.println(query);
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,userId);
			ResultSet rs = ps.executeQuery();

			if(rs.next()){				
				userName = rs.getString(1);
				email = rs.getString(2);
				created = rs.getTimestamp(3);
				mobile = rs.getString(4);
				profpic = rs.getString(5);
				country = new Country(rs.getInt(6),rs.getString(7));
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


	public static ArrayList<User> searchUsers(String searchKey){
		ArrayList<User> users = new ArrayList<User>();
		
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");

			String query = "select user_id,user_name from users where user_name like '"+searchKey+"%'";
			System.out.println(query);
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()){				
				users.add(new User(rs.getInt(1),rs.getString(2)));
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

		return users;
	}


	public void saveProfPic(){
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");

			String query = "update users set profpic=? where user_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,profpic);
			ps.setInt(2,userId);

			ps.executeUpdate();
		
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{ con.close(); }catch(SQLException e){e.printStackTrace();}
		}
	}



	public static boolean activateAccount(String email,String activationCode){
		boolean flag = false;
		
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");

			String query = "update users set status_id=1 where email=? and activation_code=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1,email);
			ps.setString(2,activationCode);

			int res = ps.executeUpdate();

			if(res==1){
				flag = true;	
			}
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{ con.close(); }catch(SQLException e){e.printStackTrace();}
		}

		return flag;
	}		


	public int signinUser(){
		int signinResp = 1;
		//signinResp: 1(Invalid Email),2(Incorrect Password),3(Status Not Active),4(Sstatus Active);

		Connection con = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");

			String query = "select password,status_id,user_id,user_name,"
							+"created,mobile,u.country_id,country,profpic "
							+"from users as u inner join countries as c "
							+"where email=? and u.country_id=c.country_id";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1,email);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
				if(spe.checkPassword(password,rs.getString(1))){
					if(rs.getInt(2)==Status.ACTIVE){
						System.out.println("~~~~~~~~~~~~~~~~~~~ 4");
						signinResp = 4;
						userId = rs.getInt(3);
						userName = rs.getString(4);
						created = rs.getTimestamp(5);
						mobile = rs.getString(6);
						country = new Country(rs.getInt(7),rs.getString(8));
						profpic = rs.getString(9);
					}else{
						System.out.println("~~~~~~~~~~~~~~~~~~~ 3");
						signinResp = 3;
					}
				}else{
					System.out.println("~~~~~~~~~~~~~~~~~~~ 2");
					signinResp = 2;
				}
			}else{
				System.out.println("~~~~~~~~~~~~~~~~~~~ 1");
				signinResp = 1;
			}

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}

		return signinResp;
	}


	public boolean signupUser(){
		boolean flag  = false;
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");

			String query = "insert into users (user_name,email,password,created,country_id,mobile,activation_code) value (?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,userName);
			ps.setString(2,email);
			StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
			ps.setString(3,spe.encryptPassword(password));
			ps.setTimestamp(4,new Timestamp(new java.util.Date().getTime()));
			ps.setInt(5,country.getCountryId());
			ps.setString(6,mobile);
			ps.setString(7,activationCode);

			int res = ps.executeUpdate();

			if(res==1){
				flag = true;
			}
			
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}finally{
			try{con.close();}catch(SQLException e){ e.printStackTrace();}
		}

		return flag;
	}

	//############ SETTER-GETTER #########
	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public Integer getUserId(){
		return userId;
	}

	public void setUserName(String userName){
		this.userName=userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getEmail(){
		return email;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getPassword(){
		return password;
	}
	
	public void setCreated(Timestamp created){
		this.created=created;
	}

	public Timestamp getCreated(){
		return created;
	}

	public void setCountry(Country country){
		this.country=country;
	}

	public Country getCountry(){
		return country;
	}

	public void setStatus(Status status){
		this.status=status;
	}

	public Status getStatus(){
		return status;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	public String getMobile(){
		return mobile;
	}

	public void setActivationCode(String activationCode){
		this.activationCode = activationCode;
	}

	public String getActivationCode(){
		return activationCode;
	}

	public void setProfpic(String profpic){
		this.profpic = profpic;
	}

	public String getProfpic(){
		return profpic;
	}
}