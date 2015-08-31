package com.cbice.distribute.buyer.web.controller.interfaces;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cbice.distribute.buyer.service.HsTransactionRecordService;
import com.cbice.distribute.buyer.service.HsUserRightsService;
import com.cbice.distribute.core.db.model.HsTransactionRecord;
import com.cbice.distribute.core.db.model.HsUserRights;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.StringUtils;
import com.cbice.distribute.core.util.constantList;

/**
 * 账户注册激活
 * @author zj
 * @date 2015年6月19日 下午1:31:14
 * @Description: TODO
 */
@Controller
public class AccountActiveControllerI {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private AccountActiveService accountActiveService;
	
	@Resource
	private HsUserRightsService hsUserRightsService;
	
	@Resource
	private HsTransactionRecordService hsTransactionRecordService;
	
	public void setHsUserRightsService(HsUserRightsService hsUserRightsService) {
		this.hsUserRightsService = hsUserRightsService;
	}
	
	/**
	 * 账号激活第一步
	 * @param request
	 * @param response
	 * @param assetsAccount
	 * @param userName
	 * @param userIdNum
	 * @param userPhone
	 * @return
	 */
	@RequestMapping(value="/accountActiveStep1",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> accountActive(HttpServletRequest request,HttpServletResponse response,String assetsAccount,String userName,String userIdNum ,String userPhone){
		HttpSession session=request.getSession();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		if(StringUtils.isEmpty(assetsAccount)){
			logger.error("资产账号为空！");
			session.setAttribute("activError", "账号激活失败，资产账号为空！");
			jsonMap.put("msg","账号激活失败，资产账号为空！" );
			jsonMap.put("value","0" );
			jsonMap.put("data",maps);
			jsonMap.put("rows",0 );
			return jsonMap;
		}
		if(StringUtils.isEmpty(userName)){
			logger.error("姓名为空！");
			session.setAttribute("activError", "账号激活失败，姓名为空！");
			jsonMap.put("msg","账号激活失败，账号激活失败，姓名为空！" );
			jsonMap.put("value","0" );
			jsonMap.put("data",maps );
			jsonMap.put("rows",0 );
			return jsonMap;
		}
		if(StringUtils.isEmpty(userIdNum)){
			logger.error("身份证号为空！");
			session.setAttribute("activError", "账号激活失败，身份证号为空！");
			jsonMap.put("msg","账号激活失败，账号激活失败，姓名为空！" );
			jsonMap.put("value","0" );
			jsonMap.put("data",maps);
			jsonMap.put("rows",0 );
			return jsonMap;
		}
		if(StringUtils.isEmpty(userPhone)){
			logger.error("电话为空！");
			session.setAttribute("activError", "账号激活失败，电话为空！");
			jsonMap.put("msg","账号激活失败，账号激活失败，姓名为空！" );
			jsonMap.put("value",0 );
			jsonMap.put("data",maps);
			jsonMap.put("rows",0 );
			return jsonMap;
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
			jsonMap.put("msg","账号激活失败，账号激活失败，姓名为空！" );
			jsonMap.put("value","0" );
			jsonMap.put("data",maps);
			jsonMap.put("rows",0 );
			return jsonMap;
		}
		user=list.get(0);
		
		if(list.size()>1||user==null){
			session.setAttribute("activError", "账号激活失败，资产账号信息有误，请检查！");
			jsonMap.put("msg","账号激活失败，账号激活失败，姓名为空！" );
			jsonMap.put("value","0" );
			jsonMap.put("data",maps);
			jsonMap.put("rows",0 );
			return jsonMap;
		}
		String pwd=user.getDealerNum();
		
		if(pwd==null||pwd.equals("")){
			jsonMap.put("msg","账号信息正确，可去输入密码激活账号！" );
			jsonMap.put("value","1" );
			jsonMap.put("data",maps );
			jsonMap.put("rows",0 );
			return jsonMap;
		}
		
		session.setAttribute("activError", "该资产账号已经激活，不能再次激活！");
		jsonMap.put("msg","该资产账号已经激活，不能再次激活！" );
		jsonMap.put("value","0" );
		jsonMap.put("data",maps );
		jsonMap.put("rows",0 );
		return jsonMap;
		
	}
	
	/**
	 *账号激活第二步 
	 * @param request
	 * @param response
	 * @param assetsAccount
	 * @param userPwd
	 * @param userPwd2
	 * @return
	 */
	@RequestMapping(value="/accountActiveStep2",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> accountActivePwd(HttpServletRequest request,HttpServletResponse response,String assetsAccount,String userPwd,String userPwd2){
		HttpSession session=request.getSession();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		if(StringUtils.isEmpty(assetsAccount)){
			logger.error("资产账号为空！");
			session.setAttribute("activError", "账号激活失败，资产账号为空，请检查！");
			jsonMap.put("msg","账号激活失败，资产账号为空，请检查！" );
			jsonMap.put("value","0" );
			jsonMap.put("data",maps);
			jsonMap.put("rows",0 );
			return jsonMap;
		}
		if(StringUtils.isEmpty(userPwd)){
			logger.error("密码为空");
			session.setAttribute("activError", "账号激活失败，密码为空，请检查！");
			jsonMap.put("msg","账号激活失败，密码为空，请检查！" );
			jsonMap.put("value","0" );
			jsonMap.put("data",maps);
			jsonMap.put("rows",0 );
			return jsonMap;
		}
		if(StringUtils.isEmpty(userPwd2)){
			logger.error("确认密码为空！");
			session.setAttribute("activError", "账号激活失败，确认密码为空，请检查！");
			jsonMap.put("msg","账号激活失败，确认密码为空，请检查！" );
			jsonMap.put("value","0" );
			jsonMap.put("data",maps);
			jsonMap.put("rows",0 );
			return jsonMap;
		}
		
		if(!userPwd.equals(userPwd2)){
			logger.error("两次输入密码不一致！");
			session.setAttribute("activError", "账号激活失败，两次输入密码不一致，请检查！");
			jsonMap.put("msg","账号激活失败，两次输入密码不一致，请检查！" );
			jsonMap.put("value","0" );
			jsonMap.put("data",maps);
			jsonMap.put("rows",0 );
			return jsonMap;
		}
		
		userPwd = new Md5PasswordEncoder().encodePassword(userPwd, null);
		
		TUser tUser=new TUser();
		tUser.setAssetsAccount(assetsAccount);
		tUser.setDealerNum(userPwd);
		
		int flag=accountActiveService.updatePwdByAss(tUser);
		
		if(flag>0){
			jsonMap.put("msg","账号激活成功！" );
			jsonMap.put("value","1" );
			jsonMap.put("data",maps);
			jsonMap.put("rows",0 );
			return jsonMap;
		}
		
		session.setAttribute("activError", "账号激活失败，密码操作有误，请检查！");
		jsonMap.put("msg","账号激活失败，密码操作有误，请检查！" );
		jsonMap.put("value","0" );
		jsonMap.put("data",maps );
		jsonMap.put("rows",0 );
		return jsonMap;
	}
	
	/**
	 * 兑换登陆
	 * @param request
	 * @param response
	 * @param assetsAccount
	 * @param userPwd
	 * @return
	 */
	@RequestMapping(value="/doConvertLogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> convertLogin(HttpServletRequest request,HttpServletResponse response,String assetsAccount,String userPwd){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		assetsAccount=assetsAccount.trim(); 
		userPwd=userPwd.trim();
		userPwd = new Md5PasswordEncoder().encodePassword(userPwd, null);
//		HttpSession session=request.getSession();
		
		TUser tUser=new TUser();
		tUser.setAssetsAccount(assetsAccount);
		tUser.setDealerNum(userPwd);
		List<TUser> list=accountActiveService.selectConvertLogin(tUser);
		 if(list.size()<=0){
	        		
//	       session.setAttribute("convertError", "账号未激活，或者登录密码错误，请检查");
	       jsonMap.put("msg","账号未激活，或者登录密码错误，请检查！" );
	       jsonMap.put("value","0" );
	       jsonMap.put("data",maps );
	       jsonMap.put("rows",0 );
	       return jsonMap;
	        	
		 }
		 
//		 session.setAttribute("id", list.get(0).getId());
//		 Long str = (Long) session.getAttribute("id");
//		 System.out.println(session.getAttribute("id"));
//		 session.setAttribute("username", list.get(0).getUserName());
//		 session.setAttribute("assetsAccount", assetsAccount);
		 
		 jsonMap.put("msg","登陆成功！" );
	     jsonMap.put("value","1" );
	     jsonMap.put("data",maps );
	     jsonMap.put("rows",0 );
	     return jsonMap;
	}
	
	/**
	 * 用户注册接口
	 * @param sysId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/userRegisterI",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userRegister(String sysId,String userId){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		Long userId1 = Long.parseLong(userId);
		HsUserRights hsUserRights = new HsUserRights();
		hsUserRights.setSysId(sysId);					//系统ID
		hsUserRights.setCustomerNum(userId1);			//用户ID
		hsUserRights.setRightsCode(constantList.DEF_RIGHT_CODE);			//用户注册默认权益代码为99999999L
		int count = hsUserRightsService.insertSelective(hsUserRights);
		if(count!=1){
			 jsonMap.put("msg","添加新用户失败！" );
		     jsonMap.put("value","0" );
		     jsonMap.put("data",maps );
		     jsonMap.put("rows",0 );
		     return jsonMap;
		}
		jsonMap.put("msg","添加新用户成功！" );
	    jsonMap.put("value","1" );
	    jsonMap.put("data",maps );
	    jsonMap.put("rows",0 );
	    return jsonMap;
	}
	
	/**
	 * 用户权益查询接口
	 * @param sysId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/queryUserRightsI",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryUserRights(HttpServletRequest request,String sysId,String userId,String rows,String pages ){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
		//当前页
		int page=Integer.parseInt((pages.equals("0")) ? "1" : pages);
		//每页显示行数
		int no=Integer.parseInt((rows.equals("0")|| "0".equals(rows)) ? "50" : rows);
		int end = page * no;
		//从第几页开始读取数据
		int start = end - no ;
		
		String userName = request.getParameter("userName");
		String rightsCode1 = request.getParameter("rightsCode");
		String rightsName = request.getParameter("rightsName");
		String transDateStart1 = request.getParameter("transDateStart");
		String transDateEnd1 = request.getParameter("transDateEnd");
		Date transDateStart;
		Date transDateEnd;
		
		if(null!=transDateStart1&&!"".equals(transDateStart1)){
			transDateStart = DateUtils.StringToDate(transDateStart1, "yyyy-MM-dd");
			map.put("transDateStart", transDateStart );
		}
		
		if(null!=transDateEnd1&&!"".equals(transDateEnd1)){
			transDateEnd = DateUtils.StringToDate(transDateEnd1, "yyyy-MM-dd");
			map.put("transDateEnd", transDateEnd );
		}
		
		if(null!=userId&&!"".equals(userId)){
			Long userId1 = Long.parseLong(userId);
			map.put("customerNum", userId1);
		}
		
		if(null!=rightsCode1&&!"".equals(rightsCode1)){
			Long rightsCode = Long.parseLong(rightsCode1);
			map.put("rightsCode", rightsCode);
		}
		if(null==sysId||"".equals(sysId)){
			jsonMap.put("msg","系统ID为空！" );
		    jsonMap.put("value","0" );
		    jsonMap.put("data",maps );
		    jsonMap.put("rows",0 );
		    return jsonMap;
		}
		map.put("sysId", sysId);
		map.put("limit", no);
		map.put("offset", start );
		map.put("customerName", userName );
		map.put("rightsName", rightsName );

		List<HsUserRights> list= hsUserRightsService.queryUserRights(map);
		long totalPages=hsUserRightsService.queryUserRightsCount(map);//共多少条
		maps.put("list", list);
		jsonMap.put("msg","查询用户权益成功！" );
	    jsonMap.put("value","1" );
	    jsonMap.put("data",maps );
	    jsonMap.put("rows",totalPages );
	    
		} catch (Exception e) {
			 jsonMap.put("msg","查询用户权益失败！" );
		     jsonMap.put("value","0" );
		     jsonMap.put("data",maps );
		     jsonMap.put("rows",0 );
		     return jsonMap;
		}
		
	    return jsonMap;
	}
	
	/**
	 * 实时交易记录接口
	 * @return
	 */
	@RequestMapping(value="/nowTransRecordI",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nowTransRecord(HttpServletRequest request){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		try{
		String sysId = request.getParameter("sysId");					//系统ID
		String customerNum = request.getParameter("customerNum");		//客户编号
		String customerName = request.getParameter("customerName");		//客户姓名
		String rightsCode = request.getParameter("rightsCode");			//权益代码
		String rightsName = request.getParameter("rightsName");			//权益名称
		String transTime = request.getParameter("transTime");			//交易时间
		String transType = request.getParameter("transType");			//交易类型
		String useNum = request.getParameter("useNum");					//使用数量
		
		Long customerNum1 = Long.parseLong(customerNum);
		Long rightsCode1 = Long.parseLong(rightsCode);
		int transType1 = Integer.parseInt(transType);
		Long useNum1 = Long.parseLong(useNum);
		Date transTime1 = DateUtils.StringToDate(transTime, "yyyy-MM-dd HH:mm:ss");
		
		HsTransactionRecord hsTransactionRecord = new HsTransactionRecord();
		hsTransactionRecord.setSysId(sysId);
		hsTransactionRecord.setCustomerNum(customerNum1);
		hsTransactionRecord.setCustomerName(customerName);
		hsTransactionRecord.setRightsCode(rightsCode1);
		hsTransactionRecord.setRightsName(rightsName);
		hsTransactionRecord.setTransTime(transTime1);
		hsTransactionRecord.setTransType(transType1);
		hsTransactionRecord.setUseNum(useNum1);
		//新增交易记录
		int result = hsTransactionRecordService.insertSelective(hsTransactionRecord);
		if(result < 1 ){
			 jsonMap.put("msg","新增实时交易记录,数据插入失败！" );
		     jsonMap.put("value","0" );
		     jsonMap.put("data",maps );
		     jsonMap.put("rows",0 );
		     return jsonMap;
		}
		jsonMap.put("msg","新增实时交易记录成功！" );
	    jsonMap.put("value","1" );
	    jsonMap.put("data",maps );
	    jsonMap.put("rows",0 );

		} catch (Exception e) {
			 jsonMap.put("msg","操作失败！" );
		     jsonMap.put("value","0" );
		     jsonMap.put("data",maps );
		     jsonMap.put("rows",0 );
		     return jsonMap;
		}
		return jsonMap;
	}
}
