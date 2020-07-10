package com.dxc.pojos;

public class transaction {
	private int accno;
	private String transaction;
	private double amount;
	public transaction()
	{
		
	}
	public transaction(int accno, String transaction, double amount) {
		super();
		this.accno = accno;
		this.transaction = transaction;
		this.amount = amount;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
