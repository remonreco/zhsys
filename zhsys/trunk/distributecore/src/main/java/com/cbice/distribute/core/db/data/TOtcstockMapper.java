package com.cbice.distribute.core.db.data;

import com.cbice.distribute.core.db.model.TOtcstock;
import com.cbice.distribute.core.db.model.TOtcstockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOtcstockMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_otcstock
     *
     * @mbggenerated Mon May 11 11:02:11 CST 2015
     */
    int countByExample(TOtcstockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_otcstock
     *
     * @mbggenerated Mon May 11 11:02:11 CST 2015
     */
    int deleteByExample(TOtcstockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_otcstock
     *
     * @mbggenerated Mon May 11 11:02:11 CST 2015
     */
    int insert(TOtcstock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_otcstock
     *
     * @mbggenerated Mon May 11 11:02:11 CST 2015
     */
    int insertSelective(TOtcstock record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_otcstock
     *
     * @mbggenerated Mon May 11 11:02:11 CST 2015
     */
    List<TOtcstock> selectByExample(TOtcstockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_otcstock
     *
     * @mbggenerated Mon May 11 11:02:11 CST 2015
     */
    int updateByExampleSelective(@Param("record") TOtcstock record, @Param("example") TOtcstockExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_otcstock
     *
     * @mbggenerated Mon May 11 11:02:11 CST 2015
     */
    int updateByExample(@Param("record") TOtcstock record, @Param("example") TOtcstockExample example);
}