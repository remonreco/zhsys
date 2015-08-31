package com.cbice.distribute.core.db.model;

import java.util.ArrayList;
import java.util.List;

public class TAgencyLevelRoleExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	public TAgencyLevelRoleExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
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

		public Criteria andTAgencyLevelIsNull() {
			addCriterion("t_agency_level is null");
			return (Criteria) this;
		}

		public Criteria andTAgencyLevelIsNotNull() {
			addCriterion("t_agency_level is not null");
			return (Criteria) this;
		}

		public Criteria andTAgencyLevelEqualTo(Integer value) {
			addCriterion("t_agency_level =", value, "tAgencyLevel");
			return (Criteria) this;
		}

		public Criteria andTAgencyLevelNotEqualTo(Integer value) {
			addCriterion("t_agency_level <>", value, "tAgencyLevel");
			return (Criteria) this;
		}

		public Criteria andTAgencyLevelGreaterThan(Integer value) {
			addCriterion("t_agency_level >", value, "tAgencyLevel");
			return (Criteria) this;
		}

		public Criteria andTAgencyLevelGreaterThanOrEqualTo(Integer value) {
			addCriterion("t_agency_level >=", value, "tAgencyLevel");
			return (Criteria) this;
		}

		public Criteria andTAgencyLevelLessThan(Integer value) {
			addCriterion("t_agency_level <", value, "tAgencyLevel");
			return (Criteria) this;
		}

		public Criteria andTAgencyLevelLessThanOrEqualTo(Integer value) {
			addCriterion("t_agency_level <=", value, "tAgencyLevel");
			return (Criteria) this;
		}

		public Criteria andTAgencyLevelIn(List<Integer> values) {
			addCriterion("t_agency_level in", values, "tAgencyLevel");
			return (Criteria) this;
		}

		public Criteria andTAgencyLevelNotIn(List<Integer> values) {
			addCriterion("t_agency_level not in", values, "tAgencyLevel");
			return (Criteria) this;
		}

		public Criteria andTAgencyLevelBetween(Integer value1, Integer value2) {
			addCriterion("t_agency_level between", value1, value2,
					"tAgencyLevel");
			return (Criteria) this;
		}

		public Criteria andTAgencyLevelNotBetween(Integer value1, Integer value2) {
			addCriterion("t_agency_level not between", value1, value2,
					"tAgencyLevel");
			return (Criteria) this;
		}

		public Criteria andTRoleIdIsNull() {
			addCriterion("t_role_id is null");
			return (Criteria) this;
		}

		public Criteria andTRoleIdIsNotNull() {
			addCriterion("t_role_id is not null");
			return (Criteria) this;
		}

		public Criteria andTRoleIdEqualTo(Long value) {
			addCriterion("t_role_id =", value, "tRoleId");
			return (Criteria) this;
		}

		public Criteria andTRoleIdNotEqualTo(Long value) {
			addCriterion("t_role_id <>", value, "tRoleId");
			return (Criteria) this;
		}

		public Criteria andTRoleIdGreaterThan(Long value) {
			addCriterion("t_role_id >", value, "tRoleId");
			return (Criteria) this;
		}

		public Criteria andTRoleIdGreaterThanOrEqualTo(Long value) {
			addCriterion("t_role_id >=", value, "tRoleId");
			return (Criteria) this;
		}

		public Criteria andTRoleIdLessThan(Long value) {
			addCriterion("t_role_id <", value, "tRoleId");
			return (Criteria) this;
		}

		public Criteria andTRoleIdLessThanOrEqualTo(Long value) {
			addCriterion("t_role_id <=", value, "tRoleId");
			return (Criteria) this;
		}

		public Criteria andTRoleIdIn(List<Long> values) {
			addCriterion("t_role_id in", values, "tRoleId");
			return (Criteria) this;
		}

		public Criteria andTRoleIdNotIn(List<Long> values) {
			addCriterion("t_role_id not in", values, "tRoleId");
			return (Criteria) this;
		}

		public Criteria andTRoleIdBetween(Long value1, Long value2) {
			addCriterion("t_role_id between", value1, value2, "tRoleId");
			return (Criteria) this;
		}

		public Criteria andTRoleIdNotBetween(Long value1, Long value2) {
			addCriterion("t_role_id not between", value1, value2, "tRoleId");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_agency_level_role
	 * @mbggenerated  Sat Mar 14 11:54:14 CST 2015
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
     * This class corresponds to the database table t_agency_level_role
     *
     * @mbggenerated do_not_delete_during_merge Wed Mar 11 18:29:01 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}