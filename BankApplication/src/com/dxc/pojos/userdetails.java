package com.dxc.pojos;

public class userdetails {
	private int accno;
	private String username;
	private double accbalance;
	private int userid;
	private String  userpassword;
	public userdetails()
	{
		
	}
	public userdetails(int accno, String username, double accbalance, int userid, String userpassword) {
		super();
		this.accno = accno;
		this.username = username;
		this.accbalance = accbalance;
		this.userid = userid;
		this.userpassword = userpassword;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getAccbalance() {
		return accbalance;
	}
	public void setAccbalance(double accbalance) {
		this.accbalance = accbalance;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	
}
