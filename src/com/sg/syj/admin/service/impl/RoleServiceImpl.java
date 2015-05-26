package com.sg.syj.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.syj.admin.dao.AccountRoleDao;
import com.sg.syj.admin.dao.RoleDao;
import com.sg.syj.admin.dao.RoleResourceDao;
import com.sg.syj.admin.service.RoleService;
import com.sg.syj.common.exception.DataAccessException;
import com.sg.syj.entity.po.Sys_Account_Role;
import com.sg.syj.entity.po.Sys_Role;
import com.sg.syj.entity.po.Sys_Role_Resource;
import com.sg.syj.entity.vo.PagePo;

/**
* @author libw
* @since 2014
* @version 1.0
*/
@Transactional
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao demodao;
	
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	@Autowired
	private AccountRoleDao accountRoleDao;
	
	/**
     * 提交demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public void saveOrUpdate(Sys_Role demo) throws Exception {
		// TODO Auto-generated method stub
		demodao.saveOrUpdate(demo);
	}

	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public Sys_Role getAdminById(String id) throws Exception {
		
		return demodao.get("  from Sys_Role o where o.id=?", new Object[]{id});
		
	}
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public PagePo<Sys_Role> getAdminByPage(PagePo<Sys_Role> page) throws Exception {
		
		return demodao.getByPage(page, " from Sys_Role o");
		
	}
	
	/**
	 * 删除demo
	 * @param ids
	 * @throws ManagerServiceException
	 */
	public void delDemo(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		for (String string : ids) {
			roleResourceDao.excuteBySql("DELETE FROM Sys_Role_Resource WHERE roleid = '"+string+"'");
		}
		demodao.delete(ids);
		
	}


	@Override
	public List<Sys_Role> getRoleByUserId(String id) throws Exception {
		// TODO Auto-generated method stub
		List<Sys_Role> tmp = demodao.find("select a from Sys_Role a,Sys_Account_Role c WHERE a.id = c.roleId  AND c.userId = '"+id+"'");
		return tmp;
	}


	@Override
	public List<Sys_Role> getRoleByNoUser(String id) throws Exception {
		// TODO Auto-generated method stub
		List<Sys_Role> tmp = demodao.find("select a from Sys_Role a where a.status= '1' and a.id not in(select t.roleId from Sys_Account_Role t where t.userId = '"+id+"')");
		return tmp;
	}

	@Override
	public void saveRoleAdmin(Sys_Account_Role demo) throws Exception {
		// TODO Auto-generated method stub
		if("0".equals(demo.getRoleId())){
			roleResourceDao.excuteBySql("DELETE FROM Sys_Account_Role WHERE userId = '"+demo.getUserId()+"'");
		} else {
			roleResourceDao.excuteBySql("DELETE FROM Sys_Account_Role WHERE userId = '"+demo.getUserId()+"'");
			String[] tmp = demo.getRoleId().split(",");
			for (String string : tmp) {
				Sys_Account_Role srr = new Sys_Account_Role();
				srr.setUserId(demo.getUserId());
				srr.setRoleId(string);
				accountRoleDao.saveOrUpdate(srr);
			}
		}
	}
	
	
	
}
