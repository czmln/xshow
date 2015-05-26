package com.sg.syj.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sg.syj.admin.dao.AdminDao;
import com.sg.syj.admin.service.AdminService;
import com.sg.syj.entity.po.Sys_Admin;

public class LoginServiceImpl implements UserDetailsService {
	
	@Autowired
	private AdminService demoService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Sys_Admin user = null;
		try {
			 user = demoService.getAdminByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        if  (user == null) {
        	// 抛出异常
            throw new UsernameNotFoundException("输入的用户名和密码错误，请确认！"); 
        }
        List<GrantedAuthority> authsList = new ArrayList<GrantedAuthority>();
        authsList.add(new SimpleGrantedAuthority("ROLE_USER"));  
        
        
        boolean accountNonExpired = true;  
        boolean credentialsNonExpired = true;  
        boolean accountNonLocked = true;  
  
        UserDetails userdetails = new org.springframework.security.core.userdetails.User(user.getId(), user.getUserpwd()  
               ,true, accountNonExpired, credentialsNonExpired, accountNonLocked, authsList);  
  
        return userdetails; 
	}

}
