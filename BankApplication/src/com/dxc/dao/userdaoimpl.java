package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.transaction;
import com.dxc.pojos.userdetails;

public class userdaoimpl implements iuserdao {

	
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

	
	public boolean authenticate(int userid,String password) {
		try {
		PreparedStatement pstmt=conn.prepareStatement("select userid,userpassword from bankuser where userid=? and userpassword=?");
		pstmt.setInt (1,userid);
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
	public boolean depositAmount(int accno, double amount) {
		double balance=0;
		try
		{PreparedStatement pstmt=conn.prepareStatement("select * from bankuser where accno=?");
		pstmt.setInt(1,accno);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		balance=rs.getDouble(3);
		balance=balance+amount;
		pstmt.close();
		PreparedStatement pstmt1=conn.prepareStatement("update bankuser set accbalance=? where accno=?");
		pstmt1.setDouble(1,balance);
		pstmt1.setInt(2, accno);
		pstmt1.executeUpdate();
		PreparedStatement pstmt2=conn.prepareStatement("insert into transactiondetails values(?,?,?)");
		pstmt2.setInt(1,accno);
		pstmt2.setString(2, "credit");
		pstmt2.setDouble(3, amount);
		pstmt2.execute();
		
		return true;
		
		
		
		}
		catch(Exception e)
		{e.printStackTrace();
			}
		return false;
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
	
	public boolean check(int accno, String password) {
		try {
			PreparedStatement pstmt=conn.prepareStatement("select accno,userpassword from bankuser where accno=? and userpassword=?");
			pstmt.setInt (1,accno);
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
	
	public boolean withdrawamount(int accno, double accbalance) {
		if(accbalance>this.viewbalance(accno))
			
		{
		return false;
	}
		double balance=0;
		try
		{
			PreparedStatement pstmt=conn.prepareStatement("select *from bankuser where accno=?");
			pstmt.setInt(1,accno);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			balance=rs.getDouble(3);
			balance=balance-accbalance;
			pstmt.close();
			PreparedStatement pstmt1=conn.prepareStatement("update bankuser set accbalance=? where accno=?");
			pstmt1.setDouble(1, balance);
			pstmt1.setInt(2,accno);
			pstmt1.executeUpdate();
			PreparedStatement pstmt2=conn.prepareStatement("insert into transactiondetails values(?,?,?)");
			pstmt2.setInt(1, accno);
			pstmt2.setString(2, "debit");
			pstmt2.setDouble(3,accbalance);
			pstmt2.execute();
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
			}
	public boolean transfer(int accno, double tamount, int taccno) {
		System.out.println(tamount);
		System.out.println(this.viewbalance(taccno));
		if(tamount>this.viewbalance(accno))
		{
			
			return false;
		}
		
	this.withdrawamount(accno, tamount);
	this.depositAmount(taccno, tamount);
	try {
		PreparedStatement pstmt=conn.prepareStatement("Insert into transactiondetails values(?,?,?)");
		pstmt.setInt(1,accno);
		pstmt.setString(2,"transfered");
		pstmt.setDouble(3,tamount);
		pstmt.execute();
		PreparedStatement pstmt1=conn.prepareStatement("Insert into transactiondetails values(?,?,?)");
		pstmt1.setInt(1,taccno);
		pstmt1.setString(2,"credit");
		pstmt1.setDouble(3,tamount);
		pstmt1.execute();
		return true;
		
	}
	catch(SQLException e) 
	{
		e.printStackTrace();
	}
		return false;
	}
	
	
	public List<transaction> TransactionDetails(int accno) {
		List <transaction>list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from transactiondetails");
			while(rs.next())
			{
				if(accno==rs.getInt(1))
				{
					list.add(new transaction(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
				
				}
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return list;
		
	}
	
	public void PasswordChange(int accno, String password) {
		  PreparedStatement pstmt1;
			try {
				pstmt1 = conn.prepareStatement("update bankuser set userpassword=? where accno=?");
				pstmt1.setString(1, password);
				pstmt1.setInt(2,accno);
				pstmt1.executeUpdate();
				  
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				

	
		
	}
	
	public int getAccno(int userid, String password) {
		int accno=0;
		try {
			PreparedStatement pstmt = conn.prepareStatement("select *from bankuser where userid=? && userpassword=?");
			pstmt.setInt(1, userid);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			accno=rs.getInt(1);
			return rs.getInt(1);
		} 
	
		catch (SQLException e) {
		
			e.printStackTrace();
		}
		return accno;
	}

		
		
	}
	
		
	

			
			
			
			
	
		
		
	
			
	
		
		
	
		

	

