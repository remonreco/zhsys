package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.TAgencyMapper;
import com.cbice.distribute.core.db.data.TAgencyTreeMapper;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TAgencyExample;
import com.cbice.distribute.core.db.model.TAgencyTree;
import com.cbice.distribute.core.service.TAgencyDbService;

public class TAgencyDbServiceImpl implements TAgencyDbService{
    private TAgencyMapper tAgencyMapper;
    private TAgencyTreeMapper tAgencyTreeMapper;

    public void settAgencyMapper(TAgencyMapper tAgencyMapper){
        this.tAgencyMapper = tAgencyMapper;
    }

    public void settAgencyTreeMapper(TAgencyTreeMapper tAgencyTreeMapper){
        this.tAgencyTreeMapper = tAgencyTreeMapper;
    }
	
	@Override
	public int levelOfAgency(long id) {
		
		return tAgencyMapper.selectLevelOfAgency(id);
	}

	@Override
	public int insertSelective(TAgency agency) {
		
		return tAgencyMapper.insertSelective(agency);
	}

    @Override
    public TAgency selectByAgencyId(long id){
        return tAgencyMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean genPreorder(){
        tAgencyTreeMapper.deleteByExample(null);
        TAgency root = new TAgency();
        root.setLft(1);
        addChildren(root);
        tAgencyMapper.syncPreorder();
        return true;
    }

    /**
     * 添加子节点，递归
     * @param node
     * 需要添加子节点的节点
     */
    private void addChildren(TAgency node) {
        Long id = node.getId();
        if (id == null) {
            id = Long.valueOf(0);
        }
        TAgencyExample example = new TAgencyExample();
        com.cbice.distribute.core.db.model.TAgencyExample.Criteria criteria = example.createCriteria();
        criteria.andHigerDealerIdEqualTo(id);
        example.setOrderByClause("dealer_name");
        List<TAgency> children = tAgencyMapper.selectByExample(example);
        int length = children.size();
        for (int i = 0; i < length; i++) {
            TAgency child = children.get(i);
            TAgencyTree tAgencyTree = new TAgencyTree();
            tAgencyTree.setAgencyId(child.getId());
            if (i == 0) {
                child.setLft(node.getLft() + 1);
            } else {
                child.setLft(children.get(i - 1).getRgt() + 1);
            }
            addChildren(child);
        }
        if (children.size() > 0) {
            node.setRgt(children.get(length - 1).getRgt() + 1);
        } else {
            node.setRgt(node.getLft() + 1);
        }
        if (null != node.getId()) {
            TAgencyTree tAgencyTree = new TAgencyTree();
            tAgencyTree.setAgencyId(node.getId());
            tAgencyTree.setLft(node.getLft());
            tAgencyTree.setRgt(node.getRgt());
            tAgencyTreeMapper.insertSelective(tAgencyTree);
        }
    }


	@Override
	public int countSelectAgency(TAgency agency) {
		return tAgencyMapper.countSelectAgency(agency);
	}

	@Override
	public List<TAgency> selectAgencyBydealerNumAndName(TAgency agency) {
		return tAgencyMapper.selectAgencyBydealerNumAndName(agency);
	}


	@Override
	public TAgency selectByAgencyNameAndAgnecyName(Map<String, Object> map) {
		return tAgencyMapper.selectByAgencyNameAndAgnecyName(map);
	}

	/**
	 * 查询当前登录的下级经销商列表
	 */
	@Override
	public List<TAgency> selectLowerDealerList(Map<String, Object> map) {
		return tAgencyMapper.selectLowerDealerList(map);
	}

	@Override
	public int UpdatebyId(TAgency agency) {
		return tAgencyMapper.updateByPrimaryKey(agency);
	}

	@Override
	public List<TAgency> selectAgency(TAgency agency) {
		return tAgencyMapper.selectAgency(agency);
	}

	@Override
	public TAgency selectByPrimaryKey(Long id) {
		return tAgencyMapper.selectByPrimaryKey(id);
	}

}
