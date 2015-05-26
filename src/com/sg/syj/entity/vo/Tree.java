package com.sg.syj.entity.vo;

import java.util.List;

public class Tree {
	
	private String id;
	private String text;
	
	private String iconCls;

	//是否选中
	private boolean checked;
	//初始时关闭
	private String state="closed";
	
	private int navType;  
	
	private List<Tree> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getNavType() {
		return navType;
	}
	public void setNavType(int navType) {
		this.navType = navType;
	}
	
	
}
