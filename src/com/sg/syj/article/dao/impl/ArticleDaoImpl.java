package com.sg.syj.article.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sg.syj.article.dao.ArticleDao;
import com.sg.syj.base.dao.impl.BaseDAOImpl;
import com.sg.syj.entity.po.Article;

@Repository
public class ArticleDaoImpl extends BaseDAOImpl<Article> implements ArticleDao{

	@Override
	public int getArticleOfMaxNumber() {
         String sql="select Max(o.pathNumber) from Article o where 1=1";
		
         Session session = this.getSessionFactory().getCurrentSession();
		 
          Query query = session.createQuery(sql);
          Object number=query.uniqueResult();
		  return  number==null?0:Integer.parseInt(String.valueOf(number));
	}

}
