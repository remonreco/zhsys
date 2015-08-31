package com.cbice.distribute.core.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cbice.distribute.core.util.AmountUtil;

public class TrancationReport {

	private Long id;//批次号
	private String goodsNum;//产品编号
	private String goodsName;//产品名称
	private Date creatTime;//创建时间
	private Long tUserId;//会员ID
	private String userName;//会员名字
	private Long goodsRemainderNum;//剩余数量
	private Long goodsPrice;//产品价格
	private Long goodCount;//产品数量
	private String creatTimeStr;//穿件时间字符串
	private String goodsPriceStr;//产品价格字符串
	
	private String assetsAccount;//资产账号
	
	
	private  String stackMarke;//股市市场
	private  String nonTradable;//非流通数量
	private  String processType;//处理类别	
	
	private String saleCode;//销售码
	
	private String tHigerUserId;//上级经销商ID
	private Integer dealerLeaver;//经销商级别
	private String dealerName;//经销商姓名
	private Long userId;//会员ID
	
	
	public Integer getDealerLeaver() {
		return dealerLeaver;
	}
	public void setDealerLeaver(Integer dealerLeaver) {
		this.dealerLeaver = dealerLeaver;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String gettHigerUserId() {
		return tHigerUserId;
	}
	public void settHigerUserId(String tHigerUserId) {
		this.tHigerUserId = tHigerUserId;
	}
	public String getSaleCode() {
		return saleCode;
	}
	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}
	public String getGoodsPriceStr() {
		return AmountUtil.parseAmountLong2Str(this.goodsPrice);
	}
	public void setGoodsPriceStr(String goodsPriceStr) {
		this.goodsPriceStr = goodsPriceStr;
	}
	public String getCreatTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(this.creatTime != null){
			return sdf.format(this.creatTime);
		}
		return "-";
	}
	public void setCreatTimeStr(String creatTimeStr) {
		this.creatTimeStr = creatTimeStr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Long getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Long goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Long gettUserId() {
		return tUserId;
	}
	public void settUserId(Long tUserId) {
		this.tUserId = tUserId;
	}
	public Long getGoodsRemainderNum() {
		return goodsRemainderNum;
	}
	public void setGoodsRemainderNum(Long goodsRemainderNum) {
		this.goodsRemainderNum = goodsRemainderNum;
	}
	public String getAssetsAccount() {
		return assetsAccount;
	}
	public void setAssetsAccount(String assetsAccount) {
		this.assetsAccount = assetsAccount;
	}
	public String getStackMarke() {
		return stackMarke;
	}
	public void setStackMarke(String stackMarke) {
		this.stackMarke = stackMarke;
	}
	public String getNonTradable() {
		return nonTradable;
	}
	public void setNonTradable(String nonTradable) {
		this.nonTradable = nonTradable;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public Long getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(Long goodCount) {
		this.goodCount = goodCount;
	}
	
}
