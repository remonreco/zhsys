package com.cbice.distribute.agency.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbice.distribute.agency.service.WebSysUserService;
//import javax.servlet.http.HttpServletRequest;
//import com.buybal.p2p.security.model.RoleDetailsImpl;
//import com.buybal.web.service.RoleService;
import com.cbice.distribute.core.db.model.TUser;

@Controller
public class MainControlller{
    @Resource
    private WebSysUserService webSysUserService; 
    
    @RequestMapping("/main")
    public String toMain(Model model,HttpServletRequest request ){
        TUser tUser = webSysUserService.getLoginSysUser();
        if(null != tUser && StringUtils.isNotEmpty(tUser.getDealerName())){
        	request.getSession().setAttribute("username", tUser.getDealerName());
            model.addAttribute("username", tUser.getDealerName());
        }
        return "main";
    }
}