package com.cbice.distribute.mgr.web.controller;

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

import com.cbice.distribute.core.db.model.TSysBusiRole;
import com.cbice.distribute.core.db.model.TSysUser;
import com.cbice.distribute.core.db.model.TSysUserBean;
import com.cbice.distribute.core.service.SysBusiRoleService;
import com.cbice.distribute.core.service.SysUserService;
import com.cbice.distribute.core.util.PayPluginException;

@Controller
public class SysUserController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysBusiRoleService sysBusiRoleService;
    
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
        TSysUser tSysUser = this.getUserInfo().gettSysUser();
        String originPwdDigest = new Md5PasswordEncoder().encodePassword(originPwd, null);
        if(!StringUtils.equals(originPwdDigest, tSysUser.getLoginPwd())){
            return "原密码输入不正确";
        }
        String newPwdDigest = new Md5PasswordEncoder().encodePassword(newPwd, null);
        TSysUser tSysUser2 = new TSysUser();
        tSysUser2.setId(tSysUser.getId());
        tSysUser2.setLoginPwd(newPwdDigest);
        if(sysUserService.updateByPrimaryKeySelective(tSysUser2) > 0){
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
        List<TSysBusiRole> tSysBusiRoles = sysBusiRoleService.selectAll();
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

        if(StringUtils.isNotEmpty(status) && NumberUtils.isNumber(status)){
            map.put("status", NumberUtils.createInteger(status));
        }
        if(StringUtils.isNotEmpty(loginName)){
            map.put("loginName", loginName);
        }
        if(StringUtils.isNotEmpty(roles)){
            List<Long> list = new ArrayList<Long>();
            String[] strings = roles.split(",");
            for (String role : strings){
                if(NumberUtils.isNumber(role)){
                    list.add(NumberUtils.createLong(role));
                }
            }
            map.put("roles", list);
        }
        int count = sysUserService.countByCondition(map);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("total", count);
        map.put("limit", no);
        map.put("offset", start);
        List<TSysUserBean> tSysUserBeans = sysUserService.selectByCondition(map);
        jsonMap.put("rows", tSysUserBeans);
        return jsonMap;
    }

    @RequestMapping("/checkDuplicateSysUserLoginName")
    public @ResponseBody String checkDuplicateSysUserLoginName(String loginName){
        return sysUserService.checkDuplicateSysUserLoginName(loginName);
    }

    @RequestMapping("/addSysUser")
    public @ResponseBody int addUser(String loginName, String realName, String loginPwd, String email, String mobile, Long[] roles){
        int result = 0;
        TSysUser tSysUser = new TSysUser();
        if(StringUtils.isNotBlank(loginName)){
            tSysUser.setLoginName(loginName);
        }
        if(StringUtils.isNotBlank(realName)){
            tSysUser.setRealName(realName);
        }
        if(StringUtils.isNotBlank(loginPwd)){
            tSysUser.setLoginPwd(loginPwd);
        }
        if(StringUtils.isNotBlank(email)){
            tSysUser.setEmail(email);
        }
        if(StringUtils.isNotBlank(mobile)){
            tSysUser.setMobile(mobile);
        }
        tSysUser.setLastModSysUserId(getUserInfo().gettSysUser().getId());
        try{
            result = sysUserService.insertUser(tSysUser, roles);
        }catch(PayPluginException e){
            logger.error("exception", e);
            return -1;
        }
        return result;
    }

    @RequestMapping("/updateSysUser")
    public @ResponseBody int updateUser(Long id, String loginPwd, String email, String mobile, Long[] roles, Integer status){
        int result = 0;
        TSysUser tSysUser = new TSysUser();
        tSysUser.setId(id);
        if(StringUtils.isNotBlank(loginPwd)){
            tSysUser.setLoginPwd(loginPwd);
        }
        if(StringUtils.isNotBlank(email)){
            tSysUser.setEmail(email);
        }
        if(StringUtils.isNotEmpty(mobile)){
            tSysUser.setMobile(mobile);
        }
        if(status != null){
            tSysUser.setStatus(status);
        }
        tSysUser.setLastModSysUserId(getUserInfo().gettSysUser().getId());
        try{
            result = sysUserService.updateUser(tSysUser, roles);
        }catch(PayPluginException e){
            logger.error(null, e);
            return -1;
        }
        return result;
    }
}
