package com.cbice.distribute.core.service;

import java.util.Map;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TUser;

public interface TAgencyDbService {
	
	TAgency selectByPrimaryKey(Long id);
	/**
	 * 查询经销商级别
	 * @param record
	 * @return
	 */
	int levelOfAgency(long id);
	
	TAgency selectByAgencyId(long id);

	List<TAgency> selectAgency(TAgency agency);
	int countSelectAgency(TAgency agency);
	
	int	UpdatebyId(TAgency agency);
	
	List<TAgency> selectAgencyBydealerNumAndName(TAgency agency);
	
    /**
     * 生成先序树
     * 
     * @return false：失败；true：成功
     */
    @Transactional
    boolean genPreorder();
    
    TAgency  selectByAgencyNameAndAgnecyName(Map<String,Object> map);
	 
	 int insertSelective(TAgency record);
	 /**
	  * 查询当前登录的下级经销商列表
	  * @param map
	  * @return
	  */
	List<TAgency> selectLowerDealerList(Map<String, Object> map); 
}
