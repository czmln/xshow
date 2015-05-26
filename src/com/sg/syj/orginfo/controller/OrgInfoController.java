package com.sg.syj.orginfo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sg.syj.entity.po.OrgInfo;
import com.sg.syj.entity.vo.ResultMsg;
import com.sg.syj.orginfo.service.OrgInfoService;

@Controller
@RequestMapping("org")
public class OrgInfoController {
	@Autowired
	private OrgInfoService orginfoservie;
	
   @RequestMapping("save")
   @ResponseBody
   public ResultMsg save(HttpServletRequest request,OrgInfo info){
	   ResultMsg rs=new ResultMsg();
	   try {
		orginfoservie.save(info);
		rs.setMsg("保存成功");
		rs.setRs(true);
	} catch (Exception e) {
		rs.setMsg("保存失败");
		rs.setRs(false);
		e.printStackTrace();
	}	   
	   return rs;
   }
   
   @RequestMapping("getInfo")
   public ModelAndView getInfo(HttpServletRequest request,ModelAndView mal) throws Exception{
	   OrgInfo info = orginfoservie.getOrgInfo();
	   mal.addObject("org", info);
	   mal.setViewName("/org/jsp/org");
	   return mal;
   }
   
}
