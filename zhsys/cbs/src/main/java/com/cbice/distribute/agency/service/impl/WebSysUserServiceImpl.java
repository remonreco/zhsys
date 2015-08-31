package com.cbice.distribute.agency.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cbice.distribute.agency.security.model.UserDetailsImpl;
import com.cbice.distribute.agency.service.WebSysUserService;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.AgencyUserService;

public class WebSysUserServiceImpl implements WebSysUserService{
    private AgencyUserService agencyUserService;

  

    
	public void setAgencyUserService(AgencyUserService agencyUserService) {
		this.agencyUserService = agencyUserService;
	}

	@Override
    public TUser getLoginSysUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if(null == auth){
            return null;
        }
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl)auth.getPrincipal();
        if(null == userDetailsImpl){
            return null;
        }
        TUser tUser = userDetailsImpl.gettUser();
        if(null == tUser){
            return null;
        }
        Long userId = tUser.getId();
        if(null == userId){
            return null;
        }
        return agencyUserService.selectById(userId);
    }

}
