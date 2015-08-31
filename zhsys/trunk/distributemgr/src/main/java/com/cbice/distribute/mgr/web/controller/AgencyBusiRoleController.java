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

import com.cbice.distribute.core.db.model.TAgencyBusiRole;
import com.cbice.distribute.core.db.model.TAgencyBusiRoleBean;
import com.cbice.distribute.core.db.model.TAgencyLevelRole;
import com.cbice.distribute.core.db.model.TAgencyRole;
import com.cbice.distribute.core.service.AgencyBusiRoleService;
import com.cbice.distribute.core.service.AgencyRoleService;
import com.cbice.distribute.core.util.PayPluginException;

@Controller
public class AgencyBusiRoleController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private AgencyRoleService agencyRoleService;
	
	@Resource
	private AgencyBusiRoleService agencyBusiRoleService;
	
	@RequestMapping("/toAgencyBusiRoles")
	public String toAgencyRoles(Model model){
		
	    List<TAgencyRole> tAgencyRoles = agencyRoleService.selectAll();
	    model.addAttribute("roles", tAgencyRoles);
		return "agencyBusiRoles";
	}
	
	@RequestMapping("/queryAgencyBusiRoles")
	public @ResponseBody Map<String, Object> queryAgencyBusiRoles(HttpServletRequest request, String page, String rows, String name, String roles,Integer agencyLevel){
	       // 当前页
        int intPage = Integer.parseInt((page == null) ? "1" : page);
        // 每页显示行数
        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
        int end = intPage * no;
        int start = end - no + 1;
        Map<String,Object> map = new HashMap<String, Object>();
        if(StringUtils.isNotEmpty(name)){
            map.put("name", name);
        }
        if(StringUtils.isNotEmpty(roles)){
            List<Long> list = new ArrayList<Long>();
            for (String role : roles.split(",")) {
                if(NumberUtils.isNumber(role)){
                    list.add(NumberUtils.createLong(role));
                }
            }
            map.put("roles", list);
        }

        if(agencyLevel!=null&&agencyLevel!=0){
        	map.put("agencyLevel", agencyLevel);//当前经销商等级  的角色列表
        }
        int count = agencyBusiRoleService.countByCondition(map);
        Map<String,Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", count);
        map.put("limit", no);
        map.put("start", start-1);
        List<TAgencyBusiRoleBean> tAgencyBusiRoleBeans = agencyBusiRoleService.selectByCondition(map);
        jsonMap.put("rows", tAgencyBusiRoleBeans);
        return jsonMap;
	}
	
	@RequestMapping("/addAgencyBusiRole")
	public @ResponseBody Map<String,String> addAgencyBusiRole(String name, String remark, Long[] roles,Integer agencyLevel ){
	    int result = 0;
	    Map<String,String> resMap = new HashMap<String,String>();
	    resMap.put("resCode", "0000");
	    resMap.put("msg", "添加成功");
	    TAgencyBusiRole tAgencyBusiRole = new TAgencyBusiRole();
	    if(StringUtils.isEmpty(name)){
			resMap.put("resCode", "1001");
			resMap.put("msg", "请填写角色名称");
			return resMap;
	    }
	    if(StringUtils.isNotEmpty(remark)){
	         tAgencyBusiRole.setRemark(remark);
	    }
	    if(agencyLevel == null){
	    	resMap.put("resCode", "1003");
			resMap.put("msg", "请选择角色所对应的经销商等级");
			return resMap;
	    }
	    if(roles==null||roles.length<1){
			resMap.put("resCode", "1004");
			resMap.put("msg", "请选择角色功能");
			return resMap;
	    }
	    tAgencyBusiRole.setName(name);
	    TAgencyLevelRole agencyLevelRole = new TAgencyLevelRole();
	    agencyLevelRole.settAgencyLevel(agencyLevel);
	    try{
	        result = agencyBusiRoleService.insertBusiRole(tAgencyBusiRole, roles,agencyLevelRole);
	    }catch(Exception e){
	        logger.error(null, e);
	    }
	    if(result!=1){
	    	resMap.put("resCode", "1005");
			resMap.put("msg", "添加角色失败");
	    }
	    return resMap;
	}
	
	@RequestMapping("/checkDuplicateAgencyBusiRoleName")
	public @ResponseBody String checkDuplicateAgencyBusiRoleName(Long id, String name,Integer agencyLevel){
		if(agencyLevel==null){
			return "0";
		}
	    return agencyBusiRoleService.checkDuplicateAgencyBusiRoleName(id, name,agencyLevel);
	}
	
	@RequestMapping("/updateAgencyBusiRole")
	public @ResponseBody int updateAgencyBusiRole(Long id,String remark, Long[] roles){
	    int result = 0;
	    TAgencyBusiRole tAgencyBusiRole = new TAgencyBusiRole();
	    tAgencyBusiRole.setId(id);
	   
	    if(StringUtils.isNotEmpty(remark)){
	        tAgencyBusiRole.setRemark(remark);
	    }
	    try{
	        result = agencyBusiRoleService.updateBusiRole(tAgencyBusiRole, roles);
	    }catch(PayPluginException e){
            logger.error(null, e);
            return -1;
        }
        return result;
	}
	
	@RequestMapping("/delAgencyRole")
	public @ResponseBody int delAgencyRole(Long id){
		int result = 0;
		if(id==null||id==1||id==2||id==3||id==4||id==5){
			return -1;
		}
		 try{
		        result = agencyBusiRoleService.delAgencyRole(id);
		    }catch(PayPluginException e){
	            logger.error(null, e);
	            return -1;
	        }
		return result;
	}
	
}
