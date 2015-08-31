package com.cbice.distribute.core.db.model;

import java.util.ArrayList;
import java.util.List;

public class HsExchangeGiftExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	public HsExchangeGiftExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andSysIdIsNull() {
			addCriterion("sys_id is null");
			return (Criteria) this;
		}

		public Criteria andSysIdIsNotNull() {
			addCriterion("sys_id is not null");
			return (Criteria) this;
		}

		public Criteria andSysIdEqualTo(String value) {
			addCriterion("sys_id =", value, "sysId");
			return (Criteria) this;
		}

		public Criteria andSysIdNotEqualTo(String value) {
			addCriterion("sys_id <>", value, "sysId");
			return (Criteria) this;
		}

		public Criteria andSysIdGreaterThan(String value) {
			addCriterion("sys_id >", value, "sysId");
			return (Criteria) this;
		}

		public Criteria andSysIdGreaterThanOrEqualTo(String value) {
			addCriterion("sys_id >=", value, "sysId");
			return (Criteria) this;
		}

		public Criteria andSysIdLessThan(String value) {
			addCriterion("sys_id <", value, "sysId");
			return (Criteria) this;
		}

		public Criteria andSysIdLessThanOrEqualTo(String value) {
			addCriterion("sys_id <=", value, "sysId");
			return (Criteria) this;
		}

		public Criteria andSysIdLike(String value) {
			addCriterion("sys_id like", value, "sysId");
			return (Criteria) this;
		}

		public Criteria andSysIdNotLike(String value) {
			addCriterion("sys_id not like", value, "sysId");
			return (Criteria) this;
		}

		public Criteria andSysIdIn(List<String> values) {
			addCriterion("sys_id in", values, "sysId");
			return (Criteria) this;
		}

		public Criteria andSysIdNotIn(List<String> values) {
			addCriterion("sys_id not in", values, "sysId");
			return (Criteria) this;
		}

		public Criteria andSysIdBetween(String value1, String value2) {
			addCriterion("sys_id between", value1, value2, "sysId");
			return (Criteria) this;
		}

		public Criteria andSysIdNotBetween(String value1, String value2) {
			addCriterion("sys_id not between", value1, value2, "sysId");
			return (Criteria) this;
		}

		public Criteria andOrderIdIsNull() {
			addCriterion("order_id is null");
			return (Criteria) this;
		}

		public Criteria andOrderIdIsNotNull() {
			addCriterion("order_id is not null");
			return (Criteria) this;
		}

		public Criteria andOrderIdEqualTo(Long value) {
			addCriterion("order_id =", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdNotEqualTo(Long value) {
			addCriterion("order_id <>", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdGreaterThan(Long value) {
			addCriterion("order_id >", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
			addCriterion("order_id >=", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdLessThan(Long value) {
			addCriterion("order_id <", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdLessThanOrEqualTo(Long value) {
			addCriterion("order_id <=", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdIn(List<Long> values) {
			addCriterion("order_id in", values, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdNotIn(List<Long> values) {
			addCriterion("order_id not in", values, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdBetween(Long value1, Long value2) {
			addCriterion("order_id between", value1, value2, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdNotBetween(Long value1, Long value2) {
			addCriterion("order_id not between", value1, value2, "orderId");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdIsNull() {
			addCriterion("be_exchange_id is null");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdIsNotNull() {
			addCriterion("be_exchange_id is not null");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdEqualTo(Long value) {
			addCriterion("be_exchange_id =", value, "beExchangeId");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdNotEqualTo(Long value) {
			addCriterion("be_exchange_id <>", value, "beExchangeId");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdGreaterThan(Long value) {
			addCriterion("be_exchange_id >", value, "beExchangeId");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdGreaterThanOrEqualTo(Long value) {
			addCriterion("be_exchange_id >=", value, "beExchangeId");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdLessThan(Long value) {
			addCriterion("be_exchange_id <", value, "beExchangeId");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdLessThanOrEqualTo(Long value) {
			addCriterion("be_exchange_id <=", value, "beExchangeId");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdIn(List<Long> values) {
			addCriterion("be_exchange_id in", values, "beExchangeId");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdNotIn(List<Long> values) {
			addCriterion("be_exchange_id not in", values, "beExchangeId");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdBetween(Long value1, Long value2) {
			addCriterion("be_exchange_id between", value1, value2,
					"beExchangeId");
			return (Criteria) this;
		}

		public Criteria andBeExchangeIdNotBetween(Long value1, Long value2) {
			addCriterion("be_exchange_id not between", value1, value2,
					"beExchangeId");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameIsNull() {
			addCriterion("be_exchange_name is null");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameIsNotNull() {
			addCriterion("be_exchange_name is not null");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameEqualTo(String value) {
			addCriterion("be_exchange_name =", value, "beExchangeName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameNotEqualTo(String value) {
			addCriterion("be_exchange_name <>", value, "beExchangeName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameGreaterThan(String value) {
			addCriterion("be_exchange_name >", value, "beExchangeName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameGreaterThanOrEqualTo(String value) {
			addCriterion("be_exchange_name >=", value, "beExchangeName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameLessThan(String value) {
			addCriterion("be_exchange_name <", value, "beExchangeName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameLessThanOrEqualTo(String value) {
			addCriterion("be_exchange_name <=", value, "beExchangeName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameLike(String value) {
			addCriterion("be_exchange_name like", value, "beExchangeName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameNotLike(String value) {
			addCriterion("be_exchange_name not like", value, "beExchangeName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameIn(List<String> values) {
			addCriterion("be_exchange_name in", values, "beExchangeName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameNotIn(List<String> values) {
			addCriterion("be_exchange_name not in", values, "beExchangeName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameBetween(String value1, String value2) {
			addCriterion("be_exchange_name between", value1, value2,
					"beExchangeName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNameNotBetween(String value1, String value2) {
			addCriterion("be_exchange_name not between", value1, value2,
					"beExchangeName");
			return (Criteria) this;
		}

		public Criteria andGiftIdIsNull() {
			addCriterion("gift_id is null");
			return (Criteria) this;
		}

		public Criteria andGiftIdIsNotNull() {
			addCriterion("gift_id is not null");
			return (Criteria) this;
		}

		public Criteria andGiftIdEqualTo(Long value) {
			addCriterion("gift_id =", value, "giftId");
			return (Criteria) this;
		}

		public Criteria andGiftIdNotEqualTo(Long value) {
			addCriterion("gift_id <>", value, "giftId");
			return (Criteria) this;
		}

		public Criteria andGiftIdGreaterThan(Long value) {
			addCriterion("gift_id >", value, "giftId");
			return (Criteria) this;
		}

		public Criteria andGiftIdGreaterThanOrEqualTo(Long value) {
			addCriterion("gift_id >=", value, "giftId");
			return (Criteria) this;
		}

		public Criteria andGiftIdLessThan(Long value) {
			addCriterion("gift_id <", value, "giftId");
			return (Criteria) this;
		}

		public Criteria andGiftIdLessThanOrEqualTo(Long value) {
			addCriterion("gift_id <=", value, "giftId");
			return (Criteria) this;
		}

		public Criteria andGiftIdIn(List<Long> values) {
			addCriterion("gift_id in", values, "giftId");
			return (Criteria) this;
		}

		public Criteria andGiftIdNotIn(List<Long> values) {
			addCriterion("gift_id not in", values, "giftId");
			return (Criteria) this;
		}

		public Criteria andGiftIdBetween(Long value1, Long value2) {
			addCriterion("gift_id between", value1, value2, "giftId");
			return (Criteria) this;
		}

		public Criteria andGiftIdNotBetween(Long value1, Long value2) {
			addCriterion("gift_id not between", value1, value2, "giftId");
			return (Criteria) this;
		}

		public Criteria andGiftNameIsNull() {
			addCriterion("gift_name is null");
			return (Criteria) this;
		}

		public Criteria andGiftNameIsNotNull() {
			addCriterion("gift_name is not null");
			return (Criteria) this;
		}

		public Criteria andGiftNameEqualTo(String value) {
			addCriterion("gift_name =", value, "giftName");
			return (Criteria) this;
		}

		public Criteria andGiftNameNotEqualTo(String value) {
			addCriterion("gift_name <>", value, "giftName");
			return (Criteria) this;
		}

		public Criteria andGiftNameGreaterThan(String value) {
			addCriterion("gift_name >", value, "giftName");
			return (Criteria) this;
		}

		public Criteria andGiftNameGreaterThanOrEqualTo(String value) {
			addCriterion("gift_name >=", value, "giftName");
			return (Criteria) this;
		}

		public Criteria andGiftNameLessThan(String value) {
			addCriterion("gift_name <", value, "giftName");
			return (Criteria) this;
		}

		public Criteria andGiftNameLessThanOrEqualTo(String value) {
			addCriterion("gift_name <=", value, "giftName");
			return (Criteria) this;
		}

		public Criteria andGiftNameLike(String value) {
			addCriterion("gift_name like", value, "giftName");
			return (Criteria) this;
		}

		public Criteria andGiftNameNotLike(String value) {
			addCriterion("gift_name not like", value, "giftName");
			return (Criteria) this;
		}

		public Criteria andGiftNameIn(List<String> values) {
			addCriterion("gift_name in", values, "giftName");
			return (Criteria) this;
		}

		public Criteria andGiftNameNotIn(List<String> values) {
			addCriterion("gift_name not in", values, "giftName");
			return (Criteria) this;
		}

		public Criteria andGiftNameBetween(String value1, String value2) {
			addCriterion("gift_name between", value1, value2, "giftName");
			return (Criteria) this;
		}

		public Criteria andGiftNameNotBetween(String value1, String value2) {
			addCriterion("gift_name not between", value1, value2, "giftName");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumIsNull() {
			addCriterion("be_exchange_num is null");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumIsNotNull() {
			addCriterion("be_exchange_num is not null");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumEqualTo(Long value) {
			addCriterion("be_exchange_num =", value, "beExchangeNum");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumNotEqualTo(Long value) {
			addCriterion("be_exchange_num <>", value, "beExchangeNum");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumGreaterThan(Long value) {
			addCriterion("be_exchange_num >", value, "beExchangeNum");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumGreaterThanOrEqualTo(Long value) {
			addCriterion("be_exchange_num >=", value, "beExchangeNum");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumLessThan(Long value) {
			addCriterion("be_exchange_num <", value, "beExchangeNum");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumLessThanOrEqualTo(Long value) {
			addCriterion("be_exchange_num <=", value, "beExchangeNum");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumIn(List<Long> values) {
			addCriterion("be_exchange_num in", values, "beExchangeNum");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumNotIn(List<Long> values) {
			addCriterion("be_exchange_num not in", values, "beExchangeNum");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumBetween(Long value1, Long value2) {
			addCriterion("be_exchange_num between", value1, value2,
					"beExchangeNum");
			return (Criteria) this;
		}

		public Criteria andBeExchangeNumNotBetween(Long value1, Long value2) {
			addCriterion("be_exchange_num not between", value1, value2,
					"beExchangeNum");
			return (Criteria) this;
		}

		public Criteria andGiftNumIsNull() {
			addCriterion("gift_num is null");
			return (Criteria) this;
		}

		public Criteria andGiftNumIsNotNull() {
			addCriterion("gift_num is not null");
			return (Criteria) this;
		}

		public Criteria andGiftNumEqualTo(Long value) {
			addCriterion("gift_num =", value, "giftNum");
			return (Criteria) this;
		}

		public Criteria andGiftNumNotEqualTo(Long value) {
			addCriterion("gift_num <>", value, "giftNum");
			return (Criteria) this;
		}

		public Criteria andGiftNumGreaterThan(Long value) {
			addCriterion("gift_num >", value, "giftNum");
			return (Criteria) this;
		}

		public Criteria andGiftNumGreaterThanOrEqualTo(Long value) {
			addCriterion("gift_num >=", value, "giftNum");
			return (Criteria) this;
		}

		public Criteria andGiftNumLessThan(Long value) {
			addCriterion("gift_num <", value, "giftNum");
			return (Criteria) this;
		}

		public Criteria andGiftNumLessThanOrEqualTo(Long value) {
			addCriterion("gift_num <=", value, "giftNum");
			return (Criteria) this;
		}

		public Criteria andGiftNumIn(List<Long> values) {
			addCriterion("gift_num in", values, "giftNum");
			return (Criteria) this;
		}

		public Criteria andGiftNumNotIn(List<Long> values) {
			addCriterion("gift_num not in", values, "giftNum");
			return (Criteria) this;
		}

		public Criteria andGiftNumBetween(Long value1, Long value2) {
			addCriterion("gift_num between", value1, value2, "giftNum");
			return (Criteria) this;
		}

		public Criteria andGiftNumNotBetween(Long value1, Long value2) {
			addCriterion("gift_num not between", value1, value2, "giftNum");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(Long value) {
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(Long value) {
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(Long value) {
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("user_id >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(Long value) {
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(Long value) {
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<Long> values) {
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<Long> values) {
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(Long value1, Long value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(Long value1, Long value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserNameIsNull() {
			addCriterion("user_name is null");
			return (Criteria) this;
		}

		public Criteria andUserNameIsNotNull() {
			addCriterion("user_name is not null");
			return (Criteria) this;
		}

		public Criteria andUserNameEqualTo(String value) {
			addCriterion("user_name =", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameNotEqualTo(String value) {
			addCriterion("user_name <>", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameGreaterThan(String value) {
			addCriterion("user_name >", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameGreaterThanOrEqualTo(String value) {
			addCriterion("user_name >=", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameLessThan(String value) {
			addCriterion("user_name <", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameLessThanOrEqualTo(String value) {
			addCriterion("user_name <=", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameLike(String value) {
			addCriterion("user_name like", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameNotLike(String value) {
			addCriterion("user_name not like", value, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameIn(List<String> values) {
			addCriterion("user_name in", values, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameNotIn(List<String> values) {
			addCriterion("user_name not in", values, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameBetween(String value1, String value2) {
			addCriterion("user_name between", value1, value2, "userName");
			return (Criteria) this;
		}

		public Criteria andUserNameNotBetween(String value1, String value2) {
			addCriterion("user_name not between", value1, value2, "userName");
			return (Criteria) this;
		}

		public Criteria andStateIsNull() {
			addCriterion("state is null");
			return (Criteria) this;
		}

		public Criteria andStateIsNotNull() {
			addCriterion("state is not null");
			return (Criteria) this;
		}

		public Criteria andStateEqualTo(Integer value) {
			addCriterion("state =", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotEqualTo(Integer value) {
			addCriterion("state <>", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThan(Integer value) {
			addCriterion("state >", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateGreaterThanOrEqualTo(Integer value) {
			addCriterion("state >=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThan(Integer value) {
			addCriterion("state <", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateLessThanOrEqualTo(Integer value) {
			addCriterion("state <=", value, "state");
			return (Criteria) this;
		}

		public Criteria andStateIn(List<Integer> values) {
			addCriterion("state in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotIn(List<Integer> values) {
			addCriterion("state not in", values, "state");
			return (Criteria) this;
		}

		public Criteria andStateBetween(Integer value1, Integer value2) {
			addCriterion("state between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andStateNotBetween(Integer value1, Integer value2) {
			addCriterion("state not between", value1, value2, "state");
			return (Criteria) this;
		}

		public Criteria andBackup2IsNull() {
			addCriterion("backup2 is null");
			return (Criteria) this;
		}

		public Criteria andBackup2IsNotNull() {
			addCriterion("backup2 is not null");
			return (Criteria) this;
		}

		public Criteria andBackup2EqualTo(String value) {
			addCriterion("backup2 =", value, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup2NotEqualTo(String value) {
			addCriterion("backup2 <>", value, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup2GreaterThan(String value) {
			addCriterion("backup2 >", value, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup2GreaterThanOrEqualTo(String value) {
			addCriterion("backup2 >=", value, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup2LessThan(String value) {
			addCriterion("backup2 <", value, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup2LessThanOrEqualTo(String value) {
			addCriterion("backup2 <=", value, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup2Like(String value) {
			addCriterion("backup2 like", value, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup2NotLike(String value) {
			addCriterion("backup2 not like", value, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup2In(List<String> values) {
			addCriterion("backup2 in", values, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup2NotIn(List<String> values) {
			addCriterion("backup2 not in", values, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup2Between(String value1, String value2) {
			addCriterion("backup2 between", value1, value2, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup2NotBetween(String value1, String value2) {
			addCriterion("backup2 not between", value1, value2, "backup2");
			return (Criteria) this;
		}

		public Criteria andBackup3IsNull() {
			addCriterion("backup3 is null");
			return (Criteria) this;
		}

		public Criteria andBackup3IsNotNull() {
			addCriterion("backup3 is not null");
			return (Criteria) this;
		}

		public Criteria andBackup3EqualTo(String value) {
			addCriterion("backup3 =", value, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup3NotEqualTo(String value) {
			addCriterion("backup3 <>", value, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup3GreaterThan(String value) {
			addCriterion("backup3 >", value, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup3GreaterThanOrEqualTo(String value) {
			addCriterion("backup3 >=", value, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup3LessThan(String value) {
			addCriterion("backup3 <", value, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup3LessThanOrEqualTo(String value) {
			addCriterion("backup3 <=", value, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup3Like(String value) {
			addCriterion("backup3 like", value, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup3NotLike(String value) {
			addCriterion("backup3 not like", value, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup3In(List<String> values) {
			addCriterion("backup3 in", values, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup3NotIn(List<String> values) {
			addCriterion("backup3 not in", values, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup3Between(String value1, String value2) {
			addCriterion("backup3 between", value1, value2, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup3NotBetween(String value1, String value2) {
			addCriterion("backup3 not between", value1, value2, "backup3");
			return (Criteria) this;
		}

		public Criteria andBackup4IsNull() {
			addCriterion("backup4 is null");
			return (Criteria) this;
		}

		public Criteria andBackup4IsNotNull() {
			addCriterion("backup4 is not null");
			return (Criteria) this;
		}

		public Criteria andBackup4EqualTo(String value) {
			addCriterion("backup4 =", value, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup4NotEqualTo(String value) {
			addCriterion("backup4 <>", value, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup4GreaterThan(String value) {
			addCriterion("backup4 >", value, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup4GreaterThanOrEqualTo(String value) {
			addCriterion("backup4 >=", value, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup4LessThan(String value) {
			addCriterion("backup4 <", value, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup4LessThanOrEqualTo(String value) {
			addCriterion("backup4 <=", value, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup4Like(String value) {
			addCriterion("backup4 like", value, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup4NotLike(String value) {
			addCriterion("backup4 not like", value, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup4In(List<String> values) {
			addCriterion("backup4 in", values, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup4NotIn(List<String> values) {
			addCriterion("backup4 not in", values, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup4Between(String value1, String value2) {
			addCriterion("backup4 between", value1, value2, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup4NotBetween(String value1, String value2) {
			addCriterion("backup4 not between", value1, value2, "backup4");
			return (Criteria) this;
		}

		public Criteria andBackup1IsNull() {
			addCriterion("backup1 is null");
			return (Criteria) this;
		}

		public Criteria andBackup1IsNotNull() {
			addCriterion("backup1 is not null");
			return (Criteria) this;
		}

		public Criteria andBackup1EqualTo(String value) {
			addCriterion("backup1 =", value, "backup1");
			return (Criteria) this;
		}

		public Criteria andBackup1NotEqualTo(String value) {
			addCriterion("backup1 <>", value, "backup1");
			return (Criteria) this;
		}

		public Criteria andBackup1GreaterThan(String value) {
			addCriterion("backup1 >", value, "backup1");
			return (Criteria) this;
		}

		public Criteria andBackup1GreaterThanOrEqualTo(String value) {
			addCriterion("backup1 >=", value, "backup1");
			return (Criteria) this;
		}

		public Criteria andBackup1LessThan(String value) {
			addCriterion("backup1 <", value, "backup1");
			return (Criteria) this;
		}

		public Criteria andBackup1LessThanOrEqualTo(String value) {
			addCriterion("backup1 <=", value, "backup1");
			return (Criteria) this;
		}

		public Criteria andBackup1Like(String value) {
			addCriterion("backup1 like", value, "backup1");
			return (Criteria) this;
		}

		public Criteria andBackup1NotLike(String value) {
			addCriterion("backup1 not like", value, "backup1");
			return (Criteria) this;
		}

		public Criteria andBackup1In(List<String> values) {
			addCriterion("backup1 in", values, "backup1");
			return (Criteria) this;
		}

		public Criteria andBackup1NotIn(List<String> values) {
			addCriterion("backup1 not in", values, "backup1");
			return (Criteria) this;
		}

		public Criteria andBackup1Between(String value1, String value2) {
			addCriterion("backup1 between", value1, value2, "backup1");
			return (Criteria) this;
		}

		public Criteria andBackup1NotBetween(String value1, String value2) {
			addCriterion("backup1 not between", value1, value2, "backup1");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table hs_exchange_gift
	 * @mbggenerated  Fri Jun 19 13:52:36 CST 2015
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table hs_exchange_gift
     *
     * @mbggenerated do_not_delete_during_merge Wed Jun 03 19:02:29 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}