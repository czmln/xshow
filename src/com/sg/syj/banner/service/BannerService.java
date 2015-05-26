package com.sg.syj.banner.service;

import java.util.List;
import java.util.Map;

import com.sg.syj.entity.po.Banner;
import com.sg.syj.entity.vo.PagePo;

public interface BannerService {
	  void saveOrUpdate(Banner banner) throws Exception;
	  void del (String[] ids) throws Exception;
	  
	  Banner getById(String id) throws Exception;
	  
	  PagePo<Banner> getByPage(PagePo<Banner> page,Map<String, Object> map) throws Exception;
	  
	  List<Banner> getAll() throws Exception;
	  
}
