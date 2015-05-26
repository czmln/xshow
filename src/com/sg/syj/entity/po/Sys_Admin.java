package com.sg.syj.entity.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 数据对象：SysAdminPO，此对象作为持久POJO对象。 创建日期：2013年08月21日14时43分
 * 
 * @author chenze
 */
@Entity
public class Sys_Admin implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7860978491864992266L;

	@Id
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=40)
	private String id;
	@Column
	private String userName;
	@Column
	private String nickName;
	@Column
	private String userpwd;
	@Column
	private String userType;
	@Column
	private String telephone;
	@Column
	private String mobile;
	@Column
	private String email;
	@Column
	private String fax;
	@Column
	private String address;
	@Column
	private String remark;
	@Column
	private String parentId;
	@Column
	private Integer userAmount;
	@Column
	private String userStatus;
	@Column
	private String operationUser;
	
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
	private Date operationDate;
	@Column
	private String areaId;
	@Column
	private Integer points;
	@Column
	private Integer submit;
	@Column
	private Integer total;
	
	@Column
	private String dept; //部门,手动输入;
	
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getPoints()
	{
		return points;
	}

	public void setPoints(Integer points)
	{
		this.points = points;
	}

	public Integer getSubmit()
	{
		return submit;
	}

	public void setSubmit(Integer submit)
	{
		this.submit = submit;
	}

	public Integer getTotal()
	{
		return total;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
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

	public String getUserStatus()
	{
		return userStatus;
	}

	public void setUserStatus(String userStatus)
	{
		this.userStatus = userStatus;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return this.id;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserName()
	{
		return this.userName;
	}

	public void setUserpwd(String userpwd)
	{
		this.userpwd = userpwd;
	}

	public String getUserpwd()
	{
		return this.userpwd;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	public String getUserType()
	{
		return this.userType;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public String getTelephone()
	{
		return this.telephone;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getMobile()
	{
		return this.mobile;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setFax(String fax)
	{
		this.fax = fax;
	}

	public String getFax()
	{
		return this.fax;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getAddress()
	{
		return this.address;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getRemark()
	{
		return this.remark;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public String getParentId()
	{
		return this.parentId;
	}

	public void setUserAmount(Integer userAmount)
	{
		this.userAmount = userAmount;
	}

	public Integer getUserAmount()
	{
		return this.userAmount;
	}

}
