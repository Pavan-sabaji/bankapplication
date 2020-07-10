package com.dxc.pojos;

public class bankAdmin {
	private String adminid;
	private String password;
	public bankAdmin()
	{
		
	}
	public bankAdmin(String adminid, String password) {
		super();
		this.adminid = adminid;
		this.password = password;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
