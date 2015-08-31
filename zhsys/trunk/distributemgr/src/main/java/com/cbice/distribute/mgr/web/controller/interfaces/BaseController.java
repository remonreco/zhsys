package com.cbice.distribute.mgr.web.controller.interfaces;

import org.springframework.security.core.context.SecurityContextHolder;

import com.cbice.distribute.mgr.security.model.UserDetailsImpl;

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
