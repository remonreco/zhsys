package com.cbice.distribute.agency.security.service.impl;

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

import com.cbice.distribute.agency.security.model.UserDetailsImpl;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TAgencyRole;
import com.cbice.distribute.core.db.model.TSysRole;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.AgencyRoleService;
import com.cbice.distribute.core.service.AgencyUserService;
import com.cbice.distribute.core.service.TAgencyDbService;

public class UserDetailsServiceImpl implements UserDetailsService {
	private static Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	private AgencyUserService agencyUserService;
	private AgencyRoleService agencyRoleService;
	private TAgencyDbService  tAgencyDbService;

    public void setAgencyUserService(AgencyUserService agencyUserService) {
		this.agencyUserService = agencyUserService;
	}


	public void setAgencyRoleService(AgencyRoleService agencyRoleService) {
		this.agencyRoleService = agencyRoleService;
	}


	public void settAgencyDbService(TAgencyDbService tAgencyDbService) {
		this.tAgencyDbService = tAgencyDbService;
	}


	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
	    TUser tUser = agencyUserService.selectAgencyUserByLoginName(userName);
		if (null == tUser) {
			throw new UsernameNotFoundException(messages.getMessage(
					"User not found", new Object[] { userName },
					"Username {0} not found"));
		}
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<TAgencyRole> tSysRoles = agencyRoleService.selectByUserId(tUser.getId());
		for (TAgencyRole tAgencyRole : tSysRoles) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(tAgencyRole.getCd());
			authorities.add(authority);
		}
		TAgency tAgency =  tAgencyDbService.selectByAgencyId(tUser.getAgencyId());
		if(null == tAgency){
			throw new UsernameNotFoundException(messages.getMessage(
					"agency not found", new Object[] { userName },
					"agency {0} not found"));
		}
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl(tUser, authorities,tAgency);
		return userDetailsImpl;
	}

}
