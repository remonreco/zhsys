package com.cbice.distribute.core.db.model;

public class TSeq {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_seq.id
     * @mbggenerated  Tue Feb 17 06:27:32 CST 2015
     */
    private String id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_seq.seq
     * @mbggenerated  Tue Feb 17 06:27:32 CST 2015
     */
    private Long seq;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_seq.memo
     * @mbggenerated  Tue Feb 17 06:27:32 CST 2015
     */
    private String memo;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_seq.id
     * @return  the value of t_seq.id
     * @mbggenerated  Tue Feb 17 06:27:32 CST 2015
     */
    public String getId(){
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_seq.id
     * @param id  the value for t_seq.id
     * @mbggenerated  Tue Feb 17 06:27:32 CST 2015
     */
    public void setId(String id){
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_seq.seq
     * @return  the value of t_seq.seq
     * @mbggenerated  Tue Feb 17 06:27:32 CST 2015
     */
    public Long getSeq(){
        return seq;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_seq.seq
     * @param seq  the value for t_seq.seq
     * @mbggenerated  Tue Feb 17 06:27:32 CST 2015
     */
    public void setSeq(Long seq){
        this.seq = seq;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_seq.memo
     * @return  the value of t_seq.memo
     * @mbggenerated  Tue Feb 17 06:27:32 CST 2015
     */
    public String getMemo(){
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_seq.memo
     * @param memo  the value for t_seq.memo
     * @mbggenerated  Tue Feb 17 06:27:32 CST 2015
     */
    public void setMemo(String memo){
        this.memo = memo == null ? null : memo.trim();
    }
}