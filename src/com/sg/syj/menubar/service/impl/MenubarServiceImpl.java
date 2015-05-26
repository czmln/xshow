package com.sg.syj.menubar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;





import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sg.syj.common.util.StringUtil;
import com.sg.syj.entity.po.Menubar;
import com.sg.syj.entity.vo.PagePo;
import com.sg.syj.entity.vo.Tree;
import com.sg.syj.menubar.dao.MenubarDao;
import com.sg.syj.menubar.service.MenubarService;
@Service
@Transactional
public class MenubarServiceImpl implements MenubarService{
	
    @Autowired
	private MenubarDao menubarDao;
	
	@Override
	public void saveOrUpdate(Menubar menubar) throws Exception {
		if(StringUtil.isEmpty(menubar.getId())){
			menubar.setAddDate(new Date());
			menubar.setEditDate(new Date());
			menubar.setId(null);
		}else{
			menubar.setEditDate(new Date());
		}
		menubarDao.saveOrUpdate(menubar);
	}

	@Override
	public void del(String[] ids) throws Exception {
		List<Menubar> list = menubarDao.getMenubarByIds(ids);
		for(Menubar m:list){
			menubarDao.delete(m);
		}
		
	}

	@Override
	public List<Menubar> getFristMenuAll() throws Exception {

		String hql=" from Menubar o where 1=1 and o.menuLevel=?";
		
		List<Menubar> list = menubarDao.find(hql, new Object[]{10011});
		return list;
	}

	@Override
	public Menubar getById(String id) throws Exception {
		
          return menubarDao.get(Menubar.class, id);
          
	}
	public PagePo<Menubar> getMenubarByPage(PagePo<Menubar> page,Map<String, Object> map) throws Exception{
        StringBuffer sb=new StringBuffer();
		
		sb.append(" from Menubar o where 1=1 and o.menuLevel=10011 ");
		
		if(map!=null){
			Object obj = map.get("name");
			if( obj!=null){
				String name=obj.toString();
				sb.append(" and o.name like '%");
				sb.append(name);
				sb.append("%'");
			}
		}
		
		sb.append(" order by o.ord asc");
		
		page=menubarDao.getByPage(page, sb.toString());
		
		
		return page;
	}
	
	public List<Menubar> getMenuBarByIds(String[] ids) throws Exception{
		return menubarDao.getMenubarByIds(ids);
	}
	
	public List<Tree> getMenubarOfTree(){
		StringBuffer sb=new StringBuffer(" from Menubar o where 1=1");
		
		sb.append(" and o.menuLevel='10011'");
		
		sb.append(" order by o.ord asc");
		List<Menubar> list = menubarDao.find(sb.toString());
		List<Tree> root=new ArrayList<Tree>();
		for(Menubar m:list){
			Tree t1=new Tree();
			t1.setText(m.getName());
			t1.setId(m.getId());
			if(m.getChildren()!=null){
				List<Menubar> list2 = m.getChildren();
				List<Tree> listTree=new ArrayList<Tree>();
				for(Menubar m2:list2){
					Tree t2=new Tree();
					t2.setText(m2.getName());
					t2.setId(m2.getId());
					listTree.add(t2);
				}
				t1.setChildren(listTree);
			}
			root.add(t1);
		}
		return root;
	}

	@Override
	public Menubar getMenubarByLinkUrl(String url) {
		String hql=" from Menubar o where o.linkUrl=?";
		
		
		return menubarDao.get(hql,new Object[]{url});
	}
}
