package com.cbice.distribute.core.db.data;

import com.cbice.distribute.core.db.model.TAgencyUserBusiRole;
import com.cbice.distribute.core.db.model.TAgencyUserBusiRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAgencyUserBusiRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    int countByExample(TAgencyUserBusiRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    int deleteByExample(TAgencyUserBusiRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("busiRoleId") Long busiRoleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    int insert(TAgencyUserBusiRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    int insertSelective(TAgencyUserBusiRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    List<TAgencyUserBusiRole> selectByExample(TAgencyUserBusiRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    int updateByExampleSelective(@Param("record") TAgencyUserBusiRole record, @Param("example") TAgencyUserBusiRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    int updateByExample(@Param("record") TAgencyUserBusiRole record, @Param("example") TAgencyUserBusiRoleExample example);
    
    int deleteByBusiRoleId(Long busiRoleId);
}