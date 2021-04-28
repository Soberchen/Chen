package com.chen.services;

import java.util.ArrayList;
import java.util.List;

import com.chen.dao.ControlDao;
import com.chen.dao.ControlDaoImpl;
import com.chen.entity.Layui;
import com.chen.entity.Menu;
import com.chen.entity.Page;
import com.chen.entity.Part;
import com.chen.entity.Product;
import com.chen.entity.Role_Menu;
import com.chen.entity.User;
import com.chen.entity.UserWorkPart;
import com.chen.entity.User_Role;
import com.chen.entity.Work;
import com.chen.entity.complation;
import com.chen.entity.ls_menu;
import com.chen.utils.TransferArray;

public class ControlServiceImpl implements ControlService{
private ControlDao cd=new ControlDaoImpl();

@Override
public User ShowLogin(String name, String password) {
	
	return cd.login(name, password);
}

@Override
public List<ls_menu> SelectAll(int userId) {
	// TODO Auto-generated method stub
	return cd.SelectAll(userId);
}

@Override
public List<ls_menu> SelectContext(int fatherId) {
	// TODO Auto-generated method stub
	return cd.SelectContext(fatherId);
}

@Override
public Layui<ls_menu> SelectMenu(String name, int currPageNo, int pageSize) {
	int startIndex = (currPageNo - 1) * pageSize;
	int maxlength = pageSize;
	List<ls_menu>list=cd.SelectMenu(name, startIndex, maxlength);
	int count=cd.countMenu(name);
	Layui<ls_menu>use=new Layui<ls_menu>();
	use.setCode(0);
	use.setMsg("");
	use.setCount(count);
	use.setData(list);
	
		//执行分页
	Page<ls_menu>page=new Page<ls_menu>();
	page.setCurrPageNo(startIndex);
		  page.setPageSize(maxlength); page.setPageCount(count);
		 page.setPageList(list);
		 
	
	return use;
}


@Override
public int addMenu(ls_menu menu) {
	// TODO Auto-generated method stub
	return cd.addMenu(menu);
}

@Override
public int delMenu(int id) {
	// TODO Auto-generated method stub
	return cd.delMenu(id);
}

@Override
public int upMenu(ls_menu menu) {
	// TODO Auto-generated method stub
	return cd.upMenu(menu);
}

@Override
public List<ls_menu> SelectMenuOne(int id) {
	// TODO Auto-generated method stub
	return cd.SelectMenuOne(id);
}

@Override
public List<ls_menu> SelectBtn(int typeId) {
	// TODO Auto-generated method stub
	return cd.SelectBtn(typeId);
}

@Override
public Layui<complation> SelectUser(String name, int currPageNo, int pageSize) {
	int startIndex = (currPageNo - 1) * pageSize;
	int maxlength = pageSize;
	List<complation>list=cd.SelectUserList(name, startIndex, maxlength);
	int count=cd.countUser(name);
	Layui<complation>use=new Layui<complation>();
	use.setCode(0);
	use.setMsg("");
	use.setCount(count);
	use.setData(list);
	
	return use;
}

@Override
public Layui<User_Role> SelectUserRole(String name, int currPageNo, int pageSize) {
	int startIndex = (currPageNo - 1) * pageSize;
	int maxlength = pageSize;
	List<User_Role>list=cd.SelectUserRole(name, startIndex, maxlength);
	int count=cd.countMenu(name);
	Layui<User_Role>use=new Layui<User_Role>();
	use.setCode(0);
	use.setMsg("");
	use.setCount(count);
	use.setData(list);
	
	return use;
}

@Override
public int addRole(User_Role ur) {
	// TODO Auto-generated method stub
	return cd.addRole(ur);
}

@Override
public int delRole(int id) {
	// TODO Auto-generated method stub
	return cd.delRole(id);
}

@Override
public int upRole(User_Role ur) {
	// TODO Auto-generated method stub
	return cd.upRole(ur);
}

@Override
public List<User_Role> SelectRoleOne(int id) {
	// TODO Auto-generated method stub
	return cd.SelectRoleOne(id);
}

@Override
public int addUser(User use) {
	// TODO Auto-generated method stub
	return cd.addUser(use);
}

@Override
public int addUserWork(int userId,UserWorkPart uwp) {
	// TODO Auto-generated method stub
	return cd.addUserWork(userId,uwp);
}

@Override
public int addUserRole(int userId,User_Role ur) {
	// TODO Auto-generated method stub
	return cd.addUserRole(userId, ur);
}

@Override
public User userByid(String name) {
	User user=cd.userByid(name);
	return user;
}

@Override
public List<User_Role> SelectBtn() {
	return cd.SelectBtn();
}

@Override
public int delUser(int id) {
	
	return cd.delUser(id);
}

@Override
public int upUser(User user) {
	// TODO Auto-generated method stub
	return cd.upUser(user);
}

@Override
public int upPart(int userId,UserWorkPart uwp) {
	// TODO Auto-generated method stub
	return cd.upPart(userId,uwp);
}

@Override
public int upUserRole(int userId,User_Role ur) {
	// TODO Auto-generated method stub
	return cd.upUserRole(userId,ur);
}

@Override
public List<complation> selectUserOne(int id) {
	// TODO Auto-generated method stub
	return cd.selectUserOne(id);
}

@Override
public int delUserByid(int roleId,String[] array) {
	int row = cd.delUserByid(roleId);
	
	int[] arrs = TransferArray.StringToInt (array);
	int count = 0;
	if(row != -1) {
		for (int i = 0; i < arrs.length; i++) {
			int yes = this.addRoleByUserid(roleId,arrs[i]);	
			if(yes > 0) {	
				count++;
			}
		}
	}
	
	return count;
}

@Override
public Layui<Menu> selectAll() {
	List<ls_menu> allMenu = cd.selectAll();
	Layui<Menu> layui = new Layui<Menu>();
	layui.setCode(0);
	layui.setCount(0);
	layui.setMsg("");
	List<Menu> dataList = new ArrayList<Menu>();
	for (ls_menu menu : allMenu) {
		Menu data = new Menu();
		data.setId(menu.getId());
		data.setParentId(menu.getMfatherid());
		data.setTitle(menu.getMname());
		data.setCheckArr("0");
		dataList.add(data);
	}
	layui.setData(dataList);
	return layui;
}

@Override
public List<ls_menu> roleByUserId(int roleId) {
	
	return cd.roleByUserId(roleId);
}

@Override
public int addRoleByUserid(int menuId, int roleid) {
	// TODO Auto-generated method stub
	return cd.addRoleByUserid(menuId,roleid);
}

@Override
public List<User_Role> selectUserRole() {
	// TODO Auto-generated method stub
	return cd.selectUserRole();
}

@Override
public int selectRolByUid(int uid) {
	// TODO Auto-generated method stub
	return cd.selectRolByUid(uid);
}

@Override
public int selectWorkByuid(int uid) {
	// TODO Auto-generated method stub
	return cd.selectWorkByuid(uid);
}

@Override
public int selectPartByuid(int uid) {
	// TODO Auto-generated method stub
	return cd.selectPartByuid(uid);
}

@Override
public List<Part> selectPart() {
	// TODO Auto-generated method stub
	return cd.selectPart();
}

@Override
public List<Work> selectWork() {
	// TODO Auto-generated method stub
	return cd.selectWork();
}

@Override
public Layui<Product> selecProducts() {
	List<Product> list =cd.selecProducts();
	Layui<Product> layui = new Layui<Product>();
	layui.setCode(0);
	layui.setCount(0);
	layui.setMsg("");
	layui.setData(list);
	return layui;
}
}
