package com.chen.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chen.entity.ls_menu;
import com.chen.services.ControlService;
import com.chen.services.ControlServiceImpl;
import com.chen.web.AbstractServlet;
@WebServlet("/ShowSelectServlet")
public class ShowSelectServlet extends AbstractServlet{

	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return ShowSelectServlet.class;
	}
	public String ShowUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		int  id=Integer.parseInt(request.getParameter("id"));
		
		ControlService cs = new ControlServiceImpl();
	
		List<ls_menu>list=cs.SelectContext(id);
		
		HttpSession session=request.getSession();
		session.setAttribute("list1", list);
		return "showButton/userbtn";
	}
	public String ShowRole(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		int  id=Integer.parseInt(request.getParameter("id"));
		
		ControlService cs = new ControlServiceImpl();
	
		List<ls_menu>list=cs.SelectContext(id);
		
		HttpSession session=request.getSession();
		session.setAttribute("list1", list);
		
		
		return "showButton/roleBtn";
	}	
	public String ShowManage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		int  id=Integer.parseInt(request.getParameter("id"));
		
		ControlService cs = new ControlServiceImpl();
	
		List<ls_menu>list=cs.SelectContext(id);
		
		HttpSession session=request.getSession();
		session.setAttribute("list1", list);
		
		
		return "showButton/manageBtn";
	}	
	public String ShowProduct(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		int  id=Integer.parseInt(request.getParameter("id"));
		
		ControlService cs = new ControlServiceImpl();
	
		List<ls_menu>list=cs.SelectContext(id);
		
		HttpSession session=request.getSession();
		session.setAttribute("list1", list);
		return "showButton/productList";
	}

}
