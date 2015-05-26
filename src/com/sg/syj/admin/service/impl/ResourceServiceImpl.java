package com.sg.syj.admin.service.impl;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.syj.admin.dao.ResourceDao;
import com.sg.syj.admin.dao.RoleResourceDao;
import com.sg.syj.admin.service.ResourceService;
import com.sg.syj.common.exception.DataAccessException;
import com.sg.syj.entity.po.Sys_Resource;
import com.sg.syj.entity.po.Sys_Role_Resource;
import com.sg.syj.entity.vo.PagePo;
import com.sg.syj.entity.vo.TreeNode;


/**
* @author libw
* @since 2014
* @version 1.0
*/
@Transactional
@Service
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	/**
     * 提交demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public void saveOrUpdate(Sys_Resource demo) throws Exception {
		// TODO Auto-generated method stub
		if("".equals(demo.getParentId()) || demo.getParentId()==null)
		{
			demo.setParentId("0");
		}
		resourceDao.saveOrUpdate(demo);
	}
	
	@Override
	public void saveRoleResource(Sys_Role_Resource demo) throws Exception {
		// TODO Auto-generated method stub
		if("0".equals(demo.getResourceId())){
			roleResourceDao.excuteBySql("DELETE FROM Sys_Role_Resource WHERE roleid = '"+demo.getRoleId()+"'");
		} else {
			roleResourceDao.excuteBySql("DELETE FROM Sys_Role_Resource WHERE roleid = '"+demo.getRoleId()+"'");
			String[] tmp = demo.getResourceId().split(",");
			for (String string : tmp) {
				Sys_Role_Resource srr = new Sys_Role_Resource();
				srr.setRoleId(demo.getRoleId());
				srr.setResourceId(string);
				roleResourceDao.saveOrUpdate(srr);
			}
		}
		
	}
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public Sys_Resource getAdminById(String id) throws Exception {
		
		return resourceDao.get("  from Sys_Resource o where o.id=?", new Object[]{id});
		
	}
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws Exception
     */
	public List<Sys_Resource> getResourceByRoleId(String id) throws Exception {
		
		List<Sys_Resource> tmp = resourceDao.find("select a from Sys_Resource a,Sys_Role_Resource c WHERE a.id = c.resourceId  AND c.roleId = '"+id+"'");
		return tmp;
	}
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws Exception
     */
	public List<Sys_Resource> getResourceByNoRole(String id) throws Exception {
		
		List<Sys_Resource> tmp = resourceDao.find("select a from Sys_Resource a where a.status= '1' and a.id not in(select t.resourceId from Sys_Role_Resource t where t.roleId = '"+id+"')");
		return tmp;
	}
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws Exception
     */
	public List<Sys_Resource> getAdminByParentId(String id) throws Exception {
		
		return resourceDao.find("  from Sys_Resource o where o.parentId=?", new Object[]{id});
		
	}
	
	/**
     * 根据ID查询demo
     * @param DemoPo
     * @throws DataAccessException
     */
	public PagePo<Sys_Resource> getAdminByPage(PagePo<Sys_Resource> page) throws Exception {
		
		return resourceDao.getByPage(page, " from Sys_Resource o");
		
	}
	
	/**
	 * 删除demo
	 * @param ids
	 * @throws ManagerServiceException
	 */
	public void delDemo(String[] ids) throws Exception {
		resourceDao.delete(ids);
	}

	@Override
	public List<TreeNode> getTree(String id) throws Exception {
		// TODO Auto-generated method stub
		List<Sys_Resource> tmp = resourceDao.find("select a from Sys_Resource a,Sys_Role_Resource b,Sys_Account_Role c WHERE a.id = b.resourceId AND b.roleId = c.roleId AND c.userId = '"+id+"'");
		List<TreeNode> tree = new ArrayList<TreeNode>();
		for (Sys_Resource sys_Resource : tmp) {
			if("0".equals(sys_Resource.getParentId())){
				TreeNode temp = new TreeNode();
				temp.setId(sys_Resource.getId());
				temp.setpId(sys_Resource.getParentId());
				temp.setName(sys_Resource.getResourceName());
				tree.add(temp);
				for (Sys_Resource sys_Resource2 : tmp) {
					if(sys_Resource.getId().equals(sys_Resource2.getParentId())){
						TreeNode temp2 = new TreeNode();
						temp2.setId(sys_Resource2.getId());
						temp2.setpId(sys_Resource2.getParentId());
						temp2.setName(sys_Resource2.getResourceName());
						temp2.setClick("addTabPage('"+sys_Resource2.getResourceName()+"','"+sys_Resource2.getResourceName()+"','"+sys_Resource2.getEntryUrl()+"')");
						tree.add(temp2);
					}
				}
			}
			
		}
		return tree;
	}

	
}
