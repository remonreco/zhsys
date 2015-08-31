package com.cbice.distribute.core.db.data;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cbice.distribute.core.db.model.TSysUser;
import com.cbice.distribute.core.db.model.TSysUserExample;

public interface TSysUserMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_user
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int countByExample(TSysUserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_user
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int deleteByExample(TSysUserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_user
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_user
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int insert(TSysUser record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_user
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int insertSelective(TSysUser record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_user
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    List<TSysUser> selectByExample(TSysUserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_user
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    TSysUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_user
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int updateByExampleSelective(@Param("record") TSysUser record, @Param("example") TSysUserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_user
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int updateByExample(@Param("record") TSysUser record, @Param("example") TSysUserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_user
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int updateByPrimaryKeySelective(TSysUser record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_sys_user
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    int updateByPrimaryKey(TSysUser record);
    
    int countByCondition(Map<String, Object> map);
    
    List<TSysUser> selectByCondition(Map<String, Object> map);
}