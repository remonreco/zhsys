package com.cbice.distribute.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cbice.distribute.core.db.data.TSysBusiRoleMapper;
import com.cbice.distribute.core.db.data.TSysBusiRoleRoleMapper;
import com.cbice.distribute.core.db.data.TSysRoleMapper;
import com.cbice.distribute.core.db.model.TSysBusiRole;
import com.cbice.distribute.core.db.model.TSysBusiRoleBean;
import com.cbice.distribute.core.db.model.TSysBusiRoleExample;
import com.cbice.distribute.core.db.model.TSysBusiRoleExample.Criteria;
import com.cbice.distribute.core.db.model.TSysBusiRoleRole;
import com.cbice.distribute.core.db.model.TSysBusiRoleRoleExample;
import com.cbice.distribute.core.db.model.TSysRole;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.SysBusiRoleService;
import com.cbice.distribute.core.util.PayPluginException;

/**
 * @author Richard Xiong 系统业务角色服务数据库实现
 */
public class SysBusiRoleServiceDbImpl implements SysBusiRoleService{
    private TSysBusiRoleMapper tSysBusiRoleMapper;

    private TSysRoleMapper tSysRoleMapper;

    private TSysBusiRoleRoleMapper tSysBusiRoleRoleMapper;

    private SeqService seqService;

    public void settSysBusiRoleMapper(TSysBusiRoleMapper tSysBusiRoleMapper){
        this.tSysBusiRoleMapper = tSysBusiRoleMapper;
    }

    public void settSysRoleMapper(TSysRoleMapper tSysRoleMapper){
        this.tSysRoleMapper = tSysRoleMapper;
    }

    public void settSysBusiRoleRoleMapper(TSysBusiRoleRoleMapper tSysBusiRoleRoleMapper){
        this.tSysBusiRoleRoleMapper = tSysBusiRoleRoleMapper;
    }

    public void setSeqService(SeqService seqService){
        this.seqService = seqService;
    }

    @Override
    public List<TSysBusiRole> selectAll(){
        TSysBusiRoleExample example = new TSysBusiRoleExample();
        example.setOrderByClause("name");
        List<TSysBusiRole> tSysBusiRoles = tSysBusiRoleMapper.selectByExample(example);
        return tSysBusiRoles;
    }

    @Override
    public List<TSysBusiRole> selectByUserId(Long userId){
        List<TSysBusiRole> tSysBusiRoles = tSysBusiRoleMapper.selectByUserId(userId);
        return tSysBusiRoles;
    }

    @Override
    public int countByCondition(Map<String, Object> map){
        if(map == null){
            return 0;
        }
        return tSysBusiRoleMapper.countByCondition(map);
    }

    @Override
    public List<TSysBusiRoleBean> selectByCondition(Map<String, Object> map){
        if(map == null){
            return null;
        }
        List<TSysBusiRole> tSysBusiRoles = tSysBusiRoleMapper.selectByCondition(map);
        List<TSysBusiRoleBean> tSysBusiRoleBeans = new ArrayList<TSysBusiRoleBean>();
        for (TSysBusiRole tSysBusiRole : tSysBusiRoles){
            TSysBusiRoleBean tSysBusiRoleBean = new TSysBusiRoleBean();
            BeanUtils.copyProperties(tSysBusiRole, tSysBusiRoleBean);
            List<TSysRole> tSysRoles = tSysRoleMapper.selectByBusiRoleId(tSysBusiRole.getId());
            tSysBusiRoleBean.settSysRoles(tSysRoles);
            tSysBusiRoleBeans.add(tSysBusiRoleBean);
        }
        return tSysBusiRoleBeans;
    }

    @Override
    public String checkDuplicateSysBusiRoleName(Long id, String name){
        if(StringUtils.isBlank(name)){
            return null;
        }
        TSysBusiRoleExample example = new TSysBusiRoleExample();
        Criteria criteria = example.createCriteria();
        if(id != null){
            criteria.andIdNotEqualTo(id);
        }
        criteria.andNameEqualTo(name);
        int count = tSysBusiRoleMapper.countByExample(example);
        return count == 0 ? "0" : "1";
    }

    @Override
    public int insertBusiRole(TSysBusiRole tSysBusiRole, Long[] roleIds) throws PayPluginException{
        if(tSysBusiRole == null || roleIds == null){
            return 0;
        }
        long busiRoleId = seqService.getSysBusiRoleId();
        tSysBusiRole.setId(busiRoleId);
        int res = tSysBusiRoleMapper.insertSelective(tSysBusiRole);
        if(res == 0){
            throw new PayPluginException("添加业务角色失败");
        }
        TSysBusiRoleRole tSysBusiRoleRole = new TSysBusiRoleRole();
        tSysBusiRoleRole.setBusiRoleId(tSysBusiRole.getId());
        for (Long roleId : roleIds){
            tSysBusiRoleRole.setRoleId(roleId);
            res = tSysBusiRoleRoleMapper.insert(tSysBusiRoleRole);
            if(res == 0){
                throw new PayPluginException("添加角色关联失败");
            }
        }
        return 1;
    }

    @Override
    public int updateBusiRole(TSysBusiRole tSysBusiRole, Long[] roleIds) throws PayPluginException{
        if(tSysBusiRole == null || roleIds == null){
            return 0;
        }
        int res = tSysBusiRoleMapper.updateByPrimaryKeySelective(tSysBusiRole);
        if(res == 0){
            throw new PayPluginException("修改账户信息失败");
        }
        TSysBusiRoleRoleExample example = new TSysBusiRoleRoleExample();
        com.cbice.distribute.core.db.model.TSysBusiRoleRoleExample.Criteria criteria = example.createCriteria();
        criteria.andBusiRoleIdEqualTo(tSysBusiRole.getId());
        tSysBusiRoleRoleMapper.deleteByExample(example);
        TSysBusiRoleRole tSysBusiRoleRole = new TSysBusiRoleRole();
        tSysBusiRoleRole.setBusiRoleId(tSysBusiRole.getId());
        for (Long roleId : roleIds){
            tSysBusiRoleRole.setRoleId(roleId);
            res = tSysBusiRoleRoleMapper.insert(tSysBusiRoleRole);
            if(res == 0){
                throw new PayPluginException("添加角色关联失败");
            }
        }
        return 1;
    }
}
