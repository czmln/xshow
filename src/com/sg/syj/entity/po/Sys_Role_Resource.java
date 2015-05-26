package com.sg.syj.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 数据对象：SysRoleResourcePO，此对象作为持久POJO对象。
 * 创建日期：2013年08月21日14时43分
 * @author chenze
 */
@Entity
public class Sys_Role_Resource implements java.io.Serializable {
	
	@Id
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=40)
    private String id;
	@Column
    private String roleId;
	@Column
    private String resourceId;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceId() {
        return this.resourceId;
    }


}
