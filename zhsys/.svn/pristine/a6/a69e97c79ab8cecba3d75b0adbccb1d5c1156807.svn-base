package com.cbice.distribute.mgr.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.db.model.HsSystem;
import com.cbice.distribute.core.db.model.TSysRole;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.SysRoleService;
import com.cbice.distribute.mgr.service.SonSysRoleService;

@Controller
public class SonSysRoleController extends BaseController {
	@Resource
	private SonSysRoleService sonSysRoleService;
	
	@Resource
	SeqService seqService;

	public SeqService getSeqService() {
		return seqService;
	}

	public void setSeqService(SeqService seqService) {
		this.seqService = seqService;
	}

	@RequestMapping("/toSonSysRoles")
	public String toSysRoles(Model model) {
		return "sonSysRoles";
	}

	@RequestMapping("/querySonSys")
	@ResponseBody
	public Map<String, Object> querySonSys(HttpServletResponse response, String page, String rows, String sysId, String sysName) {
		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
		int end = intPage * no;
		int start = end - no + 1;
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (sysId != null && !"".equals(sysId)) {
			map.put("sysId", sysId);// 序列号
		}
		if(sysName != null && !"".equals(sysName)){
			map.put("sysName", sysName);
		}
		// 产品编号
		map.put("limit", no);
		map.put("offset", start - 1);

		List<Map> list = sonSysRoleService.sysRoleQueryList(map);

		int count = sonSysRoleService.countsysRoleQueryList(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list);
		jsonmap.put("total", count);
		return jsonmap;
		
	}
	
	
	/**
	 * ajax判断sysName是否可用
	 */
	@RequestMapping(value = "/isSysNameUseful", method = RequestMethod.POST)
	@ResponseBody
	public String iscomNameUseful(HttpServletRequest request, HttpServletResponse response, @RequestBody Map mapx) {
		String sysName = (String) mapx.get("sysName");
		// ****************ajax判断输入的comName是否存在*********************
		HsSystem hsSystem = new HsSystem();

		hsSystem = sonSysRoleService.selectBySysName(sysName);
		List<Map> list = new ArrayList<Map>();
		Map<String, String> map = new HashMap<String, String>();
		if (hsSystem != null) {
			map.put("error", "0");
			list.add(0, map);
		} else {
			map.put("success", "0");
			list.add(0, map);
		}

		JSONArray jsonArray = new JSONArray().fromObject(list);
		return jsonArray.toString();
	}
	
	@RequestMapping(value = "/addSonSys", method = RequestMethod.POST)
	@ResponseBody
	public String addSonSys(HttpServletRequest request, HttpServletResponse response, @RequestBody Map mapx) throws ParseException {
		String sysName = (String) mapx.get("sysName");
		String scheduleTime = (String) mapx.get("scheduleTime");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date data1 = null;
		Date data2 = null;
		//data1 = df.parse(approvalTime);
		data2 =df2.parse(df2.format(new Date()));
		HsSystem hsSystem = new HsSystem();
		hsSystem.setSysName(sysName);
		hsSystem.setScheduleTime(scheduleTime);
		hsSystem.setEditTime(data2);
		hsSystem.setSysId(seqService.getSonSysId()+"");
		
		int m = sonSysRoleService.insertByselective(hsSystem);
		
		List<Map> list = new ArrayList<Map>();
		Map<String, String> map = new HashMap<String, String>();
		if (m == 0) {
			map.put("error", "0");
			list.add(0, map);
		} else {
			map.put("success", "0");
			list.add(0, map);
		}

		JSONArray jsonArray = new JSONArray().fromObject(list);
		return jsonArray.toString();
	}
	
	
	@RequestMapping(value = "/updateSonSys", method = RequestMethod.POST)
	@ResponseBody
	public String updateSonSys(HttpServletRequest request, HttpServletResponse response, @RequestBody Map mapx) throws ParseException {
		String sysName = (String) mapx.get("sysName");
		String sysId = (String) mapx.get("sysId");
		
		HsSystem hsSystem = new HsSystem();
		hsSystem.setSysName(sysName);
		hsSystem.setSysId(sysId);
		
		int m = sonSysRoleService.updateByselective(hsSystem);
		
		List<Map> list = new ArrayList<Map>();
		Map<String, String> map = new HashMap<String, String>();
		if (m == 0) {
			map.put("error", "0");
			list.add(0, map);
		} else {
			map.put("success", "0");
			list.add(0, map);
		}

		JSONArray jsonArray = new JSONArray().fromObject(list);
		return jsonArray.toString();
	}
	
	
}
