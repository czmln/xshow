package com.sg.syj.admin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.syj.admin.dao.AdminDao;
import com.sg.syj.common.exception.DataAccessException;
import com.sg.syj.admin.service.AdminService;
import com.sg.syj.entity.po.Sys_Admin;
import com.sg.syj.entity.vo.PagePo;

/**
* @author libw
* @since 2014
* @version 1.0
*/
@Transactional
@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao  demodao;
	
	/**
     * 提交demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public void saveOrUpdate(Sys_Admin demo) throws Exception {
		// TODO Auto-generated method stub
		demodao.saveOrUpdate(demo);
	}


	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public Sys_Admin getAdminById(String id) throws Exception {
		
		return demodao.get("  from Sys_Admin o where o.id=?", new Object[]{id});
		
	}
	
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public PagePo<Sys_Admin> getAdminByPage(PagePo<Sys_Admin> page) throws Exception {
		
		return demodao.getByPage(page," from Sys_Admin o");
		
	}
	  
	/**
	 * 删除demo
	 * @param ids
	 * @throws ManagerServiceException
	 */
	public void delDemo(String[] ids) throws Exception {
		
		demodao.delete(ids);
		
	}


	@Override
	public Sys_Admin getAdminByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return demodao.get("  from Sys_Admin o where o.userName=?", new Object[]{name});
	}


}
