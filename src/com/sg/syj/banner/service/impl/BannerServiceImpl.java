package com.sg.syj.banner.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.syj.banner.dao.BannerDao;
import com.sg.syj.banner.service.BannerService;
import com.sg.syj.common.util.StringUtil;
import com.sg.syj.entity.po.Banner;
import com.sg.syj.entity.vo.PagePo;

@Service
@Transactional
public class BannerServiceImpl implements BannerService{
	
	@Autowired
    private BannerDao bannerDao;
	
	@Override
	public void saveOrUpdate(Banner banner) throws Exception {
			if(StringUtil.isEmpty(banner.getId())){
				banner.setId(null);
			}
			bannerDao.saveOrUpdate(banner);
	}

	@Override
	public void del(String[] ids) throws Exception {
		bannerDao.delete(ids);	
	}

	@Override
	public Banner getById(String id) throws Exception {
		
		return bannerDao.get(Banner.class, id);
	}

	@Override
	public PagePo<Banner> getByPage(PagePo<Banner> page,Map<String, Object> map) throws Exception {
        StringBuffer sb=new StringBuffer();
		
		sb.append(" from Banner o where 1=1");
		
		if(map!=null){
			Object obj = map.get("remark");
			if( obj!=null){
				String name=obj.toString();
				sb.append(" and o.remarks like '%");
				sb.append(name);
				sb.append("%'");
			}
		}
		
		page=bannerDao.getByPage(page, sb.toString());

		return page;
	}

	@Override
	public List<Banner> getAll() throws Exception {
		String hql=" from Banner o";
		
		return bannerDao.find(hql);
	}

}
