package com.cbice.distribute.core.db.data;

import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.VGoodOrderUser;
import com.cbice.distribute.core.db.model.VGoodOrderUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface VGoodOrderUserMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table v_good_order_user
	 * @mbggenerated  Fri Mar 13 13:01:59 CST 2015
	 */
	int countByExample(VGoodOrderUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table v_good_order_user
	 * @mbggenerated  Fri Mar 13 13:01:59 CST 2015
	 */
	int deleteByExample(VGoodOrderUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table v_good_order_user
	 * @mbggenerated  Fri Mar 13 13:01:59 CST 2015
	 */
	int insert(VGoodOrderUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table v_good_order_user
	 * @mbggenerated  Fri Mar 13 13:01:59 CST 2015
	 */
	int insertSelective(VGoodOrderUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table v_good_order_user
	 * @mbggenerated  Fri Mar 13 13:01:59 CST 2015
	 */
	List<VGoodOrderUser> selectByExample(VGoodOrderUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table v_good_order_user
	 * @mbggenerated  Fri Mar 13 13:01:59 CST 2015
	 */
	int updateByExampleSelective(@Param("record") VGoodOrderUser record,
			@Param("example") VGoodOrderUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table v_good_order_user
	 * @mbggenerated  Fri Mar 13 13:01:59 CST 2015
	 */
	int updateByExample(@Param("record") VGoodOrderUser record,
			@Param("example") VGoodOrderUserExample example);

	//产品管理
	List<VGoodOrderUser> selectDealer(VGoodOrderUser goods);
	
	int countSelectDealer(VGoodOrderUser goods);
	
}