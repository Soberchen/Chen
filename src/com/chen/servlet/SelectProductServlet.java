package com.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.services.ControlService;
import com.chen.services.ControlServiceImpl;
import com.chen.utils.PrintUtil;
import com.chen.web.AbstractServlet;

@WebServlet("/SelectProductServlet")
public class SelectProductServlet  extends AbstractServlet{

	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return  SelectProductServlet.class;
	}
	public void selectProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControlService cs = new ControlServiceImpl();
	
		PrintUtil.write(cs.selecProducts(), response);
	}
}
