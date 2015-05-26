package com.sg.syj.admin.controller;


import java.util.HashMap;
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

import com.sg.syj.common.util.JsonUtil;
import com.sg.syj.common.util.Md5encyptUtil;
import com.sg.syj.common.util.StringUtil;
import com.sg.syj.admin.service.AdminService;
import com.sg.syj.entity.po.Sys_Admin;
import com.sg.syj.entity.vo.PagePo;
import com.sg.syj.entity.vo.ResultMsg;




/**
* TODO
* @ClassName: demoController
* @author libw
*/
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private AdminService demoService; 
	
	
	@RequestMapping(value ="/login")
	public ModelAndView admin(HttpServletRequest request,ModelAndView mal){
		
		mal.setViewName("/admin/login/login");
		return mal;
	}
	
	
	/**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/saveAdmin", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveAdmin(HttpServletRequest request) {
		String json = request.getParameter("data");
		if (StringUtils.isNotEmpty(json)) {
			Sys_Admin demo = new Sys_Admin();
			try {
				demo = JsonUtil.jsonToObject(json, Sys_Admin.class);
				if (!StringUtil.isEmpty(demo.getId())) {
					if (demo.getUserpwd().equals("********************")) {
						String enPassword = demoService.getAdminById(
								demo.getId()).getUserpwd();
						demo.setUserpwd(enPassword);
					} else {
						demo.setUserpwd(Md5encyptUtil.getEncryptedPwd(demo
								.getUserpwd()));
					}
				} else {
					demo.setUserpwd(Md5encyptUtil.getEncryptedPwd(demo
							.getUserpwd()));
				}

				demoService.saveOrUpdate(demo);
				return true;

			} catch (Exception e) {
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
	@RequestMapping(value = "/delAdmin", method = RequestMethod.POST)
	@ResponseBody
	public boolean delDemo( HttpServletRequest request) {
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
		Sys_Admin demo;
		try {
			demo = demoService.getAdminById(id);
			map.put("demo", demo);
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return new ModelAndView("admin/jsp/editAdmin", map);
	}
	
	
	/**
	 * 提交demo
	 * @Title: addDemo
	 * @param HttpServletRequest request
	 * @return String
	 */
	@RequestMapping(value = "/getAdmin")
    @ResponseBody
    public PagePo<Sys_Admin> getAdmin(HttpServletRequest request) {
	
		PagePo<Sys_Admin> page=new PagePo<Sys_Admin>();
		page.setPageNumber(Integer.parseInt(request.getParameter("page")));
		page.setPageSize(Integer.parseInt(request.getParameter("rows")));
		
		try {
			page= demoService.getAdminByPage(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
    }
    
    /***
     * 修改密码
     * @param request
     * @return
     */
   @RequestMapping(value ="/resetPassword", method = RequestMethod.POST)
   @ResponseBody
   public ResultMsg ResetPassword(HttpServletRequest request){
	   ResultMsg rs=new ResultMsg();
	   //旧密码
	   String opassword = request.getParameter("opassword");
	   //新密码
	   String npassword =request.getParameter("npassword");
	   
	   UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
	  if(userDetails==null){
		  rs.setMsg("用户未登录");
		  rs.setRs(false);
	  }
	  String userName = userDetails.getUsername();	  
	  Sys_Admin adminUser=null;	  
	  try {
			adminUser = demoService.getAdminById(userName);
			// 校验旧密码
			boolean valid = Md5encyptUtil.validPassword(opassword,adminUser.getUserpwd());
			if (!valid) {
				rs.setMsg("原密码不正确");
				rs.setRs(false);
			}else{
				adminUser.setUserpwd(Md5encyptUtil.getEncryptedPwd(npassword));
				demoService.saveOrUpdate(adminUser);
				rs.setMsg("密码修改成功");
				rs.setRs(true);
			}	
	  } catch (Exception e1) {
	      rs.setMsg("系统发生错误");
		  rs.setRs(false);		
		  e1.printStackTrace();
	  }
	  return rs;
   }

	
}
