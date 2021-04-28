package com.chen.entity;

/**
 * 权限表
 * 
 * @author 陈十七
 *
 */
public class ls_menu {
	private int id;
	private String mname;
	private int mfatherid;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getMfatherid() {
		return mfatherid;
	}

	public void setMfatherid(int mfatherid) {
		this.mfatherid = mfatherid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	private int type;
	private String url;
	private String button;

	public ls_menu() {

	}

	public ls_menu(int id, String mname, int mfatherid, int type, String url, String button) {
		this.id = id;
		this.mname = mname;
		this.mfatherid = mfatherid;
		this.type = type;
		this.url = url;
		this.button = button;
	}
}
