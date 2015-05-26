package com.sg.syj.article.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.syj.article.dao.ArticleDao;
import com.sg.syj.article.service.ArticleService;
import com.sg.syj.common.util.StringUtil;
import com.sg.syj.entity.po.Article;
import com.sg.syj.entity.vo.PagePo;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;

	@Override
	public void saveOrUpdate(Article article) {
		if(StringUtil.isEmpty(article.getId())){
			article.setId(null);
			article.setIssuedDate(new Date());
			article.setUpdateTime(new Date());
			int maxNumber= articleDao.getArticleOfMaxNumber();			
			if(maxNumber==0) maxNumber=10000;			
			++maxNumber;
			article.setPathNumber(maxNumber);
		}else{
			article.setUpdateTime(new Date());
			
		}
		articleDao.saveOrUpdate(article);
	}

	@Override
	public void del(String[] ids) {
		articleDao.delete(ids);
	}

	@Override
	public Article getByid(String id) {
		Article a = articleDao.get(Article.class, id);
		return a;
	}
	
	public PagePo<Article> getByPage(PagePo<Article> page,Map<String,Object> map){
        StringBuffer sb=new StringBuffer();
		
		sb.append(" from Article o where 1=1 ");
		
		if(map!=null){
			Object obj = map.get("name");
			if( obj!=null){
				String name=obj.toString();
				sb.append(" and o.name like '%");
				sb.append(name);
				sb.append("%'");
			}
		}
		
		sb.append(" order by o.updateTime desc");
		
		return articleDao.getByPage(page,sb.toString());
	
	}

	@Override
	public Article getArticleBySinglePage(String menuId) {
		String hql=" from Article o where 1=1 and o.menubar.id=?";
		return articleDao.get(hql, new Object[]{menuId});
	}
	
	@Override
	public PagePo<Article> getPageArticleByMenu(String menuId,PagePo<Article> pagePo) {
		
		StringBuffer hql=new StringBuffer(" from Article o where 1=1 and o.menubar.id=?");
		hql.append(" order by o.updateTime desc");
		
		return articleDao.getByPage(pagePo,hql.toString(),menuId);
	}

	@Override
	public Article getArticleByPathNumber(int number) {
		
		String hql=" from Article o where o.pathNumber=?";
		
		return articleDao.get(hql, new Object[]{number});
	}

	@Override
	public Article getArticleSingleByLinkUrl(String linkUrl) {

		String hql=" from Article o where 1=1 and o.menubar.linkUrl=?";
		return articleDao.get(hql, new Object[]{linkUrl});
		
	}

	@Override
	public PagePo<Article> getPageArticleByLinkUrl(String linkUrl,PagePo<Article> pagePo) {
		StringBuffer hql=new StringBuffer(" from Article o where 1=1 and o.menubar.linkUrl=?");
		hql.append(" order by o.updateTime desc");
		
		return articleDao.getByPage(pagePo,hql.toString(),linkUrl);

	}
		
}
