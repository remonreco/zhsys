package com.cbice.distribute.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.cbice.distribute.core.db.data.TSysBusiRoleMapper;
import com.cbice.distribute.core.db.data.TSysUserBusiRoleMapper;
import com.cbice.distribute.core.db.data.TSysUserMapper;
import com.cbice.distribute.core.db.model.TSysBusiRole;
import com.cbice.distribute.core.db.model.TSysUser;
import com.cbice.distribute.core.db.model.TSysUserBean;
import com.cbice.distribute.core.db.model.TSysUserBusiRole;
import com.cbice.distribute.core.db.model.TSysUserBusiRoleExample;
import com.cbice.distribute.core.db.model.TSysUserExample;
import com.cbice.distribute.core.db.model.TSysUserExample.Criteria;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.SysUserService;
import com.cbice.distribute.core.util.PayPluginException;

/**
 * @author Richard 系统用户服务数据库实现
 */
public class SysUserServiceDbImpl implements SysUserService{
    private TSysUserMapper tSysUserMapper;
    private TSysBusiRoleMapper tSysBusiRoleMapper;
    private TSysUserBusiRoleMapper tSysUserBusiRoleMapper;
    private SeqService seqService;

    public void settSysUserMapper(TSysUserMapper tSysUserMapper){
        this.tSysUserMapper = tSysUserMapper;
    }

    public void settSysBusiRoleMapper(TSysBusiRoleMapper tSysBusiRoleMapper){
        this.tSysBusiRoleMapper = tSysBusiRoleMapper;
    }

    public void settSysUserBusiRoleMapper(TSysUserBusiRoleMapper tSysUserBusiRoleMapper){
        this.tSysUserBusiRoleMapper = tSysUserBusiRoleMapper;
    }

    public void setSeqService(SeqService seqService){
        this.seqService = seqService;
    }

    @Override
    public TSysUser selectSysUserByLoginName(String loginName){
        TSysUserExample example = new TSysUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<TSysUser> tSysUsers = tSysUserMapper.selectByExample(example);
        if(tSysUsers.size() == 0){
            return null;
        }else{
            return tSysUsers.get(0);
        }
    }

    @Override
    public TSysUser selectById(Long id){
        return tSysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int countByCondition(Map<String, Object> map){
        if(map == null){
            return 0;
        }
        return tSysUserMapper.countByCondition(map);
    }

    @Override
    public List<TSysUserBean> selectByCondition(Map<String, Object> map){
        if(map == null){
            return null;
        }
        List<TSysUser> tSysUsers = tSysUserMapper.selectByCondition(map);
        List<TSysUserBean> tSysUserBeans = new ArrayList<TSysUserBean>();
        for (TSysUser tSysUser : tSysUsers){
            TSysUserBean tSysUserBean = new TSysUserBean();
            BeanUtils.copyProperties(tSysUser, tSysUserBean);
            List<TSysBusiRole> tSysBusiRoles = tSysBusiRoleMapper.selectByUserId(tSysUser.getId());
            tSysUserBean.settSysBusiRoles(tSysBusiRoles);
            TSysUser lastModSysUser = tSysUserMapper.selectByPrimaryKey(tSysUser.getLastModSysUserId());
            tSysUserBean.setLastModSysUser(lastModSysUser);
            tSysUserBeans.add(tSysUserBean);
        }
        return tSysUserBeans;
    }

    @Override
    public String checkDuplicateSysUserLoginName(String loginName){
        if(loginName == null){
            return null;
        }
        TSysUserExample example = new TSysUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        int count = tSysUserMapper.countByExample(example);
        return count == 0 ? "0" : "1";
    }

    @Override
    public int insertUser(TSysUser tSysUser, Long[] roles) throws PayPluginException{
        if(tSysUser == null || roles == null){
            return 0;
        }
        long userId = seqService.getSysUserId();
        tSysUser.setId(userId);
        String pwd = new Md5PasswordEncoder().encodePassword(tSysUser.getLoginPwd(), null);
        tSysUser.setLoginPwd(pwd);
        tSysUser.setLastModTs(new Date());
        tSysUser.setStatus(1);
        int res = tSysUserMapper.insertSelective(tSysUser);
        if(res == 0){
            throw new PayPluginException("添加操作员失败");
        }
        TSysUserBusiRole tSysUserBusiRole = new TSysUserBusiRole();
        tSysUserBusiRole.setUserId(userId);
        for (Long role : roles){
            tSysUserBusiRole.setBusiRoleId(role);
            res = tSysUserBusiRoleMapper.insert(tSysUserBusiRole);
            if(res == 0){
                throw new PayPluginException("添加业务角色关联失败");
            }
        }
        return 1;
    }

    @Override
    public int updateUser(TSysUser tSysUser, Long[] roles) throws PayPluginException{
        if(tSysUser == null || roles == null){
            return 0;
        }
        if(StringUtils.isNotBlank(tSysUser.getLoginPwd())){
            String pwd = new Md5PasswordEncoder().encodePassword(tSysUser.getLoginPwd(), null);
            tSysUser.setLoginPwd(pwd);
        }
        tSysUser.setLastModTs(new Date());
        int res = tSysUserMapper.updateByPrimaryKeySelective(tSysUser);
        if(res == 0){
            throw new PayPluginException("修改账户信息失败");
        }
        TSysUserBusiRoleExample example = new TSysUserBusiRoleExample();
        com.cbice.distribute.core.db.model.TSysUserBusiRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(tSysUser.getId());
        tSysUserBusiRoleMapper.deleteByExample(example);
        TSysUserBusiRole ur = new TSysUserBusiRole();
        ur.setUserId(tSysUser.getId());
        for (Long role : roles){
            ur.setBusiRoleId(role);
            res = tSysUserBusiRoleMapper.insert(ur);
            if(res == 0){
                throw new PayPluginException("添加业务角色关联失败");
            }
        }
        return 1;
    }

    @Override
    public int updateByPrimaryKeySelective(TSysUser tSysUser){
        return tSysUserMapper.updateByPrimaryKeySelective(tSysUser);
    }
}
