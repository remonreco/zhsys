package com.cbice.distribute.mgr.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.service.CommodityRuleDbservice;
import com.cbice.distribute.core.service.HsCommodityDbservice;
import com.cbice.distribute.mgr.service.TCommodityService;

public class TCommodityServiceImpl implements TCommodityService {
	private HsCommodityDbservice hsCommodityDbservice;
	@Resource
	private CommodityRuleDbservice commodityRuleDbservice;

	public void setCommodityRuleDbservice(CommodityRuleDbservice commodityRuleDbservice) {
		this.commodityRuleDbservice = commodityRuleDbservice;
	}

	public void setHsCommodityDbservice(HsCommodityDbservice hsCommodityDbservice) {
		this.hsCommodityDbservice = hsCommodityDbservice;
	}

	@Override
	public List<Map> commodityQueryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map> list = hsCommodityDbservice.commodityQueryList(map);
		for (Map map1 : list) {
			int state = (Integer) map1.get("exchange_state");
			if (state == 1) {
				map1.put("exchange_state", "启动");
			} else if (state == 2) {
				map1.put("exchange_state", "不启动");
			} else if (state == 3) {
				map1.put("exchange_state", "暂停");
			} else {
				map1.put("exchange_state", "其他");
			}
		}
		return list;
	}

	@Override
	public int countCommodityQueryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityDbservice.countCommodityQueryList(map);
	}

	@Override
	public List<Map> queryLoadItemName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityDbservice.queryLoadItemName(map);
	}

	@Override
	public List<Map> queryLoadComName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityDbservice.queryLoadComName(map);
	}

	@Override
	public HsCommodity selectByPrimaryKey(Long comId,String sysId) {
		// TODO Auto-generated method stub
		return hsCommodityDbservice.selectByPrimaryKey(comId,sysId);
	}

	@Override
	public HsCommodity selectBycomName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityDbservice.selectBycomName(map);
	}

	@Override
	public HsCommodity queryexchangeId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityDbservice.queryexchangeId(map);
	}

	@Transactional
	@Override
	public int inserAll(Map<String, Object> map, Map<String, Object> mapr0, Map<String, Object> mapr1, Map<String, Object> mapr2, Map<String, Object> mapr3, Map<String, Object> mapr4) {
		// TODO Auto-generated method stub
		int result = 0;
		int result1 = 0;
		if (map != null && map.size() > 0) {
			result1 = hsCommodityDbservice.insertNewComdity(map);
			if (result1 < 1) {
				return -1;
			}
		}
		if (mapr0 != null && mapr0.size() > 0) {
			result1 = commodityRuleDbservice.insertNewComdityRole(mapr0);
			if (result1 < 1) {
				return -1;
			}
		}
		if (mapr1 != null && mapr1.size() > 0) {
			result1 = commodityRuleDbservice.insertNewComdityRole(mapr1);
			if (result1 < 1) {
				return -1;
			}
		}
		if (mapr2 != null && mapr2.size() > 0) {
			result1 = commodityRuleDbservice.insertNewComdityRole(mapr2);
			if (result1 < 1) {
				return -1;
			}
		}
		if (mapr3 != null && mapr3.size() > 0) {
			result1 = commodityRuleDbservice.insertNewComdityRole(mapr3);
			if (result1 < 1) {
				return -1;
			}
		}
		if (mapr4 != null && mapr4.size() > 0) {
			result1 = commodityRuleDbservice.insertNewComdityRole(mapr4);
			if (result1 < 1) {
				return -1;
			}
		}

		return result;
	}
	
	@Override
	public List<Map<String, Object>> exchangeRuleQuery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = hsCommodityDbservice.exchangeRuleQuery(map);
		for(Map<String, Object> map1:list){
			if(((Integer)(map1.get("com_type"))==1)){
				map1.put("com_type", "普通商品");
			}else if(((Integer)(map1.get("com_type"))==2)){
				map1.put("com_type", "赠品");
			}else{
				map1.put("com_type", "其他");
			}
		}
		return list;
	}

	@Override
	public int deleteByPrimaryKey(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityDbservice.deleteByPrimaryKey(map);
	}
	

	@Override
	public int updateByPrimaryKey(HsCommodity hsCommodity) {
		// TODO Auto-generated method stub
		return hsCommodityDbservice.updateByPrimaryKey(hsCommodity);
	}

	@Override
	public int updateByPrimaryKeySelective(HsCommodity hsCommodity) {
		// TODO Auto-generated method stub
		return hsCommodityDbservice.updateByPrimaryKeySelective(hsCommodity);
	}


}
