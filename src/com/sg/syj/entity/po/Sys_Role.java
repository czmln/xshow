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
 * 数据对象：SysRolePO，此对象作为持久POJO对象。
 * 创建日期：2013年08月21日14时43分
 * @author chenze
 */
@Entity
public class Sys_Role implements java.io.Serializable {
	
	@Id
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=40)
    private String id;
	@Column
    private String roleName;
	@Column
    private String status;
	@Column
    private String parentRoleId;
	@Column
	private String operationUser;
	@Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
	private Date operationDate;

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

	public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setParentRoleId(String parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    public String getParentRoleId() {
        return this.parentRoleId;
    }


}
