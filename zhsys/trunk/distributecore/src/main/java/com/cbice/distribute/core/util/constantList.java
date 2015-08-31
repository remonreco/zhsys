package com.cbice.distribute.core.util;

public class constantList {
	/**
	 * ICE在数据库中对应的ID
	 */
	 public static final Long  IceId =8000000L;
	 
	 /**
	  * 系统ID
	  */
	 public static final String SYSTEM_ID = "A1000001";
	 
	 /**
	  * 系统默认权益代码
	  */
	 public static final long DEF_RIGHT_CODE = 99999999L;
	 
	 /**
	 * 兑换状态 hs_commodity
	 */
	 public static final int EXCHANGE_STATE_1=1;//启动
	 public static final int EXCHANGE_STATE_2=2;//不启动
	 public static final int EXCHANGE_STATE_3=3;//暂停
	 
	 /**
	 * 订单类型 hs_exchange_order
	 */
	 public static final int ORDER_TYPE_1=1;//兑换申请
	 public static final int ORDER_TYPE_2=2;//
	 
	 /**
	 * 订单的交易状态 hs_exchange_order
	 */
	 public static final int ORDER_STATE_1=1;//申请中
	 public static final int ORDER_STATE_2=2;//成功
	 public static final int ORDER_STATE_3=3;//失败
	 public static final int ORDER_STATE_4=4;//撤单
	 
	 /**
	 * 品种类型 hs_commodity_rule
	 */
	 public static final int COM_TYPE_1=1;//商品
	 public static final int COM_TYPE_2=2;//赠品
	 
	 /**
	 * 审批结果 hs_commodity_approval
	 */
	 public static final short APPROVAL_RESULT_1=1;//审批成功
	 public static final short APPROVAL_RESULT_2=2;//审批失败
	 
	 /**
	  * 上传文件路径
	  */
	 public static final String UPLOAD_FILEPATH = "/home/appusr/files/uploadPic/";
	 
	 /**
	 * 实时交易类型 hs_transaction_record
	 */
	 public static final int TRANS_TYPE_1=1;//交易类型 1：买 2：卖
	 public static final int TRANS_TYPE_2=2;//交易类型 1：买 2：卖
}
