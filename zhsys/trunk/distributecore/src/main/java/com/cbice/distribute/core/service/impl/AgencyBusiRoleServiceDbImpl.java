package com.cbice.distribute.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.data.TAgencyBusiRoleMapper;
import com.cbice.distribute.core.db.data.TAgencyBusiRoleRoleMapper;
import com.cbice.distribute.core.db.data.TAgencyLevelRoleMapper;
import com.cbice.distribute.core.db.data.TAgencyRoleMapper;
import com.cbice.distribute.core.db.data.TAgencyUserBusiRoleMapper;
import com.cbice.distribute.core.db.model.TAgencyBusiRole;
import com.cbice.distribute.core.db.model.TAgencyBusiRoleBean;
import com.cbice.distribute.core.db.model.TAgencyBusiRoleExample;
import com.cbice.distribute.core.db.model.TAgencyBusiRoleExample.Criteria;
import com.cbice.distribute.core.db.model.TAgencyBusiRoleRole;
import com.cbice.distribute.core.db.model.TAgencyBusiRoleRoleExample;
import com.cbice.distribute.core.db.model.TAgencyLevelRole;
import com.cbice.distribute.core.db.model.TAgencyRole;
import com.cbice.distribute.core.service.AgencyBusiRoleService;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.util.PayPluginException;

/**
 * @author Richard Xiong 系统业务角色服务数据库实现
 */
public class AgencyBusiRoleServiceDbImpl implements AgencyBusiRoleService{
    private TAgencyBusiRoleMapper tAgencyBusiRoleMapper;

    private TAgencyRoleMapper tAgencyRoleMapper;

    private TAgencyBusiRoleRoleMapper tAgencyBusiRoleRoleMapper;
    
    private TAgencyLevelRoleMapper tAgencyLevelRoleMapper;
    
    private TAgencyUserBusiRoleMapper tAgencyUserBusiRoleMapper;

    private SeqService seqService;

    public void settAgencyBusiRoleMapper(TAgencyBusiRoleMapper tAgencyBusiRoleMapper){
        this.tAgencyBusiRoleMapper = tAgencyBusiRoleMapper;
    }

    public void settAgencyRoleMapper(TAgencyRoleMapper tAgencyRoleMapper){
        this.tAgencyRoleMapper = tAgencyRoleMapper;
    }

    public void settAgencyBusiRoleRoleMapper(TAgencyBusiRoleRoleMapper tAgencyBusiRoleRoleMapper){
        this.tAgencyBusiRoleRoleMapper = tAgencyBusiRoleRoleMapper;
    }

    public void setSeqService(SeqService seqService){
        this.seqService = seqService;
    }
    
	public void settAgencyUserBusiRoleMapper(
			TAgencyUserBusiRoleMapper tAgencyUserBusiRoleMapper) {
		this.tAgencyUserBusiRoleMapper = tAgencyUserBusiRoleMapper;
	}

	public void settAgencyLevelRoleMapper(
			TAgencyLevelRoleMapper tAgencyLevelRoleMapper) {
		this.tAgencyLevelRoleMapper = tAgencyLevelRoleMapper;
	}

	@Override
    public List<TAgencyBusiRole> selectAll(){
        TAgencyBusiRoleExample example = new TAgencyBusiRoleExample();
        example.setOrderByClause("name");
        List<TAgencyBusiRole> tAgencyBusiRoles = tAgencyBusiRoleMapper.selectByExample(example);
        return tAgencyBusiRoles;
    }

    @Override
    public List<TAgencyBusiRole> selectByUserId(Long userId){
        List<TAgencyBusiRole> tAgencyBusiRoles = tAgencyBusiRoleMapper.selectByUserId(userId);
        return tAgencyBusiRoles;
    }

    @Override
    public int countByCondition(Map<String, Object> map){
        if(map == null){
            return 0;
        }
        return tAgencyBusiRoleMapper.countByCondition(map);
    }

    @Override
    public List<TAgencyBusiRoleBean> selectByCondition(Map<String, Object> map){
        if(map == null){
            return null;
        }
        List<TAgencyBusiRole> tAgencyBusiRoles = tAgencyBusiRoleMapper.selectByCondition(map);
        List<TAgencyBusiRoleBean> tAgencyBusiRoleBeans = new ArrayList<TAgencyBusiRoleBean>();
        for (TAgencyBusiRole tAgencyBusiRole : tAgencyBusiRoles){
            TAgencyBusiRoleBean tAgencyBusiRoleBean = new TAgencyBusiRoleBean();
            BeanUtils.copyProperties(tAgencyBusiRole, tAgencyBusiRoleBean);
            List<TAgencyRole> tAgencyRoles = tAgencyRoleMapper.selectByBusiRoleId(tAgencyBusiRole.getId());
            tAgencyBusiRoleBean.settAgencyRoles(tAgencyRoles);
            tAgencyBusiRoleBeans.add(tAgencyBusiRoleBean);
        }
        return tAgencyBusiRoleBeans;
    }

    @Override
    public String checkDuplicateAgencyBusiRoleName(Long id, String name ,Integer agencyLevel){
        if(StringUtils.isEmpty(name)){
            return null;
        }
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("agencyLevel", agencyLevel);
        paramMap.put("name", name);
        int count = tAgencyBusiRoleMapper.checkAgencyRoleName(paramMap);
        
        return count != 0 ? "1" : "0";
    }

    @Override
    public int insertBusiRole(TAgencyBusiRole tAgencyBusiRole, Long[] roleIds,TAgencyLevelRole agencyLevelRole) throws PayPluginException{
        if(tAgencyBusiRole == null || roleIds == null){
            return 0;
        }
        long busiRoleId = seqService.getAgencyBusiRoleId();
        tAgencyBusiRole.setId(busiRoleId);
        agencyLevelRole.settRoleId(busiRoleId);
        long agencyLevelRoleId = seqService.getAgencyBusiRoleId();
        agencyLevelRole.setId(agencyLevelRoleId);
        int res = tAgencyBusiRoleMapper.insertSelective(tAgencyBusiRole);
        if(res == 0){
            throw new PayPluginException("添加业务角色失败");
        }
        res = tAgencyLevelRoleMapper.insertSelective(agencyLevelRole);
        if(res == 0){
        	throw new PayPluginException("添加经销商角色等级失败");
        }
        TAgencyBusiRoleRole tAgencyBusiRoleRole = new TAgencyBusiRoleRole();
        tAgencyBusiRoleRole.setBusiRoleId(tAgencyBusiRole.getId());
        for (Long roleId : roleIds){
            tAgencyBusiRoleRole.setRoleId(roleId);
            res = tAgencyBusiRoleRoleMapper.insert(tAgencyBusiRoleRole);
            if(res == 0){
                throw new PayPluginException("添加角色关联失败");
            }
        }
        return 1;
    }

    @Override
    public int updateBusiRole(TAgencyBusiRole tAgencyBusiRole, Long[] roleIds) throws PayPluginException{
        if(tAgencyBusiRole == null || roleIds == null){
            return 0;
        }
        int res = tAgencyBusiRoleMapper.updateByPrimaryKeySelective(tAgencyBusiRole);
        if(res == 0){
            throw new PayPluginException("修改账户信息失败");
        }
        TAgencyBusiRoleRoleExample example = new TAgencyBusiRoleRoleExample();
        com.cbice.distribute.core.db.model.TAgencyBusiRoleRoleExample.Criteria criteria = example.createCriteria();
        criteria.andBusiRoleIdEqualTo(tAgencyBusiRole.getId());
        tAgencyBusiRoleRoleMapper.deleteByExample(example);
        TAgencyBusiRoleRole tAgencyBusiRoleRole = new TAgencyBusiRoleRole();
        tAgencyBusiRoleRole.setBusiRoleId(tAgencyBusiRole.getId());
        for (Long roleId : roleIds){
            tAgencyBusiRoleRole.setRoleId(roleId);
            res = tAgencyBusiRoleRoleMapper.insert(tAgencyBusiRoleRole);
            if(res == 0){
                throw new PayPluginException("添加角色关联失败");
            }
        }
        return 1;
    }

	@Override
	public List<TAgencyBusiRole> selectbyAgencyLevel(Integer agencyLevel) {
		if(agencyLevel == null){
			return null;
		}
		return tAgencyBusiRoleMapper.selectbyAgencyLevel(agencyLevel);
	}

	@Override
	public int delAgencyRole(Long id)  throws PayPluginException{
		int i = 0;
		i = tAgencyBusiRoleMapper.deleteByPrimaryKey(id);
		if(i!=1){
			 throw new PayPluginException("删除角色失败");
		}
		i = tAgencyLevelRoleMapper.deleteByRoleId(id);
		if(i<1){
			 throw new PayPluginException("删除经销商角色关系失败");
		}
		i = tAgencyBusiRoleRoleMapper.deleteByRoleId(id);
		if(i<1){
			 throw new PayPluginException("删除角色功能关系失败");
		}
		i = tAgencyUserBusiRoleMapper.deleteByBusiRoleId(id);
		if (i < 1) {
			 throw new PayPluginException("删除操作员与角色关系失败");
		}
		return 1;
	}
}
