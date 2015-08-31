package com.cbice.distribute.mgr.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbice.distribute.core.db.model.TSysUser;
import com.cbice.distribute.mgr.service.WebSysUserService;
//import javax.servlet.http.HttpServletRequest;
//import com.buybal.p2p.security.model.RoleDetailsImpl;
//import com.buybal.web.service.RoleService;

@Controller
public class MainControlller{
    @Resource
    private WebSysUserService webSysUserService; 
    
    @RequestMapping("/main")
    public String toMain(Model model,HttpServletRequest request ){
        TSysUser tSysUser = webSysUserService.getLoginSysUser();
        if(null != tSysUser && StringUtils.isNotBlank(tSysUser.getRealName())){
        	request.getSession().setAttribute("username", tSysUser.getRealName());
            model.addAttribute("username", tSysUser.getRealName());
        }
        return "main";
    }
}
