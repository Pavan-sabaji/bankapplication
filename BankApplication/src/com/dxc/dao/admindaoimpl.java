package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.dxc.pojos.userdetails;

public class admindaoimpl implements iadmindao {
private static Connection conn;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded...");
			
		 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankApp","root","pass");
			System.out.println("connected to database....");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	public boolean authenticate(String adminid,String password) {
		try {
		PreparedStatement pstmt=conn.prepareStatement("select adminid,password from bankadmin where adminid=? and password=?");
		pstmt.setString(1,adminid);
		pstmt.setString(2,password);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next())
		{
		return true;	
		}
		else
		{
			return false;	
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}



	public void adduser(userdetails u1) {
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("insert into bankuser values(?,?,?,?,?)");
			pstmt.setInt(1,u1.getAccno());
			pstmt.setString(2,u1.getUsername());
			pstmt.setDouble(3,u1.getAccbalance());
			pstmt.setInt(4,u1.getUserid() );
			pstmt.setString(5,u1.getUserpassword());
			pstmt.execute();
			} 
		catch (SQLException e) 
		{
		e.printStackTrace();
		
		
	
		}	
	}

	public userdetails findcustomer(int accno) {
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from bankuser");
			while(rs.next())
			{
				if(accno==rs.getInt(1))
				{
					return new userdetails(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getString(5));
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	
		
		
	}




	public boolean searchaccno(int accno) {
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from bankuser");
			while(rs.next())
			{
				if(accno==rs.getInt(1))
				{
					return true;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
		return false;
	}
       public void updateUser(userdetails u2)
       {try
       {
			PreparedStatement pstmt=conn.prepareStatement("update bankuser set username=?, accbalance=?,userid=?,userpassword=? where accno=?");
			pstmt.setString(1, u2.getUsername());
			pstmt.setDouble(2, u2.getAccbalance());
			pstmt.setInt(3,u2.getUserid());
			pstmt.setString(4,u2.getUserpassword());
			pstmt.setInt(5, u2.getAccno());
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}




	public double viewbalance(int accno) {
			Statement stmt;
			double accbalance=0;
			try {
				 stmt=conn.createStatement();
					ResultSet rset=stmt.executeQuery("select * from bankuser");
					
					while(rset.next()) 
					{
						if (accno==rset.getInt(1))
						{
							accbalance=rset.getDouble(3);
							
						}
			
			
			
					}
			
			}
			
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return accbalance;
			
		}




	public void deletaccount(int accno) {
		
		try {
			PreparedStatement pstmt=conn.prepareStatement("delete from bankuser where accno=?");
			pstmt.setInt(1, accno);
			pstmt.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}


	
	public List<userdetails> getAllaccounts() {
		List<userdetails> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from bankuser");
			while(rs.next())
			{
				list.add(new userdetails(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getString(5)));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;

	}


		
	}






		
	

