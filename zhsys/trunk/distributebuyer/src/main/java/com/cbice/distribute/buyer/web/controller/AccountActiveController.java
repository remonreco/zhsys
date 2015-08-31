package com.cbice.distribute.buyer.web.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.buyer.service.AccountActiveService;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.util.StringUtils;

@Controller
public class AccountActiveController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private AccountActiveService accountActiveService;
	
	
	@RequestMapping(value="/accActiveStep1",method=RequestMethod.POST)
	public String accountActive(HttpServletRequest request,HttpServletResponse response,String assetsAccount,String userName,String userIdNum ,String userPhone){
		HttpSession session=request.getSession();
		
		if(StringUtils.isEmpty(assetsAccount)){
			logger.error("资产账号为空！");
			session.setAttribute("activError", "账号激活失败，资产账号为空！");
			return "accountFailed";
		}
		if(StringUtils.isEmpty(userName)){
			logger.error("姓名为空！");
			session.setAttribute("activError", "账号激活失败，姓名为空！");
			return "accountFailed";
		}
		if(StringUtils.isEmpty(userIdNum)){
			logger.error("身份证号为空！");
			session.setAttribute("activError", "账号激活失败，身份证号为空！");
			return "accountFailed";
		}
		if(StringUtils.isEmpty(userPhone)){
			logger.error("电话为空！");
			session.setAttribute("activError", "账号激活失败，电话为空！");
			return "accountFailed";
		}
		
		TUser user =new TUser();
		user.setAssetsAccount(assetsAccount);
		user.setUserName(userName);
		user.setCertificateNum(userIdNum);
		user.setTel(userPhone);
		session.setAttribute("assetsAccount", assetsAccount);
		
		List<TUser> list=accountActiveService.selectAllToConvert(user);
		
		if(list.size()<=0){
			session.setAttribute("activError", "账号激活失败，资产账号信息不存在，请检查！");
			return "accountFailed";
		}
		user=list.get(0);
		
		if(list.size()>1||user==null){
			session.setAttribute("activError", "账号激活失败，资产账号信息有误，请检查！");
			return "accountFailed";
		}
		String pwd=user.getDealerNum();
		
		if(pwd==null||pwd.equals("")){
			return "accountActivePwd";
		}
		
		session.setAttribute("activError", "该资产账号已经激活，不能再次激活！");
		return "accountFailed";
		
	}
	
	@RequestMapping(value="/accActiveStep2",method=RequestMethod.POST)
	public String accountActivePwd(HttpServletRequest request,HttpServletResponse response,String assetsAccount,String userPwd,String userPwd2){
		HttpSession session=request.getSession();
		
		if(StringUtils.isEmpty(assetsAccount)){
			logger.error("资产账号为空！");
			session.setAttribute("activError", "账号激活失败，资产账号为空，请检查！");
			return "accountFailed";
		}
		if(StringUtils.isEmpty(userPwd)){
			logger.error("密码为空");
			session.setAttribute("activError", "账号激活失败，密码为空，请检查！");
			return "accountFailed";
		}
		if(StringUtils.isEmpty(userPwd2)){
			logger.error("确认密码为空！");
			session.setAttribute("activError", "账号激活失败，确认密码为空，请检查！");
			return "accountFailed";
		}
		
		if(!userPwd.equals(userPwd2)){
			logger.error("两次输入密码不一致！");
			session.setAttribute("activError", "账号激活失败，两次输入密码不一致，请检查！");
			return "accountFailed";
		}
		
		userPwd = new Md5PasswordEncoder().encodePassword(userPwd, null);
		
		TUser tUser=new TUser();
		tUser.setAssetsAccount(assetsAccount);
		tUser.setDealerNum(userPwd);
		
		int flag=accountActiveService.updatePwdByAss(tUser);
		
		if(flag>0){
			return "accountOk";
		}
		
		session.setAttribute("activError", "账号激活失败，密码操作有误，请检查！");
		return "accountFailed";
	}
	
	@RequestMapping(value="/convertLogin",method=RequestMethod.POST)
	public String convertLogin(HttpServletRequest request,HttpServletResponse response,String assetsAccount,String userPwd){
		assetsAccount=assetsAccount.trim();
		userPwd=userPwd.trim();
		userPwd = new Md5PasswordEncoder().encodePassword(userPwd, null);
		HttpSession session=request.getSession();
		
		TUser tUser=new TUser();
		tUser.setAssetsAccount(assetsAccount);
		tUser.setDealerNum(userPwd);
		List<TUser> list=accountActiveService.selectConvertLogin(tUser);
		 if(list.size()<=0){
	        		
	       session.setAttribute("convertError", "账号未激活，或者登录密码错误，请检查");
	        return "redirect:convert.jsp";
	        	
		 }
		 
		 session.setAttribute("id", list.get(0).getId());
		 System.out.println(list.get(0).getId());
		 session.setAttribute("username", list.get(0).getUserName());
		 session.setAttribute("assetsAccount", assetsAccount);
		 return "convertList";
	}
	
	
	
}
