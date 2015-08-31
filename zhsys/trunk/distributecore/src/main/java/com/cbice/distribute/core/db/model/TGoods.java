package com.cbice.distribute.core.db.model;

import com.cbice.distribute.core.util.AmountUtil;
import java.util.Date;

public class TGoods {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods.id
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods.goods_num
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	private String goodsNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods.goods_name
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	private String goodsName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods.goods_price
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	private Long goodsPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods.goods_count
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	private Long goodsCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods.modify_time
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	private Date modifyTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods.id
	 * @return  the value of t_goods.id
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public Long getId() {
		return id;
	}
	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods.id
	 * @param id  the value for t_goods.id
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods.goods_num
	 * @return  the value of t_goods.goods_num
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public String getGoodsNum() {
		return goodsNum;
	}
	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods.goods_num
	 * @param goodsNum  the value for t_goods.goods_num
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum == null ? null : goodsNum.trim();
	}
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods.goods_name
	 * @return  the value of t_goods.goods_name
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods.goods_name
	 * @param goodsName  the value for t_goods.goods_name
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName == null ? null : goodsName.trim();
	}
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods.goods_price
	 * @return  the value of t_goods.goods_price
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public Long getGoodsPrice() {
		return goodsPrice;
	}
	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods.goods_price
	 * @param goodsPrice  the value for t_goods.goods_price
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public void setGoodsPrice(Long goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods.goods_count
	 * @return  the value of t_goods.goods_count
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public Long getGoodsCount() {
		return goodsCount;
	}
	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods.goods_count
	 * @param goodsCount  the value for t_goods.goods_count
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public void setGoodsCount(Long goodsCount) {
		this.goodsCount = goodsCount;
	}
	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods.modify_time
	 * @return  the value of t_goods.modify_time
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods.modify_time
	 * @param modifyTime  the value for t_goods.modify_time
	 * @mbggenerated  Fri Mar 20 14:17:44 CST 2015
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


	private String goodsPriceStr;//产品价格字符�?
	
	public String getGoodsPriceStr() {
		return AmountUtil.parseAmountLong2Str(this.goodsPrice);
	}
	public void setGoodsPriceStr(String goodsPriceStr) {
		this.goodsPriceStr = goodsPriceStr;
	}
	
	
	private String remainderGoodsCount;

	public String getRemainderGoodsCount() {
		return remainderGoodsCount;
	}
	public void setRemainderGoodsCount(String remainderGoodsCount) {
		this.remainderGoodsCount = remainderGoodsCount;
	}
	
	private String userId;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}