package com.sg.syj.article.dao;

import com.sg.syj.base.dao.BaseDAO;
import com.sg.syj.entity.po.Article;

public interface ArticleDao extends BaseDAO<Article> {
     public int getArticleOfMaxNumber();
}
