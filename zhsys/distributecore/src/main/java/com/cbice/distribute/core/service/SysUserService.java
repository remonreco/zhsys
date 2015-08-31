package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.model.TSysUser;
import com.cbice.distribute.core.db.model.TSysUserBean;
import com.cbice.distribute.core.util.PayPluginException;

/**
 * @author Richard Xiong
 * 系统用户表服务
 */
public interface SysUserService {
    TSysUser selectById(Long id);
    
    /**
     * 通过登录名查询用户信息
     * @param loginName 登录名Long
     * @return 用户信息
     */
	TSysUser selectSysUserByLoginName(String loginName);
	
	/**
	 * 根据条件查询系统用户数量
	 * @param map 查询条件
	 * @return 系统用户数量
	 */
	int countByCondition(Map<String, Object> map);
	
	/**
	 * 根据条件查询系统用户列表
     * @param map 查询条件
	 * @return 系统用户列表
	 */
	List<TSysUserBean> selectByCondition(Map<String, Object> map);
	
	/**
     * 验证登录名是否重复
     * @param loginName
     * @return 1 存在 0 不存在
     */
    String checkDuplicateSysUserLoginName(String loginName);
    /**
     * 添加操作员、业务角色关联
     * @param user 操作员
     * @param busiRolesIds 业务角色标识列表
     * @return 结果，1：成功；其他：失败
     * @throws PayPluginException
     */
    @Transactional
    int insertUser(TSysUser tSysUser, Long[] busiRolesIds) throws PayPluginException;
    
    /**
     * 修改操作员信息
     * @param user
     * @param roles
     * @return
     */
    @Transactional
    int updateUser(TSysUser tSysUser, Long[] roles) throws PayPluginException;
    
    int updateByPrimaryKeySelective(TSysUser tSysUser);
}
