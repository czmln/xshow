package com.sg.syj.menubar.service;

import java.util.List;
import java.util.Map;

import com.sg.syj.entity.po.Menubar;
import com.sg.syj.entity.vo.PagePo;
import com.sg.syj.entity.vo.Tree;

public interface MenubarService {
  void saveOrUpdate(Menubar menubar) throws Exception;
  void del (String[] ids) throws Exception;
  List<Menubar> getFristMenuAll() throws Exception ;
  Menubar getById(String id) throws Exception;
  PagePo<Menubar> getMenubarByPage(PagePo<Menubar> page,Map<String, Object> map) throws Exception;
  
  List<Menubar> getMenuBarByIds(String[] ids) throws Exception;
  
  List<Tree> getMenubarOfTree();
  
  Menubar getMenubarByLinkUrl(String url);
}
