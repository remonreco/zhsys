package com.cbice.distribute.mgr.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.core.db.model.TSysBusiRole;
import com.cbice.distribute.core.db.model.TSysBusiRoleBean;
import com.cbice.distribute.core.db.model.TSysRole;
import com.cbice.distribute.core.service.SysBusiRoleService;
import com.cbice.distribute.core.service.SysRoleService;
import com.cbice.distribute.core.util.PayPluginException;

@Controller
public class SysBusiRoleController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private SysRoleService sysRoleService;
	
	@Resource
	private SysBusiRoleService sysBusiRoleService;
	
	@RequestMapping("/toSysBusiRoles")
	public String toSysRoles(Model model){
	    List<TSysRole> tSysRoles = sysRoleService.selectAll();
	    model.addAttribute("roles", tSysRoles);
		return "sysBusiRoles";
	}
	
	@RequestMapping("/allSysBusiRoles")
	public @ResponseBody List<TSysBusiRole> allSysBusiRoles(){
	    List<TSysBusiRole> tSysBusiRoles = sysBusiRoleService.selectAll();
		return tSysBusiRoles;
	}
	
	@RequestMapping("/querySysBusiRoles")
	public @ResponseBody Map<String, Object> querySysBusiRolesUrl(HttpServletRequest request, String page, String rows, String name, String roles){
	       // 当前页
        int intPage = Integer.parseInt((page == null) ? "1" : page);
        // 每页显示行数
        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
        int end = intPage * no;
        int start = end - no + 1;
        Map<String,Object> map = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(name)){
            map.put("name", name);
        }
        if(StringUtils.isNotBlank(roles)){
            List<Long> list = new ArrayList<Long>();
            for (String role : roles.split(",")) {
                if(NumberUtils.isNumber(role)){
                    list.add(NumberUtils.createLong(role));
                }
            }
            map.put("roles", list);
        }
        int count = sysBusiRoleService.countByCondition(map);
        Map<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", count);
        map.put("limit", no);
        map.put("offset", start);
        List<TSysBusiRoleBean> tSysBusiRoleBeans = sysBusiRoleService.selectByCondition(map);
        jsonMap.put("rows", tSysBusiRoleBeans);
        return jsonMap;
	}
	
	@RequestMapping("/checkDuplicateSysBusiRoleName")
	public @ResponseBody String checkDuplicateSysBusiRoleName(Long id, String name){
	    return sysBusiRoleService.checkDuplicateSysBusiRoleName(id, name);
	}
	
	
	@RequestMapping("/addSysBusiRole")
	public @ResponseBody int addSysBusiRole(String name, String remark, Long[] roles){
	    int result = 0;
	    TSysBusiRole tSysBusiRole = new TSysBusiRole();
	    if(StringUtils.isNotBlank("name")){
	        tSysBusiRole.setName(name);
	    }
	    if(StringUtils.isNotBlank(remark)){
	        tSysBusiRole.setRemark(remark);
	    }
	    try{
	        result = sysBusiRoleService.insertBusiRole(tSysBusiRole, roles);
	    }catch(Exception e){
	        logger.error(null, e);
	        result = -1;
	    }
	    return result;
	}
	
	@RequestMapping("/updateSysBusiRole")
	public @ResponseBody int updateSysBusiRole(Long id, String name, String remark, Long[] roles){
	    int result = 0;
	    TSysBusiRole tSysBusiRole = new TSysBusiRole();
	    tSysBusiRole.setId(id);
	    if(StringUtils.isNotBlank(name)){
	        tSysBusiRole.setName(name);
	    }
	    if(StringUtils.isNotBlank(remark)){
	        tSysBusiRole.setRemark(remark);
	    }
	    try{
	        result = sysBusiRoleService.updateBusiRole(tSysBusiRole, roles);
	    }catch(PayPluginException e){
            logger.error(null, e);
            return -1;
        }
        return result;
	}
}
