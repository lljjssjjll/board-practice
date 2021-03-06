package com.practice.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.board.dao.AppRoleDao;
import com.practice.board.dao.AppUserDao;
import com.practice.board.entity.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserDao appUserDao;
	
	@Autowired
	private AppRoleDao appRoleDao;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		AppUser appUser = this.appUserDao.findUserAccountByUserName(userName);
		
		if (appUser == null) {
			System.out.println("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		
		System.out.println("Found User : " + appUser);
		
		List<String> roleNames = this.appRoleDao.getRoleNames(appUser.getUserId());
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for(String role : roleNames) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}
		
		UserDetails userDetails = (UserDetails) new User(
				appUser.getUserName()
				, appUser.getEncPassword()
				, grantList);
		
		return userDetails;
	}

}
