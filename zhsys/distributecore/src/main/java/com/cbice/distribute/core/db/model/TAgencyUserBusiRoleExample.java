package com.cbice.distribute.core.db.model;

import java.util.ArrayList;
import java.util.List;

public class TAgencyUserBusiRoleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    public TAgencyUserBusiRoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
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

        public Criteria andBusiRoleIdIsNull() {
            addCriterion("busi_role_id is null");
            return (Criteria) this;
        }

        public Criteria andBusiRoleIdIsNotNull() {
            addCriterion("busi_role_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusiRoleIdEqualTo(Long value) {
            addCriterion("busi_role_id =", value, "busiRoleId");
            return (Criteria) this;
        }

        public Criteria andBusiRoleIdNotEqualTo(Long value) {
            addCriterion("busi_role_id <>", value, "busiRoleId");
            return (Criteria) this;
        }

        public Criteria andBusiRoleIdGreaterThan(Long value) {
            addCriterion("busi_role_id >", value, "busiRoleId");
            return (Criteria) this;
        }

        public Criteria andBusiRoleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("busi_role_id >=", value, "busiRoleId");
            return (Criteria) this;
        }

        public Criteria andBusiRoleIdLessThan(Long value) {
            addCriterion("busi_role_id <", value, "busiRoleId");
            return (Criteria) this;
        }

        public Criteria andBusiRoleIdLessThanOrEqualTo(Long value) {
            addCriterion("busi_role_id <=", value, "busiRoleId");
            return (Criteria) this;
        }

        public Criteria andBusiRoleIdIn(List<Long> values) {
            addCriterion("busi_role_id in", values, "busiRoleId");
            return (Criteria) this;
        }

        public Criteria andBusiRoleIdNotIn(List<Long> values) {
            addCriterion("busi_role_id not in", values, "busiRoleId");
            return (Criteria) this;
        }

        public Criteria andBusiRoleIdBetween(Long value1, Long value2) {
            addCriterion("busi_role_id between", value1, value2, "busiRoleId");
            return (Criteria) this;
        }

        public Criteria andBusiRoleIdNotBetween(Long value1, Long value2) {
            addCriterion("busi_role_id not between", value1, value2, "busiRoleId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated do_not_delete_during_merge Sat Mar 07 12:13:06 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_agency_user_busi_role
     *
     * @mbggenerated Sat Mar 07 12:13:06 CST 2015
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

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
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
}