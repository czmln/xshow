package com.sg.syj.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sg.syj.admin.service.ResourceService;
import com.sg.syj.common.util.JsonUtil;
import com.sg.syj.entity.po.Sys_Resource;
import com.sg.syj.entity.po.Sys_Role_Resource;
import com.sg.syj.entity.vo.PagePo;
import com.sg.syj.entity.vo.TreeNode;


/**
* TODO
* @ClassName: demoController
* @author libw
*/
@Controller
@RequestMapping("/resource")
public class ResourceController {
	
	
	@Autowired
	private ResourceService resourceService;
	
	
	/**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/saveResource", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveResource( HttpServletRequest request) {
		String json = request.getParameter("data");
		if (StringUtils.isNotEmpty(json)) {
			Sys_Resource demo = new Sys_Resource();
		try{
			demo = JsonUtil.jsonToObject(json, Sys_Resource.class);
			resourceService.saveOrUpdate(demo);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/saveRoleResource", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveRoleResource( HttpServletRequest request) {
		String json = request.getParameter("data");
		if (StringUtils.isNotEmpty(json)) {
			Sys_Role_Resource demo = new Sys_Role_Resource();
		try{
			demo = JsonUtil.jsonToObject(json, Sys_Role_Resource.class);
			resourceService.saveRoleResource(demo);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/delResource", method = RequestMethod.POST)
	@ResponseBody
	public boolean delResource( HttpServletRequest request) {
		String strIds = request.getParameter("id");
		if (StringUtils.isNotEmpty(strIds)) {
            String[] ids = strIds.split(",");
            try {
            	resourceService.delDemo(ids);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
            return true; 
		} else {
			return false;
		}
	}
	
	/**
	 * 跳转修改页面
	 * @Title: goEdit
	 * @param goEdit goEdit
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit( HttpServletRequest request, HttpServletResponse response ) {
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		Sys_Resource demo;
		try {
			demo = resourceService.getAdminById(id);
			map.put("demo", demo);
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return new ModelAndView("admin/jsp/editResource", map);
	}
	
		
	/**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/getResourceByParentId")
    @ResponseBody
    public List<Sys_Resource> getResourceByParentId(HttpServletRequest request) {
	
    	List<Sys_Resource> page=new ArrayList<Sys_Resource>();
		
		try {
			page= resourceService.getAdminByParentId("0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
    }
    
    /**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/getResourceByRoleId")
    @ResponseBody
    public PagePo<Sys_Resource> getResourceByRoleId(HttpServletRequest request) {
	
    	List<Sys_Resource> temp = new ArrayList<Sys_Resource>();
    	PagePo<Sys_Resource> page = new PagePo<Sys_Resource>();
    	String id = request.getParameter("id");
    	
		try {
			temp= resourceService.getResourceByRoleId(id);
			page.setList(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
    }
    
    /**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/getResourceByNoRole")
    @ResponseBody
    public PagePo<Sys_Resource> getResourceByNoRole(HttpServletRequest request) {
	
    	List<Sys_Resource> temp = new ArrayList<Sys_Resource>();
    	PagePo<Sys_Resource> page = new PagePo<Sys_Resource>();
    	String id = request.getParameter("id");
    	
		try {
			temp= resourceService.getResourceByNoRole(id);
			page.setList(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
    }
		
	/**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/getResource")
    @ResponseBody
    public PagePo<Sys_Resource> getResource(HttpServletRequest request) {
	
		PagePo<Sys_Resource> page=new PagePo<Sys_Resource>();
		page.setPageNumber(Integer.parseInt(request.getParameter("page")));
		page.setPageSize(Integer.parseInt(request.getParameter("rows")));
		
		try {
			page= resourceService.getAdminByPage(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
    }
    
    /**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/getTree")
    @ResponseBody
    public List<TreeNode> getTree(HttpServletRequest request) {
	
    	List<TreeNode> page=new ArrayList<TreeNode>();
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			page = resourceService.getTree(userDetails.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
    }
}
