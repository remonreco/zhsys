package com.cbice.distribute.core.db.data;

import com.cbice.distribute.core.db.model.TAgencyLevelRole;
import com.cbice.distribute.core.db.model.TAgencyLevelRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAgencyLevelRoleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	int countByExample(TAgencyLevelRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	int deleteByExample(TAgencyLevelRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	int insert(TAgencyLevelRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	int insertSelective(TAgencyLevelRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	List<TAgencyLevelRole> selectByExample(TAgencyLevelRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	TAgencyLevelRole selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	int updateByExampleSelective(@Param("record") TAgencyLevelRole record,
			@Param("example") TAgencyLevelRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	int updateByExample(@Param("record") TAgencyLevelRole record,
			@Param("example") TAgencyLevelRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	int updateByPrimaryKeySelective(TAgencyLevelRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	int updateByPrimaryKey(TAgencyLevelRole record);
	
	int deleteByRoleId(Long role_id);
}