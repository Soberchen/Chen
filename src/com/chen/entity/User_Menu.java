package com.chen.entity;

public class User_Menu {
	private int userId;
	private int menuId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public User_Menu(){}
	public User_Menu(int userId,int menuId){
		
		this.userId=userId;
		this.menuId=menuId;
	}
}
