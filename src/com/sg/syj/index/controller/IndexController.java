package com.sg.syj.index.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sg.syj.article.service.ArticleService;
import com.sg.syj.banner.service.BannerService;
import com.sg.syj.common.util.StringUtil;
import com.sg.syj.entity.po.Article;
import com.sg.syj.entity.po.Banner;
import com.sg.syj.entity.po.Menubar;
import com.sg.syj.entity.po.OrgInfo;
import com.sg.syj.entity.vo.PagePo;
import com.sg.syj.menubar.service.MenubarService;
import com.sg.syj.orginfo.service.OrgInfoService;

@RequestMapping("")
@Controller
public class IndexController{
	
  @Autowired
  private BannerService bannerService;
  
  @Autowired
  private OrgInfoService infoService;
  @Autowired
  private MenubarService menubarService;
  @Autowired
  private ArticleService articleService;
  
  @RequestMapping("")
  public ModelAndView index(HttpServletRequest request,ModelAndView mal){
	
	  getCommonData(mal);
      
	  
	  Article introduction = articleService.getArticleSingleByLinkUrl("gsjs");
	  
	  Article sucessCase = articleService.getArticleSingleByLinkUrl("cgal");
	  PagePo<Article> pagePo=new PagePo<Article>();
	  pagePo.setPageSize(3);
	  pagePo.setPageNumber(1);
	  PagePo<Article> news = articleService.getPageArticleByLinkUrl("xwzx", pagePo);
	  Article cp = articleService.getArticleSingleByLinkUrl("cpzx");
	  
	  mal.addObject("case", sucessCase);
	  mal.addObject("intro", introduction);
	  mal.addObject("news", news.getList());
	  mal.addObject("cp", cp);
	  

	  
      mal.setViewName("/desk/page/index");
	  return mal;
  }
  @RequestMapping(value="{linkUrl}")
  public ModelAndView direct(HttpServletRequest request,ModelAndView mal,@PathVariable String linkUrl){
	  
	  Menubar m = menubarService.getMenubarByLinkUrl(linkUrl);
	  if(m==null){
		  mal.setViewName("/desk/page/404");  
	  }else{
		  getCommonData(mal);
		  mal.addObject("m", m);
		  addDataByMenu(mal, m,request);
	  }
	  return mal;  
  }
  
  
  public ModelAndView getCommonData(ModelAndView mal){
	    //背景图信息
		List<Banner> banners=null;
		
		//组织机构信息
		OrgInfo orgInfo=null;
		
		//栏目
		List<Menubar> menus=null;
	    try {
	    	banners=bannerService.getAll();
	    	orgInfo=infoService.getOrgInfo();
	        menus = menubarService.getFristMenuAll();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	    
	      mal.addObject("banners", banners);
	      
	      mal.addObject("org", orgInfo);
	      
	      mal.addObject("menus", menus);
	  
	     return mal;
  }
  
  public void addDataByMenu(ModelAndView mal,Menubar m,HttpServletRequest request){
	   String pageIndex = request.getParameter("pageIndex");
	   int pageNumber=1;
	  if(!StringUtil.isEmpty(pageIndex)){
		  pageNumber=Integer.parseInt(pageIndex);
	  }
	     //10011,单页面
		// 10012,文字列表
		// 10013,图片列表
		// 10014,文字图片列表
	   int navType=m.getNavType();
	   PagePo<Article> page=new PagePo<Article>();
	   switch (navType) {
	     case 10011:
		      mal.setViewName("/desk/page/10011");
		      Article article = articleService.getArticleBySinglePage(m.getId());
		      mal.addObject("data",article);
		      break;
	     case 10012:
	    	  page.setPageNumber(pageNumber);
	    	  page.setPageSize(10);
	    	  PagePo<Article> list = articleService.getPageArticleByMenu(m.getId(), page);
	    	  mal.addObject("page",list);
	    	  mal.addObject("list",list.getList());
		      mal.setViewName("/desk/page/10012");
		      break;
	     case 10013:
	    	  page.setPageNumber(pageNumber);
	    	  page.setPageSize(8);
	    	  PagePo<Article> list2 = articleService.getPageArticleByMenu(m.getId(), page);
	    	  mal.addObject("page",list2);
	    	  mal.addObject("list",list2.getList());
		      mal.setViewName("/desk/page/10013");
		      break;
	     case 10014:
	    	  page.setPageNumber(pageNumber);
	    	  page.setPageSize(4);
	    	  PagePo<Article> list3= articleService.getPageArticleByMenu(m.getId(), page);
	    	  mal.addObject("page",list3);
	    	  mal.addObject("list",list3.getList());
		      mal.setViewName("/desk/page/10014");
		      break;
	     default:
		      break;
	  }
	  
  }
  
  @RequestMapping("{mname}/{number}")
  public ModelAndView redict(HttpServletRequest request,ModelAndView mal,@PathVariable int number,@PathVariable String mname){

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
	   getCommonData(mal);
	   mal.addObject("data", article);
	   Menubar m = menubarService.getMenubarByLinkUrl(mname);
	   mal.addObject("m", m);
	   mal.setViewName("/desk/page/articledetail");  
	   return mal;
  }
}
