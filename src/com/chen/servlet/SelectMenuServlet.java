package com.chen.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.entity.Layui;
import com.chen.entity.ls_menu;
import com.chen.services.ControlService;
import com.chen.services.ControlServiceImpl;
import com.chen.utils.PrintUtil;
import com.chen.web.AbstractServlet;
@WebServlet("/SelectMenuServlet")
public class SelectMenuServlet extends AbstractServlet{

	@Override
	public Class getServletClass() {
		// TODO Auto-generated method stub
		return SelectMenuServlet.class;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}
	//查询所有权限数据
	public void SelectMenu(HttpServletRequest request, HttpServletResponse response)
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
		Layui<ls_menu> list = cs.SelectMenu(name, currPage, pageSize);
		// System.out.println(JSONArray.toJSONString(list.getData()));
		PrintUtil.write(list, response);
	}
	//新增权限
	public int addMenu(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		String mname = request.getParameter("mname");
		String url = request.getParameter("url");
		String button = request.getParameter("mbtn");
		int fatherId = Integer.parseInt(request.getParameter("father"));
		int type = Integer.parseInt(request.getParameter("type2"));

		ControlService cs = new ControlServiceImpl();

		ls_menu menu = new ls_menu();
		menu.setMname(mname);
		menu.setMfatherid(fatherId);

		menu.setType(type);
		menu.setButton(button);
		menu.setUrl(url);

		int num = cs.addMenu(menu);

		return num;
	}
	//根据类型id 查询父级按钮
	public void SelectMenuFather(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		int typeId = Integer.parseInt(request.getParameter("type"));
		int num = typeId - 1;
		ControlService cs = new ControlServiceImpl();
		List<ls_menu> list = cs.SelectBtn(num);
		System.out.println(num);
		PrintUtil.write(list, response);
	}
	//根据类型id 查询父级按钮
		public void SelectMenuBtnSon(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			int typeId = Integer.parseInt(request.getParameter("type"));
			ControlService cs = new ControlServiceImpl();
			List<ls_menu> list = cs.SelectBtn(typeId);
			// System.out.println(num);
			PrintUtil.write(list, response);
		}
		//删除权限
		public int delMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");

			int id = Integer.parseInt(request.getParameter("id"));
			ControlService cs = new ControlServiceImpl();
			int num = cs.delMenu(id);
			return num;
		}
		// 修改权限前的查询
		public void selectMenuUp(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			ControlService cs = new ControlServiceImpl();
			int menuId = Integer.parseInt(request.getParameter("id"));
			PrintUtil.write(cs.SelectMenuOne(menuId), response);
		}
		// 执行修改权限
		public int upMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			int id = Integer.parseInt(request.getParameter("mid"));
			String mname = request.getParameter("mname");
			String url = request.getParameter("url");
			String button = request.getParameter("mbtn");
			int fatherId = Integer.parseInt(request.getParameter("father"));
			int type = Integer.parseInt(request.getParameter("type2"));

			ControlService cs = new ControlServiceImpl();

			ls_menu menu = new ls_menu();
			menu.setId(id);
			menu.setMname(mname);
			menu.setMfatherid(fatherId);

			menu.setType(type);
			menu.setButton(button);
			menu.setUrl(url);

			int num = cs.upMenu(menu);

			return num;
		}
}
