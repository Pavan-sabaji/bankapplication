package com.dxc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.transaction;
import com.dxc.pojos.userdetails;
import com.dxc.service.adminserviceimpl;
import com.dxc.service.iadminsevice;
import com.dxc.service.iuserservice;
import com.dxc.service.userserviceimpl;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

;


@WebServlet("/user")
public class userServlet extends HttpServlet {
	iuserservice userservice=new userserviceimpl();
	int accno;
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		
		String action="";
		String message="";
		
		String temp=request.getParameter("btn");
		if (temp!=null)
			action=request.getParameter("btn");
		HttpSession session=request.getSession();
		if(action.equals("login"))
		{
			int userid=Integer.parseInt(request.getParameter("userid"));
			String password=request.getParameter("userpassword");
			
			accno=userservice.getAccno(userid,password);
			boolean b=userservice.authenticate(userid,password);
			if (b==true) 
			{
			
				
				response.sendRedirect("useroption.jsp");
		
			}
			else 
			{
				message="incorrect login credentials";
				session.setAttribute("message",message);
				response.sendRedirect("view.jsp");
			}
			
	
		}
		else if(action.equals("deposit"))
				{
			int taccno=Integer.parseInt(request.getParameter("accno"));
			double amount=Double.parseDouble(request.getParameter("amount"));
			String password=(request.getParameter("password"));
			
			
			userservice.depositAmount(taccno,amount);
			try {
				PrintWriter pwt=response.getWriter();
				pwt.println("Amount deposited uccessfully");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
				}
			else if (action.equals("showbalance"))
			{
				
					
					double b=userservice.viewbalance(accno);
					try {
						PrintWriter pwt=response.getWriter();
						pwt.println("Account balance is"+b);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				
				
			}
			else if(action.equals("withdraw"))
			{
			
				String password=request.getParameter("password");
				double accbalance=Double.parseDouble(request.getParameter("amount"));
			boolean b=userservice.check(accno, password);
				if(b==true)
				{
				
					boolean b1=userservice.withdrawamount(accno,accbalance);
				    if(b1==true) 
				{
					try {
						PrintWriter pwt=response.getWriter();
						pwt.println("amount withdraw successful");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
				else
				{
					try {
						PrintWriter pwt=response.getWriter();
						pwt.println("insufficient balance");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
				    
				}
				else
				{
					try {
						PrintWriter pwt=response.getWriter();
						pwt.println("password id incorrect");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}
	
			else if(action.equals("transfer"))
			{
				
				
				String password=request.getParameter("password");
				boolean d=userservice.check(accno, password);
				if (d==true)
				{
					int taccno=Integer.parseInt(request.getParameter("taccno"));
					double tamount=Double.parseDouble(request.getParameter("tamount"));
					boolean c1=userservice.transfer(accno,tamount,taccno);
					if(c1==true)
					{
						try {
							PrintWriter pwt=response.getWriter();
							pwt.println("amount transfer successful");
						} catch (IOException e) {
							
							e.printStackTrace();
						}
					}
				}
				else 
				{
					try {
						PrintWriter pwt=response.getWriter();
						pwt.println("password is wrong");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}
			else if(action.equals("passwordchange"))
			{
				
				String password1=request.getParameter("password1");
				String cpassword=request.getParameter("npassword");
				if(password1.equals(cpassword))
				{
					userservice.PasswordChange(accno,password1);
					try {
						PrintWriter pwt=response.getWriter();
						pwt.println("password change successful");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
					else 
					{
						try {
							PrintWriter pwt=response.getWriter();
							pwt.println("password change unsuccessful");
						} catch (IOException e) {
							
							e.printStackTrace();
						}
					}
				
				
				}
			else 
			{
				List<transaction> list=userservice.TransactionDetails(accno);
				session.setAttribute("list", list);
				response.sendRedirect("showtransactions.jsp");

			}
			}
	
}





	
	
				
			
				

				
			
				
			
			
				
	
			
	



		
	


	
	


