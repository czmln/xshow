package com.sg.syj.admin.service;

import java.util.List;

import com.sg.syj.common.exception.DataAccessException;
import com.sg.syj.entity.po.Sys_Account_Role;
import com.sg.syj.entity.po.Sys_Role;
import com.sg.syj.entity.vo.PagePo;

public interface RoleService {
	
	public void saveOrUpdate(Sys_Role demo) throws Exception;
	public void saveRoleAdmin(Sys_Account_Role demo) throws Exception;
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public Sys_Role getAdminById(String id) throws Exception ;
	public List<Sys_Role> getRoleByUserId(String id) throws Exception;
	public List<Sys_Role> getRoleByNoUser(String id) throws Exception;
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public PagePo<Sys_Role> getAdminByPage(PagePo<Sys_Role> page) throws Exception ;
	
	/**
	 * 删除demo
	 * @param ids
	 * @throws ManagerServiceException
	 */
	public void delDemo(String[] ids) throws Exception;

}
