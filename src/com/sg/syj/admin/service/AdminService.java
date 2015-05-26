package com.sg.syj.admin.service;

import com.sg.syj.common.exception.DataAccessException;
import com.sg.syj.entity.po.Sys_Admin;
import com.sg.syj.entity.vo.PagePo;

public interface AdminService {
	
	public void saveOrUpdate(Sys_Admin demo) throws Exception ;
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public Sys_Admin getAdminById(String id) throws Exception ;
	public Sys_Admin getAdminByName(String name) throws Exception ;
	
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public PagePo<Sys_Admin> getAdminByPage(PagePo<Sys_Admin> page) throws Exception ;
	
	/**
	 * 删除demo
	 * @param ids
	 * @throws ManagerServiceException
	 */
	public void delDemo(String[] ids) throws Exception;
	
	
}
