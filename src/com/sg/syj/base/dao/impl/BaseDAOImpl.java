package com.sg.syj.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;









import com.sg.syj.base.dao.BaseDAO;
import com.sg.syj.entity.vo.PagePo;

@SuppressWarnings("all")
public class BaseDAOImpl<T>  implements BaseDAO<T> {
	
	
    private SessionFactory sessionFactory;
    
    //泛型的实际类对象
    private Class<Object> classPo ;
   
    public BaseDAOImpl() {
    	 this.classPo = getSuperClassGenricType(getClass(), 0);  
	}

	public SessionFactory getSessionFactory() {
    	
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Serializable save(T o) {
        return this.getCurrentSession().save(o);
    }

    public int delete(String[] ids) {
    
    
    	
    	String hql="delete  "+classPo.getName()+" where id in (:ids) ";
    	Query query = this.getCurrentSession().createQuery(hql);
    	query.setParameterList("ids", ids);
    	return query.executeUpdate();
    	
    }
    public int delete(T o) {
    	
    	this.getCurrentSession().delete(o);
    	
    	return 0;
     }

    
    public void update(T o) {
        this.getCurrentSession().update(o);
    }

    public void saveOrUpdate(T o) {
        this.getCurrentSession().saveOrUpdate(o);
    }

   
	public List<T> find(String hql) {
        return this.getCurrentSession().createQuery(hql).list();
    }

    public List<T> findWithHqlParamsValues(String hql, Map<String, Date> map) {
        Query query = this.getCurrentSession().createQuery(hql);

        Set<Entry<String, Date>> sets = map.entrySet();
        for (Entry<String, Date> entry : sets) {
            String key = entry.getKey();
            Date obj = entry.getValue();

            // query.setDate(key, obj);
            query.setTimestamp(key, obj);
        }

        List<T> list = query.list();
        return list;
    }

    public List<T> find(String hql, Object[] param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.list();
    }

    public List<T> find(String hql, List<Object> param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.list();
    }

    public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public List<T> find(String hql, List<Object> param, Integer page, Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    public T get(Class<T> c, Serializable id) {
        return (T) this.getCurrentSession().get(c, id);
    }

    public T get(String hql, Object[] param) {
        List<T> l = this.find(hql, param);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }
    
    public PagePo<T> getByPage(PagePo page,String hql, Object... params){
    	
    	Query query = this.getCurrentSession().createQuery(hql);
    	if(params != null && params.length > 0) {
			for(int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}	
    	
    	
    	
		query.setFirstResult((page.getPageNumber()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
	     
		page.setList(query.list());
		
		page.setTotal(this.count("select count(*) "+removeOrder(hql),params));
		
    	return page;
    }
    
    
    public T get(String hql, List<Object> param) {
        List<T> l = this.find(hql, param);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    public Long count(String hql) {
        return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
    }

    public Long count(String hql, Object[] param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return (Long) q.uniqueResult();
    }

    public Long count(String hql, List<Object> param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return (Long) q.uniqueResult();
    }

    public Integer executeHql(String hql) {
        return this.getCurrentSession().createQuery(hql).executeUpdate();
    }

    public Integer executeHql(String hql, Object[] param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.executeUpdate();
    }

    public Integer executeHql(String hql, List<Object> param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.executeUpdate();
    }

    public List<T> queryBySql(String sql) {
        List<T> list = this.getCurrentSession().createSQLQuery(sql).list();
        return list;
    }

    public int excuteBySql(String sql) {
        int result;
        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
        result = query.executeUpdate();
        return result;
    }
    /**
   	 * @Description:去除Order排序，以提高查询速度（查询记录数）
   	*/
   	private static String removeOrder(String hql) {
   		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
   				Pattern.CASE_INSENSITIVE);
   		Matcher m = p.matcher(hql);
   		StringBuffer sb = new StringBuffer();
   		while(m.find()) {
   			m.appendReplacement(sb, "");
   		}
   		m.appendTail(sb);
   		return sb.toString();
   	}
    //获取泛型参数
    public static Class<Object> getSuperClassGenricType(final Class clazz, final int index) {  
        
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。  
        Type genType = clazz.getGenericSuperclass();  
  
        if (!(genType instanceof ParameterizedType)) {  
           return Object.class;  
        }  
        //返回表示此类型实际类型参数的 Type 对象的数组。  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
  
        if (index >= params.length || index < 0) {  
                     return Object.class;  
        }  
        if (!(params[index] instanceof Class)) {  
              return Object.class;  
        }  
        return (Class) params[index];  
    }
}
