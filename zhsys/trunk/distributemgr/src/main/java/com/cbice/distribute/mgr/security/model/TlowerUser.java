package com.cbice.distribute.mgr.security.model;

/**
 * 发货信息页实体类
 * @author buyball
 *
 */
public class TlowerUser {
	
	private String name;//下级经销商或会员的名称
	private String assetsAccount;//资产账号
	private String tel;//联系方式
	private Long higerDealer;//上级经销商
	private String dealerNum;//经销商编号
	private String userIdenty;//用户标识 
	
	private Long loweruserId;//下级人员的Id
	private String goodsNum;//产品编号
	private String goodsName;//产品名称
	private Long goodsCount;//发产品数量
	private Long   goodsPrice;//产品单价
	private String dealerName;
    private Long id;
	private Long distributeNum;//产品数量
	private Integer orderState;
	
	private Long agencyId;//上级经销商id
	private Long userId;//本级经销商id

	
	private String rightAccount;//权益账号
	
	private String email;//邮箱
	private String address;//地址
	private String ids;//证件号码
	private Integer sex;//性别
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getRightAccount() {
		return rightAccount;
	}
	public void setRightAccount(String rightAccount) {
		this.rightAccount = rightAccount;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}
	private String returnGoodsReason;//退货申请原因
	private Integer returnGoodsState;//退货申请审核状态
	private String returnGoodsOptions;//退货申请审核原因
	
	
	
	private String saleCode;//销售码
	
	public String getSaleCode() {
		return saleCode;
	}
	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}
	public String getReturnGoodsReason() {
		return returnGoodsReason;
	}
	public void setReturnGoodsReason(String returnGoodsReason) {
		this.returnGoodsReason = returnGoodsReason;
	}
	
	public Integer getReturnGoodsState() {
		return returnGoodsState;
	}
	public void setReturnGoodsState(Integer returnGoodsState) {
		this.returnGoodsState = returnGoodsState;
	}
	public String getReturnGoodsOptions() {
		return returnGoodsOptions;
	}
	public void setReturnGoodsOptions(String returnGoodsOptions) {
		this.returnGoodsOptions = returnGoodsOptions;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDistributeNum() {
		return distributeNum;
	}
	public void setDistributeNum(Long distributeNum) {
		this.distributeNum = distributeNum;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	
	public Long getLoweruserId() {
		return loweruserId;
	}
	public void setLoweruserId(Long loweruserId) {
		this.loweruserId = loweruserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAssetsAccount() {
		return assetsAccount;
	}
	public void setAssetsAccount(String assetsAccount) {
		this.assetsAccount = assetsAccount;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getHigerDealer() {
		return higerDealer;
	}
	public void setHigerDealer(Long higerDealer) {
		this.higerDealer = higerDealer;
	}
	public String getDealerNum() {
		return dealerNum;
	}
	public void setDealerNum(String dealerNum) {
		this.dealerNum = dealerNum;
	}
	public String getUserIdenty() {
		return userIdenty;
	}
	public void setUserIdenty(String userIdenty) {
		this.userIdenty = userIdenty;
	}

	public String getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
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
	public Long getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Long goodsCount) {
		this.goodsCount = goodsCount;
	}
	
	private String higerAgency;

	public String getHigerAgency() {
		return higerAgency;
	}
	public void setHigerAgency(String higerAgency) {
		this.higerAgency = higerAgency;
	}
	
	
}
