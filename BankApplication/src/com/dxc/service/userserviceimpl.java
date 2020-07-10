package com.dxc.service;

import java.util.List;

import com.dxc.dao.iuserdao;
import com.dxc.dao.userdaoimpl;
import com.dxc.pojos.transaction;

public class userserviceimpl implements iuserservice{
iuserdao dao=new userdaoimpl();

	public boolean authenticate(int userid, String password)
	{
		return dao.authenticate(userid,password);
	}
	public boolean depositAmount(int accno, double amount) {

		return dao.depositAmount(accno, amount);
	}

	public double viewbalance(int accno) {
		
		return dao.viewbalance(accno);
	}
	
	public boolean check(int accno, String password) {
		
		return dao.check(accno, password);
	}
	
	public boolean withdrawamount(int accno, double accbalance) {
		
		return dao.withdrawamount(accno, accbalance);
	}
	public boolean transfer(int accno, double tamount, int taccno) {
	
		return dao.transfer(accno, tamount, taccno);
	}
	
	
	public List <transaction>TransactionDetails(int accno) {
		
		return dao.TransactionDetails(accno);
	}

	public void PasswordChange(int accno, String password) {
	 dao.PasswordChange(accno, password);
		
	}
	public int getAccno(int userid, String password) {
		return dao.getAccno(userid,password);
	}

	


}
