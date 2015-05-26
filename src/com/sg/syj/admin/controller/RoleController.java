package com.sg.syj.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;






import com.sg.syj.admin.service.RoleService;
import com.sg.syj.common.util.JsonUtil;
import com.sg.syj.entity.po.Sys_Account_Role;
import com.sg.syj.entity.po.Sys_Role;
import com.sg.syj.entity.po.Sys_Role_Resource;
import com.sg.syj.entity.vo.PagePo;


/**
* TODO
* @ClassName: demoController
* @author libw
*/
@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService demoService;
	
	/**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveRole( HttpServletRequest request) {
		String json = request.getParameter("data");
		if (StringUtils.isNotEmpty(json)) {
			Sys_Role demo = new Sys_Role();
		try{
			demo = JsonUtil.jsonToObject(json, Sys_Role.class);
			demoService.saveOrUpdate(demo);
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
	@RequestMapping(value = "/delRole", method = RequestMethod.POST)
	@ResponseBody
	public boolean delRole( HttpServletRequest request) {
		String strIds = request.getParameter("id");
		if (StringUtils.isNotEmpty(strIds)) {
            String[] ids = strIds.split(",");
            try {
				demoService.delDemo(ids);
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
		Sys_Role demo;
		try {
			demo = demoService.getAdminById(id);
			map.put("demo", demo);
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return new ModelAndView("admin/jsp/editRole", map);
	}
	
	
	/**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/getRole")
    @ResponseBody
    public PagePo<Sys_Role> getRole(HttpServletRequest request) {
	
		PagePo<Sys_Role> page=new PagePo<Sys_Role>();
		page.setPageNumber(Integer.parseInt(request.getParameter("page")));
		page.setPageSize(Integer.parseInt(request.getParameter("rows")));
		
		try {
			page= demoService.getAdminByPage(page);
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
	@RequestMapping(value = "/getRoleByUserId")
    @ResponseBody
    public PagePo<Sys_Role> getRoleByUserId(HttpServletRequest request) {
	
    	List<Sys_Role> temp = new ArrayList<Sys_Role>();
    	PagePo<Sys_Role> page = new PagePo<Sys_Role>();
    	String id = request.getParameter("id");
    	
		try {
			temp= demoService.getRoleByUserId(id);
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
  	@RequestMapping(value = "/getRoleByNoUser")
      @ResponseBody
      public PagePo<Sys_Role> getRoleByNoUser(HttpServletRequest request) {
  	
      	List<Sys_Role> temp = new ArrayList<Sys_Role>();
      	PagePo<Sys_Role> page = new PagePo<Sys_Role>();
      	String id = request.getParameter("id");
      	
  		try {
  			temp= demoService.getRoleByNoUser(id);
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
  	@RequestMapping(value = "/saveRoleAdmin", method = RequestMethod.POST)
  	@ResponseBody
  	public boolean saveRoleAdmin( HttpServletRequest request) {
  		String json = request.getParameter("data");
  		if (StringUtils.isNotEmpty(json)) {
  			Sys_Account_Role demo = new Sys_Account_Role();
  		try{
  			demo = JsonUtil.jsonToObject(json, Sys_Account_Role.class);
  			demoService.saveRoleAdmin(demo);
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
}
