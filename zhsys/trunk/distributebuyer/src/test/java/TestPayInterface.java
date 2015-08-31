import com.cbice.distribute.buyer.web.util.PropertiseUtil;




public class TestPayInterface {
	
//	String urlSource = PropertiseUtil.getDataFromPropertiseFile(
//			"url_config", "lakalaPayUrl");
//	String url = urlSource + "regAndSign.do";
//
//	String httpRes = HttpHelper.doHttp(url, HttpHelper.POST, "UTF-8", data,
//			Constant.TIME_OUT);
//	

	
	public static void main(String[] args) {
		String urlSource = PropertiseUtil.getDataFromPropertiseFile(
				"site", "site.pay");
		System.out.println(urlSource);
	}
}
