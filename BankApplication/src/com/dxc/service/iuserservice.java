package com.dxc.service;
import java.util.List;

import com.dxc.pojos.transaction;

public interface iuserservice {

	boolean authenticate(int userid, String password);
	boolean depositAmount(int accno, double amount);
	double viewbalance(int accno);
	boolean check(int accno, String password);
	boolean withdrawamount(int accno, double accbalance);
	boolean transfer(int accno, double tamount, int taccno);
	
	List<transaction> TransactionDetails(int accno);
	void PasswordChange(int accno, String password);
	int getAccno(int userid, String password);

}