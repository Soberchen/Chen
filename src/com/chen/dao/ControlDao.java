package com.chen.dao;

import java.util.List;

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

public interface ControlDao {
	/**
	 * 
	 * @param loginname
	 * @param password 登录
	 * @return
	 */
	public User login(String loginname,String password);
	/**
	 * 根据用户id 查询所拥有的权限
	 * @param id
	 * @return
	 */
	
	List<ls_menu>SelectAll(int userId);
	/**
	 * fatherId根据父级id查询权限
	 * @param fatherId
	 * @return
	 */
	List<ls_menu>SelectContext(int fatherId);
	/**
	 * 
	 * @param name
	 * @param startIndex查询所有权限
	 * @param maxlength
	 * @return
	 */
	List<ls_menu>SelectMenu(String name,int startIndex, int maxlength);
	int countMenu(String name);
	//执行增删改
	int addMenu(ls_menu menu);
	/**
	 * 
	 * @param typeId查询父级和子级按钮
	 * @return
	 */
	List<ls_menu>SelectBtn(Integer typeId);
	int delMenu(int id);
	int upMenu(ls_menu menu);
	List<ls_menu>SelectMenuOne(int id);//执行修改前的查询
	/**
	 * 
	 * @param name
	 * @param startIndex
	 * @param maxlength查询员工的所有信息
	 * @return
	 */
	List<complation>SelectUserList(String name,int startIndex, int maxlength);
	int countUser(String name);
	int addUser(User use);//增加用户
	int addUserWork(int userId,UserWorkPart uwp);//增加用户的职称 部门
	int addUserRole(int userId,User_Role ur);//新增用户的角色
	User userByid(String name);//检查新增用户名是否重复
	List<User_Role>SelectBtn();//查询角色下拉框
	int delUser(int id);//删除用户
	int upUser(User user);//修改用户
	int upPart(int userId,UserWorkPart uwp);//修改用户所拥有的部门 职称
	
	int upUserRole(int userId ,User_Role ur);//修改用户的角色
	List<complation>selectUserOne(int id);//修改前的查询所有数据
	List<User_Role>selectUserRole();//修改前查询按钮
	int selectRolByUid(int uid);
	int selectWorkByuid(int uid);
	int selectPartByuid(int uid);
	List<Part>selectPart();
	List<Work>selectWork();
	
	/**
	 * 查询角色对应的用户信息
	 * @param name
	 * @param startIndex
	 * @param maxlength
	 * @return
	 */
	List<User_Role>SelectUserRole(String name,int startIndex, int maxlength);
	int addRole(User_Role ur);//新增角色
	int delRole(int id);//删除角色
	int upRole(User_Role ur);//修改角色
	List<User_Role>SelectRoleOne(int id);//修改前的查询
	int delUserByid(int roleId);//分配权限前的删除
	List<ls_menu>selectAll();
	List<ls_menu> roleByUserId(int roleId);//根据角色查询权限
	int addRoleByUserid(int menuId, int roleId);//分配权限
	
	List<Product>selecProducts();//查看商品信息
	

}
