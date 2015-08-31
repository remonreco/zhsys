package com.cbice.distribute.mgr.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cbice.distribute.core.db.model.TSysUser;
import com.cbice.distribute.core.service.SysUserService;
import com.cbice.distribute.mgr.security.model.UserDetailsImpl;
import com.cbice.distribute.mgr.service.WebSysUserService;

public class WebSysUserServiceImpl implements WebSysUserService{
    private SysUserService sysUserService;

    public void setSysUserService(SysUserService sysUserService){
        this.sysUserService = sysUserService;
    }

    @Override
    public TSysUser getLoginSysUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if(null == auth){
            return null;
        }
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl)auth.getPrincipal();
        if(null == userDetailsImpl){
            return null;
        }
        TSysUser tSysUser = userDetailsImpl.gettSysUser();
        if(null == tSysUser){
            return null;
        }
        Long userId = tSysUser.getId();
        if(null == userId){
            return null;
        }
        return sysUserService.selectById(userId);
    }

}
