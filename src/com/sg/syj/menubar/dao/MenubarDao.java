package com.sg.syj.menubar.dao;

import java.util.List;

import com.sg.syj.base.dao.BaseDAO;
import com.sg.syj.entity.po.Menubar;

public interface MenubarDao extends BaseDAO<Menubar> {
   List<Menubar> getMenubarByIds(String[] ids);
}
