package com.sg.syj.banner.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sg.syj.banner.service.BannerService;
import com.sg.syj.common.util.StringUtil;
import com.sg.syj.entity.po.Banner;
import com.sg.syj.entity.vo.PagePo;
import com.sg.syj.entity.vo.ResultMsg;

@RequestMapping("banner")
@Controller
public class BannerController {
   @Autowired
   private BannerService bannerService;
   
   @RequestMapping("/save")
   @ResponseBody
   public ResultMsg save(HttpServletRequest request,Banner banner){
	   ResultMsg rs=new ResultMsg();
	   try {
		bannerService.saveOrUpdate(banner);
		rs.setMsg("保存成功");
		rs.setRs(true);
	} catch (Exception e) {
		rs.setMsg("保存失败");
		rs.setRs(false);
		e.printStackTrace();
	}
	   return rs;
   }
   
    @RequestMapping("/getByPage")
	@ResponseBody
	public PagePo<Banner> getByPage(HttpServletRequest request) throws Exception{
		
		PagePo<Banner> page=new PagePo<Banner>();
		
		page.setPageNumber(Integer.parseInt(request.getParameter("page")));
		page.setPageSize(Integer.parseInt(request.getParameter("rows")));
		
		String remark = request.getParameter("remark");
		Map<String,Object> map=new HashMap<String, Object>();
		if(!StringUtil.isEmpty(remark)){
			map.put("remark", remark);
		}
        
		return bannerService.getByPage(page, map);
	}
    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request,ModelAndView mal) throws Exception{
    	String id=request.getParameter("id");
    	Banner banner = bannerService.getById(id);
    	mal.setViewName("/banner/jsp/edit");
    	mal.addObject("banner", banner);
    	return mal;
    }
    
    
    
    
    
    @RequestMapping("/del")
   	@ResponseBody
   	public ResultMsg del(HttpServletRequest request) throws Exception{
    	ResultMsg rs=new ResultMsg();
    	String strIds = request.getParameter("id");
		if (StringUtils.isNotEmpty(strIds)) {
            String[] ids = strIds.split(",");
            
            try {
            	bannerService.del(ids);
				rs.setMsg("删除成功");
				rs.setRs(true);
			} catch (Exception e) {				
				e.printStackTrace();
				rs.setMsg("删除失败");
				rs.setRs(false);
			}   
		}
    	
    	return rs;
    			
    }
   
}
