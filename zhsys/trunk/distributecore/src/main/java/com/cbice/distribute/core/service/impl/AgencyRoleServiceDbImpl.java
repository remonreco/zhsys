package com.cbice.distribute.core.service.impl;

import java.util.List;

import com.cbice.distribute.core.db.data.TAgencyRoleMapper;
import com.cbice.distribute.core.db.model.TAgencyRole;
import com.cbice.distribute.core.db.model.TAgencyRoleExample;
import com.cbice.distribute.core.service.AgencyRoleService;

/**
 * @author Richard Xiong
 * 系统角色服务数据库实现
 */
public class AgencyRoleServiceDbImpl implements AgencyRoleService{
    private TAgencyRoleMapper tAgencyRoleMapper;

    public void settAgencyRoleMapper(TAgencyRoleMapper tAgencyRoleMapper){
        this.tAgencyRoleMapper = tAgencyRoleMapper;
    }

    @Override
    public List<TAgencyRole> selectByUserId(Long userId){
        List<TAgencyRole> tAgencyRoles = tAgencyRoleMapper.selectByUserId(userId);
        return tAgencyRoles;
    }

    @Override
    public List<TAgencyRole> selectAll(){
        TAgencyRoleExample example = new TAgencyRoleExample();
        example.setOrderByClause("name");
        List<TAgencyRole> tAgencyRoles = tAgencyRoleMapper.selectByExample(example);
        return tAgencyRoles;
    }
}
