package com.dxc.dao;




import java.util.List;

import com.dxc.pojos.userdetails;

public interface iadmindao {
	public boolean authenticate(String adminid,String password);
	public void adduser(userdetails u1);
	public userdetails findcustomer(int accno);
	public boolean searchaccno(int accno);
	public void updateUser(userdetails u2);
	public double viewbalance(int accno);
	public void deletaccount(int accno);
	public List<userdetails> getAllaccounts();
	

}
