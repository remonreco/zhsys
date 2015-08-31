package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.model.TSysBusiRole;
import com.cbice.distribute.core.db.model.TSysBusiRoleBean;
import com.cbice.distribute.core.util.PayPluginException;

/**
 * @author Richard Xiong
 * 系统业务角色表服务
 */
public interface SysBusiRoleService {
	
	/**
	 * 获取所有系统业务角色
	 * @return
	 */
    List<TSysBusiRole> selectAll();
    
    /**
     * 根据用户标识查询关联的业务角色
     * @param userId
     * @return
     */
    List<TSysBusiRole> selectByUserId(Long userId);
    
    /**
     * 根据条件查询业务角色数量
     * @param map 查询条件
     * @return 业务角色数量
     */
    int countByCondition(Map<String, Object> map);
    
    /**
     * 根据条件分页查询业务角色列表
     * @param map 分页查询条件
     * @return 业务角色列表
     */
    List<TSysBusiRoleBean> selectByCondition(Map<String, Object> map);
    
    /**
     * 添加业务角色、角色关联
     * @param tSysBusiRole 业务角色
     * @param roleIds 关联的角色标识
     * @return 响应，1：成功，其他：失败
     */
    @Transactional
    int insertBusiRole(TSysBusiRole tSysBusiRole, Long[] roleIds) throws PayPluginException;
    
    /**
     * 验证业务角色名是否重复
     * @param id
     * @param name
     * @return
     */
    String checkDuplicateSysBusiRoleName(Long id, String name);
    
    /**
     * 修改业务角色、角色关联
     * @param tSysBusiRole
     * @return
     */
    int updateBusiRole(TSysBusiRole tSysBusiRole, Long[] roleIds) throws PayPluginException;;
}
