package com.sg.syj.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 数据对象：SysAccountRolePO，此对象作为持久POJO对象。
 * 创建日期：2013年08月21日14时43分
 * @author chenze
 */
@Entity
public class Sys_Account_Role implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2930466455092815295L;
	@Id
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=40)
    private String id;
	@Column
    private String userId;
	@Column
    private String roleId;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return this.roleId;
    }


}
