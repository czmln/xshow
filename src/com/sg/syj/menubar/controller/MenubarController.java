package com.sg.syj.menubar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sg.syj.common.util.StringUtil;
import com.sg.syj.entity.po.Menubar;
import com.sg.syj.entity.vo.PagePo;
import com.sg.syj.entity.vo.ResultMsg;
import com.sg.syj.entity.vo.Tree;
import com.sg.syj.menubar.service.MenubarService;

@Controller
@RequestMapping(value="menubar")
public class MenubarController {
	
	@Autowired
    private MenubarService  menubarService;
	
	@RequestMapping(value="save")
	@ResponseBody
	public ResultMsg save(HttpServletRequest request,Menubar menubar) {
		ResultMsg rs=new ResultMsg();
		String menubarId = request.getParameter("menubarId");
		try{
			if(!StringUtil.isEmpty(menubarId)){
				
				Menubar m = menubarService.getById(menubarId);
				menubar.setMenubar(m);
				menubar.setMenuLevel(10012);
			}else{
				menubar.setMenuLevel(10011);
			}
			menubarService.saveOrUpdate(menubar);
			rs.setMsg("保存成功");
			rs.setRs(true);
			
		}catch(Exception e){
			e.printStackTrace();
			rs.setMsg("保存失败");
			rs.setRs(false);
		}
		return rs;
	}
	
	@RequestMapping("/getMenubarByPage")
	@ResponseBody
	public PagePo<Menubar> getMenubarByPage(HttpServletRequest request) throws Exception{		
		PagePo<Menubar> page=new PagePo<Menubar>();
		page.setPageNumber(Integer.parseInt(request.getParameter("page")));
		page.setPageSize(Integer.parseInt(request.getParameter("rows")));
		
		Map<String,Object> map=new HashMap<String, Object>();
		
		String name = request.getParameter("name");
		if(!StringUtil.isEmpty(name)){
			map.put("name", name);
		}
		
		page=menubarService.getMenubarByPage(page, map);
		
		List<Menubar> list = page.getList();
		for(Menubar m1:list){	
			m1.setMenubar(null);
			 if(m1.getChildren()!=null){
				 
				 List<Menubar> list2 = m1.getChildren();
				 for(Menubar m2:list2){
					 m2.setChildren(null);
					 m2.setMenubar(null);
				 }
				 
			 }
			
		}
		
		return page;
	}
	
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Menubar> getAll(HttpServletRequest httpServletRequest) throws Exception{
		List<Menubar> list = menubarService.getFristMenuAll();
		for(Menubar m:list){
			m.setMenubar(null);
			m.setChildren(null);
		}
		
		
		return list;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request,ModelAndView mal) throws Exception{
		String id=request.getParameter("id");
		if(StringUtil.isEmpty(id)){
			return mal;
		}
		Menubar menu = menubarService.getById(id);
		
		mal.addObject("menubar", menu);
		mal.setViewName("menubar/jsp/edit");
		return mal;
	}
	@RequestMapping("/del")
	@ResponseBody
	public ResultMsg del(HttpServletRequest request){
		ResultMsg rs=new ResultMsg();
		String strIds = request.getParameter("id");
		if (StringUtils.isNotEmpty(strIds)) {
            String[] ids = strIds.split(",");           
            try {
            	menubarService.del(ids);
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
	@RequestMapping("/getMenubarOfTree")
	@ResponseBody
	public List<Tree> getMenubarOfTree(HttpServletRequest request){
		
		return menubarService.getMenubarOfTree();
	}
}
