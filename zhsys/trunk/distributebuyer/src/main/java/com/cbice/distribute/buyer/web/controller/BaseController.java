package com.cbice.distribute.buyer.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.cbice.distribute.buyer.security.model.UserDetailsImpl;

public abstract class BaseController {

	protected UserDetailsImpl getUserInfo(){
		UserDetailsImpl webUserDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return webUserDetails;
	}
	
	public static UserDetailsImpl getUserInfos(){
		UserDetailsImpl webUserDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return webUserDetails;
	}
	
}
