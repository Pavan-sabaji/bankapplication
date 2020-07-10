package com.dxc.service;



import java.util.List;

import com.dxc.dao.admindaoimpl;
import com.dxc.dao.iadmindao;

import com.dxc.pojos.userdetails;

public class adminserviceimpl implements iadminsevice{

	iadmindao dao=new admindaoimpl();
	public boolean authenticate(String adminid,String password) {
		return dao.authenticate(adminid,password);
		
		
	}

	public void adduser(userdetails u1) {
		dao.adduser(u1);
		
	}
	public userdetails findcustomer(int accno) {
		return dao.findcustomer(accno);
		
	}

	public boolean searchaccno(int accno) {
	
		return dao.searchaccno(accno);
	}


	public void updateUser(userdetails u2) {
	
		dao.updateUser(u2);
	}

	
	public double viewbalance(int accno) {
	
		return dao.viewbalance(accno);
	}

	
	public void deletaccount(int accno) {
		dao.deletaccount(accno);
	
		
	}

	
	public List<userdetails> getAllaccounts() {
	
		return dao.getAllaccounts();
	}

	

	
}
