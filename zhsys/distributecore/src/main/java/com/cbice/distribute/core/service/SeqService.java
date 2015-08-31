package com.cbice.distribute.core.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Richard Xiong
 * 序列号生成器
 */
public interface SeqService {

	/**
	 * 合作伙伴商户的唯一订单号
	 * @return
	 */
	@Transactional
	String genOutTradeNo();
	/**
	 * torder 表id
	 * @return
	 */
	@Transactional
	long getOrderId();
	/**
     * t_sys_user表id
     * @return
     */
    @Transactional
    long getSysUserId();
    
    /**
     * t_sys_busi_rolo表id
     * @return
     */
    @Transactional
    long getSysBusiRoleId();
    /**
     * 用户表ID
     * @return
     */
    @Transactional
    long getTUserId();
    
    /**
     * 产品表ID
     * @return
     */
    @Transactional
    long getTGoodsId();
    
    /**
     * 产品订单表ID
     * @return
     */
    @Transactional
    long getTGoodsOrder();
	
    /**
     * 发货批次号
     * @return
     */
    @Transactional
    long getTGoodsBatch();
    
    
    /**
     * t_agency_busi_rolo表id
     * @return
     */
    @Transactional
    long getAgencyBusiRoleId();
    
    /**
     * t_sys_user表id
     * @return
     */
    @Transactional
    long getUserId();
    
    /**
     * 发货批次号
     * @return
     */
    @Transactional
    long getTRemainderGoods();
    
    @Transactional
    long getTAgentId();
    
    /**t_agency_level_role 表主键ID
     * @return
     */
    @Transactional
    long getT_Agency_Level_Role();
    
    /**
     * hs_exchange_order 表id
     * 
     */
    @Transactional
    long getExchangeOrderId();
    
    
    @Transactional
    String getSonSysId();
    
}
