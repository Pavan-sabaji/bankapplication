package com.dxc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.dxc.pojos.userdetails;
import com.dxc.service.adminserviceimpl;
import com.dxc.service.iadminsevice;


@WebServlet("/admin")
public class adminServlet extends HttpServlet {
	
	public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		iadminsevice adminservice=new adminserviceimpl();
		String action="";
		String message="";
		String temp=request.getParameter("btn");
		if (temp!=null)
			action=request.getParameter("btn");
		HttpSession session=request.getSession();
		if(action.equals("login"))
		{
			String adminid=request.getParameter("adminid");
			String password=request.getParameter("password");
			boolean b=adminservice.authenticate(adminid,password);
			if (b==true) 
			{
				message=" login successful";
				session.setAttribute("message",message);
				
				response.sendRedirect("adminoptionjsp.jsp");
			}
			else 
			{
				message="incorrect login credentials";
				session.setAttribute("message",message);
				response.sendRedirect("view.jsp");
			}
			
			
		}
		else if(action.equals("addcustomer"))
		{
			int accno=Integer.parseInt(request.getParameter("accno"));
			String username=request.getParameter("username");
			double accbalance=Double.parseDouble(request.getParameter("accbalance"));
			int userid=Integer.parseInt(request.getParameter("userid"));
			String userpassword=request.getParameter("userpassword");
			
			userdetails u1=new userdetails(accno, username, accbalance,userid,userpassword);
			adminservice.adduser(u1);
			message="user added successfully";
			session.setAttribute("message",message);
			response.sendRedirect("view.jsp");
	}
		else if (action.equals("findcustomer"))
		{
			int accno=Integer.parseInt(request.getParameter("accno"));
			session.setAttribute("accno", accno);
			 userdetails findStatus=adminservice.findcustomer(accno);
		session.setAttribute("findStatus", findStatus);
		response.sendRedirect("showusers.jsp");
			
		}
		else if(action.equals("find"))
		{
			int accno=Integer.parseInt(request.getParameter("accno"));
		session.setAttribute("accno", accno);
		boolean findStatus=adminservice.searchaccno(accno);
		if(findStatus==true)
		{
			response.sendRedirect("updatecustomer2.jsp");
		}
		else
		{
			message="account  not found!";
			session.setAttribute("message", message);
			response.sendRedirect("view.jsp");
		}
	}
	else if(action.equals("update"))
	{
		int accno=(int)session.getAttribute("accno");
		String username=request.getParameter("username");
		double accbalance=Double.parseDouble(request.getParameter("accbalance"));
		int userid=Integer.parseInt(request.getParameter("userid"));
		String userpassword=request.getParameter("userpassword");
		userdetails u2=new userdetails(accno,username,accbalance,userid,userpassword);
		
		adminservice.updateUser(u2);
		
		message="user details successfully updated!";
		session.setAttribute("message", message);
		response.sendRedirect("view.jsp");
		
	}
	else if(action.equals("showbalance"))	
	{
		int accno=Integer.parseInt(request.getParameter("accno"));
		double b=adminservice.viewbalance(accno);
		try {
			PrintWriter pwt=response.getWriter();
			pwt.println("Account balance is"+b);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	else if(action.equals("delet"))
	{
		int accno=Integer.parseInt(request.getParameter("accno"));
		adminservice.deletaccount(accno);
		message="User Account successfully deleted";
		session.setAttribute("message", message);
		response.sendRedirect("view.jsp");
	}
	else
	{

		List<userdetails> list=adminservice.getAllaccounts();
		session.setAttribute("list", list);
		response.sendRedirect("showaccounts.jsp");
	}
		}
		}
		

	