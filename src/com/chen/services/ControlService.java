package com.chen.services;

import java.util.List;

import com.chen.entity.Layui;
import com.chen.entity.Menu;
import com.chen.entity.Part;
import com.chen.entity.Product;
import com.chen.entity.Role_Menu;
import com.chen.entity.User;
import com.chen.entity.UserWorkPart;
import com.chen.entity.User_Menu;
import com.chen.entity.User_Role;
import com.chen.entity.Work;
import com.chen.entity.complation;
import com.chen.entity.ls_menu;

public interface ControlService {
	/**
	 * ��¼����
	 * @param name
	 * @param password
	 * @return
	 */
	User  ShowLogin(String name,String password);
	/**
	 *根据用户id 查询所拥有的权限
	 * @param userId
	 * @return
	 */
	List<ls_menu>SelectAll(int userId);
	/**
	 * 
	 * @param fatherId根据父级id查询权限
	 * @return
	 */
	List<ls_menu>SelectContext(int fatherId);
	/**
	 * 
	 * @param name
	 * @param currPageN查询所有权限
	 * @param pageSize
	 * @return
	 */
	Layui<ls_menu> SelectMenu(String name,int currPageNo,int pageSize);

	//ִ执行增删改
	int addMenu(ls_menu menu);
	List<ls_menu>SelectBtn(int typeId);
	int delMenu(int id);
	int upMenu(ls_menu menu);
	List<ls_menu>SelectMenuOne(int id);//执行修改前的查询
	/**
	 * 
	 * @param name
	 * @param currPageNo查询所有用户信息
	 * @param pageSize
	 * @return
	 */
	Layui<complation>SelectUser(String name,int currPageNo,int pageSize);
	int addUser(User use);//增加用户
	int addUserWork(int userId,UserWorkPart uwp);//增加职称 部门
	int addUserRole(int userId,User_Role ur);
	User userByid(String name);//检查新增用户名是否重复
	List<User_Role> SelectBtn();
	int delUser(int id);//删除用户
	int upUser(User user);//修改用户
	int upPart(int userId,UserWorkPart uwp);//修改用户所拥有的部门 职称
	int upUserRole(int userId,User_Role ur);//修改用户的角色
	List<complation>selectUserOne(int id);//修改前的查询
	List<User_Role> selectUserRole();
	int selectRolByUid(int uid);
	int selectWorkByuid(int uid);
	int selectPartByuid(int uid);
	List<Part>selectPart();
	List<Work>selectWork();
	
	/**
	 * 
	 * @param name
	 * @param currPageNo查询所有角色下的用户信息
	 * @param pageSize
	 * @return
	 */
	Layui<User_Role>SelectUserRole(String name,int currPageNo,int pageSize);
	int addRole(User_Role ur);//新增角色
	int delRole(int id);//删除角色
	int upRole(User_Role ur);//修改角色
	List<User_Role>SelectRoleOne(int id);//修改前的查询
	int delUserByid(int roleId,String[] array);//分配权限前的删除
	Layui<Menu>selectAll();
	List<ls_menu> roleByUserId(int roleId);//根据角色查询权限
	int addRoleByUserid(int menuId, int roleId);//分配权限
	
	Layui<Product>selecProducts();//查看商品信息
	
}
