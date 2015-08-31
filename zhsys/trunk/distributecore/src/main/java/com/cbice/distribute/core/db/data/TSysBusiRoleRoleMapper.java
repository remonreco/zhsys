package com.cbice.distribute.core.db.data;

import com.cbice.distribute.core.db.model.TSysBusiRoleRole;
import com.cbice.distribute.core.db.model.TSysBusiRoleRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysBusiRoleRoleMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_busi_role_role
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int countByExample(TSysBusiRoleRoleExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_busi_role_role
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int deleteByExample(TSysBusiRoleRoleExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_busi_role_role
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("busiRoleId") Long busiRoleId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_busi_role_role
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int insert(TSysBusiRoleRole record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_busi_role_role
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int insertSelective(TSysBusiRoleRole record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_busi_role_role
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    List<TSysBusiRoleRole> selectByExample(TSysBusiRoleRoleExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_busi_role_role
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int updateByExampleSelective(@Param("record") TSysBusiRoleRole record, @Param("example") TSysBusiRoleRoleExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_busi_role_role
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int updateByExample(@Param("record") TSysBusiRoleRole record, @Param("example") TSysBusiRoleRoleExample example);
}