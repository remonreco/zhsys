package com.cbice.distribute.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.cbice.distribute.core.db.data.TAgencyBusiRoleMapper;
import com.cbice.distribute.core.db.data.TAgencyUserBusiRoleMapper;
import com.cbice.distribute.core.db.data.TUserMapper;
import com.cbice.distribute.core.db.model.TAgencyBusiRole;
import com.cbice.distribute.core.db.model.TAgencyUserBean;
import com.cbice.distribute.core.db.model.TAgencyUserBusiRole;
import com.cbice.distribute.core.db.model.TAgencyUserBusiRoleExample;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.TUserExample;
import com.cbice.distribute.core.db.model.TUserExample.Criteria;
import com.cbice.distribute.core.service.AgencyUserService;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.util.PayPluginException;

/**
 * @author Richard 系统用户服务数据库实现
 */
public class AgencyUserServiceDbImpl implements AgencyUserService{
    private TUserMapper tUserMapper;
    private TAgencyBusiRoleMapper tAgencyBusiRoleMapper;
    private TAgencyUserBusiRoleMapper tAgencyUserBusiRoleMapper;
    private SeqService seqService;

    public void settUserMapper(TUserMapper tUserMapper){
        this.tUserMapper = tUserMapper;
    }

    public void settAgencyBusiRoleMapper(TAgencyBusiRoleMapper tAgencyBusiRoleMapper){
        this.tAgencyBusiRoleMapper = tAgencyBusiRoleMapper;
    }

    public void settAgencyUserBusiRoleMapper(TAgencyUserBusiRoleMapper tAgencyUserBusiRoleMapper){
        this.tAgencyUserBusiRoleMapper = tAgencyUserBusiRoleMapper;
    }

    public void setSeqService(SeqService seqService){
        this.seqService = seqService;
    }

    @Override
    public TUser selectAgencyUserByLoginName(String loginName){
        TUserExample example = new TUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andDealerNameEqualTo(loginName);
        List<TUser> tUsers = tUserMapper.selectByExample(example);
        if(tUsers.size() == 0){
            return null;
        }else{
            return tUsers.get(0);
        }
    }

    @Override
    public TUser selectById(Long id){
        return tUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int countByCondition(Map<String, Object> map){
        if(map == null){
            return 0;
        }
        return tUserMapper.countByCondition(map);
    }

    @Override
	public
    List<TAgencyUserBean> selectByCondition(Map<String, Object> map){
        if(map == null){
            return null;
        }
        List<TUser> tUsers =  tUserMapper.selectByCondition(map);
        List<TAgencyUserBean> tUserBeans = new ArrayList<TAgencyUserBean>();
        for (TUser tUser : tUsers){
        	TAgencyUserBean tUserBean = new TAgencyUserBean();
            BeanUtils.copyProperties(tUser, tUserBean);
            List<TAgencyBusiRole> tAgencyBusiRoles = tAgencyBusiRoleMapper.selectByUserId(tUser.getId());
            tUserBean.settAgencyBusiRoles(tAgencyBusiRoles);
            tUserBeans.add(tUserBean);
        }
        return tUserBeans;
    }

    @Override
    public String checkDuplicateAgencyUserLoginName(String loginName){
        if(loginName == null){
            return null;
        }
        TUserExample example = new TUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andDealerNameEqualTo(loginName);
        int count = tUserMapper.countByExample(example);
        return count == 0 ? "0" : "1";
    }

    @Override
    public int insertUser(TUser tUser, Long[] roles) throws PayPluginException{
        if(tUser == null || roles == null){
            return 0;
        }
        long userId = seqService.getTUserId();
        tUser.setId(userId);
        String pwd = new Md5PasswordEncoder().encodePassword(tUser.getDealerNum(), null);
        tUser.setDealerNum(pwd);
        tUser.setAccountStatus(1);
        int res = tUserMapper.insertSelective(tUser);
        if(res == 0){
            throw new PayPluginException("添加操作员失败");
        }
        TAgencyUserBusiRole tUserBusiRole = new TAgencyUserBusiRole();
        tUserBusiRole.setUserId(userId);
        for (Long role : roles){
            tUserBusiRole.setBusiRoleId(role);
            res = tAgencyUserBusiRoleMapper.insert(tUserBusiRole);
            if(res == 0){
                throw new PayPluginException("添加业务角色关联失败");
            }
        }
        return 1;
    }
    
    public boolean checkRoles(Long[] roles,long level){
    	boolean flag = true;
    	for(long role:roles){
    		if(level==role){
    			flag = false;
    		}
    	}
    	return flag;
    }

    @Override
    public Map<String,String> updateUser(TUser tUser, Long[] roles,long level) throws PayPluginException{
    	Map<String,String> resMap = new HashMap<String,String>();
        if(tUser == null || roles == null){
        	resMap.put("resCode", "1003");
        	resMap.put("msg", "未找到修改信息");
        	return resMap;
        }
        if(StringUtils.isNotBlank(tUser.getDealerNum())){
            String pwd = new Md5PasswordEncoder().encodePassword(tUser.getDealerNum(), null);
            tUser.setDealerNum(pwd);
        }
        TUser dataUser = tUserMapper.selectByPrimaryKey(tUser.getId());
        if(dataUser.getUserRoleId()==0&&checkRoles(roles,level)){//UserRoleId ，0：总经理，1：普通员工
        	resMap.put("resCode", "1004");
        	resMap.put("msg", "不可取消总经理的权限管理角色");
        	return resMap;
        }
        
        int res = 0;
        if(!(tUser.getAccountStatus()==null&&tUser.getTel()==null&&StringUtils.isBlank(tUser.getDealerNum()))){
        	res =	tUserMapper.updateByPrimaryKeySelective(tUser);
        	if(res == 0){
        		throw new PayPluginException("修改账户信息失败");
        	}
        }
        TAgencyUserBusiRoleExample example = new TAgencyUserBusiRoleExample();
        com.cbice.distribute.core.db.model.TAgencyUserBusiRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(tUser.getId());
        tAgencyUserBusiRoleMapper.deleteByExample(example);
        TAgencyUserBusiRole ur = new TAgencyUserBusiRole();
        ur.setUserId(tUser.getId());
        for (Long role : roles){
            ur.setBusiRoleId(role);
            res = tAgencyUserBusiRoleMapper.insert(ur);
            if(res == 0){
                throw new PayPluginException("添加业务角色关联失败");
            }
        }
        resMap.put("resCode", "0000");
    	resMap.put("msg", "修改成功");
        return resMap;
    }

    @Override
    public int updateByPrimaryKeySelective(TUser tUser){
        return tUserMapper.updateByPrimaryKeySelective(tUser);
    }
}
