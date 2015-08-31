package com.cbice.distribute.core.db.model;

import java.util.Date;

public class TFund {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.client_id
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private String clientId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.fund_account
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private String fundAccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.money_type
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private String moneyType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.rate_kind
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private String rateKind;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.branch_no
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Long branchNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.begin_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double beginBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.current_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double currentBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.cash_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double cashBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.check_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double checkBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.frozen_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double frozenBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.unfrozen_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double unfrozenBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.correct_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double correctBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.foregift_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double foregiftBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.mortgage_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double mortgageBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.integral_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double integralBalance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.fine_integral
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double fineIntegral;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.interest
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double interest;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.interest_tax
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double interestTax;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.integral_update
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Double integralUpdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.bank_no
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private String bankNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.square_flag
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private String squareFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.check_str
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private String checkStr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.position_str
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private String positionStr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_fund.nature_time
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    private Date natureTime;
    
    private String developer;

    public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.client_id
     *
     * @return the value of t_fund.client_id
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.client_id
     *
     * @param clientId the value for t_fund.client_id
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.fund_account
     *
     * @return the value of t_fund.fund_account
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public String getFundAccount() {
        return fundAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.fund_account
     *
     * @param fundAccount the value for t_fund.fund_account
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount == null ? null : fundAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.money_type
     *
     * @return the value of t_fund.money_type
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public String getMoneyType() {
        return moneyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.money_type
     *
     * @param moneyType the value for t_fund.money_type
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType == null ? null : moneyType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.rate_kind
     *
     * @return the value of t_fund.rate_kind
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public String getRateKind() {
        return rateKind;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.rate_kind
     *
     * @param rateKind the value for t_fund.rate_kind
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setRateKind(String rateKind) {
        this.rateKind = rateKind == null ? null : rateKind.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.branch_no
     *
     * @return the value of t_fund.branch_no
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Long getBranchNo() {
        return branchNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.branch_no
     *
     * @param branchNo the value for t_fund.branch_no
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setBranchNo(Long branchNo) {
        this.branchNo = branchNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.begin_balance
     *
     * @return the value of t_fund.begin_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getBeginBalance() {
        return beginBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.begin_balance
     *
     * @param beginBalance the value for t_fund.begin_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setBeginBalance(Double beginBalance) {
        this.beginBalance = beginBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.current_balance
     *
     * @return the value of t_fund.current_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.current_balance
     *
     * @param currentBalance the value for t_fund.current_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.cash_balance
     *
     * @return the value of t_fund.cash_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getCashBalance() {
        return cashBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.cash_balance
     *
     * @param cashBalance the value for t_fund.cash_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setCashBalance(Double cashBalance) {
        this.cashBalance = cashBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.check_balance
     *
     * @return the value of t_fund.check_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getCheckBalance() {
        return checkBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.check_balance
     *
     * @param checkBalance the value for t_fund.check_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setCheckBalance(Double checkBalance) {
        this.checkBalance = checkBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.frozen_balance
     *
     * @return the value of t_fund.frozen_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getFrozenBalance() {
        return frozenBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.frozen_balance
     *
     * @param frozenBalance the value for t_fund.frozen_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setFrozenBalance(Double frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.unfrozen_balance
     *
     * @return the value of t_fund.unfrozen_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getUnfrozenBalance() {
        return unfrozenBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.unfrozen_balance
     *
     * @param unfrozenBalance the value for t_fund.unfrozen_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setUnfrozenBalance(Double unfrozenBalance) {
        this.unfrozenBalance = unfrozenBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.correct_balance
     *
     * @return the value of t_fund.correct_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getCorrectBalance() {
        return correctBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.correct_balance
     *
     * @param correctBalance the value for t_fund.correct_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setCorrectBalance(Double correctBalance) {
        this.correctBalance = correctBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.foregift_balance
     *
     * @return the value of t_fund.foregift_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getForegiftBalance() {
        return foregiftBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.foregift_balance
     *
     * @param foregiftBalance the value for t_fund.foregift_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setForegiftBalance(Double foregiftBalance) {
        this.foregiftBalance = foregiftBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.mortgage_balance
     *
     * @return the value of t_fund.mortgage_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getMortgageBalance() {
        return mortgageBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.mortgage_balance
     *
     * @param mortgageBalance the value for t_fund.mortgage_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setMortgageBalance(Double mortgageBalance) {
        this.mortgageBalance = mortgageBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.integral_balance
     *
     * @return the value of t_fund.integral_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getIntegralBalance() {
        return integralBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.integral_balance
     *
     * @param integralBalance the value for t_fund.integral_balance
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setIntegralBalance(Double integralBalance) {
        this.integralBalance = integralBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.fine_integral
     *
     * @return the value of t_fund.fine_integral
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getFineIntegral() {
        return fineIntegral;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.fine_integral
     *
     * @param fineIntegral the value for t_fund.fine_integral
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setFineIntegral(Double fineIntegral) {
        this.fineIntegral = fineIntegral;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.interest
     *
     * @return the value of t_fund.interest
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getInterest() {
        return interest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.interest
     *
     * @param interest the value for t_fund.interest
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setInterest(Double interest) {
        this.interest = interest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.interest_tax
     *
     * @return the value of t_fund.interest_tax
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getInterestTax() {
        return interestTax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.interest_tax
     *
     * @param interestTax the value for t_fund.interest_tax
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setInterestTax(Double interestTax) {
        this.interestTax = interestTax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.integral_update
     *
     * @return the value of t_fund.integral_update
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Double getIntegralUpdate() {
        return integralUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.integral_update
     *
     * @param integralUpdate the value for t_fund.integral_update
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setIntegralUpdate(Double integralUpdate) {
        this.integralUpdate = integralUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.bank_no
     *
     * @return the value of t_fund.bank_no
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.bank_no
     *
     * @param bankNo the value for t_fund.bank_no
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.square_flag
     *
     * @return the value of t_fund.square_flag
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public String getSquareFlag() {
        return squareFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.square_flag
     *
     * @param squareFlag the value for t_fund.square_flag
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setSquareFlag(String squareFlag) {
        this.squareFlag = squareFlag == null ? null : squareFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.check_str
     *
     * @return the value of t_fund.check_str
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public String getCheckStr() {
        return checkStr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.check_str
     *
     * @param checkStr the value for t_fund.check_str
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setCheckStr(String checkStr) {
        this.checkStr = checkStr == null ? null : checkStr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.position_str
     *
     * @return the value of t_fund.position_str
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public String getPositionStr() {
        return positionStr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.position_str
     *
     * @param positionStr the value for t_fund.position_str
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr == null ? null : positionStr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_fund.nature_time
     *
     * @return the value of t_fund.nature_time
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public Date getNatureTime() {
        return natureTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_fund.nature_time
     *
     * @param natureTime the value for t_fund.nature_time
     *
     * @mbggenerated Mon May 11 16:18:22 CST 2015
     */
    public void setNatureTime(Date natureTime) {
        this.natureTime = natureTime;
    }
}