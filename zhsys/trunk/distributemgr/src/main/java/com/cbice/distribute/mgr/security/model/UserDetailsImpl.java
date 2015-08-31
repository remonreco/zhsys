package com.cbice.distribute.mgr.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cbice.distribute.core.db.model.TSysUser;

public class UserDetailsImpl implements UserDetails{

    private static final long serialVersionUID = -7835118167762649974L;
    
    private TSysUser tSysUser;
    
    private Collection<GrantedAuthority> authorities;

	public UserDetailsImpl(TSysUser tSysUser,
			Collection<GrantedAuthority> authorities) {
		super();
		this.tSysUser = tSysUser;
		this.authorities = authorities;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.authorities;
    }

    @Override
    public String getPassword(){
        return tSysUser.getLoginPwd();
    }

    @Override
    public String getUsername(){
        return tSysUser.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    /**
     * 1：正常；0：禁用
     */
    @Override
    public boolean isAccountNonLocked(){
    	 return tSysUser.getStatus() == 1 ? true : false;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    /**
     * 1：正常；2：禁用
     */
    @Override
    public boolean isEnabled(){
        return tSysUser.getStatus() == 1 ? true : false;
    }

    /**
     * 扩展返回属性
     */
    public TSysUser gettSysUser(){
        return tSysUser;
    }
}
