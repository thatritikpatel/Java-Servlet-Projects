package models;

import java.sql.*;
import java.util.ArrayList;

public class Country{
	private Integer countryId;
	private String country;

	public Country(Integer countryId,String country){
		this.countryId = countryId;
		this.country = country;
	}

	public Country(String country){
		this.country = country;
	}

	public Country(Integer countryId){
		this.countryId = countryId;
	}

	public Country(){

	}

	public static ArrayList<Country> collectCountries(){
		ArrayList<Country> countries = new ArrayList();
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp?user=root&password=1234");

			String query = "select * from countries";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				countries.add(new Country(rs.getInt(1),rs.getString(2)));
			}
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}finally{
			try{con.close();}catch(SQLException e){ e.printStackTrace();}
		}

		return countries;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCountryId(Integer countryId){
		this.countryId = countryId;
	}

	public Integer getCountryId(){
		return countryId;
	}
}