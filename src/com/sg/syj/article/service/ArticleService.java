package com.sg.syj.article.service;

import java.util.Map;

import com.sg.syj.entity.po.Article;
import com.sg.syj.entity.vo.PagePo;

public interface ArticleService {
  
	void saveOrUpdate(Article article);
	void del(String[] ids);
	Article getByid(String id);
	PagePo<Article> getByPage(PagePo<Article> page,Map<String,Object> map);
	Article getArticleBySinglePage(String menuId);
	Article getArticleByPathNumber(int number);
	PagePo<Article> getPageArticleByMenu(String menuId,PagePo<Article> pagePo);
	
	Article getArticleSingleByLinkUrl(String linkUrl);
	PagePo<Article> getPageArticleByLinkUrl(String linkUrl,PagePo<Article> pagePo);
	
}
