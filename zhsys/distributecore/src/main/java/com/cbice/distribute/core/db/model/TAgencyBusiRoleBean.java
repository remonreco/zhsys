package com.cbice.distribute.core.db.model;

import java.util.List;

public class TAgencyBusiRoleBean {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_sys_busi_role.id
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    private Long id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_sys_busi_role.name
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    private String name;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_sys_busi_role.remark
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    private String remark;
    
    private String tAgencyLevel;
    
    private List<TAgencyRole> tAgencyRoles;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_sys_busi_role.id
     * @return  the value of t_sys_busi_role.id
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    public Long getId(){
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_sys_busi_role.id
     * @param id  the value for t_sys_busi_role.id
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_sys_busi_role.name
     * @return  the value of t_sys_busi_role.name
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    public String getName(){
        return name;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_sys_busi_role.name
     * @param name  the value for t_sys_busi_role.name
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    public void setName(String name){
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_sys_busi_role.remark
     * @return  the value of t_sys_busi_role.remark
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    public String getRemark(){
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_sys_busi_role.remark
     * @param remark  the value for t_sys_busi_role.remark
     * @mbggenerated  Tue Feb 17 06:40:38 CST 2015
     */
    public void setRemark(String remark){
        this.remark = remark == null ? null : remark.trim();
    }

    public List<TAgencyRole> gettAgencyRoles(){
        return tAgencyRoles;
    }

    public void settAgencyRoles(List<TAgencyRole> tAgencyRoles){
        this.tAgencyRoles = tAgencyRoles;
    }

	public String gettAgencyLevel() {
		return tAgencyLevel;
	}

	public void settAgencyLevel(String tAgencyLevel) {
		this.tAgencyLevel = tAgencyLevel;
	}
}
