package com.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.CDATASection;

import com.chen.entity.Layui;
import com.chen.entity.User;
import com.chen.entity.UserWorkPart;
import com.chen.entity.User_Role;
import com.chen.entity.complation;
import com.chen.services.ControlService;
import com.chen.services.ControlServiceImpl;
import com.chen.utils.PrintUtil;
import com.chen.web.AbstractServlet;

@WebServlet("/SelectUserServlet")
public class SelectUserServlet extends AbstractServlet {

	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return SelectUserServlet.class;
	}
	//选中下拉框 角色
	public void selectUserUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControlService cs = new ControlServiceImpl();
		int uid = Integer.parseInt(request.getParameter("id"));
		PrintUtil.write(cs.selectRolByUid(uid), response);
	}
	//选中下拉框 职称
	public void selectUserUpWork(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControlService cs = new ControlServiceImpl();
		int uid = Integer.parseInt(request.getParameter("id"));
		PrintUtil.write(cs.selectWorkByuid(uid), response);
	}
	//选中下拉框 部门
		public void selectUserUpPart(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			ControlService cs = new ControlServiceImpl();
			int uid = Integer.parseInt(request.getParameter("id"));
			PrintUtil.write(cs.selectPartByuid(uid), response);
		}
	public void selectUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("uname");
		int currPage = 1;
		String currPageinfo = request.getParameter("page");
		if (currPageinfo != null && currPageinfo.length() != 0) {
			currPage = Integer.parseInt(currPageinfo);
		}
		String pageSizeInfo = request.getParameter("limit");
		int pageSize = 4;
		if (pageSizeInfo != null && pageSizeInfo.length() != 0) {
			pageSize = Integer.parseInt(pageSizeInfo);
		}

		ControlService cs = new ControlServiceImpl();
		Layui<complation> list = cs.SelectUser(name, currPage, pageSize);

		PrintUtil.write(list, response);
	}
	public void selectUserBtn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		

		ControlService cs = new ControlServiceImpl();
		PrintUtil.write(cs.SelectBtn(), response);
	}

	public int addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		String loginName = request.getParameter("loginname");
		
		int sex = Integer.parseInt(request.getParameter("sex"));
		String email = request.getParameter("email");
		String mobile = request.getParameter("phone");
		int type=Integer.parseInt(request.getParameter("type"));
		int role = Integer.parseInt(request.getParameter("role"));
		int work=Integer.parseInt(request.getParameter("work"));
		int part=Integer.parseInt(request.getParameter("part"));

		ControlService cs = new ControlServiceImpl();
		User use = new User();
		use.setUserName(uname);
		use.setPassword(password);
		use.setLoginName(loginName);
		use.setSex(sex);
		
		use.setEmail(email);
		use.setMobile(mobile);
		use.setType(type);
		
		User_Role ur = new User_Role();
		ur.setId(role);
		
		UserWorkPart uwp=new UserWorkPart();
		uwp.setpId(part);
		uwp.setwId(work);
		
		int num = cs.addUser(use);
		int num1 = cs.addUserRole(cs.userByid(uname).getId(), ur);
		int num2=cs.addUserWork(cs.userByid(uname).getId(), uwp);

		return num;
	}
	public void delUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		
		ControlService cs = new ControlServiceImpl();
		PrintUtil.write(cs.delUser(id), response);
	}
	//修改前的查询
	public void selectUserOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControlService cs = new ControlServiceImpl();
		int roleId = Integer.parseInt(request.getParameter("uid"));
		System.out.println(roleId);
		PrintUtil.write(cs.selectUserOne(roleId), response);
	}
	//修改前的查询按钮
	public void selectRoleUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ControlService cs = new ControlServiceImpl();
		
		
		PrintUtil.write(cs.selectUserRole(), response);
	}
	//修改前的查询按钮
		public void selectWorkUser(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			ControlService cs = new ControlServiceImpl();
			
			
			PrintUtil.write(cs.selectWork(), response);
		}
		//修改前的查询按钮
		public void selectPartUser(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			ControlService cs = new ControlServiceImpl();
			
			
			PrintUtil.write(cs.selectPart(), response);
		}
	//执行修改方法
	public int upUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		int uid = Integer.parseInt(request.getParameter("uid"));
		String uname = request.getParameter("uname");
		String loginname = request.getParameter("loginname");
		String password = request.getParameter("password");
		int  part=Integer.parseInt(request.getParameter("part"));
		int work=Integer.parseInt(request.getParameter("work"));
		int role=Integer.parseInt(request.getParameter("role1"));
		int sex = Integer.parseInt(request.getParameter("sex"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int type = Integer.parseInt(request.getParameter("isStatus"));
		ControlService cs = new ControlServiceImpl();
		User use = new User();
		UserWorkPart uwp=new UserWorkPart();
		uwp.setwId(work);
		uwp.setpId(part);
		User_Role ur=new User_Role();
		ur.setId(role);
		use.setId(uid);
		use.setUserName(uname);
		use.setPassword(password);
		use.setLoginName(loginname);
		use.setSex(sex);
		use.setEmail(email);
		use.setMobile(phone);
		use.setType(type);
		int num = cs.upUser(use);
		int num2=cs.upPart(uid, uwp);
		int num3=cs.upUserRole(uid, ur);
		return num;
	}
}
