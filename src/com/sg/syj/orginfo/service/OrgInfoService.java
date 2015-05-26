package com.sg.syj.orginfo.service;

import com.sg.syj.entity.po.OrgInfo;

public interface OrgInfoService {
   public void save(OrgInfo info) throws Exception;
   public OrgInfo getOrgInfo() throws Exception;
}
