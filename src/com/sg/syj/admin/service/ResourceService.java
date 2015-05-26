package com.sg.syj.admin.service;

import java.util.List;

import com.sg.syj.common.exception.DataAccessException;
import com.sg.syj.entity.po.Sys_Resource;
import com.sg.syj.entity.po.Sys_Role_Resource;
import com.sg.syj.entity.vo.PagePo;
import com.sg.syj.entity.vo.TreeNode;

public interface ResourceService {
	
	
	public void saveOrUpdate(Sys_Resource demo) throws Exception;
	public void saveRoleResource(Sys_Role_Resource demo) throws Exception;
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public Sys_Resource getAdminById(String id) throws Exception;
	public List<Sys_Resource> getAdminByParentId(String id) throws Exception;
	public List<Sys_Resource> getResourceByRoleId(String id) throws Exception;
	public List<Sys_Resource> getResourceByNoRole(String id) throws Exception;
	public List<TreeNode> getTree(String id) throws Exception; 
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public PagePo<Sys_Resource> getAdminByPage(PagePo<Sys_Resource> page) throws Exception ;
	
	/**
	 * 删除demo
	 * @param ids
	 * @throws ManagerServiceException
	 */
	public void delDemo(String[] ids) throws Exception;
}
