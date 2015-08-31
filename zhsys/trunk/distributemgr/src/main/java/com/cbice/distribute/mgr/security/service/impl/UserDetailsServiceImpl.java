package com.cbice.distribute.mgr.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cbice.distribute.core.db.model.TSysRole;
import com.cbice.distribute.core.db.model.TSysUser;
import com.cbice.distribute.core.service.SysRoleService;
import com.cbice.distribute.core.service.SysUserService;
import com.cbice.distribute.mgr.security.model.UserDetailsImpl;

public class UserDetailsServiceImpl implements UserDetailsService {
	private static Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	private SysUserService sysUserService;
	private SysRoleService sysRoleService;

	public void setSysUserService(SysUserService sysUserService){
        this.sysUserService = sysUserService;
    }

	public void setSysRoleService(SysRoleService sysRoleService){
        this.sysRoleService = sysRoleService;
    }

    @Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
	    TSysUser tSysUser = sysUserService.selectSysUserByLoginName(userName);
		if (null == tSysUser) {
			throw new UsernameNotFoundException(messages.getMessage(
					"User not found", new Object[] { userName },
					"Username {0} not found"));
		}
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<TSysRole> tSysRoles = sysRoleService.selectByUserId(tSysUser.getId());
		for (TSysRole tSysRole : tSysRoles) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(tSysRole.getCd());
			authorities.add(authority);
		}
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl(tSysUser, authorities);
		return userDetailsImpl;
	}

}
