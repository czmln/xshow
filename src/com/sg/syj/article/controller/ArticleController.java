package com.sg.syj.article.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sg.syj.article.service.ArticleService;
import com.sg.syj.common.util.StringUtil;
import com.sg.syj.entity.po.Article;
import com.sg.syj.entity.po.Menubar;
import com.sg.syj.entity.vo.PagePo;
import com.sg.syj.entity.vo.ResultMsg;
import com.sg.syj.menubar.service.MenubarService;

@Controller
@RequestMapping("article")
public class ArticleController {
	
   @Autowired
   private ArticleService articleService;
   
   @Autowired
   private MenubarService menubarService;
   
   @RequestMapping("save")
   @ResponseBody
   public ResultMsg save(HttpServletRequest request,Article article){
	   ResultMsg rs=new ResultMsg();
	   String menubarId = request.getParameter("menubarId");
	   try{
	       Menubar m=menubarService.getById(menubarId);
	       article.setMenubar(m);
	       if(!StringUtil.isEmpty(article.getId())){
	    	   Article a = articleService.getByid(article.getId());
	    	   article.setIssuedDate(a.getIssuedDate());
	    	   article.setPathNumber(a.getPathNumber());
	       }
	       
	       articleService.saveOrUpdate(article);
	       rs.setMsg("保存成功");
		   rs.setRs(true);
	   }catch(Exception e){
		   e.printStackTrace();
		   rs.setMsg("保存失败");
		   rs.setRs(false);
	   }
	   return rs;
   }
   @RequestMapping("getArticleByPage")
   @ResponseBody
  public PagePo<Article> getArticleByPage(HttpServletRequest request){
	    PagePo<Article> page=new PagePo<Article>();
	    page.setPageNumber(Integer.parseInt(request.getParameter("page")));
		page.setPageSize(Integer.parseInt(request.getParameter("rows")));
		
		Map<String,Object> map=new HashMap<String, Object>();
		
		String name = request.getParameter("name");
		if(!StringUtil.isEmpty(name)){
			map.put("name", name);
		}
		
		page=articleService.getByPage(page, map);
		
		List<Article> list = page.getList();
		
		for(Article article:list){
			Menubar menubar = article.getMenubar();
			menubar.setChildren(null);
			Menubar menubar2 = menubar.getMenubar();
			
			if(menubar2!=null){
				menubar2.setMenubar(null);
				menubar2.setChildren(null);
			}
		}
		
	    return page;
  }
   @RequestMapping("edit")
   public ModelAndView getById(HttpServletRequest request){
	   ModelAndView mal=new ModelAndView();
	   Article article = articleService.getByid(request.getParameter("id"));
	   mal.addObject("article", article);
	   mal.setViewName("/article/jsp/edit");
	   return mal;
   }
   
   @RequestMapping("del")
   @ResponseBody
   public ResultMsg del(HttpServletRequest request){
	   ResultMsg rs=new ResultMsg();
	   try{
	   String strIds = request.getParameter("id");
		if (StringUtils.isNotEmpty(strIds)) {
           String[] ids = strIds.split(",");
           articleService.del(ids);
           rs.setMsg("删除成功");
           rs.setRs(true);
		}
	   }catch (Exception e) {
		   rs.setMsg("删除失败");
           rs.setRs(false);
	   }
	   return rs;
   }
   @RequestMapping("{mname}/{anumber}")
   public ModelAndView redict(HttpServletRequest request,ModelAndView mal,@PathVariable int number){

      if(number==0){
    	  mal.setViewName("/desk/page/404");  
    	  return mal;
	  }
	   
	   Article article = articleService.getArticleByPathNumber(number);
	   
	   
	   if(article==null){
		   mal.setViewName("/desk/page/404");  
	       return mal;
	   }
	   article.setClickRate(article.getClickRate()+1);
	   articleService.saveOrUpdate(article);
	   mal.addObject("data", article);
	   mal.setViewName("/desk/page/articledetail");  
	   return mal;
   }
}
