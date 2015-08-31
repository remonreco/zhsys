package com.cbice.distribute.mgr.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.core.db.model.TSysRole;
import com.cbice.distribute.core.service.SysRoleService;

@Controller
public class SysRoleController extends BaseController{
	@Resource
	private SysRoleService sysRoleService;
	
	@RequestMapping("/toSysRoles")
	public String toSysRoles(Model model){
		return "sysRoles";
	}
	
	@RequestMapping("/querySysRoles")
	public @ResponseBody Map<String,Object> querySysRoles(){
	    List<TSysRole> tSysRoles = sysRoleService.selectAll();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", tSysRoles);
		return map;
	}
	
	@RequestMapping("/allSysRoles")
	public @ResponseBody List<TSysRole> allSysRoles(){
	    List<TSysRole> tSysRoles = sysRoleService.selectAll(); 
	    return tSysRoles;
	}
}
