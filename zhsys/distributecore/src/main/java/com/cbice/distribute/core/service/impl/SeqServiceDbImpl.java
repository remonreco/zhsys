package com.cbice.distribute.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.data.TSeqMapper;
import com.cbice.distribute.core.db.model.TSeq;
import com.cbice.distribute.core.service.SeqService;

/**
 * @author Richard Xiong
 * 序列号生成器服务数据库实现
 */
public class SeqServiceDbImpl implements SeqService{
    private TSeqMapper tSeqMapper;

    public void settSeqMapper(TSeqMapper tSeqMapper){
        this.tSeqMapper = tSeqMapper;
    }

    /**
     * 生成序列号
     * @param seqKey 序列号键
     * @param count 生成序列号的数量
     * @return 第一个可用序列号
     */
    private long genSequence(String seqKey, int count){
        if(count < 1){
            throw new RuntimeException("count should greater than 0");
        }
        int size = tSeqMapper.tickCountSeq(seqKey, count);
        if(size == 0){
            TSeq tSeq = new TSeq();
            tSeq.setSeq(Long.valueOf(count - 1));
            tSeq.setId(seqKey);
            tSeqMapper.insertSelective(tSeq);
            return 0;
        }else{
            TSeq seq = tSeqMapper.selectByPrimaryKey(seqKey);
            return seq.getSeq() - count + 1;
        }
    }
    
    private long getSequence(String id){
        return genSequence(id, 1);
    }
    
    private String getZeroLeftPadSequence(String seqKey, int size){
        long seqValue = genSequence(seqKey, 1);
        String seqValueStr = String.valueOf(seqValue);
        if(seqValueStr.length() > size){
            seqValueStr = StringUtils.right(seqValueStr, size);
            return seqValueStr;
        }else if(seqValueStr.length() < size){
            return StringUtils.leftPad(seqValueStr, size, '0');
        }else{
            return seqValueStr;
        }
    }

    @Override
    public String genOutTradeNo(){
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        StringBuilder builder = new StringBuilder()
            .append(simpleDateFormat.format(now))
            .append(getZeroLeftPadSequence("outTradeNo", 8));
        return builder.toString();
    }
    
    @Override
	public long getOrderId() {
		return getSequence("order_id");
	}

    @Override
    public long getSysUserId(){
        return getSequence("sys_user_id");
    }

    @Override
    public long getSysBusiRoleId(){
        return getSequence("sys_busi_role_id");
    }

	@Override
	public long getTUserId() {
	     return getSequence("t_user_id");
	}

	@Override
	public long getTGoodsId() {
		 return getSequence("t_goods_id");
	}

	@Override
	public long getTGoodsOrder() {
		return getSequence("t_goods_order_id");
	}

	@Override
	public long getTGoodsBatch() {
		return getSequence("t_goods_batch_id");
	}

	@Override
	public long getAgencyBusiRoleId() {
		  return getSequence("agency_role_id");
	}

	@Override
	public long getUserId(){
		 return getSequence("agency_user_id");
	}

	@Override
	public long getTRemainderGoods() {
		return getSequence("remainder_id");
	}

	@Override
	public long getTAgentId() {
		String numStr = format((int)getSequence("tAgency_id"),6);
		return Long.parseLong("8"+numStr);
	}
	
	@Override
	public String getSonSysId() {
		String numStr = format((int)getSequence("sys_id"),6);
		return "A1"+numStr;
	}

	public String format(int num, int width) {
		if (num<0) return "";
		StringBuffer sb = new StringBuffer();
		String s = ""+num;
		if (s.length()<width) {
			int addNum = width-s.length();
			for (int i=0;i<addNum;i++) {
				sb.append("0");
			}
			sb.append(s);
		} else {
			return s.substring(s.length()-width,s.length());
		}
		return sb.toString();
	}

	@Override
	public long getT_Agency_Level_Role() {
		return getSequence("aLevelRoleId");
	}

	@Override
	public long getExchangeOrderId() {
		return genSequence("id",1);
	}
}
