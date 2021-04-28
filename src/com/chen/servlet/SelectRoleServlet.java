package com.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.entity.Layui;
import com.chen.entity.User_Role;

import com.chen.services.ControlService;
import com.chen.services.ControlServiceImpl;
import com.chen.utils.PrintUtil;
import com.chen.utils.ReturnResult;
import com.chen.web.AbstractServlet;

@WebServlet("/SelectRoleServlet")
public class SelectRoleServlet extends AbstractServlet{

	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return SelectRoleServlet.class;
	}
	// 查询角色数据
		public void selectRole(HttpServletRequest request, HttpServletResponse response)
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
			Layui<User_Role> list = cs.SelectUserRole(name, currPage, pageSize);

			PrintUtil.write(list, response);
		}

		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			doPost(request, response);
		}

		// 删除角色数据
		public int delRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			int id = Integer.parseInt(request.getParameter("id"));
			ControlService cs = new ControlServiceImpl();
			int num = cs.delRole(id);
			return num;
		}
		// 增加角色
		public int addRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");

			String rname = request.getParameter("rname");
			User_Role role = new User_Role();
			role.setRname(rname);
			
			ControlService cs = new ControlServiceImpl();
			int num = cs.addRole(role);

			return num;
		}
		// 修改角色前的查询
		public void selectRoleUp(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			ControlService cs = new ControlServiceImpl();
			int id = Integer.parseInt(request.getParameter("id"));
			PrintUtil.write(cs.SelectRoleOne(id), response);
		}

		// 修改权限
		public int upRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			int uid = Integer.parseInt(request.getParameter("roleid"));
			String rname = request.getParameter("rname");
			ControlService cs = new ControlServiceImpl();
		     User_Role role=new User_Role();
		     role.setId(uid);
		     role.setRname(rname);
			
			int num = cs.upRole(role);
			return num;
		}
		public ReturnResult menuByUserListId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int roleId = Integer.parseInt(request.getParameter("id"));	
			String[] menuidList = request.getParameterValues("array");
		
			ReturnResult result = new ReturnResult();
			int row = new ControlServiceImpl().delUserByid(roleId, menuidList);
			if(row != 0 ) {
				result.returnSuccess("分配权限成功");
			}
			return result;
		}
		// 根据用户查询 并分配权限
		public void menuByUserid1(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");

			int id = Integer.parseInt(request.getParameter("id"));
			
			ControlService cs = new ControlServiceImpl();
			PrintUtil.write(cs.roleByUserId(id), response);
		}
		// 查询分配权限
		public void allRole(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			ControlService cs = new ControlServiceImpl();

			PrintUtil.write(cs.selectAll(), response);
		}
}
