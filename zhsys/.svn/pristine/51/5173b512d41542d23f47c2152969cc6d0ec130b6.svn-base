package com.cbice.distribute.agency.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TUser;

public class UserDetailsImpl implements UserDetails{

    private static final long serialVersionUID = -7835118167762649974L;
    
    private TUser tUser;
    
    private TAgency tAgency;
    
    private Collection<GrantedAuthority> authorities;

	public UserDetailsImpl(TUser tUser,
			Collection<GrantedAuthority> authorities,TAgency tAgency) {
		super();
		this.tUser = tUser;
		this.authorities = authorities;
		this.tAgency = tAgency;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.authorities;
    }

    @Override
    public String getPassword(){
        return tUser.getDealerNum();
    }

    @Override
    public String getUsername(){
        return tUser.getDealerName();
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
    	 return tUser.getAccountStatus()==1 ? true : false;
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
        return tUser.getAccountStatus()==1? true : false;
    }

	public TUser gettUser() {
		return tUser;
	}

	public void settUser(TUser tUser) {
		this.tUser = tUser;
	}

	public TAgency gettAgency() {
		return tAgency;
	}

	public void settAgency(TAgency tAgency) {
		this.tAgency = tAgency;
	}

	
  
}
