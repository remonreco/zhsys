import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.buybal.epay.util.EncException;
import com.buybal.epay.util.PayEncrypt;
import com.buybal.epay.util.PaySign;
import com.buybal.epay.util.SharingPayMsg;
import com.cbice.distribute.buyer.web.util.PayInterface;
import com.cbice.distribute.buyer.web.util.PropertiseUtil;


public class Testpay {
	
	
	
	
	public static void main(String[] args) {
		String totalAmt = "1000.00";//总价
   		String orderSerial = "ZF201503241330390"; //订单流水号
   		String distributeNum = "5";//交易数量orderTime
   		String orderTime = "20150324133039";//订单有效时间
   		Map<String,String> map=new HashMap<String,String>();
   		map.put("goodsName", "和田玉");
   		map.put("totalAmt", totalAmt);
   		map.put("orderSerial", orderSerial);
   		map.put("distributeNum", distributeNum);
		    map.put("orderTime", orderTime);
   		Map<String,String> sendMap=PayInterface.pay(map);
   		SharingPayMsg sharingPayMsg = SharingPayMsg.getSharingPayMsg(sendMap);
   		// 对分账数据进行加密
		PayEncrypt pe = new PayEncrypt();
		String sharingData = "";
		String merKey = PropertiseUtil.getDataFromPropertiseFile(
				"site", sharingPayMsg.getPid());
		try {
			sharingData = pe.encryptMode(merKey, "1^105290054110500^1000.00^0^0.00^desc");
			sharingPayMsg.setSharingdata(sharingData);
		} catch (EncException e) {
			e.printStackTrace();
		}
		// 签名
		PaySign ps = new PaySign();
		String signmsg;
		String sharingPayStr = sharingPayMsg.getSharingPayStr();
		try {
			signmsg = ps.sign(sharingPayStr, merKey);
			sharingPayMsg.setSignmsg(signmsg);
		} catch (EncException e) {
			e.printStackTrace();
		}
		
		
	}

}
