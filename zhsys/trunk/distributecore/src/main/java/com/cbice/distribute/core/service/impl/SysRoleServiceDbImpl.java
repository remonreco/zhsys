package com.cbice.distribute.core.service.impl;

import java.util.List;

import com.cbice.distribute.core.db.data.TSysRoleMapper;
import com.cbice.distribute.core.db.model.TSysRole;
import com.cbice.distribute.core.db.model.TSysRoleExample;
import com.cbice.distribute.core.service.SysRoleService;

/**
 * @author Richard Xiong
 * 系统角色服务数据库实现
 */
public class SysRoleServiceDbImpl implements SysRoleService{
    private TSysRoleMapper tSysRoleMapper;

    public void settSysRoleMapper(TSysRoleMapper tSysRoleMapper){
        this.tSysRoleMapper = tSysRoleMapper;
    }

    @Override
    public List<TSysRole> selectByUserId(Long userId){
        List<TSysRole> tSysRoles = tSysRoleMapper.selectByUserId(userId);
        return tSysRoles;
    }

    @Override
    public List<TSysRole> selectAll(){
        TSysRoleExample example = new TSysRoleExample();
        example.setOrderByClause("name");
        List<TSysRole> tSysRoles = tSysRoleMapper.selectByExample(example);
        return tSysRoles;
    }
}
