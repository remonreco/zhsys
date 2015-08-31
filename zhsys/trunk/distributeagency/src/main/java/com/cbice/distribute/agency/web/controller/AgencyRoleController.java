package com.cbice.distribute.agency.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.core.db.model.TAgencyRole;
import com.cbice.distribute.core.service.AgencyRoleService;

@Controller
public class AgencyRoleController extends BaseController{
	@Resource
	private AgencyRoleService agencyRoleService;
	
	@RequestMapping("/toSysRoles")
	public String toSysRoles(Model model){
		return "sysRoles";
	}
	
	@RequestMapping("/querySysRoles")
	public @ResponseBody Map<String,Object> querySysRoles(){
	    List<TAgencyRole> tSysRoles = agencyRoleService.selectAll();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", tSysRoles);
		return map;
	}
	
	@RequestMapping("/allSysRoles")
	public @ResponseBody List<TAgencyRole> allSysRoles(){
	    List<TAgencyRole> tAgencyRoles = agencyRoleService.selectAll(); 
	    return tAgencyRoles;
	}
}
