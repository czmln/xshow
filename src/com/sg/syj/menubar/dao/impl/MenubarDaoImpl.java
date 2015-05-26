package com.sg.syj.menubar.dao.impl;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sg.syj.base.dao.impl.BaseDAOImpl;
import com.sg.syj.entity.po.Menubar;
import com.sg.syj.menubar.dao.MenubarDao;

@Repository
public class MenubarDaoImpl extends BaseDAOImpl<Menubar> implements MenubarDao {
	
	public List<Menubar> getMenubarByIds(String[] ids){
		String hql=" from Menubar  where id in (:ids) ";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
    	query.setParameterList("ids", ids);
		
		@SuppressWarnings("unchecked")
		List<Menubar> list = query.list();		
		return list;
	}
}
