package com.cbice.distribute.mgr.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.mgr.security.model.UserDetailsImpl;
import com.cbice.distribute.mgr.service.AccessGoldSerice;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月11日 下午2:08:14
 * @describe 出入金查询
 */

@Controller
public class AccessGoldController extends BaseController{
	
	@Resource				  
	private AccessGoldSerice  accessGoldSerice;
	
	@RequestMapping("/toAccessGoldQuery")
	public String toAccessGoldQuery(){
		return "toAccessGoldQuery";
	}

	@RequestMapping("/queryAccessGold")
	public @ResponseBody Map<String, Object> queryAgencyBusiRoles(HttpServletResponse response,String page, String rows,String developer,String clientId,
			String initDateStart, String initDateEnd){
		
		response.setContentType("text/html;charset=utf-8");
		
		   // 当前页
        int intPage = Integer.parseInt((page == null) ? "1" : page);
        // 每页显示行数
        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
        int end = intPage * no;
        int start = end - no + 1;
        int initDateStart1 =0 ;
		int initDateEnd1 =0;
		if (initDateStart != null && !initDateStart.equals("")) {
			initDateStart1 = StringToNum(initDateStart);
		}
		if (initDateEnd != null && !initDateEnd.equals("")) {
			initDateEnd1 = StringToNum(initDateEnd);
		}
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("developer",developer);
        map.put("clientId", clientId);
        map.put("initDateStart", initDateStart1);
		map.put("initDateEnd", initDateEnd1);
		map.put("limit", no);
		map.put("offset", start - 1);
		
		
		


//		List<Map<String, Object>> accessGoldList1 = accessGoldSerice.
		
		
//		if (end > list1.size()) {
//			end = list1.size();
//			start = end - no + 1;
//			map.put("offset", start - 1);
//		}
//
//		int count = tHisOtcrealtimeService.countSelectOrderBy(map);
//		Map<String, Object> jsonmap = new HashMap<String, Object>();
//		jsonmap.put("rows", list1);
//		jsonmap.put("total", count);
       
		return null;
	}
	
	/**
	 * 将String型的标准数据转换成Integer
	 * 
	 * @param t
	 * @return
	 */
	public Integer StringToNum(String t) {
		return Integer.parseInt(t.replace("-", ""));
	}
}
