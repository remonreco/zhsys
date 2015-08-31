package com.cbice.distribute.agency.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.agency.security.model.UserDetailsImpl;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TAgencyBusiRole;
import com.cbice.distribute.core.db.model.TAgencyUserBean;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.AgencyBusiRoleService;
import com.cbice.distribute.core.service.AgencyUserService;
import com.cbice.distribute.core.util.PayPluginException;

@Controller
public class AgencyUserController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AgencyUserService agencyUserService;
    @Resource
    private AgencyBusiRoleService agencyBusiRoleService;
    
    @RequestMapping("/modPwdInput")
    public String modPwdInput(){
        return "modPwdInput";
    }
    
    @RequestMapping("/modPwd")
    public @ResponseBody String modPwd(@RequestParam("orginPwd") String originPwd, @RequestParam("newPwd") String newPwd, @RequestParam("newPwd") String newPwd2){
        if(StringUtils.isBlank(originPwd)){
            return "原密码为空";
        }
        if(StringUtils.isBlank(newPwd)){
            return "新密码为空";
        }
        if(StringUtils.isBlank(newPwd2)){
            return "二次输入新密码为空";
        }
        if(!StringUtils.equals(newPwd, newPwd2)){
            return "新密码两次输入不一致";
        }
        TUser tUser = this.getUserInfo().gettUser();
        String originPwdDigest = new Md5PasswordEncoder().encodePassword(originPwd, null);
        if(!StringUtils.equals(originPwdDigest, tUser.getDealerNum())){
            return "原密码输入不正确";
        }
        String newPwdDigest = new Md5PasswordEncoder().encodePassword(newPwd, null);
        TUser tUser2 = new TUser();
        tUser2.setId(tUser.getId());
        tUser2.setDealerName(tUser.getDealerName());
        tUser2.setDealerNum(newPwdDigest);
        if(agencyUserService.updateByPrimaryKeySelective(tUser2) > 0){
            return "密码修改成功";
        }else{
            return "密码修改失败";
        }
    }
    
    /**
     * 系统用户列表主界面
     * @param model
     * @return
     */
    @RequestMapping("/toSysUsers")
    public String toUserList(Model model){
    	UserDetailsImpl users = this.getUserInfo();
        TAgency tAgency = users.gettAgency();
        List<TAgencyBusiRole> tSysBusiRoles = agencyBusiRoleService.selectbyAgencyLevel(tAgency.getDealerLevel());
        model.addAttribute("roles", tSysBusiRoles);
        return "sysUsers";
    }

    /**
     * 系统用户列表json对象
     * @param request
     * @param page
     * @param rows
     * @param status
     * @param loginName
     * @param roles
     * @return
     */
    @RequestMapping("/querySysUsers")
    public @ResponseBody Map<String, Object> querySysUsers(String page, String rows, String status, String loginName, String roles){
        // 当前页
        int intPage = Integer.parseInt((page == null) ? "1" : page);
        // 每页显示行数
        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);

        int end = intPage * no;
        int start = end - no + 1;
        Map<String, Object> map = new HashMap<String, Object>();

        if(StringUtils.isNotBlank(status) && NumberUtils.isNumber(status)){
            map.put("status", NumberUtils.createInteger(status));
        }
        if(StringUtils.isNotBlank(loginName)){
            map.put("loginName", loginName);
        }
        UserDetailsImpl users = this.getUserInfo();
        TAgency tAgency = users.gettAgency();
        Long agency_Id = tAgency.getId();
        map.put("agency_Id", agency_Id);
        map.put("user_identy", 1);
        if(StringUtils.isNotBlank(roles)){
            List<Long> list = new ArrayList<Long>();
            String[] strings = roles.split(",");
            for (String role : strings){
                if(NumberUtils.isNumber(role)){
                    list.add(NumberUtils.createLong(role));
                }
            }
            map.put("roles", list);
        }
        int count = agencyUserService.countByCondition(map);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", count);
        map.put("limit", no);
        map.put("start", start-1);
        List<TAgencyUserBean> tSysUserBeans = agencyUserService.selectByCondition(map);
        jsonMap.put("rows", tSysUserBeans);
        return jsonMap;
    }

    @RequestMapping("/checkDuplicateSysUserLoginName")
    public @ResponseBody String checkDuplicateSysUserLoginName(String loginName){
        return agencyUserService.checkDuplicateAgencyUserLoginName(loginName);
    }

    @RequestMapping("/addSysUser")
    public @ResponseBody Map<String,String> addUser(String loginName, String realName, String loginPwd, String mobile, Long[] roles){
        int result = 0;
        Map<String,String> resMap = new HashMap<String,String>();
        resMap.put("resCode", "0000");
    	resMap.put("msg", "添加成功");
        if(StringUtils.isBlank(loginName)){
        	resMap.put("resCode", "1001");
        	resMap.put("msg", "登录名为空");
        	return resMap;
        }
        if(StringUtils.isBlank(realName)){
        	resMap.put("resCode", "1002");
        	resMap.put("msg", "真实姓名为空");
        	return resMap;
        }
        if(StringUtils.isBlank(loginPwd)){
        	resMap.put("resCode", "1003");
        	resMap.put("msg", "登录密码为空");
        	return resMap;
        }
        if(roles==null||roles.length<1){
        	resMap.put("resCode", "1004");
        	resMap.put("msg", "请为账户配置角色");
        	return resMap;
        }
        if(StringUtils.isBlank(mobile)){
        	resMap.put("resCode", "1005");
        	resMap.put("msg", "联系电话为空");
        	return resMap;
        }
        
        UserDetailsImpl users = this.getUserInfo();
        TAgency sysuser=users.gettAgency();
        TUser tUser = new TUser();
        tUser.setDealerName(loginName);
        tUser.setUserName(realName);
        tUser.setDealerNum(loginPwd);
        tUser.setUserIdenty(1);//0，会员；1，经销商，3：操作员
        tUser.setAgencyId(sysuser.getId());
        tUser.setTel(mobile);
        tUser.setUserRoleId(1l);//操作员标识：0：权限最高者，1：普通操作员
        try{
            result = agencyUserService.insertUser(tUser, roles);
        }catch(PayPluginException e){
            logger.error("exception", e);
        }
        if(result!=1){
        	resMap.put("resCode", "1006");
        	resMap.put("msg", "添加账户失败");
        }
        return resMap;
    }

    @RequestMapping("/updateSysUser")
    public @ResponseBody Map<String,String> updateUser(Long id, String loginPwd, String email, String mobile, Long[] roles, Integer status){
        TUser tUser = new TUser();
        UserDetailsImpl users = this.getUserInfo();
        TAgency sysuser=users.gettAgency();
        Map<String,String> resMap = new HashMap<String,String>();
        resMap.put("resCode", "0000");
    	resMap.put("msg", "修改成功");
        if(id==null){
        	resMap.put("resCode", "1001");
        	resMap.put("msg", "修改失败");
        	return resMap;
        }
        tUser.setId(id);
        if(StringUtils.isNotBlank(loginPwd)){
            tUser.setDealerNum(loginPwd);
        }
        if(StringUtils.isNotBlank(mobile)){
            tUser.setTel(mobile);
        }
        if(status != null){
            tUser.setAccountStatus(status);
        }
        if(roles == null ||roles.length<1){
        	resMap.put("resCode", "1002");
        	resMap.put("msg", "修改失败,请配置角色");
        	return resMap;
        }
        try{
        	resMap = agencyUserService.updateUser(tUser, roles,sysuser.getDealerLevel());
        }catch(PayPluginException e){
            logger.error(null, e);
        }
        return resMap;
    }
}
