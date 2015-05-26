package com.sg.syj.entity.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.sg.syj.entity.po.Menubar;

/**
 * 数据对象：SysResourcePO，此对象作为持久POJO对象。 创建日期：2013年08月21日14时43分
 * 
 * @author chenze
 */
@Entity
public class Sys_Resource implements java.io.Serializable
{
	@Id
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=40)
	private String id;
	@Column
	private String resourceName;
	@Column
	private String resourceType;
	@Column
	private String parentId;
	@Column
	private String resourceCode;
	@Column
	private String status;
	@Column
	private Integer resourceOrder;
	@Column
	private String resourcePath;
	@Column
	private String resourceLabel;
	@Column
	private String entryUrl;
	@Column
	private String operationUser;
	private String parentname;
    private String statusname;
	@Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
	private Date operationDate;
	
	private String menubarId;
	
	@Transient
	private String menubarName;
	
	
	
	
	public String getMenubarId() {
		return menubarId;
	}

	public void setMenubarId(String menubarId) {
		this.menubarId = menubarId;
	}

	public String getMenubarName() {
		return menubarName;
	}

	public void setMenubarName(String menubarName) {
		this.menubarName = menubarName;
	}

	public String getOperationUser()
	{
		return operationUser;
	}

	public void setOperationUser(String operationUser)
	{
		this.operationUser = operationUser;
	}

	public Date getOperationDate()
	{
		return operationDate;
	}

	public void setOperationDate(Date operationDate)
	{
		this.operationDate = operationDate;
	}

	public Integer getResourceOrder()
	{
		return resourceOrder;
	}

	public void setResourceOrder(Integer resourceOrder)
	{
		this.resourceOrder = resourceOrder;
	}

	public String getEntryUrl()
	{
		return entryUrl;
	}

	public void setEntryUrl(String entryUrl)
	{
		this.entryUrl = entryUrl;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getResourcePath()
	{
		return resourcePath;
	}

	public void setResourcePath(String resourcePath)
	{
		this.resourcePath = resourcePath;
	}

	public String getResourceLabel()
	{
		return resourceLabel;
	}

	public void setResourceLabel(String resourceLabel)
	{
		this.resourceLabel = resourceLabel;
	}

	public String getId()
	{
		return this.id;
	}

	public void setResourceName(String resourceName)
	{
		this.resourceName = resourceName;
	}

	public String getResourceName()
	{
		return this.resourceName;
	}

	public void setResourceType(String resourceType)
	{
		this.resourceType = resourceType;
	}

	public String getResourceType()
	{
		return this.resourceType;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public String getParentId()
	{
		return this.parentId;
	}

	public void setResourceCode(String resourceCode)
	{
		this.resourceCode = resourceCode;
	}

	public String getResourceCode()
	{
		return this.resourceCode;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getStatus()
	{
		return this.status;
	}
	@Transient
	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	@Transient
	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	
}
