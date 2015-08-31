package com.cbice.distribute.core.db.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class TAgencyExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	public TAgencyExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
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

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andDealerNameIsNull() {
			addCriterion("dealer_name is null");
			return (Criteria) this;
		}

		public Criteria andDealerNameIsNotNull() {
			addCriterion("dealer_name is not null");
			return (Criteria) this;
		}

		public Criteria andDealerNameEqualTo(String value) {
			addCriterion("dealer_name =", value, "dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNameNotEqualTo(String value) {
			addCriterion("dealer_name <>", value, "dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNameGreaterThan(String value) {
			addCriterion("dealer_name >", value, "dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNameGreaterThanOrEqualTo(String value) {
			addCriterion("dealer_name >=", value, "dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNameLessThan(String value) {
			addCriterion("dealer_name <", value, "dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNameLessThanOrEqualTo(String value) {
			addCriterion("dealer_name <=", value, "dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNameLike(String value) {
			addCriterion("dealer_name like", value, "dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNameNotLike(String value) {
			addCriterion("dealer_name not like", value, "dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNameIn(List<String> values) {
			addCriterion("dealer_name in", values, "dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNameNotIn(List<String> values) {
			addCriterion("dealer_name not in", values, "dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNameBetween(String value1, String value2) {
			addCriterion("dealer_name between", value1, value2, "dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNameNotBetween(String value1, String value2) {
			addCriterion("dealer_name not between", value1, value2,
					"dealerName");
			return (Criteria) this;
		}

		public Criteria andDealerNumIsNull() {
			addCriterion("dealer_num is null");
			return (Criteria) this;
		}

		public Criteria andDealerNumIsNotNull() {
			addCriterion("dealer_num is not null");
			return (Criteria) this;
		}

		public Criteria andDealerNumEqualTo(String value) {
			addCriterion("dealer_num =", value, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerNumNotEqualTo(String value) {
			addCriterion("dealer_num <>", value, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerNumGreaterThan(String value) {
			addCriterion("dealer_num >", value, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerNumGreaterThanOrEqualTo(String value) {
			addCriterion("dealer_num >=", value, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerNumLessThan(String value) {
			addCriterion("dealer_num <", value, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerNumLessThanOrEqualTo(String value) {
			addCriterion("dealer_num <=", value, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerNumLike(String value) {
			addCriterion("dealer_num like", value, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerNumNotLike(String value) {
			addCriterion("dealer_num not like", value, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerNumIn(List<String> values) {
			addCriterion("dealer_num in", values, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerNumNotIn(List<String> values) {
			addCriterion("dealer_num not in", values, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerNumBetween(String value1, String value2) {
			addCriterion("dealer_num between", value1, value2, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerNumNotBetween(String value1, String value2) {
			addCriterion("dealer_num not between", value1, value2, "dealerNum");
			return (Criteria) this;
		}

		public Criteria andDealerLevelIsNull() {
			addCriterion("dealer_level is null");
			return (Criteria) this;
		}

		public Criteria andDealerLevelIsNotNull() {
			addCriterion("dealer_level is not null");
			return (Criteria) this;
		}

		public Criteria andDealerLevelEqualTo(Integer value) {
			addCriterion("dealer_level =", value, "dealerLevel");
			return (Criteria) this;
		}

		public Criteria andDealerLevelNotEqualTo(Integer value) {
			addCriterion("dealer_level <>", value, "dealerLevel");
			return (Criteria) this;
		}

		public Criteria andDealerLevelGreaterThan(Integer value) {
			addCriterion("dealer_level >", value, "dealerLevel");
			return (Criteria) this;
		}

		public Criteria andDealerLevelGreaterThanOrEqualTo(Integer value) {
			addCriterion("dealer_level >=", value, "dealerLevel");
			return (Criteria) this;
		}

		public Criteria andDealerLevelLessThan(Integer value) {
			addCriterion("dealer_level <", value, "dealerLevel");
			return (Criteria) this;
		}

		public Criteria andDealerLevelLessThanOrEqualTo(Integer value) {
			addCriterion("dealer_level <=", value, "dealerLevel");
			return (Criteria) this;
		}

		public Criteria andDealerLevelIn(List<Integer> values) {
			addCriterion("dealer_level in", values, "dealerLevel");
			return (Criteria) this;
		}

		public Criteria andDealerLevelNotIn(List<Integer> values) {
			addCriterion("dealer_level not in", values, "dealerLevel");
			return (Criteria) this;
		}

		public Criteria andDealerLevelBetween(Integer value1, Integer value2) {
			addCriterion("dealer_level between", value1, value2, "dealerLevel");
			return (Criteria) this;
		}

		public Criteria andDealerLevelNotBetween(Integer value1, Integer value2) {
			addCriterion("dealer_level not between", value1, value2,
					"dealerLevel");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdIsNull() {
			addCriterion("higer_dealer_id is null");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdIsNotNull() {
			addCriterion("higer_dealer_id is not null");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdEqualTo(Long value) {
			addCriterion("higer_dealer_id =", value, "higerDealerId");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdNotEqualTo(Long value) {
			addCriterion("higer_dealer_id <>", value, "higerDealerId");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdGreaterThan(Long value) {
			addCriterion("higer_dealer_id >", value, "higerDealerId");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdGreaterThanOrEqualTo(Long value) {
			addCriterion("higer_dealer_id >=", value, "higerDealerId");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdLessThan(Long value) {
			addCriterion("higer_dealer_id <", value, "higerDealerId");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdLessThanOrEqualTo(Long value) {
			addCriterion("higer_dealer_id <=", value, "higerDealerId");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdIn(List<Long> values) {
			addCriterion("higer_dealer_id in", values, "higerDealerId");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdNotIn(List<Long> values) {
			addCriterion("higer_dealer_id not in", values, "higerDealerId");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdBetween(Long value1, Long value2) {
			addCriterion("higer_dealer_id between", value1, value2,
					"higerDealerId");
			return (Criteria) this;
		}

		public Criteria andHigerDealerIdNotBetween(Long value1, Long value2) {
			addCriterion("higer_dealer_id not between", value1, value2,
					"higerDealerId");
			return (Criteria) this;
		}

		public Criteria andLftIsNull() {
			addCriterion("lft is null");
			return (Criteria) this;
		}

		public Criteria andLftIsNotNull() {
			addCriterion("lft is not null");
			return (Criteria) this;
		}

		public Criteria andLftEqualTo(Integer value) {
			addCriterion("lft =", value, "lft");
			return (Criteria) this;
		}

		public Criteria andLftNotEqualTo(Integer value) {
			addCriterion("lft <>", value, "lft");
			return (Criteria) this;
		}

		public Criteria andLftGreaterThan(Integer value) {
			addCriterion("lft >", value, "lft");
			return (Criteria) this;
		}

		public Criteria andLftGreaterThanOrEqualTo(Integer value) {
			addCriterion("lft >=", value, "lft");
			return (Criteria) this;
		}

		public Criteria andLftLessThan(Integer value) {
			addCriterion("lft <", value, "lft");
			return (Criteria) this;
		}

		public Criteria andLftLessThanOrEqualTo(Integer value) {
			addCriterion("lft <=", value, "lft");
			return (Criteria) this;
		}

		public Criteria andLftIn(List<Integer> values) {
			addCriterion("lft in", values, "lft");
			return (Criteria) this;
		}

		public Criteria andLftNotIn(List<Integer> values) {
			addCriterion("lft not in", values, "lft");
			return (Criteria) this;
		}

		public Criteria andLftBetween(Integer value1, Integer value2) {
			addCriterion("lft between", value1, value2, "lft");
			return (Criteria) this;
		}

		public Criteria andLftNotBetween(Integer value1, Integer value2) {
			addCriterion("lft not between", value1, value2, "lft");
			return (Criteria) this;
		}

		public Criteria andRgtIsNull() {
			addCriterion("rgt is null");
			return (Criteria) this;
		}

		public Criteria andRgtIsNotNull() {
			addCriterion("rgt is not null");
			return (Criteria) this;
		}

		public Criteria andRgtEqualTo(Integer value) {
			addCriterion("rgt =", value, "rgt");
			return (Criteria) this;
		}

		public Criteria andRgtNotEqualTo(Integer value) {
			addCriterion("rgt <>", value, "rgt");
			return (Criteria) this;
		}

		public Criteria andRgtGreaterThan(Integer value) {
			addCriterion("rgt >", value, "rgt");
			return (Criteria) this;
		}

		public Criteria andRgtGreaterThanOrEqualTo(Integer value) {
			addCriterion("rgt >=", value, "rgt");
			return (Criteria) this;
		}

		public Criteria andRgtLessThan(Integer value) {
			addCriterion("rgt <", value, "rgt");
			return (Criteria) this;
		}

		public Criteria andRgtLessThanOrEqualTo(Integer value) {
			addCriterion("rgt <=", value, "rgt");
			return (Criteria) this;
		}

		public Criteria andRgtIn(List<Integer> values) {
			addCriterion("rgt in", values, "rgt");
			return (Criteria) this;
		}

		public Criteria andRgtNotIn(List<Integer> values) {
			addCriterion("rgt not in", values, "rgt");
			return (Criteria) this;
		}

		public Criteria andRgtBetween(Integer value1, Integer value2) {
			addCriterion("rgt between", value1, value2, "rgt");
			return (Criteria) this;
		}

		public Criteria andRgtNotBetween(Integer value1, Integer value2) {
			addCriterion("rgt not between", value1, value2, "rgt");
			return (Criteria) this;
		}

		public Criteria andModifyTimeIsNull() {
			addCriterion("modify_time is null");
			return (Criteria) this;
		}

		public Criteria andModifyTimeIsNotNull() {
			addCriterion("modify_time is not null");
			return (Criteria) this;
		}

		public Criteria andModifyTimeEqualTo(Date value) {
			addCriterion("modify_time =", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeNotEqualTo(Date value) {
			addCriterion("modify_time <>", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeGreaterThan(Date value) {
			addCriterion("modify_time >", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("modify_time >=", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeLessThan(Date value) {
			addCriterion("modify_time <", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
			addCriterion("modify_time <=", value, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeIn(List<Date> values) {
			addCriterion("modify_time in", values, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeNotIn(List<Date> values) {
			addCriterion("modify_time not in", values, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeBetween(Date value1, Date value2) {
			addCriterion("modify_time between", value1, value2, "modifyTime");
			return (Criteria) this;
		}

		public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
			addCriterion("modify_time not between", value1, value2,
					"modifyTime");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_agency
	 * @mbggenerated  Fri Mar 20 16:52:36 CST 2015
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
     * This class corresponds to the database table t_agency
     *
     * @mbggenerated do_not_delete_during_merge Thu Mar 12 17:00:19 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}