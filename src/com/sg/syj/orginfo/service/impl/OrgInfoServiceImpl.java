package com.sg.syj.orginfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.syj.common.util.StringUtil;
import com.sg.syj.entity.po.OrgInfo;
import com.sg.syj.orginfo.dao.OrgInfoDao;
import com.sg.syj.orginfo.service.OrgInfoService;
@Service
@Transactional
public class OrgInfoServiceImpl implements OrgInfoService{
	@Autowired
    private OrgInfoDao orgInfoDao;
	
	@Override
	public void save(OrgInfo info) throws Exception {
		if(StringUtil.isEmpty(info.getId())){
			info.setId(null);
		}
		orgInfoDao.saveOrUpdate(info);
	}

	@Override
	public OrgInfo getOrgInfo() throws Exception {
		String hql=" from OrgInfo o ";
		
		return orgInfoDao.get(hql,new Object[]{});
	}

}
