package com.sg.syj.entity.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/***
 * 说明:前台显示的栏目实体
 */
@Entity
public class Menubar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2832363933546946476L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(length = 40)
	private String id;

	// 栏目名称
	private String name;

	// 连接地址
	private String linkUrl;

	// 导航类型
	// menuLevel 10011,一级栏目
	// menuLevel 10012,二级栏目
	private int menuLevel;

	// 数据显示的类型
	// 10011,单页面
	// 10012,文字列表
	// 10013,图片列表
	// 10014,文字图片列表
	private int navType;

	// 排序
	private String ord;

	// 是否启用 0 禁用 ,1 使用
	private int enable;

	private String descrip;

	// 添加数据的时间
	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date addDate;

	// 添加数据的时间
	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date editDate;

	// 所包含的子栏目
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "menubar")
	private List<Menubar> children;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menubar_id")
	private Menubar menubar;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public List<Menubar> getChildren() {
		return children;
	}

	public void setChildren(List<Menubar> children) {
		this.children = children;
	}

	public Menubar getMenubar() {
		return menubar;
	}

	public void setMenubar(Menubar menubar) {
		this.menubar = menubar;
	}
   
	

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	

	public int getNavType() {
		return navType;
	}

	public void setNavType(int navType) {
		this.navType = navType;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

}
